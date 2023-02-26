package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;

import java.util.List;

public interface IRutaInnovacionService {
    PrimeraAtencionView detallePrimeraAtencionPendiente(Integer idProyectoEmprendimiento);
    List<SolicitudesProyectoEmprendimientoView> listarPrimerasAtencionesPendientes();

}
