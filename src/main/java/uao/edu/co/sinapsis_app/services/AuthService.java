package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.beans.AuthUser;
import uao.edu.co.sinapsis_app.beans.AuthenthicatedUser;
import uao.edu.co.sinapsis_app.beans.ResponseDTO;
import uao.edu.co.sinapsis_app.dao.interfaces.IAuthDAO;
import uao.edu.co.sinapsis_app.model.Rol;
import uao.edu.co.sinapsis_app.model.Usuario;
import uao.edu.co.sinapsis_app.model.UsuarioRol;
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
}
