package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
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
            EmprendedoresView emprendedor = emprendedorService.getInformacionEmprendedor(Long.parseLong(idUsuario));

            if (emprendedor == null) {
                response.setCode(0);
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
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> actualizarEmprendedor(@ModelAttribute EmprendedorUpdateDTO emprendedorUpdateDTO, @RequestBody(required = false) EmprendedorUpdateDTO empDTO ) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado;
            if (empDTO.getIdEmprendedor() != null) {
                esActualizado = emprendedorService.actualizarEmprendedor(empDTO);
            } else if (emprendedorUpdateDTO.getIdEmprendedor() != null) {
                esActualizado = emprendedorService.actualizarEmprendedor(emprendedorUpdateDTO);
            }else {
                esActualizado = false;
                response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
                response.setMessage("Parametros no validos");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }


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
