package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmprendedoresAsignadosFilterDTO {
    @NotNull
    private Long idMentor;
    private String numeroDocumento;
    private String nombreEmprendedor;
    @NotNull
    private Integer estadoAsesoramiento;
    private String nombreEmprendimiento;
}
