package uao.edu.co.sinapsis_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uao.edu.co.sinapsis_app.model.embeddable.ActividadRutaEmpId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_SINAPSIS_ACT_RUTA_EMP")
public class ActividadRutaEmp {
    @Id
    private ActividadRutaEmpId actividadRutaEmpId;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CREATED_AT")
    private Date fechaCreacion;

    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;

    public ActividadRutaEmp(Long idRutaEmprendimiento, Long idActividad, String estado, Date fechaCreacion, Date fechaModificacion) {
        this.actividadRutaEmpId = new ActividadRutaEmpId(idRutaEmprendimiento, idActividad);
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }
}
