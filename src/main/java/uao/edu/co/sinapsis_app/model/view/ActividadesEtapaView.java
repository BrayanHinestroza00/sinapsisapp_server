package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "V_SINAPSIS_ACTIVIDADES_ETAPA")
public class ActividadesEtapaView {
    @Id
    @Column(name = "ID_SUB_ACT_RUTA")
    private Long idSubActividadRuta;

    @Column(name = "NOMBRE_SUB_ACT_RUTA")
    private String nombreSubActividadRuta;
}
