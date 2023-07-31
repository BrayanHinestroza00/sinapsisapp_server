package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.FinalizarAcompanamientoDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IMentoresService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.STATUS_EMPTY;

@RestController
@RequestMapping("/mentores")
public class MentoresController {
    @Autowired
    private IMentoresService mentoresService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerMentores(
            @Valid MentoresAdmFilterDTO mentoresAdmFilterDTO){

        ResponseDTO response = new ResponseDTO();
        try {
            List<MentoresView> data = new ArrayList<>();

            if ((mentoresAdmFilterDTO.getIdMentor() != null && mentoresAdmFilterDTO.getIdEtapaRutaInnovacion() != null) )  {
                response.setCode(0);
                response.setMessage("Datos no validos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {

                if (mentoresAdmFilterDTO.getIdMentor() != null) {
                    data =mentoresService.obtenerMentoresPorId(mentoresAdmFilterDTO.getIdMentor());
                } else if (mentoresAdmFilterDTO.getIdEtapaRutaInnovacion() != null) {
                    data =mentoresService.obtenerMentoresPorEtapaRutaInnovacion(mentoresAdmFilterDTO.getIdEtapaRutaInnovacion());
                } else {
                    data =mentoresService.obtenerMentores(mentoresAdmFilterDTO);
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
    @RequestMapping(value = "/ruta_emprendimiento", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerMentoresPorRutaEmprendimiento(
            @RequestParam(required = true) Long idRutaEmprendimiento ){

        ResponseDTO response = new ResponseDTO();
        try {
            List<MentoresProyectoEmprendimientoView> data =
                    mentoresService.obtenerMentoresPorRutaEmprendimiento(idRutaEmprendimiento);

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
    @RequestMapping(value = "/proyecto_emprendimiento", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerMentoresPorProyectoEmprendimiento(
            @RequestParam(required = true) Long idProyectoEmprendimiento,
            @RequestParam(required = false) String tipoBusqueda){
        ResponseDTO response = new ResponseDTO();
        try {
            List<MentoresProyectoEmprendimientoView> data;

            if (tipoBusqueda.equalsIgnoreCase("P")) {
                data = mentoresService.obtenerMentorPrincipalPorProyectoEmprendimiento(idProyectoEmprendimiento);
            }else if (tipoBusqueda.equalsIgnoreCase("H")){
                data = mentoresService.obtenerHistoricoMentoresPorProyectoEmprendimiento(idProyectoEmprendimiento);
            } else {
                data = mentoresService.obtenerMentoresPorProyectoEmprendimiento(idProyectoEmprendimiento);
            }

            if (data != null && data.size() > 0) {
                response.setCode(1);
                if (tipoBusqueda.equalsIgnoreCase("P")) {
                    response.setResponse(data.get(0));
                } else {
                    response.setResponse(data);
                }
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
    @RequestMapping(value = "/emprendedores", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerEmprendedoresPorMentor(
             @Valid EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO){

        ResponseDTO response = new ResponseDTO();
        try {
            List<AsesoramientosView> data =
                    mentoresService.obtenerEmprendedoresPorMentor(emprendedoresAsignadosFilterDTO);

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
    @RequestMapping(value = "/finalizar_acompanamiento", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> finalizarAcompanamiento(
            @RequestBody() @Valid FinalizarAcompanamientoDTO finalizarAcompanamientoDTO ){

        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado = mentoresService.finalizarAcompanamiento(finalizarAcompanamientoDTO);

            if (esActualizado) {
                response.setCode(STATUS_EMPTY);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("No se pudo finalizar el acompañamiento");
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
    @RequestMapping(value = "/emprendimientos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerEmprendimientos(@RequestParam(required = true) String idMentor){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Emprendimiento> emprendimientos = mentoresService.obtenerEmprendimientos(idMentor);
            List<Emprendimiento> data = new ArrayList<>();

            if (emprendimientos.size() > 0) {
                for (Emprendimiento emprendimiento: emprendimientos ) {
                    List<RedSocialEmprendimientoView> redesSociales =
                            mentoresService.obtenerRedesSocialesEmprendimiento(String.valueOf(emprendimiento.getId()));

                    emprendimiento.setRedesSociales(redesSociales);
                    data.add(emprendimiento);
                }
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
}
