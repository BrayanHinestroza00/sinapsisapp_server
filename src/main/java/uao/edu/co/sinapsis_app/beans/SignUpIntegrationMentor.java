package uao.edu.co.sinapsis_app.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignUpIntegrationMentor implements Serializable {
    private String usuario;
    private int tipoDocumento;
    private String numeroDocumento;
    private Long etapaRuta;
}
