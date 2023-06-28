package uao.edu.co.sinapsis_app.model.reportes.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uao.edu.co.sinapsis_app.model.embeddable.NroConsultoriasXMentorXMesId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NroConsultoriasXMentorXMes {
    @EmbeddedId
    private NroConsultoriasXMentorXMesId id;

    @Column(name = "NRO_CONSULTORIAS")
    private String nroConsultorias;

    public NroConsultoriasXMentorXMes(String mesConsultoria, String mentor, String nroConsultorias) {
        this.id = new NroConsultoriasXMentorXMesId(mesConsultoria, mentor);
        this.nroConsultorias = nroConsultorias;
    }
}
