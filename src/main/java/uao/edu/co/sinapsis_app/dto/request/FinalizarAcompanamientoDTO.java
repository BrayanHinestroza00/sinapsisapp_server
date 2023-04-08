package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FinalizarAcompanamientoDTO {
    @NotNull
    private Long idRutaProyectoEmprendimiento;
    @NotNull
    private Long idMentorCrea;
    @NotNull
    private String observaciones;
}
