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
import uao.edu.co.sinapsis_app.model.ActividadRutaEmp;
import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.Consultoria;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.HerramientaRuta;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.SubActividadRuta;
import uao.edu.co.sinapsis_app.model.SubActividadRutaEmp;
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
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_RUT_EMPRENDIMIENTO_ESTADO_COMPLETADO;
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
                sql += " AND MENTOR_ASESORAMIENTOS_ID IS NOT NULL";
            } else {
                sql += " AND MENTOR_ASESORAMIENTOS_ID IS NULL";
            }
        }

        sql += " ORDER BY FECHA_REGISTRO_PA DESC";

        Query query = entityManager.createNativeQuery(sql, ListadoProyectoEmprendimientoView.class);

        return (List<ListadoProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<PrimeraAtencionView> detallePrimeraAtencion(Integer idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_PRIMERA_ATENCION WHERE PROYECTO_EMPRENDIMIENTO_ID = ?1";
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
    public boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception {
        ProyectoEmprendimiento proyectoEmprendimiento = proyectoEmprendimientoDAO.find(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());

        if (proyectoEmprendimiento != null) {
            if (rutaPrimeraAtencionDTO.getIdEtapaRuta() == 1L) {
                return asignarEtapaSonar(rutaPrimeraAtencionDTO, proyectoEmprendimiento);
            } else {
                return asignarEtapaPensar(rutaPrimeraAtencionDTO, proyectoEmprendimiento);
            }
        } else {
            throw new Exception("No se encontró el proyecto de emprendimiento");
        }
    }

    private boolean asignarEtapaSonar(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO, ProyectoEmprendimiento proyectoEmprendimiento) throws Exception {
        Date fechaActual = new Date();

        RutaProyectoEmprendimiento etapaRutaEmprendimiento = new RutaProyectoEmprendimiento();
        etapaRutaEmprendimiento.setIdProyectoEmprendimiento(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());
        etapaRutaEmprendimiento.setIdEtapa(rutaPrimeraAtencionDTO.getIdEtapaRuta());
        etapaRutaEmprendimiento.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO);
        etapaRutaEmprendimiento.setFechaCreacion(fechaActual);
        etapaRutaEmprendimiento.setFechaInicio(fechaActual);
        etapaRutaEmprendimiento.setFechaModificacion(fechaActual);
        etapaRutaEmprendimiento.setCreadoPor(rutaPrimeraAtencionDTO.getCreado_por());

        RutaProyectoEmprendimiento isRegistered = entityManager.merge(etapaRutaEmprendimiento);

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
        return true;
    }

    private boolean asignarEtapaPensar(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO, ProyectoEmprendimiento proyectoEmprendimiento) throws Exception {
        Date fechaActual = new Date();

        for (int i = 1; i <= rutaPrimeraAtencionDTO.getIdEtapaRuta(); i++) {
            if (i == rutaPrimeraAtencionDTO.getIdEtapaRuta()) {
                RutaProyectoEmprendimiento etapaRutaEmprendimiento = new RutaProyectoEmprendimiento();
                etapaRutaEmprendimiento.setIdProyectoEmprendimiento(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());
                etapaRutaEmprendimiento.setIdEtapa(rutaPrimeraAtencionDTO.getIdEtapaRuta());
                etapaRutaEmprendimiento.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO);
                etapaRutaEmprendimiento.setFechaCreacion(fechaActual);
                etapaRutaEmprendimiento.setFechaInicio(fechaActual);
                etapaRutaEmprendimiento.setFechaModificacion(fechaActual);
                etapaRutaEmprendimiento.setCreadoPor(rutaPrimeraAtencionDTO.getCreado_por());

                RutaProyectoEmprendimiento isRegistered = entityManager.merge(etapaRutaEmprendimiento);

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
            } else {
                RutaProyectoEmprendimiento etapaRutaEmprendimiento = new RutaProyectoEmprendimiento();
                etapaRutaEmprendimiento.setIdProyectoEmprendimiento(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());
                etapaRutaEmprendimiento.setIdEtapa(Long.valueOf(i));
                etapaRutaEmprendimiento.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_ESTADO_COMPLETADO);
                etapaRutaEmprendimiento.setFechaCreacion(fechaActual);
                etapaRutaEmprendimiento.setFechaInicio(fechaActual);
                etapaRutaEmprendimiento.setFechaFin(fechaActual);
                etapaRutaEmprendimiento.setFechaModificacion(fechaActual);
                etapaRutaEmprendimiento.setCreadoPor(rutaPrimeraAtencionDTO.getCreado_por());
                etapaRutaEmprendimiento.setFechaEstadoRuta(fechaActual);


                RutaProyectoEmprendimiento isRegistered = entityManager.merge(etapaRutaEmprendimiento);
                entityManager.flush();

                if (isRegistered != null) {
                    // Busca actividades creadas
                    String sqlActRutaEmp = "SELECT * FROM T_SINAPSIS_ACT_RUTA_EMP WHERE RUTAS_EMPRENDIMIENTOS_ID = ?1 AND ESTADO = 'COMPLETADO'";
                    Query query = entityManager.createNativeQuery(sqlActRutaEmp, ActividadRutaEmp.class);
                    query.setParameter(1, isRegistered.getId());

                    List<ActividadRutaEmp> actividadRutaEmps = query.getResultList();

                    if (actividadRutaEmps.size() > 0) {
                        for (ActividadRutaEmp actividadRutaEmp : actividadRutaEmps) {

                            String sqlSubActRuta = "SELECT * FROM T_SINAPSIS_SUB_ACT_RUTA WHERE ACTIVIDADES_RUTAS_ID = ?1";
                            Query querySubAct = entityManager.createNativeQuery(sqlSubActRuta, SubActividadRuta.class);
                            querySubAct.setParameter(1, actividadRutaEmp.getActividadRutaEmpId().getIdActividad());

                            List<SubActividadRuta> subActividadRutas = querySubAct.getResultList();

                            for (SubActividadRuta sub : subActividadRutas) {
                                SubActividadRutaEmp newSubActividadRutaEmp = new SubActividadRutaEmp();

                                newSubActividadRutaEmp.setEstadoActividad("COMPLETADO");
                                newSubActividadRutaEmp.setSubActividadRutaId(sub.getId());
                                newSubActividadRutaEmp.setRutaEmprendimientoId(isRegistered.getId());
                                newSubActividadRutaEmp.setFechaEstadoActividad(new Date());
                                newSubActividadRutaEmp.setFechaCreacion(new Date());
                                newSubActividadRutaEmp.setFechaModificacion(new Date());
                                entityManager.persist(newSubActividadRutaEmp);
                            }

                            actividadRutaEmp.setEstado("COMPLETADO");
                            entityManager.merge(actividadRutaEmp);
                        }
                    }

                } else {
                    throw new Exception("Problema registrar la etapa de emprendimiento");
                }
            }
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean asignarMentor(AsignarMentorDTO asignarMentorDTO) throws Exception {
        ProyectoEmprendimiento proyectoEmprendimiento =
                proyectoEmprendimientoDAO.find(asignarMentorDTO.getIdProyectoEmprendimiento());

        if (proyectoEmprendimiento != null) {
            RutaProyectoEmprendimiento isRegistered =
                    entityManager.find(RutaProyectoEmprendimiento.class, asignarMentorDTO.getIdRutaProyectoEmprendimiento());

            if (isRegistered != null) {
                Date fechaActual = new Date();

                Asesoramiento asesoramiento = proyectoEmprendimientoDAO.buscarAsesoramiento(isRegistered.getId(), asignarMentorDTO.getIdMentorPrincipal());

                if (asesoramiento != null) {
                    asesoramiento.setFechaInicio(fechaActual);
                    asesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_ENCURSO);
                    asesoramiento.setFechaModificacion(fechaActual);
                    asesoramiento.setIdMentor(asignarMentorDTO.getIdMentorPrincipal());
                } else {
                    asesoramiento = new Asesoramiento();
                    asesoramiento.setFechaInicio(fechaActual);
                    asesoramiento.setEstado(T_SINAPSIS_ASESORAMIENTO_ESTADO_ENCURSO);
                    asesoramiento.setIdRutaEmprendimiento(isRegistered.getId());
                    asesoramiento.setIdMentor(asignarMentorDTO.getIdMentorPrincipal());
                    asesoramiento.setFechaCreacion(fechaActual);
                    asesoramiento.setFechaModificacion(fechaActual);
                }

                Asesoramiento asesoramientoNew = entityManager.merge(asesoramiento);
                entityManager.flush();

                if (asesoramientoNew != null) {
                    return true;
                } else {
                    throw new Exception("Problema al almacenar el asesoramiento");
                }
            } else {
                throw new Exception("No se encontró el la etapa en la ruta del proyecto de emprendimiento");
            }
        } else {
            throw new Exception("No se encontró el proyecto de emprendimiento");
        }
    }

    @Override
    public AsesoramientosView obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_RUTA_EMPRENDI) AS ID_VIEW, v.* FROM V_SINAPSIS_ASESORAMIENTOS v " +
                "WHERE --(v.ESTADO_RUTA_EMPRENDI = 'PENDIENTE' or v.ESTADO_RUTA_EMPRENDI= 'PENDIENTE_APROBAR') AND " +
                "\nv.ID_PROY_EMPRENDIMIENTO = ?1 ORDER BY ID_VIEW DESC";

        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        List<AsesoramientosView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados.get(0);
        }
        return null;
    }

    @Override
    public List<RutaProyectoEmprendimiento> obtenerRutaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_RUT_EMPRENDIMIENTO WHERE PROYECTOS_EMPRENDIMIENTOS_ID = ?1  ORDER BY ETAPAS_ID";

        Query query = entityManager.createNativeQuery(sql, RutaProyectoEmprendimiento.class);

        query.setParameter(1, idProyectoEmprendimiento);

        List<RutaProyectoEmprendimiento> result = query.getResultList();

        if (result.size() > 0) {
            return result;
        } else {
            return null;
        }
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
                "WHERE ESTADO = 'A' AND ETAPAS_RUTAS_ID = ?1 ORDER BY ID ASC";

        Query query = entityManager.createNativeQuery(sql, ActividadRuta.class);
        query.setParameter(1, idEtapa);

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
                "    JOIN T_SINAPSIS_SUB_ACT_RUTA TSAR ON TH.SUB_ACTIVIDADES_RUTAS_ID = TSAR.ID\n" +
                "    JOIN T_SINAPSIS_ACTIVIDADES_RUTA TAR ON TSAR.ACTIVIDADES_RUTAS_ID = TAR.ID\n" +
                "    WHERE TAR.ETAPAS_RUTAS_ID = ?1 AND TSAR.TIPO = 2";

        Query query = entityManager.createNativeQuery(sql, HerramientaRuta.class);
        query.setParameter(1, idEtapa);

        return (List<HerramientaRuta>) query.getResultList();
    }

    @Override
    public List<SubActividadesEmprendedorView> obtenerSubActividadesEmprendedor(Long idProyectoEmprendimiento, Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_ACT_EMPRENDEDOR \n" +
                "    WHERE ESTADO_ACTIVIDAD = 'COMPLETADO' \n " +
                "    AND RUTAS_EMPRENDIMIENTOS_ID = ?1";

        Query query = entityManager.createNativeQuery(sql, SubActividadesEmprendedorView.class);
        query.setParameter(1, idRutaEmprendimiento);

        return (List<SubActividadesEmprendedorView>) query.getResultList();
    }

    @Override
    public List<ActividadesEmprendedorView> obtenerActividadesEmprendedor(Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_ACT_RUTA_EMP \n" +
                "    WHERE ESTADO = 'COMPLETADO' AND RUTAS_EMPRENDIMIENTOS_ID = ?1 ORDER BY ACTIVIDADES_RUTAS_ID ASC ";

        Query query = entityManager.createNativeQuery(sql, ActividadesEmprendedorView.class);

        query.setParameter(1, idRutaEmprendimiento);

        return (List<ActividadesEmprendedorView>) query.getResultList();
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, String tipoBusqueda) {
        String sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                "WHERE ESTADO_ENTREGA = ?1 AND PROYECTOS_EMPRENDIMIENTOS_ID = ?2";

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);
        query.setParameter(1, tipoBusqueda);
        query.setParameter(2, idProyectoEmprendimiento);

        return (List<TareasProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, boolean historico) {
        String sql = "";

        if (historico) {
            sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                    "WHERE ESTADO_ENTREGA NOT IN ('PENDIENTE', 'ENTREGADA') " +
                    "AND PROYECTOS_EMPRENDIMIENTOS_ID = ?1";
        } else {
            sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                    "WHERE PROYECTOS_EMPRENDIMIENTOS_ID = ?1";
        }

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        return (List<TareasProyectoEmprendimientoView>) query.getResultList();
    }

    @Override
    public TareasProyectoEmprendimientoView obtenerTareasPorId(Long idTarea) {
        String sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                "WHERE ID_TAREA = ?1";

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);
        query.setParameter(1, idTarea);

        return (TareasProyectoEmprendimientoView) query.getSingleResult();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoria(Long idConsultoria) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_CONSULTORIA = ?1 ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idConsultoria);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaNormal(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_SUB_ACT_RUTA IS NULL " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = ?1 ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaEspecializada(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_SUB_ACT_RUTA IS NOT NULL " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = ?1 ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerHistoricoConsultoria(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = ?1 ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaEmprendedor(Long idEmprendedor) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE EMPRENDEDORES_ID = ?1 " +
                "AND (FECHA_CONSULTORIA - SYSDATE) <= 1 " +
                "AND (ESTADO_CONSULTORIA = 'PROGRAMADA' OR ESTADO_CONSULTORIA = 'EN CURSO') " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idEmprendedor);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaProyectoEmprendimiento(Long idEmprendedor, Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE EMPRENDEDORES_ID = ?1 " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = ?2 " +
                "AND (FECHA_CONSULTORIA - SYSDATE) <= 1 " +
                "AND (ESTADO_CONSULTORIA = 'PROGRAMADA' OR ESTADO_CONSULTORIA = 'EN CURSO') " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idProyectoEmprendimiento);
        query.setParameter(2, idProyectoEmprendimiento);

        return (List<ConsultoriasView>) query.getResultList();
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaMentor(Long idMentor) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_MENTOR = ?1 " +
                "AND (FECHA_CONSULTORIA - SYSDATE) <= 1 " +
                "AND ESTADO_CONSULTORIA = 'PROGRAMADA' ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);
        query.setParameter(1, idMentor);

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
            throw new Exception("Problema al programar la consultoría");
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
                throw new Exception("Problema al iniciar la consultoría");
            }
        } else {
            throw new Exception("Problema al buscar la consultoría");
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
                throw new Exception("Problema al marcar inasistencia de la consultoría");
            }
        } else {
            throw new Exception("Problema al buscar la consultoría");
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
                throw new Exception("Problema al marcar terminar la consultoría");
            }
        } else {
            throw new Exception("Problema al buscar la consultoría");
        }
        return true;
    }

    @Override
    public List<EmprendedoresView> obtenerEmprendedores(EmprendedoresAdmFilterDTO emprendedoresAdmFilterDTO) {
        String sql = "SELECT * FROM V_SINAPSIS_EMPRENDEDORES WHERE ";


        if (emprendedoresAdmFilterDTO.getEstadosCuenta() != null &&
                !(emprendedoresAdmFilterDTO.getEstadosCuenta().trim().isEmpty())) {
            if (!(emprendedoresAdmFilterDTO.getEstadosCuenta().equalsIgnoreCase("-1"))) {
                sql += "ESTADO_CUENTA = " + emprendedoresAdmFilterDTO.getEstadosCuenta();
            } else {
                sql += "ESTADO_CUENTA in (1,0) ";
            }

        } else {
            sql += "  ESTADO_CUENTA = 1";
        }

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

        sql += " ORDER BY NOMBRES DESC, APELLIDOS DESC";

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
//        tareaNueva.setFechaLimiteEntrega(getFormatoFecha(crearTareaDTO.getFechaEntrega(), "dd/MM/yyyy hh:mm:ss"));
        tareaNueva.setFechaCreacion(new Date());
        tareaNueva.setFechaModificacion(new Date());
        tareaNueva.setIdProyectoEmprendimiento(crearTareaDTO.getIdProyectoEmprendimiento());
        tareaNueva.setUrlMaterialApoyo(crearTareaDTO.getFileTareaURL() == null ? null : crearTareaDTO.getFileTareaURL());
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

//        if (tarea.getFechaLimiteEntrega().compareTo(new Date()) < 0) {
//            throw new Exception("La tarea se encuentra vencida");
//        }

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
        tarea.setCalificacionCuantitativa(calificarTareaDTO.getCalificacionCuantitativaEntrega());
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
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY ID_PROY_EMPRENDIMIENTO) AS ID_VIEW, V.* FROM V_SINAPSIS_ASESORAMIENTOS V WHERE ID_PROY_EMPRENDIMIENTO = ?1 ORDER BY ID_RUTA_EMPRENDI DESC";

        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);
        query.setParameter(1, idProyectoEmprendimiento);

        List<AsesoramientosView> result = query.getResultList();

        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
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
