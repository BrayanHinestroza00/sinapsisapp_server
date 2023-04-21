package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.beans.AuthUser;
import uao.edu.co.sinapsis_app.beans.SignUpExterno;
import uao.edu.co.sinapsis_app.beans.SignUpIntegration;
import uao.edu.co.sinapsis_app.beans.SignUpIntegrationMentor;
import uao.edu.co.sinapsis_app.dto.request.ActualizarContrasenaDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;

public interface IAuthService {
    public ResponseDTO login(AuthUser authUser);

    ResponseDTO signUpIntegration(SignUpIntegration signUpUser) throws Exception;

    ResponseDTO signUpMentor(SignUpIntegrationMentor signUpMentor) throws Exception;

    ResponseDTO signUpExterno(SignUpExterno signUpUser) throws Exception;

    ResponseDTO actualizarContrasena(ActualizarContrasenaDTO actualizarContrasenaDTO);
}
