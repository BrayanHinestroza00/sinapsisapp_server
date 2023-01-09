package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.model.Emprendedor;
import uao.edu.co.sinapsis_app.model.IntegrationTable;
import uao.edu.co.sinapsis_app.model.Usuario;

public interface IAuthDAO {
    public Usuario findByTipoDocumentoAndNumeroDocumento(int tipoDocumento, String numeroDocumento);

    IntegrationTable findByUserNameInITB(String usuario);
    IntegrationTable findByDocumentoInITB(int tipoDocumento, String numeroDocumento);

    boolean registrarEmprendedor(Emprendedor emprendedor) throws Exception;
}
