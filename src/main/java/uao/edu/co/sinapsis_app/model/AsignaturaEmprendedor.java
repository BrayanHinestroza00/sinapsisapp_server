package uao.edu.co.sinapsis_app.model;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.embeddable.AsignaturaEmprendedorId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "T_SINAPSIS_ASIG_EMPRENDEDOR")
public class AsignaturaEmprendedor {
    @Id
    private AsignaturaEmprendedorId id;

    public AsignaturaEmprendedor() {

    }

    public AsignaturaEmprendedor(AsignaturaEmprendedorId id) {
        this.id = id;
    }
}
