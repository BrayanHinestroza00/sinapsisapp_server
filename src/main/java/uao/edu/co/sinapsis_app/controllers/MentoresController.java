package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.response.HorarioMentorResponseDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Mentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IMentoresService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mentores")
public class MentoresController {
    @Autowired
    private IMentoresService mentoresService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerMentores(
            @RequestParam(required = false) Long idMentor,
            @RequestParam(required = false) Long idEtapaRutaInnovacion){

        ResponseDTO response = new ResponseDTO();
        try {
            List<Mentor> data = new ArrayList<>();

            if ((idMentor != null && idEtapaRutaInnovacion != null) )  {
                response.setCode(0);
                response.setMessage("Datos no validos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {

                if (idMentor != null) {
                    data =mentoresService.obtenerMentoresPorId(idMentor);
                } else if (idEtapaRutaInnovacion != null) {
                    data =mentoresService.obtenerMentoresPorEtapaRutaInnovacion(idEtapaRutaInnovacion);
                } else {
                    data =mentoresService.obtenerMentores();
                }

                if (data != null && data.size() > 0) {
                    response.setCode(1);
                    response.setResponse(data);
                }else {
                    response.setCode(1);
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
            @RequestParam(required = true) Long idMentor ){

        ResponseDTO response = new ResponseDTO();
        try {
            List<AsesoramientosView> data =
                    mentoresService.obtenerEmprendedoresPorMentor(idMentor);

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
    @RequestMapping(value = "/horario", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerHorarioMentor(
            @RequestParam(required = true) Long idMentor ){

        ResponseDTO response = new ResponseDTO();
        try {
            HorarioMentorResponseDTO horarioMentor =
                    mentoresService.obtenerHorarioMentor(idMentor);

            if (horarioMentor != null ) {
                response.setCode(1);
                response.setResponse(horarioMentor);
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
