package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

@Data
public class SolicitudesPEFilterDTO {
    private String numeroDocumento;
    private String nombreEmprendedor;
    private String estadosRuta;
    private Long etapasRuta;
    private Long mentorAsociado;
}
