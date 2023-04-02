package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "V_SINAPSIS_EMPRENDEDORES")
public class AsesoramientosView {
    @Id
    @Column(name = "ID_ASESORAMIENTO")
    private Long idAsesoramiento;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "NOMBRE_EMPRENDIMIENTO")
    private String nombreEmprendimiento;
    @Column(name = "ESTADO_ASESORAMIENTO")
    private String estadoAsesoramiento;
    @Column(name = "ID_EMPRENDEDOR")
    private Long idEmprendedor;
    @Column(name = "ID_PROY_EMPRENDIMIENTO")
    private Long idProyectoEmprendimiento;
    @Column(name = "ID_EMPRENDIMIENTO")
    private Long idEmprendimiento;
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonal;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucional;
}
