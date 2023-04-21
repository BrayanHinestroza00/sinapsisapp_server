package uao.edu.co.sinapsis_app.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Entity
@Data
@Table(name = "V_SINAPSIS_EMPRENDEDORES")
public class EmprendedoresView {
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

    // Datos de Emprendedor
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "DIRECCION_RESIDENCIA")
    private String direccion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;
    @Column(name = "CODIGO_ESTUDIANTIL")
    private String codigoEstudiantil;
    @Column(name = "PROGRAMA_ACADEMICO_ID")
    private Long programaAcademicoId;
    @Column(name = "PROGRAMA_ACADEMICO")
    private String programaAcademico;
    @Column(name = "PROGRAMA_ACADEMICO_CODIGO")
    private String programaAcademicoCodigo;
    @Column(name = "PROGRAMA_ACADEMICO_ACRONIMO")
    private String programaAcademicoAcronimo;
    @Column(name = "FACULTAD")
    private String facultad;
    @Column(name = "FACULTAD_ID")
    private String facultadId;
    @Column(name = "TIPO_CONTACTO")
    private String tipoContacto;
    @Column(name = "TIPO_CONTACTO_ID")
    private Integer tipoContactoId;
    @Column(name = "NIVEL_ACADEMICO")
    private String nivelAcademico;
    @Column(name = "MODALIDAD_TRABAJO_GRADO")
    private Long modalidadTrabajoGrado;
    @Column(name = "DEPENDENCIA_COLABORADOR")
    private String dependencia;
    @Column(name = "CARGO_COLABORADOR")
    private String cargo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "ULTIMO_INGRESO")
    private Date ultimoIngreso;
    @Column(name = "MUNICIPIOS_ID")
    private Long municipioId;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "DEPARTAMENTOS_ID")
    private Long departamentosId;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "PRIMERA_VEZ")
    private Integer primeraVez;

    @Transient
    List<AsignaturaEmprendedor> asignaturasEmprendedor;
}
