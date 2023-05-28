package uao.edu.co.sinapsis_app.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import uao.edu.co.sinapsis_app.model.embeddable.RedSocialEmprendimientoId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_EMPREN_RED_SOCIAL")
public class RedSocialEmprendimiento {
    @Id
    @JsonUnwrapped
    private RedSocialEmprendimientoId id;
    @Column(name = "ENLACE")
    private String enlace;


    public RedSocialEmprendimiento(Long idEmprendimiento, Long idRedSocial, String enlace) {
        this.id = new RedSocialEmprendimientoId(idRedSocial, idEmprendimiento);
        this.enlace = enlace;
    }

    @Override
    public String toString() {
        return "RedSocialEmprendimientoView{" +
                "idRedSocial=" + id.getIdRedSocial() +
                ", idEmprendimiento='" + id.getIdEmprendimiento() + '\'' +
                ", enlace='" + enlace + '\'' +
                '}';
    }
}
