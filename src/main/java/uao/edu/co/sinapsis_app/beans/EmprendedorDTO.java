package uao.edu.co.sinapsis_app.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EmprendedorDTO implements Serializable {
    private long idEmprendedor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo 'Fecha de Nacimiento' no puede estar vacio")
    private String fechaNacimiento;
    @NotNull(message = "El campo 'Genero' no puede estar vacio")
    private String genero;
    private String telefono;
    private String celular;
    @NotNull(message = "El campo 'Municipio' no puede estar vacio")
    private long municipioId;
    @NotNull(message = "El campo 'Direccion' no puede estar vacio")
    private String direccion;
    @NotNull(message = "El campo 'Vinculo Con U' no puede estar vacio")
    private String vinculoConU;
    private String codigoEstudiantil;
    private String tipoEstudiante;
    private long programaAcademicoId;
    private int modTrabajoGrado;
    private String cursosEmprendimiento;
    private long profesionEgresadoId;
    private String tipoEstudianteEgresado;
    private String cargoColaborador;
    private String dependenciaColaborador;
    private MultipartFile fotoPerfil;
    private String fotoPerfilURL;
}
