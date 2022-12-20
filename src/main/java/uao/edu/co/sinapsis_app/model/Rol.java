package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Rol {
    @Id
    private int id;
    private String nombre;
}
