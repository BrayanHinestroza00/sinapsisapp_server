package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.model.HorarioMentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MentoresDAO implements IMentoresDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_RUTA_EMPRENDIMIENTO = " + idRutaEmprendimiento;

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        List<MentoresProyectoEmprendimientoView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "AND ESTADO_ASESORAMIENTO = 'FINALIZADA'";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        List<MentoresProyectoEmprendimientoView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "AND ESTADO_ASESORAMIENTO = 'EN CURSO'";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        List<MentoresProyectoEmprendimientoView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_MENTORES_PROYECTO " +
                "WHERE ID_PROYECTO_EMPRENDIMIENTO = " + idProyectoEmprendimiento + " " +
                "AND ESTADO_ASESORAMIENTO = 'FINALIZADA'";

        Query query = entityManager.createNativeQuery(sql, MentoresProyectoEmprendimientoView.class);

        List<MentoresProyectoEmprendimientoView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<AsesoramientosView> obtenerEmprendedoresPorMentor(Long idMentor) {
        String sql = "SELECT * FROM V_SINAPSIS_ASESORAMIENTOS " +
                "WHERE ID_MENTOR = " + idMentor;

        Query query = entityManager.createNativeQuery(sql, AsesoramientosView.class);

        List<AsesoramientosView> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }

    @Override
    public List<HorarioMentor> obtenerHorarioMentor(Long idMentor) {
        String sql = "SELECT * FROM T_SINAPSIS_HORARIOS_MENTOR WHERE " +
                "MENTORES_ID = "+ idMentor + " ORDER BY DIA";

        Query query = entityManager.createNativeQuery(sql, HorarioMentor.class);

        List<HorarioMentor> resultados = query.getResultList();

        if (resultados.size() > 0) {
            return resultados;
        }
        return null;
    }
}
