package uao.edu.co.sinapsis_app.dao.interfaces;


import uao.edu.co.sinapsis_app.model.Archivo;


public interface IStorageDAO {
    Archivo store(Archivo file);

    Archivo downloadDB(Long id);
}
