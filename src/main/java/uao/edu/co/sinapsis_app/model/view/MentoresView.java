package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SINAPSIS_MENTORES")
@Data
public class MentoresView {
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
    @Column(name = "TIPOS_DOCUMENTO_ID")
    private Long tipoDocumentoId;
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;
    @Column(name = "A_TIPO_DOCUMENTO")
    private String acronimoTipoDocumento;
    @Column(name = "ESTADO_CUENTA")
    private Integer estadoCuenta;

    // Datos del Mentor
    @Column(name = "FACULTAD")
    private String facultadMentor;
    @Column(name = "CARGO")
    private String cargoMentor;
    @Column(name = "DEPENDENCIA")
    private String dependenciaMentor;
    @Column(name = "ETAPAS_RUTA_ID")
    private String etapaRutaId;
    @Column(name = "ETAPA_RUTA")
    private String etapaRuta;
}
