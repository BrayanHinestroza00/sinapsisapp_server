package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Usuario {
    @Id
    private long id;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String username;
    private String telefono;
    private String celular;
    private boolean aceptoTratamientoDatos;
    private String estado;
    private String fotoUrl;
    private int activo;
    private int tipoDocumento;

    public Usuario() {
    }
    public Usuario(Object o) {
        Object[] obj = (Object[]) o;
        if (!(String.valueOf(obj[0]).equalsIgnoreCase("null"))) {
            this.id = Long.parseLong(String.valueOf(obj[0]));
        }
        this.numeroDocumento = String.valueOf(obj[1]);
        this.nombres = String.valueOf(obj[2]);
        this.apellidos = String.valueOf(obj[3]);
        this.correo = String.valueOf(obj[4]);
        this.password = String.valueOf(obj[5]);
        this.username = String.valueOf(obj[6]);
        this.telefono = String.valueOf(obj[7]);
        this.celular = String.valueOf(obj[8]);
        if (!(String.valueOf(obj[9]).equalsIgnoreCase("null"))) {
            this.aceptoTratamientoDatos = Boolean.parseBoolean(String.valueOf(obj[9]));
        }
        this.estado = String.valueOf(obj[10]);
        this.fotoUrl = String.valueOf(obj[11]);
        if (!(String.valueOf(obj[12]).equalsIgnoreCase("null"))) {
            this.activo = Integer.parseInt(String.valueOf(obj[12]));
        }
        if (!(String.valueOf(obj[13]).equalsIgnoreCase("null"))) {
            this.tipoDocumento = Integer.parseInt(String.valueOf(obj[13]));
        }
    }

    public Usuario(long id, String numeroDocumento, String nombres, String apellidos, String correo, String password, String username, String telefono, String celular, boolean aceptoTratamientoDatos, String estado, String fotoUrl, int activo, int tipoDocumento) {
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.password = password;
        this.username = username;
        this.telefono = telefono;
        this.celular = celular;
        this.aceptoTratamientoDatos = aceptoTratamientoDatos;
        this.estado = estado;
        this.fotoUrl = fotoUrl;
        this.activo = activo;
        this.tipoDocumento = tipoDocumento;
    }

    public String getFinalUsername(){
        if (username != null) {
            return username;
        }else {
            return correo.split("@")[0];
        }
    }
}
