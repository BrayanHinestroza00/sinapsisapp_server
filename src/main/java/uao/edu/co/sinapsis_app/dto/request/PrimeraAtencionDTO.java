package uao.edu.co.sinapsis_app.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import uao.edu.co.sinapsis_app.dto.RedSocialDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class PrimeraAtencionDTO implements Serializable {
    private long idEmprendedor;
    // PASO 1 - Información Básica del Usuario
    // Datos de usuario
    private String celular;
    @JsonAlias("files")
    private MultipartFile fotoPerfil;
    private Long fotoPerfilURL;
    private String correoPersonal;

    // Datos del emprendedor
    @NotNull(message = "El campo 'Genero' no puede estar vació")
    private String genero;
    @NotNull(message = "El campo 'Dirección' no puede estar vació")
    private String direccion;
    @NotNull(message = "El campo 'Fecha de Nacimiento' no puede estar vació")
    private String fechaNacimiento;

    @NotNull(message = "El campo 'Departamento' no puede estar vació")
    private Long departamento;
    @NotNull(message = "El campo 'Municipio' no puede estar vació")
    private Long municipio;

    @NotNull(message = "El campo 'Vinculo con Universidad' no puede estar vació")
    private Integer vinculoConU;
    private String codigoEstudiantil;
    private String tipoEstudiante;
    private Long programaAcademico;
    private String cualOtroProgramaAcademico;
    private Integer modTrabajoGrado;
    private List<String> cursosEmprendimiento; // códigoAsignaturas separadas por ','
    private String cargoColaborador;
    private String dependenciaColaborador;

    // Datos del emprendimiento
    @NotNull(message = "El campo 'Nombre Emprendimiento' no puede estar vació")
    private String nombreEmprendimiento;
    private String nombreEmpresa;
    private String sectorEmprendimiento;
    private String sitioWeb;
    @NotNull(message = "El campo 'Esta Constituida' no puede estar vació")
    private String estaConstituida;
    private String fechaConstitucion;
    private String nitEmpresa;
    private String razonSocialEmpresa;
    private MultipartFile logoEmpresa;
    private Long logoEmpresaURL;
    @NotNull(message = "El campo 'Descripción Producto' no puede estar vació")
    private String descripcionProducto;
    @NotNull(message = "El campo 'Materias Primas' no puede estar vació")
    private String materiasPrimas;
    @NotNull(message = "El campo 'Descripción Clientes' no puede estar vació")
    private String descripcionClientes;
    private String enfoqueSocial;
    private String necesidadesIdentificadas;
    private RedSocialDTO[] redesSociales;

    // Datos de Primera atención
    @NotNull(message = "El campo 'Nombre Producto' no puede estar vació")
    private String nombreProducto;
    private Double promedioVentas;
    private String evidenciaProducto;
    @NotNull(message = "El campo 'Obtención Materias Primas' no puede estar vació")
    private String obtencionMateriasPrimas;
    @NotNull(message = "El campo 'Equipo Trabajo' no puede estar vació")
    private String equipoTrabajo;
    private String cualEquipoTrabajo;
    private String dedicacion;
    private Date desdeFechaEjecucion;
    private Integer horasSemanales;
    private String motivacion;
    private String descubrioSinapsis;
    private String cualOtroDescubrioSinapsis;
    @NotNull(message = "El campo 'Autodiagnostico' no puede estar vació")
    private MultipartFile fileAutodiagnostico;
    private Long fileAutodiagnosticoURL;

    public void setRedesSociales(String redesSociales) {
        Gson gson = new Gson();

        RedSocialDTO[] redes = gson.fromJson(redesSociales, RedSocialDTO[].class);

        this.redesSociales = redes;
    }
}
