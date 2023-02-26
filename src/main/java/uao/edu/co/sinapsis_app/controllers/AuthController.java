package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uao.edu.co.sinapsis_app.beans.AuthUser;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.beans.SignUpExterno;
import uao.edu.co.sinapsis_app.beans.SignUpIntegration;
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
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 400){
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
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 400){
                return new ResponseEntity<>(response, null, HttpStatus.NOT_FOUND);
            }else if (response.getCode() == 409){
                return new ResponseEntity<>(response, null, HttpStatus.CONFLICT);
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
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }else if (response.getCode() == 409){
                return new ResponseEntity<>(response, null, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
