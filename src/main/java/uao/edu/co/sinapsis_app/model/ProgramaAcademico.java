package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "T_SINAPSIS_PROGRAMAS")
public class ProgramaAcademico {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "ACRONIMO")
    private String acronimo;
    @Column(name = "FACULTADES_ID")
    private Integer facultades_id;
}
