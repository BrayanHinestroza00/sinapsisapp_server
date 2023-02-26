package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RutaInnovacionDAO implements IRutaInnovacionDAO {
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
}
