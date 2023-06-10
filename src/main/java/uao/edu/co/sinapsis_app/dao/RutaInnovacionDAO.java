package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IProyectoEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.dto.CrearTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarMentorDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.CalificarTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ConsultoriaDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.EntregaTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ProgramarConsultoriaDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPAFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPEFilterDTO;
import uao.edu.co.sinapsis_app.model.ActividadRuta;
import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.Consultoria;
import uao.edu.co.sinapsis_app.model.EtapaRutaEmprendimiento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.HerramientaRuta;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.Tarea;
import uao.edu.co.sinapsis_app.model.view.ActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.ListadoProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SubActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.TareasProyectoEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.AppUtil.getFormatoFecha;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_ASESORAMIENTO_ESTADO_ENCURSO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_CONSULTORIAS_ESTADO_EN_CURSO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_CONSULTORIAS_ESTADO_NO_ASISTIDA;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_CONSULTORIAS_ESTADO_PROGRAMADA;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_CONSULTORIAS_ESTADO_TERMINADA;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_PROY_EMPRENDIMIENTO_ESTADO_APROBADO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_TAREAS_ESTADO_ENTREGA_CALIFICADA;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_TAREAS_ESTADO_ENTREGA_ENTREGADA;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_TAREAS_ESTADO_ENTREGA_PENDIENTE;

