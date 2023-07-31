package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorSignUpDTO;
import uao.edu.co.sinapsis_app.dto.MentorSignUpDTO;
import uao.edu.co.sinapsis_app.model.IntegrationTable;
import uao.edu.co.sinapsis_app.model.ProgramaAcademico;
import uao.edu.co.sinapsis_app.model.Usuario;

public interface IAuthDAO {
    public Usuario buscarUsuarioPorTipoDocumentoYNumeroDocumento(long tipoDocumento, String numeroDocumento);
    public Usuario buscarUsuario(int tipoDocumento, String numeroDocumento, String usuario);
    Usuario buscarUsuarioByCorreo(String correo);
    Usuario buscarUsuarioById(Long idUsuario);

    IntegrationTable buscarIntegracionPorUsuarioYTipoDocumentoYNumeroDocumento(String usuario, int tipoDocumento, String numeroDocumento);

    boolean registrarEmprendedor(EmprendedorSignUpDTO emprendedor) throws Exception;
    boolean registrarMentor(MentorSignUpDTO mentorSignUpDTO) throws Exception;

    boolean actualizarContrasena(Usuario usuarioActualizado);

    boolean restablecerContraseña(Long idUsuario);

    boolean desactivarUsuario(Long idUsuario);

    void updateLastLogin(Usuario usuario);

    ProgramaAcademico buscarProgramaAcademicoPorAcronimo(String acronimoProgramaAcademico);
}
