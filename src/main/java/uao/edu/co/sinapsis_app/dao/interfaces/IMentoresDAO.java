package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.model.HorarioMentor;
import uao.edu.co.sinapsis_app.model.Mentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;

import java.util.List;

public interface IMentoresDAO {
    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<AsesoramientosView> obtenerEmprendedoresPorMentor(EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO);

    List<HorarioMentor> obtenerHorarioMentor(Long idMentor);

    List<Mentor> obtenerMentores();

    List<Mentor> obtenerMentoresPorId(Long idMentor);

    List<Mentor> obtenerMentoresPorEtapaRutaInnovacion(Long idEtapaRutaInnovacion);

    boolean actualizarHorarioMentor(Long idMentor, List<HorarioMentor> horarios) throws Exception;

    boolean finalizarAcompanamiento(Long idRutaProyectoEmprendimiento, Long idMentorCrea, String observaciones) throws Exception;
}
