package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.HorarioMentor;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
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
    public List<HorarioMentor> obtenerHorarioMentor(Long idMentor) {
        String sql = "SELECT * FROM T_SINAPSIS_HORARIOS_MENTOR WHERE " +
                "MENTORES_ID = "+ idMentor + " ORDER BY DIA";

        Query query = entityManager.createNativeQuery(sql, HorarioMentor.class);

        return (List<HorarioMentor>) query.getResultList();
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
                "ETAPAS_RUTA_ID = "+ idEtapaRutaInnovacion + " ORDER BY ID";

        Query query = entityManager.createNativeQuery(sql, MentoresView.class);

        return (List<MentoresView>) query.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean actualizarHorarioMentor(Long idMentor, List<HorarioMentor> horarios) throws Exception {
        String sql = "DELETE FROM T_SINAPSIS_HORARIOS_MENTOR WHERE MENTORES_ID = ?";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1, idMentor);
        query.executeUpdate();

        for (HorarioMentor horario: horarios ) {
            horario.setId(null);
            horario.setIdMentor(idMentor);
            entityManager.persist(horario);
        }

        entityManager.flush();

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean finalizarAcompanamiento(Long idRutaProyectoEmprendimiento, Long idMentorCrea, String observaciones) throws Exception {
        String sql = "SELECT * FROM T_SINAPSIS_RUT_EMPRENDIMIENTO WHERE ID = ?";
        Query query = entityManager.createNativeQuery(sql, RutaProyectoEmprendimiento.class);

        query.setParameter(1, idRutaProyectoEmprendimiento);
        RutaProyectoEmprendimiento rutaProyecto = (RutaProyectoEmprendimiento) query.getSingleResult();

        if (rutaProyecto == null) {
            throw new Exception("No se encontro un proyecto de emprendimiento en la ruta ingresada. Contacte con el administrador");
        }

        String sqlEtapa = "SELECT * FROM T_SINAPSIS_ETAPAS_RUTA WHERE ESTADO = 'A' AND ORDEN_ETAPA = ?";
        Query queryEtapa = entityManager.createNativeQuery(sqlEtapa, EtapaRutaInnovacion.class);

        queryEtapa.setParameter(1, rutaProyecto.getIdEtapa());
        EtapaRutaInnovacion etapaRutaActual = (EtapaRutaInnovacion) queryEtapa.getSingleResult();

        if (etapaRutaActual == null) {
            throw new Exception("No se encontro la etapa de la ruta de innovacion. Contacte con el administrador");
        }

        if (etapaRutaActual.getOrdenEtapa() < MAX_ETAPA_RUTA_INNOVACION_EMPRENDIMIENTO) {
            String sqlNewEtapa = "SELECT * FROM T_SINAPSIS_ETAPAS_RUTA WHERE ESTADO = 'A' AND ORDEN_ETAPA = ?";
            Query queryNewEtapa = entityManager.createNativeQuery(sqlNewEtapa, EtapaRutaInnovacion.class);

            queryNewEtapa.setParameter(1, (etapaRutaActual.getOrdenEtapa() + 1) );
            EtapaRutaInnovacion etapaRutaNueva = (EtapaRutaInnovacion) queryEtapa.getSingleResult();

            RutaProyectoEmprendimiento newRuta = new RutaProyectoEmprendimiento();
            newRuta.setIdEtapa(etapaRutaNueva.getId());
            newRuta.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO);
            newRuta.setCreadoPor(idMentorCrea);
            newRuta.setIdProyectoEmprendimiento(rutaProyecto.getIdProyectoEmprendimiento());
            newRuta.setFechaEstadoRuta(new Date());

            entityManager.persist(newRuta);
        }
        rutaProyecto.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_ESTADO_COMPLETADO);

        entityManager.merge(rutaProyecto);
        entityManager.flush();

        // Se culmina el asesoramiento
        String sqlAsesoramiento = "SELECT * FROM T_SINAPSIS_ASESORAMIENTO WHERE RUTA_EMPRENDIMIENTO_EMP_ID = ?";
        Query queryAsesoramiento = entityManager.createNativeQuery(sqlAsesoramiento, Asesoramiento.class);
        queryAsesoramiento.setParameter(1, rutaProyecto.getId());

        Asesoramiento asesoramiento = (Asesoramiento) queryAsesoramiento.getSingleResult();

        if (asesoramiento == null) {
            throw new Exception("No se encontro asesoria del mentor con el proyecto de emprendimiento. Contacte con el administrador");
        }

        Date nuevaFecha = new Date();
        asesoramiento.setFechaFin(nuevaFecha);
        asesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_FINALIZADA);
        asesoramiento.setComentarios(observaciones);
        asesoramiento.setFechaModificacion(nuevaFecha);

        entityManager.merge(asesoramiento);
        entityManager.flush();

        Asesoramiento newAsesoramiento = new Asesoramiento();
        newAsesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_SIN_MENTOR);
        newAsesoramiento.setIdRutaEmprendimiento(rutaProyecto.getId());

        entityManager.persist(newAsesoramiento);
        entityManager.flush();

        return true;
    }
}
