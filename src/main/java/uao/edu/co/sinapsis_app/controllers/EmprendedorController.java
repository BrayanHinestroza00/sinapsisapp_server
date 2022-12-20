package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uao.edu.co.sinapsis_app.beans.EmprendedorDTO;
import uao.edu.co.sinapsis_app.beans.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.beans.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Emprendedor;
import uao.edu.co.sinapsis_app.services.interfaces.IEmprendedorService;

import static uao.edu.co.sinapsis_app.util.Constants.*;


@RestController
@RequestMapping("/emprendedor")
public class EmprendedorController {
    @Autowired
    IEmprendedorService emprendedorService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getInformacionEmprendedor(@RequestParam String idUsuario) {
        ResponseDTO response = new ResponseDTO();
        try {
            Emprendedor emprendedor = emprendedorService.getInformacionEmprendedor(Long.parseLong(idUsuario));

            if (emprendedor == null) {
                response.setCode(1);
                response.setMessage("EMPRENDEDOR NO ENCONTRADO");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setCode(1);
                response.setMessage("OK");
                response.setResponse(emprendedor);
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
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> actualizarEmprendedor(@ModelAttribute EmprendedorDTO emprendedorDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado = emprendedorService.actualizarEmprendedor(emprendedorDTO);

            if (esActualizado) {
                response.setCode(STATUS_OK);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("EMPRENDEDOR NO ENCONTRADO");
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
    @RequestMapping(value = "/primeraAtencion", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registrarPrimeraAtencion(@ModelAttribute PrimeraAtencionDTO primeraAtencion) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = emprendedorService.registrarPrimeraAtencion(primeraAtencion);

            if (esRegistrado) {
                response.setCode(STATUS_OK);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(STATUS_EMPTY);
                response.setMessage("EMPRENDEDOR NO ENCONTRADO");
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
