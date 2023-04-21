package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

@Data
public class MentoresAdmFilterDTO {
    private String numeroDocumento;
    private String nombreMentor;
    private Long etapasRuta;
    private Integer estadoCuenta;
    /**
     * Soporte
     */
    private Long idMentor;
    private Long idEtapaRutaInnovacion;
}
