package uao.edu.co.sinapsis_app.model.reportes.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NroConsultoriasXMes {
    @Column(name = "NRO_CONSULTORIAS")
    private String nroConsultorias;

    @Id
    @Column(name = "MES_CONSULTORIA")
    private String mesConsultoria;
}
