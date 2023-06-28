package uao.edu.co.sinapsis_app.dto.reportes.gestion;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.reportes.gestion.EstadoConsultoriaXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMentorXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMesXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXTipoXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedores;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresRutaXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresXModalidad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresXMunicipio;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresRutaXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimiento;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimientoXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimientoXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosXEmprendedor;

import java.util.List;

@Data
public class ReportesGestionDTO {
    private NroEmprendedores nroEmprendedores;
    private List<NroEmprendedoresRutaXPrograma> nroEmprendedoresRutaXPrograma;
    private List<NroEmprendedoresRutaXFacultad> nroEmprendedoresRutaXFacultad;
    private List<NroEmprendedoresXMunicipio> nroEmprendedoresXMunicipio;
    private List<NroEmprendedoresXModalidad> nroEmprendedoresXModalidad;
    private List<NroProyectosXEmprendedor> nroProyectosXEmprendedor;

    private NroProyectosEmprendimiento nroProyectosEmprendimiento;
    private List<NroProyectosEmprendimientoXPrograma> nroProyectosEmprendimientoXPrograma;
    private List<NroProyectosEmprendimientoXFacultad> nroProyectosEmprendimientoXFacultad;

    private List<NroConsultoriasXTipoXMes> nroConsultoriasXTipoXMes;
    private List<NroConsultoriasXMes> nroConsultoriasXMes;
    private List<NroConsultoriasXMesXPrograma> nroConsultoriasXMesXPrograma;
    private List<NroConsultoriasXMentorXMes> nroConsultoriasXMentorXMes;
    private List<EstadoConsultoriaXMes> estadoConsultoriaXMes;
}
