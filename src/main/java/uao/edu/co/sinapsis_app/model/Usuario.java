package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_SINAPSIS_USUARIOS")
public class Usuario {
    @Id
//    @SequenceGenerator(name = "SEC_T_SINAPSIS_USUARIOS", sequenceName = "SEC_T_SINAPSIS_USUARIOS", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_USUARIOS")
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

//
//    public Usuario(Object o) {
//        Object[] obj = (Object[]) o;
//        if (!(String.valueOf(obj[0]).equalsIgnoreCase("null"))) {
//            this.id = Long.parseLong(String.valueOf(obj[0]));
//        }
//        this.numeroDocumento = String.valueOf(obj[1]);
//        this.nombres = String.valueOf(obj[2]);
//        this.apellidos = String.valueOf(obj[3]);
//        this.correo = String.valueOf(obj[4]);
//        this.password = String.valueOf(obj[5]);
//        this.username = String.valueOf(obj[6]);
//        this.telefono = String.valueOf(obj[7]);
//        if (!(String.valueOf(obj[9]).equalsIgnoreCase("null"))) {
//            this.aceptoTratamientoDatos = Boolean.parseBoolean(String.valueOf(obj[9]));
//        }
//        this.estado = String.valueOf(obj[10]);
//        this.fotoUrl = String.valueOf(obj[11]);
//        if (!(String.valueOf(obj[12]).equalsIgnoreCase("null"))) {
//            this.activo = Integereger.parseInteger(String.valueOf(obj[12]));
//        }
//        if (!(String.valueOf(obj[13]).equalsIgnoreCase("null"))) {
//            this.tipoDocumento = Integereger.parseInteger(String.valueOf(obj[13]));
//        }
//    }
//
//    public Usuario(Long id, String numeroDocumento, String nombres, String apellidos, String correo, String password, String username, String telefono, String celular, boolean aceptoTratamientoDatos, String estado, String fotoUrl, Integer activo, Integer tipoDocumento) {
//        this.id = id;
//        this.numeroDocumento = numeroDocumento;
//        this.nombres = nombres;
//        this.apellidos = apellidos;
//        this.correo = correo;
//        this.password = password;
//        this.username = username;
//        this.telefono = telefono;
//        this.aceptoTratamientoDatos = aceptoTratamientoDatos;
//        this.estado = estado;
//        this.fotoUrl = fotoUrl;
//        this.activo = activo;
//        this.tipoDocumento = tipoDocumento;
//    }
//
//    public String getFinalUsername(){
//        if (username != null) {
//            return username;
//        }else {
//            return correo.split("@")[0];
//        }
//    }
}
