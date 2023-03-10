package uao.edu.co.sinapsis_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Data
public class EmprendedorUpdateDTO implements Serializable {
    private Long idEmprendedor;
//    @NotNull(message = "El campo 'Genero' no puede estar vacio")
    private String genero;
//    @NotNull(message = "El campo 'Direccion' no puede estar vacio")
    private String direccion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
//    @NotNull(message = "El campo 'Fecha de Nacimiento' no puede estar vacio")
    private String fechaNacimiento;
//    @NotNull(message = "El campo 'Municipio' no puede estar vacio")
    private Long municipio;
//    @NotNull(message = "El campo 'Tipo de Contacto' no puede estar vacio")
    private Integer tipoContacto;
    private String codigoEstudiantil;
    private Long programaAcademico;
    private String nivelAcademico;
    private Integer modalidadTrabajoGrado;
    private String dependenciaColaborador;
    private String cargoColaborador;
    private Integer primeraVez;
    private String otroProgramaAcademico;
    private List<String> cursosEmprendimiento;

    //Datos de usuario
    private String correoPersonal;
    private String telefonoContacto;
    private MultipartFile fotoPerfil;
    private String fotoPerfilURL;
}
