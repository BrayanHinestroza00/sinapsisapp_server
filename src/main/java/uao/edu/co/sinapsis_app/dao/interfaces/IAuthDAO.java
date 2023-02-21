package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorSignUpDTO;
import uao.edu.co.sinapsis_app.model.IntegrationTable;
import uao.edu.co.sinapsis_app.model.Usuario;

public interface IAuthDAO {
    public Usuario findUsuarioByTipoDocumentoAndNumeroDocumento(long tipoDocumento, String numeroDocumento);
    public Usuario buscarUsuario(int tipoDocumento, String numeroDocumento, String usuario);

    IntegrationTable findUsuarioByUserNameInITB(String usuario);
    IntegrationTable findUsuarioByDocumentoInITB(int tipoDocumento, String numeroDocumento);

    boolean registrarEmprendedor(EmprendedorSignUpDTO emprendedor) throws Exception;
}
