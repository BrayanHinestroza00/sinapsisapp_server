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
@Table(name = "T_SINAPSIS_TAREAS")
public class Tarea {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_TAREAS", sequenceName = "SEC_T_SINAPSIS_TAREAS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_TAREAS")
    @Column(name = "ID")
    private Long idTarea;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA_ENTREGA")
    private Date fechaEntrega;
    @Column(name = "FECHA_LIMITE_ENTREGA")
    private Date fechaLimiteEntrega;
    @Column(name = "URL_MATERIAL_APOYO")
    private Long urlMaterialApoyo;
    @Column(name = "ESTADO_ENTREGA")
    private String estadoEntrega;
    @Column(name = "URL_ARCHIVOS_ENTREGA")
    private Long urlArchivosEntregados;
    @Column(name = "COMENTARIOS_ENTREGA")
    private String comentariosEntregaUsuario;
    @Column(name = "CALIFICACION")
    private String calificacion;
    @Column(name = "COMENTARIOS_EMP_ENTREGA")
    private String comentariosEntregaEmprendedor;
    @Column(name = "PROYECTOS_EMPRENDIMIENTOS_ID")
    private Long idProyectoEmprendimiento;
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
    @Column(name = "USUARIOS_ID")
    private Long usuarioCrea;
}
