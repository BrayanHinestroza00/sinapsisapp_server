package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CalificarTareaDTO {
    @NotNull(message = "El campo 'ID Tarea' no puede estar vacio")
    private Long idTarea;
    private String comentariosEntrega;
    private String calificacionEntrega;
}
