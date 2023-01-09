package uao.edu.co.sinapsis_app.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignUpExterno implements Serializable {
    private String nombres;
    private String apellidos;
    private int tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private String usuario;
    private String password;

    public String getUsername() {
        return correo.split("@")[0];
    }
}