@Repository
public class RutaInnovacionDAO implements IRutaInnovacionDAO {
    @Autowired
    IProyectoEmprendimientoDAO proyectoEmprendimientoDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ListadoProyectoEmprendimientoView> listarProyectosDeEmprendimiento(SolicitudesPEFilterDTO solicitudesPEFilterDTO) {
        String sql = "SELECT * FROM V_SINAPSIS_LISTADO_PE WHERE ESTADO_EMPRENDIMIENTO = 'APROBADO'";

        if (solicitudesPEFilterDTO.getNumeroDocumento() != null &&
                !(solicitudesPEFilterDTO.getNumeroDocumento().trim().isEmpty())) {
            sql += " AND NUMERO_DOCUMENTO = " + solicitudesPEFilterDTO.getNumeroDocumento();
        }

        if (solicitudesPEFilterDTO.getNombreEmprendedor() != null &&
                !(solicitudesPEFilterDTO.getNombreEmprendedor().trim().isEmpty())) {
            sql += " AND UPPER(NOMBRE_COMPLETO) " +
                    "like UPPER('%" + solicitudesPEFilterDTO.getNombreEmprendedor() + "%')";
        }

        if (solicitudesPEFilterDTO.getEtapasRuta() != null &&
                solicitudesPEFilterDTO.getEtapasRuta() != -1) {
            sql += " AND ETAPAS_RUTA_ID = " + solicitudesPEFilterDTO.getEtapasRuta();
        }

        if (solicitudesPEFilterDTO.getEstadosRuta() != null &&
                !solicitudesPEFilterDTO.getEstadosRuta().equalsIgnoreCase("-1")) {
            sql += " AND ESTADO_RUTA = '" + solicitudesPEFilterDTO.getEstadosRuta() + "'";
        }

        if (solicitudesPEFilterDTO.getMentorAsociado() != null &&
                solicitudesPEFilterDTO.getMentorAsociado() != -1) {
            if (solicitudesPEFilterDTO.getMentorAsociado() == 1) {
                sql += " AND ASESORAMIENTOS_ID IS NOT NULL";
            } else {
                sql += " AND ASESORAMIENTOS_ID IS NULL";
            }
        }

        sql += " ORDER BY FECHA_REGISTRO_PA DESC";

        Query query = entityManager.createNativeQuery(sql, ListadoProyectoEmprendimientoView.class);

        return (List<ListadoProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<PrimeraAtencionView> detallePrimeraAtencion(Integer idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_PRIMERA_ATENCION WHERE PROYECTO_EMPRENDIMIENTO_ID = ?";
        Query query = entityManager.createNativeQuery(sql, PrimeraAtencionView.class);

        query.setParameter(1, idProyectoEmprendimiento);

        return (List<PrimeraAtencionView>) query.getResultList();
    }

    @Override
    public List<ListadoProyectoEmprendimientoView> listarPrimerasAtencionesPendientes(SolicitudesPAFilterDTO solicitudesPAFilterDTO) {
        String sql = "SELECT * FROM V_SINAPSIS_LISTADO_PE WHERE ESTADO_EMPRENDIMIENTO = 'PENDIENTE' ";

        if (solicitudesPAFilterDTO.getTiposDocumento() != null &&
                solicitudesPAFilterDTO.getTiposDocumento() != -1) {
            sql += " AND TIPOS_DOCUMENTO_ID = " + solicitudesPAFilterDTO.getTiposDocumento();
        }

        if (solicitudesPAFilterDTO.getNumeroDocumento() != null &&
                !(solicitudesPAFilterDTO.getNumeroDocumento().trim().isEmpty())) {
            sql += " AND NUMERO_DOCUMENTO = " + solicitudesPAFilterDTO.getNumeroDocumento();
        }

        if (solicitudesPAFilterDTO.getNombreEmprendedor() != null &&
                !(solicitudesPAFilterDTO.getNombreEmprendedor().trim().isEmpty())) {
            sql += " AND UPPER(NOMBRE_COMPLETO) " +
                    "like UPPER('%" + solicitudesPAFilterDTO.getNombreEmprendedor() + "%')";
        }

        if (solicitudesPAFilterDTO.getNombreEmprendimiento() != null &&
                !(solicitudesPAFilterDTO.getNombreEmprendimiento().trim().isEmpty())) {
            sql += " AND NOMBRE_EMPRENDIMIENTO LIKE '%" + solicitudesPAFilterDTO.getNombreEmprendimiento() + "%'";
        }

        sql += " ORDER BY FECHA_REGISTRO_PA DESC";

        Query query = entityManager.createNativeQuery(sql, ListadoProyectoEmprendimientoView.class);

        return (List<ListadoProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception {
        ProyectoEmprendimiento proyectoEmprendimiento = proyectoEmprendimientoDAO.find(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());
        Date fechaActual = new Date();

        if (proyectoEmprendimiento != null) {
            EtapaRutaEmprendimiento etapaRutaEmprendimiento = new EtapaRutaEmprendimiento();
            etapaRutaEmprendimiento.setProyectoEmprendimiento(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());
            etapaRutaEmprendimiento.setEtapaEmprendimiento(rutaPrimeraAtencionDTO.getIdEtapaRuta());
            etapaRutaEmprendimiento.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO);
            etapaRutaEmprendimiento.setFechaCreacion(fechaActual);
            etapaRutaEmprendimiento.setFechaModificacion(fechaActual);
            etapaRutaEmprendimiento.setCreadoPor(rutaPrimeraAtencionDTO.getCreado_por());

            EtapaRutaEmprendimiento isRegistered = entityManager.merge(etapaRutaEmprendimiento);

            if (isRegistered != null) {
                proyectoEmprendimiento.setEstadoEmprendimiento(T_SINAPSIS_PROY_EMPRENDIMIENTO_ESTADO_APROBADO);

                boolean isUpdated = proyectoEmprendimientoDAO.updateProyecto(proyectoEmprendimiento);

                if (isUpdated) {
                    if (rutaPrimeraAtencionDTO.getIdMentorPrincipal() != null) {
                        Asesoramiento asesoramiento = new Asesoramiento();
                        asesoramiento.setFechaInicio(fechaActual);
                        asesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_ENCURSO);
                        asesoramiento.setIdRutaEmprendimiento(isRegistered.getId());
                        asesoramiento.setIdMentor(rutaPrimeraAtencionDTO.getIdMentorPrincipal());
                        asesoramiento.setFechaCreacion(fechaActual);
                        asesoramiento.setFechaModificacion(fechaActual);

                        Asesoramiento asesoramientoNew = entityManager.merge(asesoramiento);
                        entityManager.flush();

                        if (asesoramientoNew != null) {
                            return true;
                        } else {
                            throw new Exception("Problema al almacenar el asesoramiento");
                        }
                    }
                } else {
                    throw new Exception("Problema al actualizar proyecto de emprendimiento");
                }

            } else {
                throw new Exception("Problema registrar la etapa de emprendimiento");
            }
        } else  {
            throw new Exception("No se encontro el proyecto de emprendimiento");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean asignarMentor(AsignarMentorDTO asignarMentorDTO) throws Exception {
        ProyectoEmprendimiento proyectoEmprendimiento =
                proyectoEmprendimientoDAO.find(asignarMentorDTO.getIdProyectoEmprendimiento());

        if (proyectoEmprendimiento != null) {
            EtapaRutaEmprendimiento isRegistered =
                    entityManager.find(EtapaRutaEmprendimiento.class, asignarMentorDTO.getIdRutaProyectoEmprendimiento());

            if (isRegistered != null) {
                Date fechaActual = new Date();

                Asesoramiento asesoramiento = new Asesoramiento();
                asesoramiento.setFechaInicio(fechaActual);
                asesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_ENCURSO);
                asesoramiento.setIdRutaEmprendimiento(isRegistered.getId());
                asesoramiento.setIdMentor(asignarMentorDTO.getIdMentorPrincipal());
                asesoramiento.setFechaCreacion(fechaActual);
                asesoramiento.setFechaModificacion(fechaActual);

                Asesoramiento asesoramientoNew = entityManager.merge(asesoramiento);
                entityManager.flush();

                if (asesoramientoNew != null) {
                    return true;
                } else {
                    throw new Exception("Problema al almacenar el asesoramiento");
                }
            } else {
                throw new Exception("No se encontro el la etapa en la ruta del proyecto de emprendimiento");
            }
        } else  {
            throw new Exception("No se encontro el proyecto de emprendimiento");
        }
    }

    @Override
    public AsesoramientosView obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_PROY_EMPRENDIMIENTO) AS ID_VIEW, v.* FROM V_SINAPSIS_ASESORAMIENTOS v " +
                "WHERE (v.ESTADO_RUTA_EMPRENDI = 'PENDIENTE' or v.ESTADO_RUTA_EMPRENDI= 'PENDIENTE_APROBAR') AND v.ID_PROY_EMPRENDIMIENTO = " + idProyectoEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);

        List<AsesoramientosView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados.get(0);
        }
        return null;
    }

    @Override
    public List<ActividadRuta> obtenerActividadesEtapa() {
        String sql = "SELECT * FROM T_SINAPSIS_ACTIVIDADES_RUTA";
        Query query = entityManager.createNativeQuery(sql, ActividadRuta.class);

        return query.getResultList();
    }

    @Override
    public List<ActividadRuta> obtenerActividadesEtapaById(Long idEtapa) {
        String sql = "SELECT * FROM T_SINAPSIS_ACTIVIDADES_RUTA " +
                "WHERE ESTADO = 'A' AND ETAPAS_RUTAS_ID = " + idEtapa;
        Query query = entityManager.createNativeQuery(sql, ActividadRuta.class);

        return (List<ActividadRuta>) query.getResultList();
    }

    @Override
    public List<HerramientaRuta> obtenerHerramientasEtapa() {
        String sql = "SELECT * FROM T_SINAPSIS_HERRAMIENTAS";
        Query query = entityManager.createNativeQuery(sql, HerramientaRuta.class);

        return (List<HerramientaRuta>) query.getResultList();
    }

    @Override
    public List<HerramientaRuta> obtenerHerramientasEtapaById(Long idEtapa) {
        String sql = "SELECT TH.* FROM T_SINAPSIS_HERRAMIENTAS TH \n" +
                "    JOIN T_SINAPSIS_ACTIVIDADES_RUTA TAR ON TH.ACTIVIDADES_RUTAS_ID = TAR.ID\n" +
                "    WHERE TAR.ETAPAS_RUTAS_ID = " + idEtapa;
        Query query = entityManager.createNativeQuery(sql, HerramientaRuta.class);

        return (List<HerramientaRuta>) query.getResultList();
    }

    @Override
    public List<SubActividadesEmprendedorView> obtenerSubActividadesEmprendedor(Long idProyectoEmprendimiento, Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_ACT_EMPRENDEDOR \n" +
                "    WHERE ESTADO_ACTIVIDAD = 'COMPLETADA' AND PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento + "\n" +
                "    AND RUTAS_EMPRENDIMIENTOS_ID = " + idRutaEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, SubActividadesEmprendedorView.class);

        return (List<SubActividadesEmprendedorView>) query.getResultList();
    }

    @Override
    public List<ActividadesEmprendedorView> obtenerActividadesEmprendedor(Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_ACT_RUTA_EMP \n" +
                "    WHERE ESTADO = 'COMPLETADA' AND RUTAS_EMPRENDIMIENTOS_ID = " + idRutaEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, ActividadesEmprendedorView.class);

        return (List<ActividadesEmprendedorView>) query.getResultList();
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, String tipoBusqueda) {
        String sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                "WHERE ESTADO_ENTREGA = '" + tipoBusqueda + "' " +
                "AND PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);

        return (List<TareasProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, boolean historico) {
        String sql = "";

        if (historico) {
            sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                    "WHERE ESTADO_ENTREGA NOT IN ('PENDIENTE', 'ENTREGADA') " +
                    "AND PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento;
        } else {
            sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                    "WHERE PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento;
        }

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);

        return (List<TareasProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public TareasProyectoEmprendimientoView obtenerTareasPorId(Long idTarea) {
        String  sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                "WHERE ID_TAREA = " + idTarea;

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);

        return (TareasProyectoEmprendimientoView) query.getSingleResult();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoria(Long idConsultoria) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_CONSULTORIA = " + idConsultoria + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaNormal(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_SUB_ACT_RUTA IS NULL " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaEspecializada(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_SUB_ACT_RUTA IS NOT NULL " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerHistoricoConsultoria(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaEmprendedor(Long idEmprendedor) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE EMPRENDEDORES_ID = " + idEmprendedor + " " +
                "AND FECHA_CONSULTORIA >= TRUNC(SYSDATE) " +
                "AND (ESTADO_CONSULTORIA = 'PROGRAMADA' OR ESTADO_CONSULTORIA = 'EN CURSO') " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaMentor(Long idMentor) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_MENTOR = " + idMentor + " " +
                "AND TRUNC(FECHA_CONSULTORIA) >= TRUNC(SYSDATE) " +
                "AND ESTADO_CONSULTORIA = 'PROGRAMADA' ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Consultoria programarConsultoriaEmprendedor(ProgramarConsultoriaDTO programarConsultoriaDTO) throws Exception {
        Consultoria nuevaConsultoria = new Consultoria();
        nuevaConsultoria.setTitulo(programarConsultoriaDTO.getTitulo());
        nuevaConsultoria.setTipoConsultoria(programarConsultoriaDTO.getTipoConsultoria());
        nuevaConsultoria.setFechaConsultoria(getFormatoFecha(programarConsultoriaDTO.getFechaConsultoria(), "dd/MM/yyyy hh:mm:ss"));
        nuevaConsultoria.setHoraInicio(programarConsultoriaDTO.getHoraInicio());
        nuevaConsultoria.setHoraFinalizacion(programarConsultoriaDTO.getHoraFinalizacion());
        nuevaConsultoria.setAsuntoConsultoria(programarConsultoriaDTO.getAsuntoConsultoria());
        nuevaConsultoria.setEstadoConsultoria(T_SINAPSIS_CONSULTORIAS_ESTADO_PROGRAMADA);
        nuevaConsultoria.setIdProyectoEmprendimiento(programarConsultoriaDTO.getProyectoEmprendimiento());
        nuevaConsultoria.setIdMentor(programarConsultoriaDTO.getMentor());
        if (programarConsultoriaDTO.getSubActividadRuta() != null) {
            nuevaConsultoria.setIdSubActividadRuta(programarConsultoriaDTO.getSubActividadRuta());
        }
        nuevaConsultoria.setFechaCreacion(new Date());
        nuevaConsultoria.setFechaModificacion(new Date());
        nuevaConsultoria.setIdUsuarioCrea(programarConsultoriaDTO.getUsuarioCrea());

        Consultoria isRegistered = entityManager.merge(nuevaConsultoria);
        entityManager.flush();

        if (isRegistered == null) {
            throw new Exception("Problema al programar la consultoria");
        }

        return isRegistered;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean iniciarConsultoriaEmprendedor(Long idConsultoria) throws Exception {
        Consultoria consultoria = entityManager.find(Consultoria.class, idConsultoria);

        if (consultoria != null) {
            consultoria.setEstadoConsultoria(T_SINAPSIS_CONSULTORIAS_ESTADO_EN_CURSO);
            consultoria.setFechaInicioReal(new Date());

            Consultoria isRegistered = entityManager.merge(consultoria);
            entityManager.flush();

            if (isRegistered == null) {
                throw new Exception("Problema al iniciar la consultoria");
            }
        } else {
            throw new Exception("Problema al buscar la consultoria");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean inasistenciaConsultoriaEmprendedor(ConsultoriaDTO consultoriaDTO) throws Exception {
        Consultoria consultoria = entityManager.find(Consultoria.class, consultoriaDTO.getIdConsultoria());

        if (consultoria != null) {
            consultoria.setEstadoConsultoria(T_SINAPSIS_CONSULTORIAS_ESTADO_NO_ASISTIDA);
            consultoria.setComentariosConsultoria(consultoriaDTO.getComentariosConsultoria());

            Consultoria isRegistered = entityManager.merge(consultoria);
            entityManager.flush();

            if (isRegistered == null) {
                throw new Exception("Problema al marcar inasistencia de la consultoria");
            }
        } else {
            throw new Exception("Problema al buscar la consultoria");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean terminarConsultoriaEmprendedor(ConsultoriaDTO consultoriaDTO) throws Exception {
        Consultoria consultoria = entityManager.find(Consultoria.class, consultoriaDTO.getIdConsultoria());

        if (consultoria != null) {
            consultoria.setEstadoConsultoria(T_SINAPSIS_CONSULTORIAS_ESTADO_TERMINADA);
            consultoria.setComentariosConsultoria(consultoriaDTO.getComentariosConsultoria());
            consultoria.setFechaFinalizacionReal(new Date());

            Consultoria isRegistered = entityManager.merge(consultoria);
            entityManager.flush();

            if (isRegistered == null) {
                throw new Exception("Problema al marcar terminar la consultoria");
            }
        } else {
            throw new Exception("Problema al buscar la consultoria");
        }
        return true;
    }

    @Override
    public List<EmprendedoresView> obtenerEmprendedores(EmprendedoresAdmFilterDTO emprendedoresAdmFilterDTO) {
        String sql = "SELECT * FROM V_SINAPSIS_EMPRENDEDORES WHERE ESTADO_CUENTA = 1 ";


        if (emprendedoresAdmFilterDTO.getNumeroDocumento() != null &&
                !(emprendedoresAdmFilterDTO.getNumeroDocumento().trim().isEmpty())) {
            sql += " AND NUMERO_DOCUMENTO = " + emprendedoresAdmFilterDTO.getNumeroDocumento();
        }

        if (emprendedoresAdmFilterDTO.getNombreEmprendedor() != null &&
                !(emprendedoresAdmFilterDTO.getNombreEmprendedor().trim().isEmpty())) {
            sql += " AND UPPER(NOMBRE_COMPLETO) " +
                    "like UPPER('%" + emprendedoresAdmFilterDTO.getNombreEmprendedor() + "%')";
        }

        if (emprendedoresAdmFilterDTO.getTiposContacto() != null &&
                emprendedoresAdmFilterDTO.getTiposContacto() != -1) {
            sql += " AND TIPO_CONTACTO_ID = " + emprendedoresAdmFilterDTO.getTiposContacto();
        }

        if (emprendedoresAdmFilterDTO.getEstadoEnRuta() != null &&
                !(emprendedoresAdmFilterDTO.getEstadoEnRuta().trim().isEmpty())) {
            sql += " AND ESTADO = " + emprendedoresAdmFilterDTO.getEstadoEnRuta();
        }

        sql += "ORDER BY NOMBRES DESC, APELLIDOS DESC";

        Query query = entityManager.createNativeQuery(sql, EmprendedoresView.class);

        return (List<EmprendedoresView>) query.getResultList();
    }

    @Override
    public List<MentoresView> obtenerMentores() {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES " +
                "ORDER BY NOMBRES DESC, APELLIDOS DESC";

        Query query = entityManager.createNativeQuery(sql, MentoresView.class);

        return (List<MentoresView>) query.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Tarea registrarTareaEmprendedor(CrearTareaDTO crearTareaDTO) throws Exception {
        Tarea tareaNueva = new Tarea();
        tareaNueva.setEstadoEntrega(T_SINAPSIS_TAREAS_ESTADO_ENTREGA_PENDIENTE);
        tareaNueva.setTitulo(crearTareaDTO.getNombreTarea());
        tareaNueva.setUsuarioCrea(crearTareaDTO.getUsuarioCrea());
        tareaNueva.setFechaLimiteEntrega(getFormatoFecha(crearTareaDTO.getFechaEntrega(), "dd/MM/yyyy hh:mm:ss"));
        tareaNueva.setFechaCreacion(new Date());
        tareaNueva.setFechaModificacion(new Date());
        tareaNueva.setIdProyectoEmprendimiento(crearTareaDTO.getIdProyectoEmprendimiento());
        tareaNueva.setUrlMaterialApoyo(crearTareaDTO.getFileTareaURL() == null ? null : crearTareaDTO.getFileTareaURL() );
        tareaNueva.setDescripcion(crearTareaDTO.getDescripcionTarea());

        Tarea isRegistered = entityManager.merge(tareaNueva);
        entityManager.flush();

        if (isRegistered == null) {
            throw new Exception("Problema al registrar la tarea");
        }

        return isRegistered;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarEntregaTareaEmprendedor(EntregaTareaDTO entregaTareaDTO) throws Exception {
        Tarea tarea = entityManager.find(Tarea.class, entregaTareaDTO.getIdTarea());

        if (tarea == null) {
            throw new Exception("Problema al encontrar la tarea");
        }

        if (!(tarea.getEstadoEntrega().equalsIgnoreCase(T_SINAPSIS_TAREAS_ESTADO_ENTREGA_PENDIENTE))) {
            throw new Exception("La tarea se encuentra en estado: "
                    + tarea.getEstadoEntrega()
                    + ". Por lo que no se pudo realizar la entrega.");
        }

        if (tarea.getFechaLimiteEntrega().compareTo(new Date()) < 0) {
            throw new Exception("La tarea se encuentra vencida");
        }

        tarea.setEstadoEntrega(T_SINAPSIS_TAREAS_ESTADO_ENTREGA_ENTREGADA);
        tarea.setFechaEntrega(new Date());
        tarea.setFechaModificacion(new Date());

        if (entregaTareaDTO.getComentariosEntrega() != null) {
            tarea.setComentariosEntregaEmprendedor(entregaTareaDTO.getComentariosEntrega());
        }

        if (entregaTareaDTO.getFileEntregaURL() != null) {
            tarea.setUrlArchivosEntregados(entregaTareaDTO.getFileEntregaURL());
        }

        Tarea isUpdated = entityManager.merge(tarea);
        entityManager.flush();

        if (isUpdated == null) {
            throw new Exception("Problema al entregar la tarea");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarCalificacionTareaEmprendedor(CalificarTareaDTO calificarTareaDTO) throws Exception {
        Tarea tarea = entityManager.find(Tarea.class, calificarTareaDTO.getIdTarea());

        if (tarea == null) {
            throw new Exception("Problema al encontrar la tarea");
        }

        tarea.setEstadoEntrega(T_SINAPSIS_TAREAS_ESTADO_ENTREGA_CALIFICADA);
        tarea.setFechaModificacion(new Date());
        tarea.setCalificacion(calificarTareaDTO.getCalificacionEntrega());
        if (calificarTareaDTO.getComentariosEntrega() != null) {
            tarea.setComentariosEntregaUsuario(calificarTareaDTO.getComentariosEntrega());
        }

        Tarea isUpdated = entityManager.merge(tarea);
        entityManager.flush();

        if (isUpdated == null) {
            throw new Exception("Problema al Calificar la tarea");
        }

        return true;
    }

    @Override
    public AsesoramientosView buscarAsesoramientoPorIdProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_PROY_EMPRENDIMIENTO) AS ID_VIEW, V.* FROM V_SINAPSIS_ASESORAMIENTOS V WHERE ID_PROY_EMPRENDIMIENTO = ?1";

        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        return (AsesoramientosView) query.getSingleResult();
    }

    @Override
    public AsesoramientosView buscarAsesoramientoPorIdRutaProyectoEmprendimiento(Long idRutaProyectoEmprendimiento) {
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_PROY_EMPRENDIMIENTO) AS ID_VIEW, V.* FROM V_SINAPSIS_ASESORAMIENTOS V WHERE ID_RUTA_EMPRENDI = ?1";

        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);
        query.setParameter(1, idRutaProyectoEmprendimiento);

        return (AsesoramientosView) query.getSingleResult();
    }

    @Override
    public MentoresProyectoEmprendimientoView buscarAsesoramientoMentorPorIdRutaProyectoEmprendimiento(Long idRutaProyectoEmprendimiento) {
        String sql = "SELECT V.* FROM V_SINAPSIS_MENTORES_PROYECTO V WHERE ID_RUTA_EMPRENDIMIENTO = ?1";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);
        query.setParameter(1, idRutaProyectoEmprendimiento);

        return (MentoresProyectoEmprendimientoView) query.getSingleResult();
    }

    @Override
    public EtapaRutaInnovacion buscarEtapaRutaInnovacion(Long idEtapaRuta) {
        String sql = "SELECT * FROM T_SINAPSIS_ETAPAS_RUTA WHERE ID = ?1";

        Query query = entityManager.createNativeQuery(sql, EtapaRutaInnovacion.class);
        query.setParameter(1, idEtapaRuta);

        return (EtapaRutaInnovacion) query.getSingleResult();
    }
}
