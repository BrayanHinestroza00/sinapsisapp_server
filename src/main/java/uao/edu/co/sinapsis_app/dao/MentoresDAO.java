package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.MAX_ETAPA_RUTA_INNOVACION_EMPRENDIMIENTO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_ASESORAMIENTO_ESTADO_FINALIZADA;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_ASESORAMIENTO_ESTADO_SIN_MENTOR;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_RUT_EMPRENDIMIENTO_ESTADO_COMPLETADO;

@Repository
public class MentoresDAO implements IMentoresDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_RUTA_EMPRENDIMIENTO = " + idRutaEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        return (List<MentoresProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "AND ESTADO_ASESORAMIENTO = 'FINALIZADA'";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        return (List<MentoresProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "AND ESTADO_ASESORAMIENTO = 'EN CURSO'";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        return (List<MentoresProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "AND ESTADO_ASESORAMIENTO = 'FINALIZADA'";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        return (List<MentoresProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<AsesoramientosView> obtenerEmprendedoresPorMentor(EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO) {
        String sql = "SELECT * FROM (SELECT " +
                "V.*, ROW_NUMBER() OVER (PARTITION BY ID_PROY_EMPRENDIMIENTO ORDER BY ID_ASESORAMIENTO DESC) ID_VIEW " +
                "FROM V_SINAPSIS_ASESORAMIENTOS V " +
                "WHERE ID_MENTOR = " + emprendedoresAsignadosFilterDTO.getIdMentor();

        if (emprendedoresAsignadosFilterDTO.getNumeroDocumento() != null &&
                !(emprendedoresAsignadosFilterDTO.getNumeroDocumento().trim().isEmpty())) {
            sql += " AND NUMERO_DOCUMENTO = " + emprendedoresAsignadosFilterDTO.getNumeroDocumento();
        }

        if (emprendedoresAsignadosFilterDTO.getNombreEmprendedor() != null &&
                !(emprendedoresAsignadosFilterDTO.getNombreEmprendedor().trim().isEmpty())) {
            sql += " AND UPPER(CONCAT(NOMBRES, ' ' || APELLIDOS)) " +
                    "like UPPER('%" + emprendedoresAsignadosFilterDTO.getNombreEmprendedor() + "%')";
        }

        if (emprendedoresAsignadosFilterDTO.getEstadoAsesoramiento() != null) {
            if (emprendedoresAsignadosFilterDTO.getEstadoAsesoramiento() == 1) {
                sql += " AND ESTADO_ASESORAMIENTO = 'EN CURSO'";
            } else {
                sql += " AND ESTADO_ASESORAMIENTO = 'FINALIZADA'";
            }
        }

        if (emprendedoresAsignadosFilterDTO.getNombreEmprendimiento() != null &&
                !(emprendedoresAsignadosFilterDTO.getNombreEmprendimiento().trim().isEmpty())) {
            sql += " AND NOMBRE_EMPRENDIMIENTO LIKE '%" + emprendedoresAsignadosFilterDTO.getNombreEmprendimiento() + "%'";
        }

        sql += " ) WHERE ID_VIEW = 1";

        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);

        return (List<AsesoramientosView>) query.getResultList();
    }

    @Override
    public List<MentoresView> obtenerMentores(MentoresAdmFilterDTO mentoresAdmFilterDTO) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES WHERE ESTADO_CUENTA = " + mentoresAdmFilterDTO.getEstadoCuenta();

        if (mentoresAdmFilterDTO.getNumeroDocumento() != null &&
                !(mentoresAdmFilterDTO.getNumeroDocumento().trim().isEmpty())) {
            sql += " AND NUMERO_DOCUMENTO = " + mentoresAdmFilterDTO.getNumeroDocumento();
        }

        if (mentoresAdmFilterDTO.getNombreMentor() != null &&
                !(mentoresAdmFilterDTO.getNombreMentor().trim().isEmpty())) {
            sql += " AND UPPER(NOMBRE_COMPLETO) " +
                    "like UPPER('%" + mentoresAdmFilterDTO.getNombreMentor() + "%')";
        }

        if (mentoresAdmFilterDTO.getEtapasRuta() != null &&
                mentoresAdmFilterDTO.getEtapasRuta() != -1) {
            sql += " AND ETAPAS_RUTA_ID = " + mentoresAdmFilterDTO.getEtapasRuta();
        }

        sql += " ORDER BY NOMBRE_COMPLETO ASC";

        Query query = entityManager.createNativeQuery(sql, MentoresView.class);

        return (List<MentoresView>) query.getResultList();
    }

    @Override
    public List<MentoresView> obtenerMentoresPorId(Long idMentor) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES WHERE " +
                "ID = "+ idMentor + " ORDER BY CREATED_AT DESC";

        Query query = entityManager.createNativeQuery(sql, MentoresView.class);

        return (List<MentoresView>) query.getResultList();
    }

    @Override
    public List<MentoresView> obtenerMentoresPorEtapaRutaInnovacion(Long idEtapaRutaInnovacion) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES WHERE " +
                "ETAPAS_RUTA_ID = "+ idEtapaRutaInnovacion + " AND ESTADO_CUENTA = 1 ORDER BY ID";

        Query query = entityManager.createNativeQuery(sql, MentoresView.class);

        return (List<MentoresView>) query.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public HashMap<String, Object> finalizarAcompanamiento(Long idRutaProyectoEmprendimiento, Long idMentorCrea, String observaciones) throws Exception {
        String sql = "SELECT * FROM T_SINAPSIS_RUT_EMPRENDIMIENTO WHERE ID = ?";
        Query query = entityManager.createNativeQuery(sql, RutaProyectoEmprendimiento.class);

        query.setParameter(1, idRutaProyectoEmprendimiento);
        RutaProyectoEmprendimiento rutaProyecto = (RutaProyectoEmprendimiento) query.getSingleResult();

        if (rutaProyecto == null) {
            throw new Exception("No se encontró un proyecto de emprendimiento en la ruta ingresada. Contacte con el administrador");
        }

        String sqlEtapa = "SELECT * FROM T_SINAPSIS_ETAPAS_RUTA WHERE ESTADO = 'A' AND ORDEN_ETAPA = ?";
        Query queryEtapa = entityManager.createNativeQuery(sqlEtapa, EtapaRutaInnovacion.class);

        queryEtapa.setParameter(1, rutaProyecto.getIdEtapa());
        EtapaRutaInnovacion etapaRutaActual = (EtapaRutaInnovacion) queryEtapa.getSingleResult();

        if (etapaRutaActual == null) {
            throw new Exception("No se encontró la etapa de la ruta de innovación. Contacte con el administrador");
        }


        // Se finaliza la etapa en la ruta de innovacion y emprendimiento
        rutaProyecto.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_ESTADO_COMPLETADO);
        rutaProyecto.setFechaEstadoRuta(new Date());
        rutaProyecto.setFechaFin(new Date());

        entityManager.merge(rutaProyecto);
        entityManager.flush();

        // Se culmina el asesoramiento
        String sqlAsesoramiento = "SELECT * FROM T_SINAPSIS_ASESORAMIENTO WHERE RUTA_EMPRENDIMIENTO_EMP_ID = ?";
        Query queryAsesoramiento = entityManager.createNativeQuery(sqlAsesoramiento, Asesoramiento.class);
        queryAsesoramiento.setParameter(1, rutaProyecto.getId());

        Asesoramiento asesoramiento = (Asesoramiento) queryAsesoramiento.getSingleResult();

        if (asesoramiento == null) {
            throw new Exception("No se encontró asesoría del mentor con el proyecto de emprendimiento. Contacte con el administrador");
        }

        Date nuevaFecha = new Date();
        asesoramiento.setFechaFin(nuevaFecha);
        asesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_FINALIZADA);
        asesoramiento.setComentarios(observaciones);
        asesoramiento.setFechaModificacion(nuevaFecha);

        entityManager.merge(asesoramiento);
        entityManager.flush();

        HashMap<String, Object> resultado = new HashMap<>();

        if (etapaRutaActual.getOrdenEtapa() < MAX_ETAPA_RUTA_INNOVACION_EMPRENDIMIENTO) {
            String sqlNewEtapa = "SELECT * FROM T_SINAPSIS_ETAPAS_RUTA WHERE ESTADO = 'A' AND ORDEN_ETAPA = ?";
            Query queryNewEtapa = entityManager.createNativeQuery(sqlNewEtapa, EtapaRutaInnovacion.class);

            long idEtapaRutaNueva = etapaRutaActual.getOrdenEtapa() + 1L;

            queryNewEtapa.setParameter(1, idEtapaRutaNueva );
            EtapaRutaInnovacion etapaRutaNueva = (EtapaRutaInnovacion) queryNewEtapa.getSingleResult();

            Date fechaActual = new Date();

            // Se genera la etapa en la ruta de innovacion
            RutaProyectoEmprendimiento newRuta = new RutaProyectoEmprendimiento();
            newRuta.setIdEtapa(etapaRutaNueva.getId());
            newRuta.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO);
            newRuta.setCreadoPor(idMentorCrea);
            newRuta.setIdProyectoEmprendimiento(rutaProyecto.getIdProyectoEmprendimiento());
            newRuta.setFechaInicio(fechaActual);
            newRuta.setFechaEstadoRuta(fechaActual);
            newRuta.setFechaCreacion(fechaActual);
            newRuta.setFechaModificacion(fechaActual);

            entityManager.persist(newRuta);

            // Se genera el asesoramiento
            Asesoramiento newAsesoramiento = new Asesoramiento();
            newAsesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_SIN_MENTOR);
            newAsesoramiento.setIdRutaEmprendimiento(newRuta.getId());
            newAsesoramiento.setFechaModificacion(new Date());
            newAsesoramiento.setFechaCreacion(new Date());
            newAsesoramiento.setFechaInicio(new Date());

            entityManager.persist(newAsesoramiento);
