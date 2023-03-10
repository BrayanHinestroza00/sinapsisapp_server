package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;

import java.util.List;

public interface IRutaInnovacionDAO {
    List<PrimeraAtencionView> detallePrimeraAtencionPendiente(Integer idProyectoEmprendimiento);
    List<SolicitudesProyectoEmprendimientoView> listarPrimerasAtencionesPendientes();

    boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception;
}
