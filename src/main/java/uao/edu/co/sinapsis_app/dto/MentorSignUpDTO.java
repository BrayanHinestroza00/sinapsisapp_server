package uao.edu.co.sinapsis_app.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MentorSignUpDTO implements Serializable {
    // Datos del usuario
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String correoPersonal;
    private String password;
    private String username;
    private String telefonoContacto;
    private String estado;
    private Boolean aceptoTratamientoDatos;
    private String fotoUrl;
    private Long idTipoDocumento;
    private Integer estadoCuenta;

    // Datos de mentor
    private String facultadMentor;
    private String dependenciaMentor;
    private String cargoMentor;
    private Long etapaRuta;
}
