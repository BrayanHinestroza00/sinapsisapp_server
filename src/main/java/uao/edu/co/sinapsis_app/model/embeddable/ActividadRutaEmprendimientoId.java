package uao.edu.co.sinapsis_app.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ActividadRutaEmprendimientoId implements Serializable {
    @Column(name = "RUTAS_EMPRENDIMIENTOS_ID")
    private Long idRutaEmprendimiento;
    @Column(name = "ACTIVIDADES_RUTAS_ID")
    private Long idActividad;

    public ActividadRutaEmprendimientoId() {
    }
    public ActividadRutaEmprendimientoId(Long idRutaEmprendimiento, Long idActividad) {
        this.idRutaEmprendimiento = idRutaEmprendimiento;
        this.idActividad = idActividad;
    }
}
