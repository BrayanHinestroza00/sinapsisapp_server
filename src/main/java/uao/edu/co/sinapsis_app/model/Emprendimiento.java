package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Emprendimiento {
    @Id
    private Long id;
    private String nombreEmprendimiento;
    private String estadoEmprendimiento;
    private Long emprendedorId;
}
