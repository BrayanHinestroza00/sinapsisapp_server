package uao.edu.co.sinapsis_app.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CrearTareaDTO {
    @NotNull(message = "El campo 'Nombre Tarea' no puede estar vacio")
    private String nombreTarea;
    @NotNull(message = "El campo 'Descripcion' no puede estar vacio")
    private String descripcionTarea;
    @NotNull(message = "El campo 'Fecha Limite Entrega' no puede estar vacio")
    private String fechaEntrega;
    @NotNull(message = "El campo 'ID Proyecto' no puede estar vacio")
    private Long idProyectoEmprendimiento;
    @NotNull(message = "El campo 'Usuario Crea' no puede estar vacio")
    private Long usuarioCrea;
    private MultipartFile fileTarea;
    private Long fileTareaURL;
}
