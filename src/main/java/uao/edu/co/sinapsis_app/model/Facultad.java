package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "T_SINAPSIS_FACULTADES")
public class Facultad {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
}
