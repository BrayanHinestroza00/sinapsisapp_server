package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import java.util.HashMap;
import java.util.List;

public interface IMentoresDAO {
    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<AsesoramientosView> obtenerEmprendedoresPorMentor(EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO);

    List<MentoresView> obtenerMentores(MentoresAdmFilterDTO mentoresAdmFilterDTO);

    List<MentoresView> obtenerMentoresPorId(Long idMentor);

    List<MentoresView> obtenerMentoresPorEtapaRutaInnovacion(Long idEtapaRutaInnovacion);

    HashMap<String, Object> finalizarAcompanamiento(Long idRutaProyectoEmprendimiento, Long idMentorCrea, String observaciones) throws Exception;

    List<Emprendimiento> obtenerEmprendimientos(String idMentor);

    List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento);

    ProyectoEmprendimiento obtenerProyectoEmprendimiento(Long idProyectoEmprendimiento);

    void almacenarProyectoEmprendimiento(ProyectoEmprendimiento proyectoEmprendimiento);
}
