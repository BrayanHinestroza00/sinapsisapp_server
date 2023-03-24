package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.ActividadRuta;
import uao.edu.co.sinapsis_app.model.HerramientaRuta;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.ActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.model.view.SubActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.TareasProyectoEmprendimientoView;

import java.util.List;

public interface IRutaInnovacionDAO {
    List<PrimeraAtencionView> detallePrimeraAtencionPendiente(Integer idProyectoEmprendimiento);
    List<SolicitudesProyectoEmprendimientoView> listarPrimerasAtencionesPendientes();

    boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception;

    RutaProyectoEmprendimiento obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<ActividadRuta> obtenerActividadesEtapa();

    List<ActividadRuta> obtenerActividadesEtapaById(Long idEtapa);

    List<HerramientaRuta> obtenerHerramientasEtapa();

    List<HerramientaRuta> obtenerHerramientasEtapaById(Long idEtapa);

    List<SubActividadesEmprendedorView> obtenerSubActividadesEmprendedor(Long idProyectoEmprendimiento, Long idRutaEmprendimiento);

    List<ActividadesEmprendedorView> obtenerActividadesEmprendedor(Long idRutaEmprendimiento);

    List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, String tipoBusqueda);

    List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento);

    List<ConsultoriasView> obtenerConsultoria(Long idTarea);

    List<ConsultoriasView> obtenerConsultoriaNormal(Long idProyectoEmprendimiento);

    List<ConsultoriasView> obtenerConsultoriaEspecializada(Long idProyectoEmprendimiento);

    List<ConsultoriasView> obtenerHistoricoConsultoria(Long idProyectoEmprendimiento);
}
