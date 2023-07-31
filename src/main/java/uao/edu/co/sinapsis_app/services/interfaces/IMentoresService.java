package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.FinalizarAcompanamientoDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import java.util.List;

public interface IMentoresService {
    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<AsesoramientosView> obtenerEmprendedoresPorMentor(EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO);

    List<MentoresView> obtenerMentores(MentoresAdmFilterDTO mentoresAdmFilterDTO);

    List<MentoresView> obtenerMentoresPorId(Long idMentor);

    List<MentoresView> obtenerMentoresPorEtapaRutaInnovacion(Long idEtapaRutaInnovacion);

    boolean finalizarAcompanamiento(FinalizarAcompanamientoDTO finalizarAcompanamientoDTO) throws Exception;

    List<Emprendimiento> obtenerEmprendimientos(String idMentor);

    List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento);
}
