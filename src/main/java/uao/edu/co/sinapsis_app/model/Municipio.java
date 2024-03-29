package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "T_SINAPSIS_MUNICIPIOS")
public class Municipio {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DEPARTAMENTOS_ID")
    private Integer departamentoId;
}
