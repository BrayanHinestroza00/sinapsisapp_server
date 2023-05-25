package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IReportesDAO;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReportesDAO implements IReportesDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ConsultoriasView> generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters) {
        String sql = "SELECT * \n" +
                "FROM V_SINAPSIS_CONSULTORIAS \n" +
                "WHERE ID_MENTOR =:id \n" +
                "    AND FECHA_CONSULTORIA >= TO_DATE('"+reportesFilters.getFechaInicio()+"', 'YYYY-MM-DD') \n" +
                "    AND FECHA_CONSULTORIA <= TO_DATE('"+reportesFilters.getFechaFin()+"', 'YYYY-MM-DD')";
        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        query.setParameter("id", reportesFilters.getIdMentor());
//        query.setParameter("desde", reportesFilters.getFechaInicio());
//        query.setParameter("hasta", reportesFilters.getFechaFin());

        return query.getResultList();
    }
}
