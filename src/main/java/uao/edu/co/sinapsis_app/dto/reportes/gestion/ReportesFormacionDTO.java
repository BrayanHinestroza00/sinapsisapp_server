package uao.edu.co.sinapsis_app.dto.reportes.gestion;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXEtapa;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXTipoContacto;

import java.util.List;

@Data
public class ReportesFormacionDTO {
    private List<NroEmprendedoresXEtapa> nroEmprendedoresXEtapa;
    private List<NroEmprendedoresXTipoContacto> nroEmprendedoresXTipoContacto;
    private List<NroEmprendedoresXPrograma> nroEmprendedoresXPrograma;
    private List<NroEmprendedoresXFacultad> nroEmprendedoresXFacultad;
}