//            entityManager.flush();

            resultado.put("result", 1);
            resultado.put("asesoramiento", newAsesoramiento);
            resultado.put("etapaRuta", etapaRutaNueva);

        } else if (etapaRutaActual.getOrdenEtapa() == MAX_ETAPA_RUTA_INNOVACION_EMPRENDIMIENTO) {
            resultado.put("result", 0);
            resultado.put("asesoramiento", asesoramiento);
            resultado.put("proyectoEmprendimiento", rutaProyecto.getIdProyectoEmprendimiento());
        }

        return resultado;
    }

    @Override
    public List<Emprendimiento> obtenerEmprendimientos(String idMentor) {
        String sql = "SELECT \n" +
                "    TE.* \n" +
                "FROM T_SINAPSIS_EMPRENDIMIENTOS TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDIMIENTOS_ID = TE.ID \n" +
                "JOIN T_SINAPSIS_RUT_EMPRENDIMIENTO TRE ON TRE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "JOIN T_SINAPSIS_ASESORAMIENTO TA ON TA.RUTA_EMPRENDIMIENTO_EMP_ID = TRE.ID\n" +
                "WHERE TRE.ESTADO_RUTA != 'COMPLETADO' AND TA.ESTADO = 'EN CURSO' AND TA.MENTOR_ID =" + idMentor;

        Query query = entityManager.createNativeQuery(sql, Emprendimiento.class);

        return (List<Emprendimiento>) query.getResultList();
    }

    @Override
    public List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_EMPRENDI_RED_SOCIAL WHERE ID_EMPRENDIMIENTO = " + idEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, RedSocialEmprendimientoView.class);

        return (List<RedSocialEmprendimientoView>) query.getResultList();
    }

    @Override
    public ProyectoEmprendimiento obtenerProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_PROY_EMPRENDIMIENTO " +
                "WHERE ID = ?1";

        Query query = entityManager.createNativeQuery(sql, ProyectoEmprendimiento.class);
        query.setParameter(1, idProyectoEmprendimiento);

        List<ProyectoEmprendimiento> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void almacenarProyectoEmprendimiento(ProyectoEmprendimiento proyectoEmprendimiento) {
        entityManager.merge(proyectoEmprendimiento);
    }
}
