package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "T_SINAPSIS_USUARIOS")
public class Usuario {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucional;
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonal;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "ACEPTO_TRATAMIENTO_DATOS")
    private Boolean aceptoTratamientoDatos;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FOTO_URL")
    private String fotoUrl;
    @Column(name = "ESTADO_CUENTA")
    private Integer estadoCuenta;
    @Column(name = "TIPOS_DOCUMENTO_ID")
    private Long tipoDocumento;
}
