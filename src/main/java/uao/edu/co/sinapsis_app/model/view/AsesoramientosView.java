package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "V_SINAPSIS_ASESORAMIENTOS")
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
    @Column(name = "ID_MENTOR")
    private Long idMentor;
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonal;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucional;

    //T_SINAPSIS_RUT_EMPRENDIMIENTO
    @Column(name = "ID_RUTA_EMPRENDI")
    private Long idRutaProyEmprendimiento;
    @Column(name = "ID_ETAPA")
    private Long idEtapa;
    @Column(name = "ESTADO_RUTA_EMPRENDI")
    private String estadoRuta;
    @Column(name = "FECHA_ESTADO_RUTA_EMPRENDI")
    private Date fechaEstadoRuta;
    @Column(name = "CREATED_BY")
    private Long creadoPor;
}
