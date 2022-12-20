package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class UsuarioRol implements Serializable {
    @Id
    private Long id;
    private int rolId;
    private Long usuarioId;
}
