package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.beans.*;
import uao.edu.co.sinapsis_app.dao.interfaces.IAuthDAO;
import uao.edu.co.sinapsis_app.model.*;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;
import uao.edu.co.sinapsis_app.services.interfaces.IAuthService;

import java.util.List;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IAppService appService;
    @Autowired
    private IAuthDAO authDAO;

    @Override
    public ResponseDTO login(AuthUser authUser) {
        ResponseDTO response = new ResponseDTO();
        Usuario usuario = authDAO.findByTipoDocumentoAndNumeroDocumento(
                authUser.getTipoDocumento(),
                authUser.getNumeroDocumento()
        );

        if (usuario == null){
            response.setCode(400);
            response.setMessage("EL DOCUMENTO INGRESADO, NO SE ENCUENTRA REGISTRADO");
            return response;
        }

        if (!(usuario.getPassword().equals(authUser.getPassword()))){
            response.setCode(400);
            response.setMessage("CONTRASENA INCORRECTA");
            return response;
        }

        if (!(usuario.getActivo() == 1)){
            response.setCode(400);
            response.setMessage("SU CUENTA SE ENCUENTRA DESACTIVADA");
            return response;
        }

        List<UsuarioRol> listaRoles = appService.getRolesByUser(usuario.getId());
        int[] roles = new int[listaRoles.size()];

        for (int i = 0; i < listaRoles.size(); i++){
            roles[i] = listaRoles.get(i).getRolId();
        }

        AuthenthicatedUser authenthicatedUser = new AuthenthicatedUser();
        authenthicatedUser.setId(usuario.getId());
        authenthicatedUser.setRoles(roles);
        authenthicatedUser.setUsername(usuario.getFinalUsername());

        response.setCode(200);
        response.setMessage("OK");
        response.setResponse(authenthicatedUser);
        return response;
    }

    @Override
    public ResponseDTO signUpIntegration(SignUpIntegration signUpUser) throws Exception {
        ResponseDTO response = new ResponseDTO();
        Usuario usuarioRegistrado = authDAO.findByTipoDocumentoAndNumeroDocumento(
                signUpUser.getTipoDocumento(),
                signUpUser.getNumeroDocumento()
        );

        if (usuarioRegistrado != null) {
            response.setCode(409);
            response.setMessage("EL DOCUMENTO DE IDENTIDAD YA SE ENCUENTRA REGISTRADO");
            return response;
        }

        IntegrationTable usuario = authDAO.findByUserNameInITB(
                signUpUser.getUsuario()
        );

        if (usuario == null){
            usuario = authDAO.findByDocumentoInITB(signUpUser.getTipoDocumento(), signUpUser.getNumeroDocumento());
            if (usuario == null){
                response.setCode(400);
                response.setMessage("EL USUARIO Y/O DOCUMENTO NO SE ENCUENTRA REGISTRADO COMO PARTE DE LA UAO");
                return response;
            }
        }

        Emprendedor emprendedor  = new Emprendedor();
        emprendedor.setNumeroDocumento(usuario.getNumeroDocumento());
        emprendedor.setNombres(usuario.getNombres());
        emprendedor.setApellidos(usuario.getApellidos());
        emprendedor.setCorreo(usuario.getCorreo());
        emprendedor.setPassword(signUpUser.getPassword());
        emprendedor.setUsername(usuario.getUsername());
        emprendedor.setCelular(usuario.getCelular());
        emprendedor.setAceptoTratamientoDatos(true);
        emprendedor.setTipoDocumento(usuario.getTipoDocumento());
        emprendedor.setFechaNacimiento(usuario.getFechaNacimiento());
        emprendedor.setGenero(usuario.getGenero());
        emprendedor.setDireccion(usuario.getDireccion());
        emprendedor.setVinculoConU(usuario.getVinculoConU());
        emprendedor.setCodigoEstudiantil(usuario.getCodigoEstudiantil());
        emprendedor.setTipoEstudiante(usuario.getTipoEstudiante());
        emprendedor.setModTrabajoGrado(usuario.getModTrabajoGrado());
        emprendedor.setCursosEmprendimiento(usuario.getCursosEmprendimiento());
        emprendedor.setCargo(usuario.getCargo());
        emprendedor.setDependencia(usuario.getDependencia());
        emprendedor.setTipoEstudianteEgresado(usuario.getTipoEstudianteEgresado());
        emprendedor.setProgramaAcademicoId(usuario.getProgramaAcademico());
        emprendedor.setProfesionEgresadoId(usuario.getProfesionEgresado());

        boolean registrado = authDAO.registrarEmprendedor(emprendedor);

        if (registrado) {
            response.setCode(200);
            response.setMessage("OK");
            return response;
        } else {
            response.setCode(500);
            response.setMessage("Error desconocido");
            return response;
        }
    }

    @Override
    public ResponseDTO signUpExterno(SignUpExterno signUpUser) throws Exception {
        ResponseDTO response = new ResponseDTO();
        Usuario usuarioRegistrado = authDAO.findByTipoDocumentoAndNumeroDocumento(
                signUpUser.getTipoDocumento(),
                signUpUser.getNumeroDocumento()
        );

        if (usuarioRegistrado != null) {
            response.setCode(409);
            response.setMessage("EL DOCUMENTO DE IDENTIDAD YA SE ENCUENTRA REGISTRADO");
            return response;
        }

        Emprendedor emprendedor  = new Emprendedor();
        emprendedor.setNombres(signUpUser.getNombres());
        emprendedor.setApellidos(signUpUser.getApellidos());
        emprendedor.setTipoDocumento(signUpUser.getTipoDocumento());
        emprendedor.setNumeroDocumento(signUpUser.getNumeroDocumento());
        emprendedor.setCorreo(signUpUser.getCorreo());
        emprendedor.setPassword(signUpUser.getPassword());
        emprendedor.setUsername(signUpUser.getUsername());
        emprendedor.setAceptoTratamientoDatos(true);

        boolean registrado = authDAO.registrarEmprendedor(emprendedor);

        if (registrado) {
            response.setCode(200);
            response.setMessage("OK");
            return response;
        } else {
            response.setCode(500);
            response.setMessage("Error desconocido");
            return response;
        }
    }
}
