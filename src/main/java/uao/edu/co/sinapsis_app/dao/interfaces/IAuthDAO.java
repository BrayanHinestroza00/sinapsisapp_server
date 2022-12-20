package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.model.Usuario;

public interface IAuthDAO {
    public Usuario findByTipoDocumentoAndNumeroDocumento(int tipoDocumento, String numeroDocumento);
}
