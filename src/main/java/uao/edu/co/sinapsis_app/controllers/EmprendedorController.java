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
import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.IniciarAvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.RegistrarAvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IEmprendedorService;

import java.util.ArrayList;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.STATUS_EMPTY;
import static uao.edu.co.sinapsis_app.util.Constants.STATUS_ERROR;
import static uao.edu.co.sinapsis_app.util.Constants.STATUS_OK;


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
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> actualizarEmprendedor(@ModelAttribute EmprendedorUpdateDTO emprendedorUpdateDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado;
            if (emprendedorUpdateDTO.getIdEmprendedor() != null) {
                esActualizado = emprendedorService.actualizarEmprendedor(emprendedorUpdateDTO);
            }else {
                esActualizado = false;
                response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
                response.setMessage("Parámetros no validos");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }

            if (esActualizado) {
                response.setCode(STATUS_EMPTY);
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
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> actualizarEmprendedorJson(@RequestBody(required = false) EmprendedorUpdateDTO empDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado;
            if (empDTO.getIdEmprendedor() != null) {
                esActualizado = emprendedorService.actualizarEmprendedor(empDTO);
            } else {
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
                response.setCode(0);
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
    @RequestMapping(value = "/emprendimiento", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerEmprendimiento(@RequestParam(required = true) String idEmprendimiento){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Emprendimiento> data = emprendedorService.obtenerEmprendimiento(idEmprendimiento);

            if (data.size() > 0) {
                List<RedSocialEmprendimientoView> redesSociales = emprendedorService.obtenerRedesSocialesEmprendimiento(idEmprendimiento);

                data.get(0).setRedesSociales(redesSociales);

                response.setCode(1);
                response.setResponse(data.get(0));
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
    @RequestMapping(value = "/emprendimientos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerEmprendimientos(@RequestParam(required = true) String idEmprendedor){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Emprendimiento> emprendimientos = emprendedorService.obtenerEmprendimientos(idEmprendedor);
            List<Emprendimiento> data = new ArrayList<>();

            if (emprendimientos.size() > 0) {
                for (Emprendimiento emprendimiento: emprendimientos ) {
                    List<RedSocialEmprendimientoView> redesSociales =
                            emprendedorService.obtenerRedesSocialesEmprendimiento(String.valueOf(emprendimiento.getId()));

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

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/emprendimiento", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> actualizarEmprendimiento(@ModelAttribute EmprendimientoUpdateDTO emprendimientoUpdateDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esActualizado;
            if (emprendimientoUpdateDTO.getIdEmprendimiento() != null) {
                esActualizado = emprendedorService.actualizarEmprendimiento(emprendimientoUpdateDTO);
            }else {
                response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
                response.setMessage("Parámetros no validos");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }

            if (esActualizado) {
                response.setCode(STATUS_EMPTY);
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
    @RequestMapping(value = "/avance_ruta", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerAvanceEnRuta(@RequestParam(required = true) Long idProyectoEmprendimiento){
        ResponseDTO response = new ResponseDTO();
        try {
            response = emprendedorService.obtenerAvanceEnRuta(idProyectoEmprendimiento);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/avance_ruta/iniciar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> iniciarAvanceEnRuta(@RequestBody IniciarAvanceRutaDTO iniciarAvanceRutaDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            response = emprendedorService.iniciarAvanceEnRuta(iniciarAvanceRutaDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/avance_ruta/continuar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> continuarAvanceEnRuta(@RequestBody RegistrarAvanceRutaDTO registrarAvanceRutaDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            response = emprendedorService.continuarAvanceEnRuta(registrarAvanceRutaDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
