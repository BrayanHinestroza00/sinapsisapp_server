package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.response.HorarioMentorResponseDTO;
import uao.edu.co.sinapsis_app.model.HorarioMentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;

import java.util.List;

public interface IMentoresService {
    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<AsesoramientosView> obtenerEmprendedoresPorMentor(Long idMentor);

    HorarioMentorResponseDTO obtenerHorarioMentor(Long idMentor);
}
