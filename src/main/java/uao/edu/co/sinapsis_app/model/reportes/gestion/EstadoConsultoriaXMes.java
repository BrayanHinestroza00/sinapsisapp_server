package uao.edu.co.sinapsis_app.model.reportes.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uao.edu.co.sinapsis_app.model.embeddable.EstadoConsultoriaXMesId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EstadoConsultoriaXMes {
    @EmbeddedId
    private EstadoConsultoriaXMesId id;

    @Column(name = "NRO_CONSULTORIAS")
    private String nroConsultorias;

    public EstadoConsultoriaXMes(String mesConsultoria, String estadoConsultoria, String nroConsultorias) {
        this.id = new EstadoConsultoriaXMesId(mesConsultoria, estadoConsultoria);
        this.nroConsultorias = nroConsultorias;
    }
}
