package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IProyectoEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.EtapaRutaEmprendimiento;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;

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
    public List<PrimeraAtencionView> detallePrimeraAtencionPendiente(Integer idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_PRIMERA_ATENCION WHERE PROYECTO_EMPRENDIMIENTO_ID = ?";
        Query query = entityManager.createNativeQuery(sql, PrimeraAtencionView.class);

        query.setParameter(1, idProyectoEmprendimiento);

        return query.getResultList();
    }

    @Override
    public List<SolicitudesProyectoEmprendimientoView> listarPrimerasAtencionesPendientes() {
        String sql = "SELECT * FROM V_SINAPSIS_SOLICITUDES_PA ORDER BY FECHA_REGISTRO_PA DESC";
        Query query = entityManager.createNativeQuery(sql, SolicitudesProyectoEmprendimientoView.class);

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
}
