package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.ReporteConsultoriasMentorExportExcel;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;

public interface IReportesService {
    public ReporteConsultoriasMentorExportExcel generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters);
}
