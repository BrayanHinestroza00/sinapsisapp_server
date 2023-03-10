package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_ASIGNATURAS")
public class Asignatura {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CODIGO")
    private String codigo;
}
