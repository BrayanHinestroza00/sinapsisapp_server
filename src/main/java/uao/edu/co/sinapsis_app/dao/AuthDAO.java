package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IAuthDAO;
import uao.edu.co.sinapsis_app.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthDAO implements IAuthDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Usuario findByTipoDocumentoAndNumeroDocumento(int tipoDocumento, String numeroDocumento) {
        String sql = "SELECT * FROM USUARIOS WHERE tipoDocumento = '"+tipoDocumento+"' AND numeroDocumento = '"+numeroDocumento+"'";

        Query q = entityManager.createNativeQuery(sql);

        List<Object> result =  q.getResultList();

        if (result.size() > 0) {
            return new Usuario(result.get(0));
        }

        return null;
    }
}
