package uao.edu.co.sinapsis_app.model.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class NroConsultoriasXTipoXMesId implements Serializable {
    @Column(name = "MES_CONSULTORIA")
    private String mesConsultoria;

    @Column(name = "TIPO_CONSULTORIA")
    private String tipoConsultoria;
}
