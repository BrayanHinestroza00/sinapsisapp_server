package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.beans.AuthUser;
import uao.edu.co.sinapsis_app.beans.ResponseDTO;

public interface IAuthService {
    public ResponseDTO login(AuthUser authUser);
}
