package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.dto.CrearTareaDTO;
import uao.edu.co.sinapsis_app.dto.EtapaRutaEmprendimientoDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarMentorDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.CalificarTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ConsultoriaDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.EntregaTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ProgramarConsultoriaDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPAFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPEFilterDTO;
import uao.edu.co.sinapsis_app.model.ActividadRuta;
import uao.edu.co.sinapsis_app.model.Consultoria;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.HerramientaRuta;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.Tarea;
import uao.edu.co.sinapsis_app.model.view.ActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.ListadoProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SubActividadesEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.TareasProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IEmailService;
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

    @Autowired
    IEmailService emailService;

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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean asignarRutaPrimeraAtencion(AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO) throws Exception {
        boolean  rutaAsignada = rutaInnovacionDAO.asignarRutaPrimeraAtencion(rutaPrimeraAtencionDTO);

        if (rutaAsignada) {
            AsesoramientosView asesoramientosView =
                    rutaInnovacionDAO.buscarAsesoramientoPorIdProyectoEmprendimiento(rutaPrimeraAtencionDTO.getIdProyectoEmprendimiento());

            String correoDestino = asesoramientosView.getCorreoInstitucional() != null
                                        ? asesoramientosView.getCorreoInstitucional() : asesoramientosView.getCorreoPersonal();

            EtapaRutaInnovacion etapaRutaInnovacion = rutaInnovacionDAO.buscarEtapaRutaInnovacion(rutaPrimeraAtencionDTO.getIdEtapaRuta());

            emailService.notificarAsignacionEtapaInicialRuta(correoDestino, etapaRutaInnovacion.getNombre());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean asignarMentor(AsignarMentorDTO asignarMentorDTO) throws Exception {
        boolean mentorAsignado = rutaInnovacionDAO.asignarMentor(asignarMentorDTO);

        if (mentorAsignado) {
            // Notificacion nuevo emprendedor asignado
            AsesoramientosView asesoramientosView =
                    rutaInnovacionDAO.buscarAsesoramientoPorIdRutaProyectoEmprendimiento(asignarMentorDTO.getIdRutaProyectoEmprendimiento());

            String nombreEmprendedor = asesoramientosView.getNombres() + " " + asesoramientosView.getApellidos();

            String correoEmprendedor = asesoramientosView.getCorreoInstitucional() != null
                    ? asesoramientosView.getCorreoInstitucional() : asesoramientosView.getCorreoPersonal();

            String nombreProyecto = asesoramientosView.getNombreEmprendimiento();

            EtapaRutaInnovacion etapaRutaInnovacion = rutaInnovacionDAO.buscarEtapaRutaInnovacion(asesoramientosView.getIdEtapa());

            // Notificacion nuevo mentor asignado
            MentoresProyectoEmprendimientoView asesoramientosMentorView =
                    rutaInnovacionDAO.buscarAsesoramientoMentorPorIdRutaProyectoEmprendimiento(asignarMentorDTO.getIdRutaProyectoEmprendimiento());

            String nombreMentor = asesoramientosMentorView.getNombresMentor() + " " + asesoramientosMentorView.getApellidosMentor();

            String correoMentor = asesoramientosMentorView.getCorreoInstitucionalMentor();

            String cargoMentor = asesoramientosMentorView.getCargoMentor();

            emailService.notificarAsignacionNuevoEmprendedor(correoMentor, nombreEmprendedor, correoEmprendedor, nombreProyecto, etapaRutaInnovacion.getNombre());

            emailService.notificarAsignacionNuevoMentor(correoEmprendedor, nombreMentor, correoMentor, cargoMentor, nombreProyecto, etapaRutaInnovacion.getNombre());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public EtapaRutaEmprendimientoDTO obtenerEtapaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        AsesoramientosView etapasProyecto = rutaInnovacionDAO.obtenerEtapaProyectoEmprendimiento(idProyectoEmprendimiento);

        List<RutaProyectoEmprendimiento> rutaProyectoEmprendimientos = rutaInnovacionDAO.obtenerRutaProyectoEmprendimiento(idProyectoEmprendimiento);

        EtapaRutaEmprendimientoDTO etapaRutaEmprendimientoDTO = new EtapaRutaEmprendimientoDTO();
        etapaRutaEmprendimientoDTO.setAsesoramientosView(etapasProyecto);
        etapaRutaEmprendimientoDTO.setRutaProyectoEmprendimientos(rutaProyectoEmprendimientos);

        return etapaRutaEmprendimientoDTO;
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
    public TareasProyectoEmprendimientoView obtenerTareasPorId(Long idTarea) {
        return rutaInnovacionDAO.obtenerTareasPorId(idTarea);
    }

    @Override
    public List<ConsultoriasView> obtenerConsultoria(Long idConsultoria) {
        return rutaInnovacionDAO.obtenerConsultoria(idConsultoria);
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
        Consultoria consultoria = rutaInnovacionDAO.programarConsultoriaEmprendedor(programarConsultoriaDTO) ;

        if (consultoria != null) {
            ConsultoriasView consultoriasView = rutaInnovacionDAO.obtenerConsultoria(consultoria.getIdConsultoria()).get(0);

            PrimeraAtencionView informacionEmprendimiento = rutaInnovacionDAO.detallePrimeraAtencion(Integer.parseInt(consultoriasView.getIdProyectoEmprendimiento())).get(0);

            String correoEmprendedor = consultoriasView.getCorreoInstitucionalEmprendedor() != null ?
                    consultoriasView.getCorreoInstitucionalEmprendedor() : consultoriasView.getCorreoPersonalEmprendedor();
            String correoMentor = consultoriasView.getCorreoInstitucionalMentor();

            String[] destinatarios = new String[] {correoEmprendedor, correoMentor};

            emailService.notificarProgramacionConsultoria(destinatarios, consultoriasView.getAsuntoConsultoria(),
                    consultoriasView.getFechaConsultoria(), consultoriasView.getHoraInicioConsultoria(), consultoriasView.getHoraFinConsultoria(),
                    consultoriasView.getNombreEmprendedor(), consultoriasView.getNombreMentor(), informacionEmprendimiento.getNombreEmprendimiento());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean iniciarConsultoriaEmprendedor(Long idConsultoria) throws Exception {
        return rutaInnovacionDAO.iniciarConsultoriaEmprendedor(idConsultoria) ;
    }

    @Override
    public boolean inasistenciaConsultoriaEmprendedor(ConsultoriaDTO consultoriaDTO) throws Exception {
        return rutaInnovacionDAO.inasistenciaConsultoriaEmprendedor(consultoriaDTO) ;
    }

    @Override
    public boolean terminarConsultoriaEmprendedor(ConsultoriaDTO consultoriaDTO) throws Exception {
        return rutaInnovacionDAO.terminarConsultoriaEmprendedor(consultoriaDTO) ;
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
            Long filePathFileTarea = storageService.storeDB(crearTareaDTO.getFileTarea());
            crearTareaDTO.setFileTareaURL(filePathFileTarea);
        }

        Tarea tareaRegistrada = rutaInnovacionDAO.registrarTareaEmprendedor(crearTareaDTO);

        if (tareaRegistrada != null) {
            TareasProyectoEmprendimientoView tarea = rutaInnovacionDAO.obtenerTareasPorId(tareaRegistrada.getIdTarea());

            String correoEmprendedor = tarea.getCorreoInstitucionalEmprendedor() != null
                    ? tarea.getCorreoInstitucionalEmprendedor() : tarea.getCorreoPersonalEmprendedor();

            String nombreCompletoRegistra = tarea.getNombresCrea() + " " + tarea.getApellidosCrea();

            emailService.notificarAsignacionTarea(correoEmprendedor, tarea.getTitulo(), tarea.getFechaLimiteEntrega(),
                    nombreCompletoRegistra, tarea.getNombreEmprendimiento());

            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean registrarEntregaTareaEmprendedor(EntregaTareaDTO entregaTareaDTO) throws Exception {
        if (entregaTareaDTO.getFileEntrega() != null) {
            Long filePathFileEntrega = storageService.storeDB(entregaTareaDTO.getFileEntrega());
            entregaTareaDTO.setFileEntregaURL(filePathFileEntrega);
        }

        boolean tareaEntregada = rutaInnovacionDAO.registrarEntregaTareaEmprendedor(entregaTareaDTO);
        if (tareaEntregada) {
            TareasProyectoEmprendimientoView tarea = rutaInnovacionDAO.obtenerTareasPorId(entregaTareaDTO.getIdTarea());

            String correoEmprendedor = tarea.getCorreoInstitucionalEmprendedor() != null
                    ? tarea.getCorreoInstitucionalEmprendedor() : tarea.getCorreoPersonalEmprendedor();

            String correoCrea = tarea.getCorreoInstitucionalCrea() != null
                    ? tarea.getCorreoInstitucionalCrea() : tarea.getCorreoPersonalCrea();

            String[] destinatarios = new String[] {correoCrea, correoEmprendedor};

            emailService.notificarEntregaTarea(destinatarios, tarea.getTitulo(), tarea.getFechaLimiteEntrega(), tarea.getFechaEntrega(),
                    tarea.getNombreCompletoEmprendedor(), tarea.getNombreEmprendimiento());

            return true;

        } else {
            return false;
        }

    }

    @Override
    public boolean registrarCalificacionTareaEmprendedor(CalificarTareaDTO calificarTareaDTO) throws Exception {
        boolean tareaCalificada = rutaInnovacionDAO.registrarCalificacionTareaEmprendedor(calificarTareaDTO);

        if (tareaCalificada) {
            TareasProyectoEmprendimientoView tarea = rutaInnovacionDAO.obtenerTareasPorId(calificarTareaDTO.getIdTarea());

            String correoEmprendedor = tarea.getCorreoInstitucionalEmprendedor() != null
                    ? tarea.getCorreoInstitucionalEmprendedor() : tarea.getCorreoPersonalEmprendedor();

            String nombreCompletoCalifica = tarea.getNombresCrea() + " " + tarea.getApellidosCrea();

            emailService.notificarCalificacionTarea(correoEmprendedor, tarea.getTitulo(), tarea.getFechaEntrega(),
                    nombreCompletoCalifica, tarea.getCalificacion(), tarea.getNombreEmprendimiento());

            return true;

        } else {
            return false;
        }

    }
}
