package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Municipio {
    @Id
    private Long id;
    private String nombre;
    private Integer departamentoId;
}
