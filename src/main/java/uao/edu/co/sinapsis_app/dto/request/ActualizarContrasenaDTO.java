package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

@Data
public class ActualizarContrasenaDTO {
    private Long idUsuario;
    private String oldPassword;
    private String newPassword;
}
