package uao.edu.co.sinapsis_app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String to;
    private String[] multipleTo;
    private String cuerpoMensaje;
    private String asunto;
}
