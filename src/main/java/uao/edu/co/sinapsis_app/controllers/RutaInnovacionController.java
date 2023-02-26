package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.request.AsignarRutaPrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.view.PrimeraAtencionView;
import uao.edu.co.sinapsis_app.model.view.SolicitudesProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IRutaInnovacionService;

import java.util.List;

@RestController()
@RequestMapping("/ruta_innovacion")
public class RutaInnovacionController {
    @Autowired
    IRutaInnovacionService rutaInnovacionService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/primeraAtencion/detalle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> detallePrimeraAtencionPendiente(
            @RequestParam(required = true) Integer idProyectoEmprendimiento) {
        ResponseDTO response = new ResponseDTO();
        try {
            PrimeraAtencionView solicitud = rutaInnovacionService.detallePrimeraAtencionPendiente(idProyectoEmprendimiento);

            if (solicitud == null) {
                response.setCode(1);
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
            @RequestParam(required = false) Integer tipoDocumento, @RequestParam(required = false) String numeroDocumento,
            @RequestParam(required = false) String nombres, @RequestParam(required = false) String apellidos) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<SolicitudesProyectoEmprendimientoView> solicitudes = rutaInnovacionService.listarPrimerasAtencionesPendientes();

            if (solicitudes == null) {
                response.setCode(1);
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
}
