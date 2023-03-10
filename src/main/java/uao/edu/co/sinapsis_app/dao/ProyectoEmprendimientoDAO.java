package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IProyectoEmprendimientoDAO;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
