package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AsignarMentorDTO {
    @NotNull(message = "El campo 'Proyecto Emprendimiento' no puede estar vacio")
    private Long idProyectoEmprendimiento;
    @NotNull(message = "El campo 'Mentor principal' no puede estar vacio")
    private Long idMentorPrincipal;
    @NotNull(message = "El campo 'Ruta Proyecto Emprendimiento' no puede estar vacio")
    private Long idRutaProyectoEmprendimiento;
}
