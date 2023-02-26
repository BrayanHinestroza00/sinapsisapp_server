package uao.edu.co.sinapsis_app.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Entity
@Table(name = "V_SINAPSIS_PRIMERA_ATENCION")
@Data
public class PrimeraAtencionView {
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
    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FOTO_URL")
    private String fotoUrl;
    @Column(name = "ESTADO_CUENTA")
    private Integer estadoCuenta;

    /*------*/
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ACRONIMO")
    private String acortado;

    /*------*/
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
    private Long programaAcademico;
    @Column(name = "TIPO_CONTACTO")
    private Integer tipoContacto;
    @Column(name = "NIVEL_ACADEMICO")
    private String nivelAcademico;
    @Column(name = "MODALIDAD_TRABAJO_GRADO")
    private Integer modalidadTrabajoGrado;
    @Column(name = "DEPENDENCIA_COLABORADOR")
    private String dependencia;
    @Column(name = "CARGO_COLABORADOR")
    private String cargo;
    @Column(name = "ULTIMO_INGRESO")
    private Date ultimoIngreso;
    @Column(name = "MUNICIPIOS_ID")
    private Long municipio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "PRIMERA_VEZ")
    private Integer primeraVez;
    @Column(name = "OTRO_PROGRAMA_ACADEMICO")
    private String otroProgramaAcademico;

    /*------*/
    @Column(name = "PROYECTO_EMPRENDIMIENTO_ID")
    private Long id;
    @Column(name = "ESTADO_EMPRENDIMIENTO")
    private String estadoEmprendimiento;
    @Column(name = "EMPRENDEDORES_ID")
    private Long emprendedor;
    @Column(name = "EMPRENDIMIENTOS_ID")
    private Long emprendimiento;
    @Column(name = "PRIMERA_ATENCION_ID")
    private Long primeraAtencion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;

    /*------*/
    @Column(name = "NOMBRE_EMPRENDIMIENTO")
    private String nombreEmprendimiento;
    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;
    @Column(name = "SECTOR_EMPRENDIMIENTO")
    private String sectorEmprendimiento;
    @Column(name = "SITIO_WEB")
    private String sitioWeb;
    @Column(name = "ESTA_CONSTITUIDA")
    private String estaConstituida;
    @Column(name = "FECHA_CONSTITUCION")
    private Date fechaConstitucion;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "URL_LOGO_EMPRESA")
    private String urlLogoEmpresa;
    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;
    @Column(name = "MATERIAS_PRIMAS")
    private String materiasPrimas;
    @Column(name = "DESCRIPCION_CLIENTES")
    private String descripcionClientes;
    @Column(name = "ENFOQUE_SOCIAL")
    private String enfoqueSocial;
    @Column(name = "URL_FILE_AUTODIAGNOSTICO")
    private String urlFileAutodiagnostico;
    @Column(name = "NECESIDADES_IDENTIFICADAS")
    private String necesidadesIdentificadas;

    /*------*/
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "PROMEDIO_VENTAS")
    private Double promedioVentas;
    @Column(name = "EVIDENCIA_PRODUCTO")
    private String evidenciasProducto;
    @Column(name = "OBTENCION_MATERIAS_PRIMAS")
    private String obtencionMateriasPrimas;
    @Column(name = "EQUIPO_TRABAJO")
    private String equipoTrabajo;
    @Column(name = "CUAL_EQUIPO_TRABAJO")
    private String cualEquipoTrabajo;
    @Column(name = "DEDICACION")
    private String dedicacion;
    @Column(name = "FECHA_EJECUCION")
    private Date desdeFechaEjecucion;
    @Column(name = "HORAS_SEMANALES")
    private Integer horasSemanales;
    @Column(name = "MOTIVACION")
    private String motivacion;
    @Column(name = "DESCUBRIO_SINAPSIS")
    private String descubrioSinapsis;
    @Column(name = "AUTODIAGNOSTICO")
    private String fileAutodiagnosticoURL;
    @Column(name = "FECHA_REGISTRO_PA")
    private Date fechaRegistroPA;
}
