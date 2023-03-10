package uao.edu.co.sinapsis_app.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class PrimeraAtencionDTO implements Serializable {
    private long idEmprendedor;
    // PASO 1 - Información Básica del Usuario
    // Datos de usuario
    private String telefonoContacto;
    @JsonAlias("files")
    private MultipartFile fotoPerfil;
    private String fotoPerfilURL;
    private String correoPersonal;

    // Datos del emprendedor
    @NotNull(message = "El campo 'Genero' no puede estar vacio")
    private String genero;
    @NotNull(message = "El campo 'Direccion' no puede estar vacio")
    private String direccion;
    @NotNull(message = "El campo 'Fecha de Nacimiento' no puede estar vacio")
    private String fechaNacimiento;

    @NotNull(message = "El campo 'Departamento' no puede estar vacio")
    private Long departamento;
    @NotNull(message = "El campo 'Municipio' no puede estar vacio")
    private Long municipio;

    @NotNull(message = "El campo 'Tipo Contacto' no puede estar vacio")
    private Integer tipoContacto;
    private String codigoEstudiantil;
    private String nivelAcademico;
    private Long programaAcademico;
    private String otroProgramaAcademico;
    private Integer modalidadTrabajoGrado;
    private List<String> cursosEmprendimiento; // idAsignaturas separadas por ','
    private String cargoColaborador;
    private String dependenciaColaborador;

    // Datos del emprendimiento
    @NotNull(message = "El campo 'Nombre Emprendimiento' no puede estar vacio")
    private String nombreEmprendimiento;
    private String nombreEmpresa;
    private String sectorEmprendimiento;
    private String sitioWebEmpresa;
    @NotNull(message = "El campo 'Esta Constituida' no puede estar vacio")
    private String estaConstituida;
    private String fechaConstitucion;
    private String nitEmpresa;
    private String razonSocialEmpresa;
    private MultipartFile logoEmpresa;
    private String logoEmpresaURL;
    @NotNull(message = "El campo 'Descripcion Producto' no puede estar vacio")
    private String descripcionProducto;
    @NotNull(message = "El campo 'Naterias Primas' no puede estar vacio")
    private String materiasPrimas;
    @NotNull(message = "El campo 'Descripcion Clientes' no puede estar vacio")
    private String descripcionClientes;
    private String enfoqueSocial;
    private String necesidadIdentificada;

    // Datos de Primera atencion
    @NotNull(message = "El campo 'Nombre Producto' no puede estar vacio")
    private String nombreProducto;
    private Double promedioVentas;
    private String evidenciasProducto;
    @NotNull(message = "El campo 'Obtencion Materias Primas' no puede estar vacio")
    private String obtencionMateriasPrimas;
    @NotNull(message = "El campo 'Equipo Trabajo' no puede estar vacio")
    private String equipoTrabajo;
    private String cualEquipoTrabajo;
    private String dedicacion;
    private Date desdeFechaEjecucion;
    private Integer horasSemanales;
    private String motivacion;
    private String descubrioSinapsis;
    @NotNull(message = "El campo 'Autodiagnostico' no puede estar vacio")
    private MultipartFile fileAutodiagnostico;
    private String fileAutodiagnosticoURL;
}
