package uao.edu.co.sinapsis_app.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UsuarioRolId implements Serializable {
    @Column(name = "ROLES_ID")
    private Long rolId;
    @Column(name = "USUARIOS_ID")
    private Long usuarioId;

    public UsuarioRolId() {
    }
    public UsuarioRolId(Long usuarioId, Long rolId) {
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }
}
