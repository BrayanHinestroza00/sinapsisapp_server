package uao.edu.co.sinapsis_app.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PrimeraAtencionDTO implements Serializable {
    // Datos del emprendedor
    private long idEmprendedor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo 'Fecha de Nacimiento' no puede estar vacio")
    private String fechaNacimiento;
    @NotNull(message = "El campo 'Genero' no puede estar vacio")
    private String genero;
    private String telefono;
    private String celular;
    @NotNull(message = "El campo 'Departamento' no puede estar vacio")
    private int departamento;
    @NotNull(message = "El campo 'Municipio' no puede estar vacio")
    private long municipio;
    @NotNull(message = "El campo 'Direccion' no puede estar vacio")
    private String direccion;
    @NotNull(message = "El campo 'Vinculo Con U' no puede estar vacio")
    private String vinculoConU;
    private String codigoEstudiantil;
    private String tipoEstudiante;
    private long programaAcademico;
    private int modTrabajoGrado;
    private String cursosEmprendimiento;
    private long profesionEgresado;
    private String tipoEstudianteEgresado;
    private String cargoColaborador;
    private String dependenciaColaborador;
    @JsonAlias("files")
    private MultipartFile fotoPerfil;
    private String fotoPerfilURL;

    // Datos del emprendimiento
    private long idEmprendimiento;
    @NotNull(message = "El campo 'Nombre Emprendimiento' no puede estar vacio")
    private String nombreEmprendimiento;
    private String nombreEmpresa;
    private String sectorEmprendimiento;
    private String sitioWebEmpresa;
    @NotNull(message = "El campo 'Esta Constituida' no puede estar vacio")
    private String estaConstituida;
    private String fechaConstitucion;
    private String fechaCreacionEmpresa;
    private String nitEmpresa;
    private String razonSocialEmpresa;
    @NotNull(message = "El campo 'Descripcion Producto' no puede estar vacio")
    private String descripcionProducto;
    @NotNull(message = "El campo 'Naterias Primas' no puede estar vacio")
    private String materiasPrimas;
    @NotNull(message = "El campo 'Cliente Emprendimiento' no puede estar vacio")
    private String clienteEmprendimiento;
    private String enfoqueSocial;
    private int tieneEquipoTrabajo;
    private String equipoTrabajo;
    private MultipartFile logoEmpresa;
    private String logoEmpresaURL;
    private MultipartFile fileAutodiagnostico;
    private String fileAutodiagnosticoURL;
}
