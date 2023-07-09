package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IProyectoEmprendimientoDAO;
import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProyectoEmprendimientoDAO implements IProyectoEmprendimientoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProyectoEmprendimiento registrarProyectoEmprendimiento(ProyectoEmprendimiento proyectoEmprendimiento) throws Exception {
        ProyectoEmprendimiento isRegistered = entityManager.merge(proyectoEmprendimiento);

        if (isRegistered == null) {
            throw new Exception("Problema al almacenar el proyecto de emprendimiento");
        }

        return isRegistered;
    }

    @Override
    public ProyectoEmprendimiento find(Long idProyectoEmprendimiento) {
        return entityManager.find(ProyectoEmprendimiento.class, idProyectoEmprendimiento);
    }

    @Override
    public boolean updateProyecto(ProyectoEmprendimiento proyectoEmprendimiento) {
        ProyectoEmprendimiento emprendimiento = entityManager.merge(proyectoEmprendimiento);

        return emprendimiento != null;
    }

    @Override
    public Asesoramiento buscarAsesoramiento(Long idRutaProyecto, Long idMentorPrincipal) {
        String sql = "SELECT * FROM T_SINAPSIS_ASESORAMIENTO WHERE RUTA_EMPRENDIMIENTO_EMP_ID = ?1";

        Query query = entityManager.createNativeQuery(sql, Asesoramiento.class);
        query.setParameter(1, idRutaProyecto);

        List<Asesoramiento> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados.get(0);
        }

        return null;
    }
}
