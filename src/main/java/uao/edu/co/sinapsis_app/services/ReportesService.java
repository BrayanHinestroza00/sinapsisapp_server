package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IReportesDAO;
import uao.edu.co.sinapsis_app.dto.ReporteConsultoriasMentorExportExcel;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.services.interfaces.IReportesService;

import java.util.List;

@Service
public class ReportesService implements IReportesService {
    @Autowired
    private IReportesDAO reportesDAO;

    @Override
    public ReporteConsultoriasMentorExportExcel generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters) {
        List<ConsultoriasView> data = reportesDAO.generarReporteConsultoriasPorMentor(reportesFilters);
        if (data.size() > 0) {
            return new ReporteConsultoriasMentorExportExcel(data);
        }
        return null;
    }
}
