package uao.edu.co.sinapsis_app.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EmprendedorSignUpDTO implements Serializable {
    // Datos del usuario
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String password;
    private String username;
    private String telefonoContacto;
    private String estado;
    private Boolean aceptoTratamientoDatos;
    private Long fotoUrl;
    private Long idTipoDocumento;
    private Integer estadoCuenta;

    // Datos de emprendedor
    private String genero;
    private String direccion;
    private Date fechaNacimiento;
    private String codigoEstudiantil;
    private Long idProgramaAcademico;
    private Integer tipoContacto;
    private String nivelAcademico;
    private Integer modalidadTrabajoGrado;
    private String dependencia;
    private String cargo;
    private Date ultimoIngreso;
    private Long idMunicipio;
    private Integer primeraVez;
    private String correoPersonal;
    private String otroProgramaAcademico;

    public String getNombres() {
        if (nombres == null){
            return null;
        }
        return nombres.toUpperCase();
    }

    public String getApellidos() {
        if (apellidos == null){
            return null;
        }
        return apellidos.toUpperCase();
    }

    public String getUsername() {
        if (username == null){
            return null;
        }
        return username.toUpperCase();
    }

    public String getGenero() {
        if (genero == null){
            return null;
        }
        return genero.toUpperCase();
    }

    public String getDireccion() {
        if (direccion == null){
            return null;
        }
        return direccion.toUpperCase();
    }

    public String getNivelAcademico() {
        if (nivelAcademico == null){
            return null;
        }
        return nivelAcademico.toUpperCase();
    }

    public String getDependencia() {
        if (dependencia == null){
            return null;
        }
        return dependencia.toUpperCase();
    }

    public String getCargo() {
        if (cargo == null){
            return null;
        }
        return cargo.toUpperCase();
    }

    public String getCorreoInstitucional() {
        if (correoInstitucional == null){
            return null;
        }
        return correoInstitucional.toUpperCase();
    }

    public String getEstado() {
        if (estado == null){
            return null;
        }
        return estado.toUpperCase();
    }

    public String getCorreoPersonal() {
        if (correoPersonal == null){
            return null;
        }
        return correoPersonal.toUpperCase();
    }

    public String getOtroProgramaAcademico() {
        if (otroProgramaAcademico == null){
            return null;
        }
        return otroProgramaAcademico.toUpperCase();
    }
}
