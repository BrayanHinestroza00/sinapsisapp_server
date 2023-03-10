package uao.edu.co.sinapsis_app.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AsignaturaEmprendedorId implements Serializable {
    @Column(name = "EMPRENDEDORES_ID")
    private Long emprendedorId;
    @Column(name = "ASIGNATURAS_ID")
    private String asignaturaId;

    public AsignaturaEmprendedorId() {
    }
    public AsignaturaEmprendedorId(Long emprendedorId, String asignaturaId) {
        this.emprendedorId = emprendedorId;
        this.asignaturaId = asignaturaId;
    }
}
