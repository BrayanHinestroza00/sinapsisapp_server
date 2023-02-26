package uao.edu.co.sinapsis_app.model;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.embeddable.UsuarioRolId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "T_SINAPSIS_USUARIOS_ROL")
public class UsuarioRol {
    @EmbeddedId
    private UsuarioRolId id;

    public UsuarioRol() {
    }

    public UsuarioRol(UsuarioRolId id) {
        this.id = id;
    }
}
