package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.beans.AuthUser;
import uao.edu.co.sinapsis_app.beans.ResponseDTO;
import uao.edu.co.sinapsis_app.beans.SignUpExterno;
import uao.edu.co.sinapsis_app.beans.SignUpIntegration;

public interface IAuthService {
    public ResponseDTO login(AuthUser authUser);

    ResponseDTO signUpIntegration(SignUpIntegration signUpUser) throws Exception;

    ResponseDTO signUpExterno(SignUpExterno signUpUser) throws Exception;
}
