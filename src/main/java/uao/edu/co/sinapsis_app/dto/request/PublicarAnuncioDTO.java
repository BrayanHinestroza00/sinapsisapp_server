package uao.edu.co.sinapsis_app.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
public class PublicarAnuncioDTO implements Serializable {
    @JsonAlias("fileAnuncio")
    private MultipartFile flyerAnuncio;
    private Long flyerAnuncioURL;
    private String tituloAnuncio;
    private String descripcionAnuncio;
    private String permanente;
    private Date fechaHasta;
}
