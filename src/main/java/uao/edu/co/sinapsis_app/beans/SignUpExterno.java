package uao.edu.co.sinapsis_app.beans;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class SignUpExterno implements Serializable {
    private String nombres;
    private String apellidos;
    private long tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private String usuario;
    private String password;
    @Getter(AccessLevel.NONE)
    private boolean aceptoTratamientoDatos;

    public String getUsername() {
        return correo.split("@")[0]+"_ext";
    }

    public boolean getAceptoTratamientoDatos() {
        return aceptoTratamientoDatos;
    }

    public void setAceptoTratamientoDatos(boolean aceptoTratamientoDatos) {
        this.aceptoTratamientoDatos = aceptoTratamientoDatos;
    }
}
