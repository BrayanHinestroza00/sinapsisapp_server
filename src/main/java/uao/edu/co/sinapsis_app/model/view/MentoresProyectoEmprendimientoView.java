package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "V_SINAPSIS_MENTORES_PROYECTO")
public class MentoresProyectoEmprendimientoView {
    @Id
    @Column(name = "ID_ASESORAMIENTO")
    private Long idAsesoramiento;
    @Column(name = "FECHA_INICIO")
    private String fechaInicio;
    @Column(name = "FECHA_FIN")
    private String fechaFin;
    @Column(name = "ESTADO_ASESORAMIENTO")
    private String estadoAsesoramiento;
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @Column(name = "ID_MENTOR")
    private String idMentor;
    @Column(name = "ID_RUTA_EMPRENDIMIENTO")
    private String idRutaEmprendimiento;
    @Column(name = "ID_PROYECTO_EMPRENDIMIENTO")
    private String idProyectoEmprendimiento;
    @Column(name = "FACULTAD")
    private String facultadMentor;
    @Column(name = "DEPENDENCIA")
    private String dependenciaMentor;
    @Column(name = "CARGO")
    private String cargoMentor;
    @Column(name = "NOMBRES")
    private String nombresMentor;
    @Column(name = "APELLIDOS")
    private String apellidosMentor;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucionalMentor;
    @Column(name = "FOTO_URL")
    private String fotoPerfilMentor;
}
