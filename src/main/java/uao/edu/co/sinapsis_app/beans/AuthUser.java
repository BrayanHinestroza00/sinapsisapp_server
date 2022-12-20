package uao.edu.co.sinapsis_app.beans;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AuthUser implements Serializable {
    @NotNull(message = "El tipo de documento es obligatorio")
    private int tipoDocumento;
    @NotNull(message = "El número de documento es obligatorio")
    private String numeroDocumento;
    @NotNull(message = "La contraseña es obligatoria")
    private String password;
}
