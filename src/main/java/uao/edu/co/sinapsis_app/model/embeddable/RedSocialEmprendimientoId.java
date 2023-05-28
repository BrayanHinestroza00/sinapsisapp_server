package uao.edu.co.sinapsis_app.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RedSocialEmprendimientoId implements Serializable {
    @Column(name = "REDES_SOCIALES_ID")
    private Long idRedSocial;
    @Column(name = "EMPRENDIMIENTOS_ID")
    private Long idEmprendimiento;

    public RedSocialEmprendimientoId(Long idRedSocial, Long idEmprendimiento) {
        this.idRedSocial = idRedSocial;
        this.idEmprendimiento = idEmprendimiento;
    }
}
