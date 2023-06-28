package uao.edu.co.sinapsis_app.model.reportes.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uao.edu.co.sinapsis_app.model.embeddable.NroConsultoriasXTipoXMesId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NroConsultoriasXTipoXMes {
    @EmbeddedId
    private NroConsultoriasXTipoXMesId id;

    @Column(name = "NRO_CONSULTORIAS")
    private String nroConsultorias;

    public NroConsultoriasXTipoXMes(String mesConsultoria, String tipoConsultoria, String nroConsultorias) {
        this.id = new NroConsultoriasXTipoXMesId(mesConsultoria, tipoConsultoria);
        this.nroConsultorias = nroConsultorias;
    }

    @Override
    public String toString() {
        return "NroConsultoriasXTipoXMes{" +
                "mesConsultoria=" + id.getMesConsultoria() +
                ", tipoConsultoria='" + id.getTipoConsultoria() + '\'' +
                ", nroConsultorias='" + nroConsultorias + '\'' +
                '}';
    }
}
