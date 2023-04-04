package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IProyectoEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.ActividadRuta;
import uao.edu.co.sinapsis_app.model.EtapaRutaEmprendimiento;
import uao.edu.co.sinapsis_app.model.HerramientaRuta;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.ActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.SubActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.ListadoProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.TareasProyectoEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_PROY_EMPRENDIMIENTO_ESTADO_APROBADO;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO;

@Repository
public class RutaInnovacionDAO implements IRutaInnovacionDAO {
    @Autowired
    IProyectoEmprendimientoDAO proyectoEmprendimientoDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ListadoProyectoEmprendimientoView> listarProyectosDeEmprendimiento() {
        String sql = "SELECT * FROM V_SINAPSIS_LISTADO_PE WHERE ESTADO_EMPRENDIMIENTO = 'APROBADO' ORDER BY FECHA_REGISTRO_PA DESC";
        Query query = entityManager.createNativeQuery(sql, ListadoProyectoEmprendimientoView.class);

        return query.getResultList();
    }

    @Override
    public List<PrimeraAtencionView> detallePrimeraAtencion(Integer idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_PRIMERA_ATENCION WHERE PROYECTO_EMPRENDIMIENTO_ID = ?";
        Query query = entityManager.createNativeQuery(sql, PrimeraAtencionView.class);

        query.setParameter(1, idProyectoEmprendimiento);

        return query.getResultList();
    }

    @Override
    public List<ListadoProyectoEmprendimientoView> listarPrimerasAtencionesPendientes() {
        String sql = "SELECT * FROM V_SINAPSIS_LISTADO_PE WHERE ESTADO_EMPRENDIMIENTO = 'PENDIENTE' ORDER BY FECHA_REGISTRO_PA DESC";
        Query query = entityManager.createNativeQuery(sql, ListadoProyectoEmprendimientoView.class);

        return query.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception {
        ProyectoEmprendimiento proyectoEmprendimiento = proyectoEmprendimientoDAO.find(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());

        if (proyectoEmprendimiento != null) {
            EtapaRutaEmprendimiento etapaRutaEmprendimiento = new EtapaRutaEmprendimiento();
            etapaRutaEmprendimiento.setProyectoEmprendimiento(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());
            etapaRutaEmprendimiento.setEtapaEmprendimiento(rutaPrimeraAtencionDTO.getIdEtapaRuta());
            etapaRutaEmprendimiento.setEstadoRuta(T_SINAPSIS_RUT_EMPRENDIMIENTO_DEFAULT_ESTADO);
            etapaRutaEmprendimiento.setFechaCreacion(new Date());
            etapaRutaEmprendimiento.setFechaModificacion(new Date());
            etapaRutaEmprendimiento.setCreadoPor(rutaPrimeraAtencionDTO.getCreado_por());

            EtapaRutaEmprendimiento isRegistered = entityManager.merge(etapaRutaEmprendimiento);

            if (isRegistered != null) {
                proyectoEmprendimiento.setEstadoEmprendimiento(T_SINAPSIS_PROY_EMPRENDIMIENTO_ESTADO_APROBADO);

                boolean isUpdated = proyectoEmprendimientoDAO.updateProyecto(proyectoEmprendimiento);

                if (isUpdated) {
                    return true;
                } else {
                    throw new Exception("Problema al actualizar proyecto de emprendimiento");
                }

            } else {
                throw new Exception("Problema registrar la etapa de emprendimiento");
            }
        } else  {
            throw new Exception("No se encontro el proyecto de emprendimiento");
        }
    }

    @Override
    public RutaProyectoEmprendimiento obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_RUT_EMPRENDIMIENTO " +
                "WHERE ESTADO_RUTA = 'PENDIENTE' AND PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, RutaProyectoEmprendimiento.class);

        List<RutaProyectoEmprendimiento> resultados = query.getResultList();

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

        List<ActividadRuta> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<HerramientaRuta> obtenerHerramientasEtapa() {
        String sql = "SELECT * FROM T_SINAPSIS_HERRAMIENTAS";
        Query query = entityManager.createNativeQuery(sql, HerramientaRuta.class);

        return query.getResultList();
    }

    @Override
    public List<HerramientaRuta> obtenerHerramientasEtapaById(Long idEtapa) {
        String sql = "SELECT TH.* FROM T_SINAPSIS_HERRAMIENTAS TH \n" +
                "    JOIN T_SINAPSIS_SUB_ACT_RUTA TSAR ON TH.SUB_ACTIVIDADES_RUTAS_ID = TSAR.ID\n" +
                "    JOIN T_SINAPSIS_ACTIVIDADES_RUTA TAR ON TSAR.ACTIVIDADES_RUTAS_ID = TAR.ID \n" +
                "    WHERE ETAPAS_RUTAS_ID = " + idEtapa;
        Query query = entityManager.createNativeQuery(sql, HerramientaRuta.class);

        List<HerramientaRuta> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<SubActividadesEmprendedorView> obtenerSubActividadesEmprendedor(Long idProyectoEmprendimiento, Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_ACT_EMPRENDEDOR \n" +
                "    WHERE PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento + "\n" +
                "    AND RUTAS_EMPRENDIMIENTOS_ID = " + idRutaEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, SubActividadesEmprendedorView.class);

        List<SubActividadesEmprendedorView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<ActividadesEmprendedorView> obtenerActividadesEmprendedor(Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_ACT_RUTA_EMP \n" +
                "    WHERE RUTAS_EMPRENDIMIENTOS_ID = " + idRutaEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, ActividadesEmprendedorView.class);

        List<ActividadesEmprendedorView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, String tipoBusqueda) {
        String sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                "WHERE ESTADO_ENTREGA = '" + tipoBusqueda + "' " +
                "AND PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);

        List<TareasProyectoEmprendimientoView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_TAREAS_PROYECTO_EMP " +
                "WHERE ESTADO_ENTREGA NOT IN ('PENDIENTE', 'ENTREGADA') " +
                "AND PROYECTOS_EMPRENDIMIENTOS_ID = " + idProyectoEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, TareasProyectoEmprendimientoView.class);

        List<TareasProyectoEmprendimientoView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }



    @Override
    public List<ConsultoriasView> obtenerConsultoria(Long idConsultoria) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_CONSULTORIA = " + idConsultoria + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        List<ConsultoriasView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaNormal(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_SUB_ACT_RUTA IS NULL " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        List<ConsultoriasView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaEspecializada(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_SUB_ACT_RUTA IS NOT NULL " +
                "AND ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        List<ConsultoriasView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<ConsultoriasView> obtenerHistoricoConsultoria(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        List<ConsultoriasView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaEmprendedor(Long idEmprendedor) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE EMPRENDEDORES_ID = " + idEmprendedor + " " +
                "AND FECHA_CONSULTORIA >= SYSDATE ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        List<ConsultoriasView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaMentor(Long idMentor) {
        String sql = "SELECT * FROM V_SINAPSIS_CONSULTORIAS " +
                "WHERE ID_MENTOR = " + idMentor + " " +
                "AND FECHA_CONSULTORIA >= SYSDATE ORDER BY FECHA_CONSULTORIA DESC";

        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        List<ConsultoriasView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<EmprendedoresView> obtenerEmprendedores() {
        String sql = "SELECT * FROM V_SINAPSIS_EMPRENDEDORES " +
                "ORDER BY NOMBRES DESC, APELLIDOS DESC";

        Query query = entityManager.createNativeQuery(sql, EmprendedoresView.class);

        List<EmprendedoresView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }
}
