package uao.edu.co.sinapsis_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class CalificarTareaDTO {
    @NotNull(message = "El campo 'ID Tarea' no puede estar vacio")
    private Long idTarea;
    private String comentariosEntrega;
    private String calificacionEntrega;
    private Integer calificacionCuantitativaEntrega;

    public CalificarTareaDTO(Long idTarea, String comentariosEntrega, int calificacionEntrega) {
        this.idTarea = idTarea;
        this.comentariosEntrega = comentariosEntrega;


        this.calificacionEntrega = calificacionEntrega >= 3 ? "A" : "R";
        this.calificacionCuantitativaEntrega = calificacionEntrega;
    }
}
