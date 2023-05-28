package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.CrearTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.CalificarTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ConsultoriaDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.EntregaTareaDTO;
import uao.edu.co.sinapsis_app.dto.request.ProgramarConsultoriaDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPAFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.SolicitudesPEFilterDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.exceptions.StorageException;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static uao.edu.co.sinapsis_app.util.Constants.STATUS_EMPTY;
import static uao.edu.co.sinapsis_app.util.Constants.STATUS_ERROR;

@RestController()
@RequestMapping("/ruta_innovacion")
public class RutaInnovacionController {
    @Autowired
    IRutaInnovacionService rutaInnovacionService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/proyectos_emprendimiento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> listarProyectosDeEmprendimiento(
            @Valid SolicitudesPEFilterDTO solicitudesPEFilterDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<ListadoProyectoEmprendimientoView> solicitudes
                    = rutaInnovacionService.listarProyectosDeEmprendimiento(solicitudesPEFilterDTO);

            if (solicitudes == null) {
                response.setCode(0);
                response.setMessage("SIN PROYECTOS REGISTRADOS");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setCode(1);
                response.setMessage("OK");
                response.setResponse(solicitudes);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(0);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/primeraAtencion/detalle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> detallePrimeraAtencionPendiente(
            @RequestParam(required = true) Integer idProyectoEmprendimiento) {
        ResponseDTO response = new ResponseDTO();
        try {
            PrimeraAtencionView solicitud = rutaInnovacionService.detallePrimeraAtencion(idProyectoEmprendimiento);

            if (solicitud == null) {
                response.setCode(0);
                response.setMessage("SIN SOLICITUDES");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setCode(1);
                response.setMessage("OK");
                response.setResponse(solicitud);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(0);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/primeraAtencion/pendientes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> listarPrimerasAtencionesPendientes(
            @Valid SolicitudesPAFilterDTO solicitudesPAFilterDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<ListadoProyectoEmprendimientoView> solicitudes
                    = rutaInnovacionService.listarPrimerasAtencionesPendientes(solicitudesPAFilterDTO);

            if (solicitudes == null) {
                response.setCode(0);
                response.setMessage("SIN SOLICITUDES");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setCode(1);
                response.setMessage("OK");
                response.setResponse(solicitudes);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(0);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/primeraAtencion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> asignarRutaPrimeraAtencion(@RequestBody AsignarRutaPrimeraAtencionDTO rutaPrimeraAtencionDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.asignarRutaPrimeraAtencion(rutaPrimeraAtencionDTO);
            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("Ha ocurrido algun error");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/etapa_emprendedimiento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> obtenerEtapaProyectoEmprendimiento(
            @RequestParam(required = true) Long idProyectoEmprendimiento) {
        ResponseDTO response = new ResponseDTO();
        try {
            AsesoramientosView solicitud = rutaInnovacionService.obtenerEtapaProyectoEmprendimiento(idProyectoEmprendimiento);

            if (solicitud == null) {
                response.setCode(0);
                response.setMessage("SIN SOLICITUDES");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setCode(1);
                response.setMessage("OK");
                response.setResponse(solicitud);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/etapa/actividades", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerActividadesEtapa(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<ActividadRuta> data;
            if (requestData.size() > 0) {
                Long idEtapa = Long.parseLong(requestData.get("idEtapa"));
                data = rutaInnovacionService.obtenerActividadesEtapaById(idEtapa);
            }else{
                data = rutaInnovacionService.obtenerActividadesEtapa();
            }

            if (data != null && data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(1);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/etapa/herramientas", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerHerramientasEtapa(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<HerramientaRuta> data;
            if (requestData.size() > 0) {
                Long idEtapa = Long.parseLong(requestData.get("idEtapa"));
                data = rutaInnovacionService.obtenerHerramientasEtapaById(idEtapa);
            }else{
                data = rutaInnovacionService.obtenerHerramientasEtapa();
            }

            if (data != null && data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(1);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/actividades_emprendedimiento", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerActividadesEmprendedor(
            @RequestParam(required = true) Long idRutaEmprendimiento ){

        ResponseDTO response = new ResponseDTO();
        try {
            List<ActividadesEmprendedorView> data =
                    rutaInnovacionService.obtenerActividadesEmprendedor(idRutaEmprendimiento);

            if (data != null && data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(1);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/subactividades_emprendedimiento", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerSubActividadesEmprendedor(
            @RequestParam(required = true) Long idProyectoEmprendimiento,
            @RequestParam(required = true) Long idRutaEmprendimiento ){

        ResponseDTO response = new ResponseDTO();
        try {
            List<SubActividadesEmprendedorView> data =
                    rutaInnovacionService.obtenerSubActividadesEmprendedor(
                            idProyectoEmprendimiento, idRutaEmprendimiento);

            if (data != null && data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(1);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Tareas
     */
    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/tareas", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerTareas(
            @RequestParam(required = true) Long idProyectoEmprendimiento,
            @RequestParam(required = true) String tipoBusqueda){

        /**
         * TipoBusqueda = ESTADO TAREA
         */

        ResponseDTO response = new ResponseDTO();
        try {
            List<TareasProyectoEmprendimientoView> data =
                    rutaInnovacionService.obtenerTareasPendientes(idProyectoEmprendimiento, tipoBusqueda);

            if (data != null && data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(0);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerConsultorias(
            @RequestParam(required = false) Long idTarea,
            @RequestParam(required = false) String tipoBusqueda,
            @RequestParam(required = false) Long idProyectoEmprendimiento){

        /**
         * TipoBusqueda = Especializada o Normal
         */

        ResponseDTO response = new ResponseDTO();
        try {
            List<ConsultoriasView> data = new ArrayList<>();

            if ((idTarea == null && tipoBusqueda == null) ||
                    (idTarea != null && tipoBusqueda != null) ||
                    (tipoBusqueda != null && idProyectoEmprendimiento == null))  {
                response.setCode(0);
                response.setMessage("Datos no validos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                if (idTarea != null) {
                    data =rutaInnovacionService.obtenerConsultoria(idTarea);
                }

                if (tipoBusqueda != null) {
                    if (tipoBusqueda.equalsIgnoreCase("N")) {
                        data =rutaInnovacionService.obtenerConsultoriaNormal(idProyectoEmprendimiento);
                    } else if (tipoBusqueda.equalsIgnoreCase("E")){
                        data =rutaInnovacionService.obtenerConsultoriaEspecializada(idProyectoEmprendimiento);
                    } else {
                        data =rutaInnovacionService.obtenerHistoricoConsultoria(idProyectoEmprendimiento);
                    }
                }
                if (data != null && data.size() > 0) {
                    response.setCode(1);
                    response.setResponse(data);
                }else {
                    response.setCode(0);
                    response.setMessage("Sin datos");
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias_programadas", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerConsultorias(
            @RequestParam(required = true) Long idUsuario,
            @RequestParam(required = true) Integer tipoUsuario){

        ResponseDTO response = new ResponseDTO();
        try {
            List<ConsultoriasView> data = new ArrayList<>();

            if (idUsuario == null || tipoUsuario == null)  {
                response.setCode(0);
                response.setMessage("Datos no validos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                final int TYPE_EMPRENDEDOR = 1;
                final int TYPE_MENTOR = 2;
                final int TYPE_ADMINISTRADOR = 3;

                if (tipoUsuario == TYPE_EMPRENDEDOR || tipoUsuario == TYPE_ADMINISTRADOR) {
                    data =rutaInnovacionService.obtenerConsultoriaProgramadaEmprendedor(idUsuario);
                } else if (tipoUsuario == TYPE_MENTOR){
                    data =rutaInnovacionService.obtenerConsultoriaProgramadaMentor(idUsuario);
                } else {
                    response.setCode(0);
                    response.setMessage("Datos no validos");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

                if (data != null && data.size() > 0) {
                    response.setCode(1);
                    response.setResponse(data);
                }else {
                    response.setCode(0);
                    response.setMessage("Sin datos");
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias/programar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> programarConsultoriaEmprendedor(@RequestBody @Valid ProgramarConsultoriaDTO programarConsultoriaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.programarConsultoriaEmprendedor(programarConsultoriaDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL PROGRAMAR LA CONSULTORIA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias/iniciar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> iniciarConsultoriaEmprendedor(@RequestBody ConsultoriaDTO consultoriaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.iniciarConsultoriaEmprendedor(consultoriaDTO.getIdConsultoria());

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL INICIAR LA CONSULTORIA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias/inasistencia", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> inasistenciaConsultoriaEmprendedor(@RequestBody ConsultoriaDTO consultoriaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.inasistenciaConsultoriaEmprendedor(consultoriaDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL MARCAR INASISTENCIA A LA CONSULTORIA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias/terminar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> terminarConsultoriaEmprendedor(@RequestBody ConsultoriaDTO consultoriaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.terminarConsultoriaEmprendedor(consultoriaDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL TERMINAR A LA CONSULTORIA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/emprendedores", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerEmprendedores(
            @Valid EmprendedoresAdmFilterDTO emprendedoresAdmFilterDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            List<EmprendedoresView> data = rutaInnovacionService.obtenerEmprendedores(emprendedoresAdmFilterDTO);

            if (data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(1);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/mentores", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerMentores(){
        ResponseDTO response = new ResponseDTO();
        try {
            List<MentoresView> data = rutaInnovacionService.obtenerMentores();

            if (data.size() > 0) {
                response.setCode(1);
                response.setResponse(data);
            }else {
                response.setCode(1);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/tareas/crear", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registrarTareaEmprendedor(@ModelAttribute @Valid CrearTareaDTO crearTareaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.registrarTareaEmprendedor(crearTareaDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL REGISTRAR TAREA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/tareas/entregar", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registrarEntregaTareaEmprendedor(@ModelAttribute @Valid EntregaTareaDTO entregaTareaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.registrarEntregaTareaEmprendedor(entregaTareaDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL ENTREGAR TAREA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/tareas/calificar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registrarCalificacionTareaEmprendedor(@RequestBody @Valid CalificarTareaDTO calificarTareaDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = rutaInnovacionService.registrarCalificacionTareaEmprendedor(calificarTareaDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("FALLO AL ENTREGAR TAREA");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(STATUS_ERROR);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
