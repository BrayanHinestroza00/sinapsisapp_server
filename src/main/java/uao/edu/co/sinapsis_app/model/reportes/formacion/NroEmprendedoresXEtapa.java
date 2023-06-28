package uao.edu.co.sinapsis_app.model.reportes.formacion;

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
public class NroEmprendedoresXEtapa {
    @Column(name = "NRO_PROYECTOS_EMPRENDIMIENTO")
    private String nroEmprendedores;

    @Id
    @Column(name = "ETAPA_RUTA")
    private String etapaRuta;
}
