package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IMentoresService;

import java.util.List;

@Service
public class MentoresService implements IMentoresService {
    @Autowired
    private IMentoresDAO mentoresDAO;

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento) {
        return mentoresDAO.obtenerMentoresPorRutaEmprendimiento(idRutaEmprendimiento);
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return mentoresDAO.obtenerMentoresPorProyectoEmprendimiento(idProyectoEmprendimiento);
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return mentoresDAO.obtenerMentorPrincipalPorProyectoEmprendimiento(idProyectoEmprendimiento);
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return mentoresDAO.obtenerHistoricoMentoresPorProyectoEmprendimiento(idProyectoEmprendimiento);
    }
}
