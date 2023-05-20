package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.request.PublicarAnuncioDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Anuncio;
import uao.edu.co.sinapsis_app.model.Asignatura;
import uao.edu.co.sinapsis_app.model.Departamento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.Facultad;
import uao.edu.co.sinapsis_app.model.Municipio;
import uao.edu.co.sinapsis_app.model.ProgramaAcademico;
import uao.edu.co.sinapsis_app.model.RedSocial;
import uao.edu.co.sinapsis_app.model.TipoContacto;
import uao.edu.co.sinapsis_app.model.TipoDocumento;
import uao.edu.co.sinapsis_app.model.view.ActividadesEtapaView;
import uao.edu.co.sinapsis_app.model.view.EmprendimientosEmprendedorView;
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
    @RequestMapping(value = "/preload/emprendedor", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getPreFetchEmprendedor(@RequestParam Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            int idUsuario = Integer.parseInt(requestData.get("idUsuario"));

            List<EmprendimientosEmprendedorView> proyectos = appService.getProyectosEmprendimientoEmprendedor(idUsuario);
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
    @RequestMapping(value = "/facultades", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getFacultades(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Facultad> data;
            if (requestData.size() > 0) {
                int idPrograma = Integer.parseInt(requestData.get("idPrograma"));
                data = appService.getFacultadesByProgramaAcademico(idPrograma);
            }else{
                data = appService.getFacultades();
            }

            if (data.size() > 0) {
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
    @RequestMapping(value = "/programas_academicos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getProgramasAcademicos(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<ProgramaAcademico> data;
            if (requestData.size() > 0) {
                if (requestData.containsKey("idFacultad")){
                    int idFacultad = Integer.parseInt(requestData.get("idFacultad"));
                    data = appService.getProgramasAcademicosByidFacultad(idFacultad);
                } else {
                    long idPrograma = Long.parseLong(requestData.get("idPrograma"));
                    data = appService.getProgramasAcademicosById(idPrograma);
                }
            }else{
                data = appService.getProgramasAcademicos();
            }

            if (data.size() > 0) {
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
    @RequestMapping(value = "/asignaturas", method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getAsignaturas(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Asignatura> data;
            if (requestData.size() > 0) {
                int idAsignatura = Integer.parseInt(requestData.get("idAsignatura"));
                data = appService.getAsignaturasById(idAsignatura);
            }else{
                data = appService.getAsignaturas();
            }

            if (data.size() > 0) {
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
    @RequestMapping(value = "/redes_sociales", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerRedesSociales(){
        ResponseDTO response = new ResponseDTO();
        try {
            List<RedSocial> data = appService.obtenerRedesSociales();

            if (data.size() > 0) {
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
    @RequestMapping(value = "/etapas_ruta", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerEtapasRutaInnovacionEmprendimiento(){
        ResponseDTO response = new ResponseDTO();
        try {
            List<EtapaRutaInnovacion> data = appService.obtenerEtapasRutaInnovacionEmprendimiento();

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
    @RequestMapping(value = "/tematicas_etapa", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerTematicasEtapasRutaInnovacionEmprendimiento(@RequestParam(required = true) Long idEtapa ){
        ResponseDTO response = new ResponseDTO();
        try {
            List<ActividadesEtapaView> data = appService.obtenerTematicasEtapasRutaInnovacionEmprendimiento(idEtapa);

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
    @RequestMapping(value = "/anuncios", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> obtenerAnuncios(){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Anuncio> data = appService.obtenerAnuncios();

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
    @RequestMapping(value = "/tipoContacto", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getTipoContacto(@RequestParam(required = false) Map<String,String> requestData){
        ResponseDTO response = new ResponseDTO();
        try {
            List<TipoContacto> data;
            if (requestData.size() > 0) {
                long idTipoContacto = Long.parseLong(requestData.get("idTipoContacto"));
                data = appService.getTipoContactoById(idTipoContacto);
            }else{
                data = appService.getAllTipoContacto();
            }

            if (data.size() > 0) {
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
    @RequestMapping(value = "/publicar_anuncio", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> registrarAnuncio(@ModelAttribute PublicarAnuncioDTO anuncioDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean esRegistrado = appService.registrarAnuncio(anuncioDTO);

            if (esRegistrado) {
                response.setCode(0);
                response.setMessage("OK");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {
                response.setCode(0);
                response.setMessage("No se pudo publicar el anuncio");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
