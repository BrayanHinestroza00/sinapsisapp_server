package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

@Data
public class SolicitudesPAFilterDTO {
    private String numeroDocumento;
    private String nombreEmprendedor;
    private Long tiposDocumento;
    private String nombreEmprendimiento;
}
