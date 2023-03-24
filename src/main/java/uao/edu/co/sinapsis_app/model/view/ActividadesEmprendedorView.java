package uao.edu.co.sinapsis_app.model.view;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import uao.edu.co.sinapsis_app.model.embeddable.ActividadRutaEmprendimientoId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "T_SINAPSIS_ACT_RUTA_EMP")
public class ActividadesEmprendedorView {
    @Id
    @JsonUnwrapped
    private ActividadRutaEmprendimientoId id;

    @Column(name = "ESTADO")
    private String estadoActividad;

    @Column(name = "CREATED_AT")
    private String fechaCreacion;
    @Column(name = "UPDATED_AT")
    private String fechaModificacion;

    @Override
    public String toString() {
        return "ActividadesEmprendedorView{" +
                "idRutaEmprendimiento=" + id.getIdRutaEmprendimiento() +
                ", idActividad='" + id.getIdActividad() + '\'' +
                ", estadoActividad='" + estadoActividad + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                '}';
    }
}
