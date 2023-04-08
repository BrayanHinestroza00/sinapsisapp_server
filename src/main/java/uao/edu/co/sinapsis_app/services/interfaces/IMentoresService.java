package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.HorarioMentorDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.FinalizarAcompanamientoDTO;
import uao.edu.co.sinapsis_app.dto.request.HorarioMentorRequestDTO;
import uao.edu.co.sinapsis_app.model.Mentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;

import java.util.List;

public interface IMentoresService {
    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<AsesoramientosView> obtenerEmprendedoresPorMentor(EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO);

    HorarioMentorDTO obtenerHorarioMentor(Long idMentor);

    List<Mentor> obtenerMentores();

    List<Mentor> obtenerMentoresPorId(Long idMentor);

    List<Mentor> obtenerMentoresPorEtapaRutaInnovacion(Long idEtapaRutaInnovacion);

    boolean actualizarHorarioMentor(HorarioMentorRequestDTO horarioMentorDTO) throws Exception;

    boolean finalizarAcompanamiento(FinalizarAcompanamientoDTO finalizarAcompanamientoDTO) throws Exception;
}
