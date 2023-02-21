package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.beans.*;
import uao.edu.co.sinapsis_app.dao.interfaces.IAuthDAO;
import uao.edu.co.sinapsis_app.dto.EmprendedorSignUpDTO;
import uao.edu.co.sinapsis_app.dto.ResponseDTO;
import uao.edu.co.sinapsis_app.model.*;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;
import uao.edu.co.sinapsis_app.services.interfaces.IAuthService;

import java.util.List;

import static uao.edu.co.sinapsis_app.util.AppUtil.stringToDateFormatter;
import static uao.edu.co.sinapsis_app.util.Constants.*;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IAppService appService;
    @Autowired
    private IAuthDAO authDAO;

    @Override
    public ResponseDTO login(AuthUser authUser) {
        ResponseDTO response = new ResponseDTO();
        Usuario usuario = authDAO.findUsuarioByTipoDocumentoAndNumeroDocumento(
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

        if (!(usuario.getEstadoCuenta() == 1)){
            response.setCode(400);
            response.setMessage("SU CUENTA SE ENCUENTRA DESACTIVADA");
            return response;
        }

        List<UsuarioRol> usuariosRol = appService.getRolesByUser(usuario.getId());
        int[] roles = new int[usuariosRol.size()];

        for (int i = 0; i < usuariosRol.size(); i++){
            roles[i] = usuariosRol.get(i).getId().getRolId();
        }

        AuthenthicatedUser authenthicatedUser = new AuthenthicatedUser();
        authenthicatedUser.setId(usuario.getId());
        authenthicatedUser.setRoles(roles);
        authenthicatedUser.setUsername(usuario.getUsername());

        response.setCode(200);
        response.setMessage("OK");
        response.setResponse(authenthicatedUser);
        return response;
    }

    @Override
    public ResponseDTO signUpIntegration(SignUpIntegration signUpUser) throws Exception {
        ResponseDTO response = new ResponseDTO();

        // Busca si el usuario/documento hace parte de la Comunidad UAO
        IntegrationTable usuarioIntegration = authDAO.findUsuarioByUserNameInITB(
                signUpUser.getUsuario()
        );

        if (usuarioIntegration == null) {
            usuarioIntegration = authDAO.findUsuarioByDocumentoInITB(signUpUser.getTipoDocumento(), signUpUser.getNumeroDocumento());
            if (usuarioIntegration == null){
                response.setCode(400);
                response.setMessage("El usuario y/o documento NO se encuentra registrado como parte de la COMUNIDAD UAO");
                return response;
            }
        }

        if ((usuarioIntegration.getTipoDocumento() != signUpUser.getTipoDocumento()) || !(usuarioIntegration.getNumeroDocumento().equalsIgnoreCase(signUpUser.getNumeroDocumento()))) {
            response.setCode(400);
            response.setMessage("El tipo de documento y/o numero documento ingresado no coincide con el usuario");
            return response;
        }

        // Busca si el usuario se encuentra registrado dentro de Sinapsis APP
        Usuario usuarioRegistrado = authDAO.buscarUsuario(
                signUpUser.getTipoDocumento(),
                signUpUser.getNumeroDocumento(),
                signUpUser.getUsuario()
        );

        if (usuarioRegistrado != null) {
            if (!usuarioRegistrado.getUsername().equalsIgnoreCase(usuarioIntegration.getUsername())) {
                response.setCode(409);
                response.setMessage("El usuario y/o documento YA se encuentra asociado a un usuario DIFERENTE");
            }else {
                response.setCode(409);
                response.setMessage("El usuario y/o documento YA se encuentra REGISTRADO");
            }

            return response;
        }

        EmprendedorSignUpDTO emprendedor  = new EmprendedorSignUpDTO();

        // Datos de usuario
        emprendedor.setNumeroDocumento(usuarioIntegration.getNumeroDocumento());
        emprendedor.setNombres(usuarioIntegration.getNombres());
        emprendedor.setApellidos(usuarioIntegration.getApellidos());
        emprendedor.setCorreoInstitucional(usuarioIntegration.getCorreoInstitucional());
        emprendedor.setCorreoPersonal(usuarioIntegration.getCorreoPersonal());
        emprendedor.setPassword(signUpUser.getPassword());
        emprendedor.setUsername(usuarioIntegration.getUsername());
        emprendedor.setTelefonoContacto(usuarioIntegration.getTelefono_contacto());
        emprendedor.setEstado(T_SINAPSIS_USUARIOS_DEFAULT_ESTADO);
        emprendedor.setAceptoTratamientoDatos(T_SINAPSIS_USUARIOS_DEFAULT_TTO_DATOS_PERSONALES_INTEGRATION);
        emprendedor.setIdTipoDocumento(usuarioIntegration.getTipoDocumento());
        emprendedor.setEstadoCuenta(T_SINAPSIS_USUARIOS_DEFAULT_ESTADO_CUENTA);

        // Datos de emprendedor
        emprendedor.setGenero(usuarioIntegration.getGenero());
        emprendedor.setDireccion(usuarioIntegration.getDireccion());
        /**
         * REALIZAR TRANSFORMACION DE MUNICIPIO
         */
//        emprendedor.setIdMunicipio(1L);
        emprendedor.setFechaNacimiento(stringToDateFormatter(usuarioIntegration.getFechaNacimiento(), APP_INTEGRATION_DATE_INPUT_FORMAT, APP_DATE_OUT_FORMAT));
        emprendedor.setTipoContacto(usuarioIntegration.getTipoContacto());

        // Datos de Estudiante
        if (usuarioIntegration.getTipoContacto().equalsIgnoreCase(TIPO_CONTACTO_ESTUDIANTE)) {
            emprendedor.setCodigoEstudiantil(usuarioIntegration.getCodigoEstudiantil());
            emprendedor.setNivelAcademico(usuarioIntegration.getNivelAcademico());
            emprendedor.setModalidadTrabajoGrado(usuarioIntegration.getModalidadTrabajoGrado());
            /**
             * Realizar manejo de cursos de emprendimiento
             */
            // ....

            /**
             * Realizar Transformacion de Programa academico
             */
            //emprendedor.setIdProgramaAcademico(usuarioIntegration.getProgramaAcademico());
        }

        // Datos de Egresado
        if (usuarioIntegration.getTipoContacto().equalsIgnoreCase(TIPO_CONTACTO_EGRESADO)) {
            emprendedor.setCodigoEstudiantil(usuarioIntegration.getCodigoEstudiantil());
            emprendedor.setNivelAcademico(usuarioIntegration.getNivelAcademicoEgresado());
            /**
             * Realizar Transformacion de Programa academico
             */
            //emprendedor.setIdProgramaAcademico(usuarioIntegration.getProgramaAcademicoEgresado());
        }

        if (usuarioIntegration.getTipoContacto().equalsIgnoreCase(TIPO_CONTACTO_COLABORADOR)) {
            emprendedor.setCargo(usuarioIntegration.getCargo());
            emprendedor.setDependencia(usuarioIntegration.getDependencia());
        }

        emprendedor.setPrimeraVez(T_SINAPSIS_EMPRENDEDORES_DEFAULT_PRIMERA_VEZ);

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
        if (!signUpUser.getAceptoTratamientoDatos()) {
            response.setCode(409);
            response.setMessage("Debes aceptar el tratamiento de datos personales");
            return response;
        }

        Usuario usuarioRegistrado = authDAO.findUsuarioByTipoDocumentoAndNumeroDocumento(
                signUpUser.getTipoDocumento(),
                signUpUser.getNumeroDocumento()
        );

        if (usuarioRegistrado != null) {
            response.setCode(409);
            response.setMessage("El documento YA se encuentra REGISTRADO");
            return response;
        }

//        Usuario correoRegistrado = authDAO.findUsuarioByCorreo(
//                signUpUser.getCorreo());
//
//        if (correoRegistrado != null) {
//            response.setCode(409);
//            response.setMessage("El correo electronico YA se encuentra REGISTRADO");
//            return response;
//        }


        EmprendedorSignUpDTO emprendedor  = new EmprendedorSignUpDTO();
        emprendedor.setNombres(signUpUser.getNombres());
        emprendedor.setApellidos(signUpUser.getApellidos());
        emprendedor.setIdTipoDocumento(signUpUser.getTipoDocumento());
        emprendedor.setNumeroDocumento(signUpUser.getNumeroDocumento());
        emprendedor.setCorreoPersonal(signUpUser.getCorreo());
        emprendedor.setPassword(signUpUser.getPassword());
        emprendedor.setUsername(signUpUser.getUsername());
        emprendedor.setEstado(T_SINAPSIS_USUARIOS_DEFAULT_ESTADO);
        emprendedor.setAceptoTratamientoDatos(signUpUser.getAceptoTratamientoDatos());
        emprendedor.setEstadoCuenta(T_SINAPSIS_USUARIOS_DEFAULT_ESTADO_CUENTA);
        emprendedor.setTipoContacto(T_SINAPSIS_EMPRENDEDORES_DEFAULT_TIPO_CONTACTO);
        emprendedor.setPrimeraVez(T_SINAPSIS_EMPRENDEDORES_DEFAULT_PRIMERA_VEZ);

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
