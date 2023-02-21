package uao.edu.co.sinapsis_app.model;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.embeddable.UsuarioRolId;

import javax.persistence.*;

@Entity
@Data
@Table(name = "T_SINAPSIS_USUARIOS_ROL")
public class UsuarioRol {
    @EmbeddedId
    private UsuarioRolId id;

}
