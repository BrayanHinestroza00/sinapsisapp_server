package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IStorageDAO;
import uao.edu.co.sinapsis_app.model.Archivo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StorageDAO implements IStorageDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Archivo store(Archivo file) {
        return entityManager.merge(file);
    }

    @Override
    public Archivo downloadDB(Long id) {
        return entityManager.find(Archivo.class, id);
    }
}
