package uao.edu.co.sinapsis_app.model.view;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import uao.edu.co.sinapsis_app.model.embeddable.RedSocialEmprendimientoId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SINAPSIS_EMPRENDI_RED_SOCIAL")
@Data
public class RedSocialEmprendimientoView {
    @Id
    @JsonUnwrapped
    private RedSocialEmprendimientoId id;
    @Column(name = "RED_SOCIAL")
    private String redSocial;
    @Column(name = "ENLACE")
    private String enlace;

    public RedSocialEmprendimientoView() {
    }

    public RedSocialEmprendimientoView(RedSocialEmprendimientoId id, String redSocial, String enlace) {
        this.id = id;
        this.redSocial = redSocial;
        this.enlace = enlace;
    }

    @Override
    public String toString() {
        return "RedSocialEmprendimientoView{" +
                "idRedSocial=" + id.getIdRedSocial() +
                ", idEmprendimiento='" + id.getIdEmprendimiento() + '\'' +
                ", redSocial='" + redSocial + '\'' +
                ", enlace='" + enlace + '\'' +
                '}';
    }
}
