package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.beans.AuthUser;
import uao.edu.co.sinapsis_app.beans.SignUpExterno;
import uao.edu.co.sinapsis_app.beans.SignUpIntegration;
import uao.edu.co.sinapsis_app.beans.SignUpIntegrationMentor;
import uao.edu.co.sinapsis_app.dto.request.ActualizarContrasenaDTO;
import uao.edu.co.sinapsis_app.dto.request.UpdateUsuarioDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.services.interfaces.IAuthService;

@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8",  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> login(@RequestBody AuthUser authUser){
        try {
            ResponseDTO response = authService.login(authUser);
            if (response.getCode() == 200){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 400){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/SignUp/Integration", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8",  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> signUpIntegration(@RequestBody SignUpIntegration signUpUser) {
        try {
            ResponseDTO response = authService.signUpIntegration(signUpUser);
            if (response.getCode() == 200){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 400){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 409){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/SignUp/Externo", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8",  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> signUpExterno(@RequestBody SignUpExterno signUpUser) {
        try {
            ResponseDTO response = authService.signUpExterno(signUpUser);
            if (response.getCode() == 200){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 409){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/SignUp/Mentor", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8",  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> signUpMentor(@RequestBody SignUpIntegrationMentor signUpMentor) {
        try {
            ResponseDTO response = authService.signUpMentor(signUpMentor);
            if (response.getCode() == 200){
                response.setCode(0);
                return new ResponseEntity<>(response,  HttpStatus.OK);
            }else if (response.getCode() == 400){
                response.setCode(0);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else if (response.getCode() == 409){
                response.setCode(0);
                return new ResponseEntity<>(response,  HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/actualizar_contrasena", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> actualizarContrasena(@RequestBody ActualizarContrasenaDTO actualizarContrasenaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            response = authService.actualizarContrasena(actualizarContrasenaDTO);
            if (response.getCode() == 200){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 409){
                response.setCode(0);
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/restablecer_password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> restablecerContraseña(@RequestBody UpdateUsuarioDTO updateUsuarioDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado = authService.restablecerContraseña(updateUsuarioDTO.getIdUsuario());

            if (esActualizado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(0);
                response.setMessage("No se pudo restablecer la contraseña");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/desactivar_usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> desactivarUsuario(@RequestBody UpdateUsuarioDTO updateUsuarioDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado = authService.desactivarUsuario(updateUsuarioDTO.getIdUsuario());

            if (esActualizado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(0);
                response.setMessage("No se pudo desactivar la cuenta");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
