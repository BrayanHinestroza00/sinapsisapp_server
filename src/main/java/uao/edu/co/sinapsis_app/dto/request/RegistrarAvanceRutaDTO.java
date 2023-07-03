package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

@Data
public class RegistrarAvanceRutaDTO {
    private Long idRutaProyecto;
    private Long idSubActividadRuta;
    private String evidencia;
}
