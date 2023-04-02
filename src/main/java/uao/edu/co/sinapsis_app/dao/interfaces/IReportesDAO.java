package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;

import java.util.List;

public interface IReportesDAO {
    List<ConsultoriasView> generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters);
}
