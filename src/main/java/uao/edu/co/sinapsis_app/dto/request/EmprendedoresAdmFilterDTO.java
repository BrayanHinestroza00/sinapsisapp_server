package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

@Data
public class EmprendedoresAdmFilterDTO {
    private String numeroDocumento;
    private String nombreEmprendedor;
    private Long tiposContacto;
    private String estadoEnRuta;
    private String estadosCuenta;
}
