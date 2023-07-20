package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.ArchivoDTO;
import uao.edu.co.sinapsis_app.dto.UsuarioUpdateDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

import java.io.IOException;
import java.sql.SQLException;


@RestController
@RequestMapping("/test/")
public class TestController {
    @Autowired
    IStorageService storageService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<ArchivoDTO> getTipoDocumento(@RequestParam Long idArchivo) {

        ArchivoDTO file = null;
        try {
            file = storageService.downloadDB(idArchivo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/load", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> getTipoDocumento(@ModelAttribute UsuarioUpdateDTO usuarioUpdateDTO) {

        System.out.println(storageService.storeDB(usuarioUpdateDTO.getFotoPerfil()));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
