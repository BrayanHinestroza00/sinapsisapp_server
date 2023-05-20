package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.dto.CrearTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.CalificarTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.EntregaTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ProgramarConsultoriaDTO;
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
import uao.edu.co.sinapsis_app.services.interfaces.IRutaInnovacionService;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutaInnovacionService implements IRutaInnovacionService {
    @Autowired
    IRutaInnovacionDAO rutaInnovacionDAO;

    @Autowired
    IStorageService storageService;

    @Override
    public List<ListadoProyectoEmprendimientoView> listarProyectosDeEmprendimiento(SolicitudesPEFilterDTO solicitudesPEFilterDTO) {
        return rutaInnovacionDAO.listarProyectosDeEmprendimiento(solicitudesPEFilterDTO);
    }

    @Override
    public PrimeraAtencionView detallePrimeraAtencion(Integer idProyectoEmprendimiento) {
        List<PrimeraAtencionView> respuesta = rutaInnovacionDAO.detallePrimeraAtencion(idProyectoEmprendimiento);

        if (respuesta.size() > 0) {
            return respuesta.get(0);
        }

        return null;
    }

    @Override
    public List<ListadoProyectoEmprendimientoView> listarPrimerasAtencionesPendientes(SolicitudesPAFilterDTO solicitudesPAFilterDTO) {
        return rutaInnovacionDAO.listarPrimerasAtencionesPendientes(solicitudesPAFilterDTO);
    }

    @Override
    public boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception {

        return rutaInnovacionDAO.asignarRutaPrimeraAtencion(rutaPrimeraAtencionDTO);
    }

    @Override
    public AsesoramientosView obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return rutaInnovacionDAO.obtenerEtapaProyectoEmprendimiento(idProyectoEmprendimiento);
    }

    @Override
    public List<ActividadRuta> obtenerActividadesEtapa() {
        return rutaInnovacionDAO.obtenerActividadesEtapa();
    }

    @Override
    public List<ActividadRuta> obtenerActividadesEtapaById(Long idEtapa) {
        return rutaInnovacionDAO.obtenerActividadesEtapaById(idEtapa);
    }

    @Override
    public List<HerramientaRuta> obtenerHerramientasEtapa() {
        return rutaInnovacionDAO.obtenerHerramientasEtapa();
    }

    @Override
    public List<HerramientaRuta> obtenerHerramientasEtapaById(Long idEtapa) {
        return rutaInnovacionDAO.obtenerHerramientasEtapaById(idEtapa);
    }

    @Override
    public List<SubActividadesEmprendedorView> obtenerSubActividadesEmprendedor(Long idProyectoEmprendimiento, Long idRutaEmprendimiento) {
        return rutaInnovacionDAO.obtenerSubActividadesEmprendedor(idProyectoEmprendimiento, idRutaEmprendimiento);
    }

    @Override
    public List<ActividadesEmprendedorView> obtenerActividadesEmprendedor(Long idRutaEmprendimiento) {
        return rutaInnovacionDAO.obtenerActividadesEmprendedor(idRutaEmprendimiento);
    }

    @Override
    public List<TareasProyectoEmprendimientoView> obtenerTareasPendientes( Long idProyectoEmprendimiento, String tipoBusqueda) {
        List<TareasProyectoEmprendimientoView> resultados = new ArrayList<>();
        if (tipoBusqueda.equalsIgnoreCase("pendientes")){
            resultados = rutaInnovacionDAO.obtenerTareasPendientes(idProyectoEmprendimiento, "PENDIENTE");
        }

        if (tipoBusqueda.equalsIgnoreCase("entregadas")) {
            resultados = rutaInnovacionDAO.obtenerTareasPendientes(idProyectoEmprendimiento, "ENTREGADA");
        }

        if (tipoBusqueda.equalsIgnoreCase("historial")) {
            resultados = rutaInnovacionDAO.obtenerTareasPendientes(idProyectoEmprendimiento, true);
        }

        if (tipoBusqueda.equalsIgnoreCase("todas")) {
            resultados = rutaInnovacionDAO.obtenerTareasPendientes(idProyectoEmprendimiento, false);
        }

        return resultados;
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoria(Long idTarea) {
        return rutaInnovacionDAO.obtenerConsultoria(idTarea);
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaNormal(Long idProyectoEmprendimiento) {
        return rutaInnovacionDAO.obtenerConsultoriaNormal(idProyectoEmprendimiento);
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaEspecializada(Long idProyectoEmprendimiento) {
        return rutaInnovacionDAO.obtenerConsultoriaEspecializada(idProyectoEmprendimiento);
    }

    @Override
    public List<ConsultoriasView> obtenerHistoricoConsultoria(Long idProyectoEmprendimiento) {
        return rutaInnovacionDAO.obtenerHistoricoConsultoria(idProyectoEmprendimiento);
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaEmprendedor(Long idEmprendedor) {
        return rutaInnovacionDAO.obtenerConsultoriaProgramadaEmprendedor(idEmprendedor);
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoriaProgramadaMentor(Long idMentor) {
        return rutaInnovacionDAO.obtenerConsultoriaProgramadaMentor(idMentor);
    }

    @Override
    public boolean programarConsultoriaEmprendedor(ProgramarConsultoriaDTO programarConsultoriaDTO) throws Exception{
        return rutaInnovacionDAO.programarConsultoriaEmprendedor(programarConsultoriaDTO) ;
    }

    @Override
    public List<EmprendedoresView> obtenerEmprendedores(EmprendedoresAdmFilterDTO emprendedoresAdmFilterDTO) {
        return rutaInnovacionDAO.obtenerEmprendedores(emprendedoresAdmFilterDTO);
    }

    @Override
    public List<MentoresView> obtenerMentores() {
        return rutaInnovacionDAO.obtenerMentores();
    }

    @Override
    public boolean registrarTareaEmprendedor(CrearTareaDTO crearTareaDTO) throws Exception {
        if (crearTareaDTO.getFileTarea() != null) {
            String filePathFileTarea = storageService.store(crearTareaDTO.getFileTarea());
            crearTareaDTO.setFileTareaURL(filePathFileTarea);
        }

        return rutaInnovacionDAO.registrarTareaEmprendedor(crearTareaDTO);
    }

    @Override
    public boolean registrarEntregaTareaEmprendedor(EntregaTareaDTO entregaTareaDTO) throws Exception {
        if (entregaTareaDTO.getFileEntrega() != null) {
            String filePathFileEntrega = storageService.store(entregaTareaDTO.getFileEntrega());
            entregaTareaDTO.setFileEntregaURL(filePathFileEntrega);
        }

        return rutaInnovacionDAO.registrarEntregaTareaEmprendedor(entregaTareaDTO);
    }

    @Override
    public boolean registrarCalificacionTareaEmprendedor(CalificarTareaDTO calificarTareaDTO) throws Exception {
        return rutaInnovacionDAO.registrarCalificacionTareaEmprendedor(calificarTareaDTO);
    }
}
