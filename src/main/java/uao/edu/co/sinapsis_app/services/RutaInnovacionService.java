package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IRutaInnovacionService;

import java.util.List;

@Service
public class RutaInnovacionService implements IRutaInnovacionService {
    @Autowired
    IRutaInnovacionDAO rutaInnovacionDAO;

    @Override
    public PrimeraAtencionView detallePrimeraAtencionPendiente(Integer idProyectoEmprendimiento) {
        List<PrimeraAtencionView> respuesta = rutaInnovacionDAO.detallePrimeraAtencionPendiente(idProyectoEmprendimiento);

        if (respuesta.size() > 0) {
            return respuesta.get(0);
        }

        return null;
    }

    @Override
    public List<SolicitudesProyectoEmprendimientoView> listarPrimerasAtencionesPendientes() {
        return rutaInnovacionDAO.listarPrimerasAtencionesPendientes();
    }
}
