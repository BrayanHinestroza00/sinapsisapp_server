package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.CrearTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.CalificarTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.EntregaTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPAFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPEFilterDTO;
import uao.edu.co.sinapsis_app.model.ActividadRuta;
import uao.edu.co.sinapsis_app.model.HerramientaRuta;
import uao.edu.co.sinapsis_app.model.view.ActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.ListadoProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SubActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.TareasProyectoEmprendimientoView;

import java.util.List;

public interface IRutaInnovacionService {
    List<ListadoProyectoEmprendimientoView> listarProyectosDeEmprendimiento(SolicitudesPEFilterDTO solicitudesPEFilterDTO);
    PrimeraAtencionView detallePrimeraAtencion(Integer idProyectoEmprendimiento);
    List<ListadoProyectoEmprendimientoView> listarPrimerasAtencionesPendientes(SolicitudesPAFilterDTO solicitudesPAFilterDTO);

    boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception;

    AsesoramientosView obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento);

    List<ActividadRuta> obtenerActividadesEtapa();

    List<ActividadRuta> obtenerActividadesEtapaById(Long idEtapa);

    List<HerramientaRuta> obtenerHerramientasEtapa();

    List<HerramientaRuta> obtenerHerramientasEtapaById(Long idEtapa);

    List<SubActividadesEmprendedorView> obtenerSubActividadesEmprendedor(Long idProyectoEmprendimiento, Long idRutaEmprendimiento);

    List<ActividadesEmprendedorView> obtenerActividadesEmprendedor(Long idRutaEmprendimiento);

    List<TareasProyectoEmprendimientoView> obtenerTareasPendientes(Long idProyectoEmprendimiento, String tipoBusqueda);

    List<ConsultoriasView> obtenerConsultoria(Long idTarea);

    List<ConsultoriasView> obtenerConsultoriaNormal(Long idProyectoEmprendimiento);

    List<ConsultoriasView> obtenerConsultoriaEspecializada(Long idProyectoEmprendimiento);

    List<ConsultoriasView> obtenerHistoricoConsultoria(Long idProyectoEmprendimiento);

    List<ConsultoriasView> obtenerConsultoriaProgramadaEmprendedor(Long idEmprendedor);
    List<ConsultoriasView> obtenerConsultoriaProgramadaMentor(Long idMentor);

    List<EmprendedoresView> obtenerEmprendedores(EmprendedoresAdmFilterDTO emprendedoresAdmFilterDTO);

    List<MentoresView> obtenerMentores();

    boolean registrarTareaEmprendedor(CrearTareaDTO crearTareaDTO) throws Exception;

    boolean registrarEntregaTareaEmprendedor(EntregaTareaDTO entregaTareaDTO) throws Exception;

    boolean registrarCalificacionTareaEmprendedor(CalificarTareaDTO calificarTareaDTO) throws Exception;
}
