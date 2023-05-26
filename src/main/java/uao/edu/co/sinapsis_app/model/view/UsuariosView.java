package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "V_SINAPSIS_USUARIOS")
public class UsuariosView {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucional;
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonal;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "FOTO_URL")
    private String fotoUrl;
    @Column(name = "TIPOS_DOCUMENTO_ID")
    private Long tipoDocumentoId;
    @Column(name = "TIPOS_DOCUMENTO")
    private String tipoDocumento;
    @Column(name = "A_TIPO_DOCUMENTO")
    private String acronimoTipoDocumento;
    @Column(name = "ESTADO_CUENTA")
    private Integer estadoCuenta;

    // Datos compartidos
    @Column(name = "DEPENDENCIA_COLABORADOR")
    private String dependencia;
    @Column(name = "FACULTAD")
    private String facultad;
    @Column(name = "CARGO_COLABORADOR")
    private String cargo;

    //Datos de mentor
    @Column(name = "ETAPA_RUTA")
    private String etapaRuta;
    @Column(name = "ETAPAS_RUTA_ID")
    private String etapaRutaId;
}
