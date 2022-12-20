package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uao.edu.co.sinapsis_app.beans.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Departamento;
import uao.edu.co.sinapsis_app.model.Municipio;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.TipoDocumento;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/")
public class AppController {
    @Autowired
    private IAppService appService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/tipoDocumento", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getTipoDocumento(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<TipoDocumento> data;
            if (requestData.size() > 0) {
                int idTipoDocumento = Integer.parseInt(requestData.get("idTipoDocumento"));
                data = appService.getTipoDocumentoById(idTipoDocumento);
            }else{
                data = appService.getAllTipoDocumento();
            }

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
    @RequestMapping(value = "/preload/emprendedor", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getPreFetchEmprendedor(@RequestParam Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            int idUsuario = Integer.parseInt(requestData.get("idUsuario"));

            List<Emprendimiento> proyectos = appService.getProyectosEmprendimientoEmprendedor(idUsuario);
            int primeraVez = appService.getPreFetchEmprendedor(idUsuario);

            Map<String, Object> data = new HashMap<>();
            data.put("primeraVez", primeraVez);
            data.put("proyectosEmprendimiento", proyectos);

            response.setCode(1);
            response.setResponse(data);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/departamentos", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getDepartamentos(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Departamento> data;
            if (requestData.size() > 0) {
                int idMunicipio = Integer.parseInt(requestData.get("idMunicipio"));
                data = appService.getDepartamentosByMunicipio(idMunicipio);
            }else{
                data = appService.getDepartamentos();
            }

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
    @RequestMapping(value = "/municipios", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getMunicipios(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Municipio> data;
            if (requestData.size() > 0) {
                if (requestData.containsKey("idDepartamento")){
                    int idDepartamento = Integer.parseInt(requestData.get("idDepartamento"));
                    data = appService.getMunicipiosByDepartamento(idDepartamento);
                } else {
                    long idMunicipio = Long.parseLong(requestData.get("idMunicipio"));
                    data = appService.getMunicipiosById(idMunicipio);
                }
            }else{
                data = appService.getMunicipios();
            }

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
}
