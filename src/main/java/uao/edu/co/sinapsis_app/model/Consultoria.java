package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "T_SINAPSIS_CONSULTORIAS")
public class Consultoria {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_CONSULTORIAS", sequenceName = "SEC_T_SINAPSIS_CONSULTORIAS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_CONSULTORIAS")
    @Column(name = "ID")
    private Long idConsultoria;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "TIPO_CONSULTORIA")
    private String tipoConsultoria;
    @Column(name = "FECHA_CONSULTORIA")
    private Date fechaConsultoria;
    @Column(name = "HORA_INICIO")
    private String horaInicio;
    @Column(name = "HORA_FIN")
    private String horaFinalizacion;
    @Column(name = "ASUNTO_CONSULTORIA")
    private String asuntoConsultoria;
    @Column(name = "ESTADO_CONSULTORIA")
    private String estadoConsultoria;
    @Column(name = "COMENTARIOS_CONSULTORIA")
    private String comentariosConsultoria;
    @Column(name = "PROYECTOS_EMPRENDIMIENTOS_ID")
    private Long idProyectoEmprendimiento;
    @Column(name = "MENTORES_ID")
    private Long idMentor;
    @Column(name = "SUB_ACTIVIDADES_RUTAS_ID")
    private Long idSubActividadRuta;
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
    @Column(name = "CREATED_BY")
    private Long idUsuarioCrea;
}
