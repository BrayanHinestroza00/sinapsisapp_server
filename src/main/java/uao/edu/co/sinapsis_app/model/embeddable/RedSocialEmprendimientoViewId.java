package uao.edu.co.sinapsis_app.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RedSocialEmprendimientoViewId implements Serializable {
    @Column(name = "ID_RED_SOCIAL")
    private String idRedSocial;
    @Column(name = "ID_EMPRENDIMIENTO")
    private String idEmprendimiento;

    public RedSocialEmprendimientoViewId() {
    }
    public RedSocialEmprendimientoViewId(String idRedSocial, String idEmprendimiento) {
        this.idRedSocial = idRedSocial;
        this.idEmprendimiento = idEmprendimiento;
    }
}
