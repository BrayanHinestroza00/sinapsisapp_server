package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IPrimeraAtencionDAO;
import uao.edu.co.sinapsis_app.model.PrimeraAtencion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PrimeraAtencionDAO implements IPrimeraAtencionDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public PrimeraAtencion registrarPrimeraAtencion(PrimeraAtencion primeraAtencion) throws Exception {
        PrimeraAtencion isRegistered = entityManager.merge(primeraAtencion);

        if (isRegistered == null) {
            throw new Exception("Problema al almacenar la primera atencion");
        }

        return isRegistered;
    }
}
