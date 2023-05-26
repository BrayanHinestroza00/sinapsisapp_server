package uao.edu.co.sinapsis_app.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UsuarioUpdateDTO implements Serializable {
    //Datos de usuario
    private Long idUsuario;
    private Integer tipoUsuario;
    private String correoPersonal;
    private String correoInstitucional;
    private String telefonoContacto;
    private MultipartFile fotoPerfil;
    private String fotoPerfilURL;

    //Datos compartidos (Mentor y Administrador)
    private String dependencia;
    private String cargo;
    private String facultad;
}
