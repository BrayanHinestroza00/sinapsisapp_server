package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.ReporteConsultoriasMentorExportExcel;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;

public interface IReportesService {
    public ReporteConsultoriasMentorExportExcel generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters);

    ResponseDTO consultarIndicadoresGestion();

    ResponseDTO consultarIndicadoresFormacion();
}
