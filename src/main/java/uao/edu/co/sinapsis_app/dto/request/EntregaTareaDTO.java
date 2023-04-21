package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class EntregaTareaDTO {
    @NotNull(message = "El campo 'ID Tarea' no puede estar vacio")
    private Long idTarea;
    private String comentariosEntrega;
    private String fileEntregaURL;
    private MultipartFile fileEntrega;
}
