package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegistrarAvanceRutaDTO {
    private Long idRutaProyecto;
    private Long idSubActividadRuta;
    private MultipartFile evidencia;
}
