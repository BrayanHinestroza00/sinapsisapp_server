package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "V_SINAPSIS_PROY_EMPRENDIMIENTO")
public class EmprendimientosEmprendedorView {
    @Id
    @Column(name = "ID_PROY_EMPRENDIMIENTO")
    private Long idProyectoEmprendimiento;
    @Column(name = "ESTADO_EMPRENDIMIENTO")
    private String estadoEmprendimiento;
    @Column(name = "ID_EMPRENDEDOR")
    private Long idEmprendedor;
    @Column(name = "ID_EMPRENDIMIENTO")
    private Long idEmprendimiento;
    @Column(name = "ID_PRIMERA_ATENCION")
    private Long idPrimeraAtencion;
    @Column(name = "NOMBRE_EMPRENDIMIENTO")
    private String nombreEmprendimiento;
}
