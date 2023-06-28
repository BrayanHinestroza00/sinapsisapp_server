package uao.edu.co.sinapsis_app.model.reportes.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NroProyectosEmprendimientoXPrograma {
    @Column(name = "NRO_PROYECTOS_EMPRENDIMIENTO")
    private String nroProyectosEmprendimiento;

    @Id
    @Column(name = "PROOGRAMA_ACADEMICO")
    private String programaAcademico;
}
