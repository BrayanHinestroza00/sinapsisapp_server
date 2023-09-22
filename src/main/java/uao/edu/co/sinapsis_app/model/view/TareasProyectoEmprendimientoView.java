package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "V_SINAPSIS_TAREAS_PROYECTO_EMP")
public class TareasProyectoEmprendimientoView {
    @Id
    @Column(name = "ID_TAREA")
    private Long idTarea;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRIPCION")
    private String descripcionTarea;
    @Column(name = "FECHA_ENTREGA")
    private String fechaEntrega;
//    @Column(name = "FECHA_LIMITE_ENTREGA")
//    private String fechaLimiteEntrega;
    @Column(name = "URL_MATERIAL_APOYO")
    private String urlMaterialApoyo;
    @Column(name = "ESTADO_ENTREGA")
    private String estadoEntrega;
    @Column(name = "URL_ARCHIVOS_ENTREGA")
    private String urlArchivosEntrega;
    @Column(name = "COMENTARIOS_ENTREGA")
    private String comentariosEntrega;
    @Column(name = "CALIFICACION")
    private String calificacion;
    @Column(name = "CALIFICACION_CUANTITATIVA")
    private String calificacionCuantitativa;
    @Column(name = "COMENTARIOS_EMP_ENTREGA")
    private String comentariosEntregaEmprendedor;
    @Column(name = "PROYECTOS_EMPRENDIMIENTOS_ID")
    private String idProyectoEmprendimiento;
    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private String fechaModificacion;
    @Column(name = "USUARIOS_ID")
    private String idUsuarioCrea;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumentoCrea;
    @Column(name = "NOMBRES")
    private String nombresCrea;
    @Column(name = "APELLIDOS")
    private String apellidosCrea;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucionalCrea;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContactoCrea;
    @Column(name = "TIPOS_DOCUMENTO_ID")
    private String tipoDocumentoCrea;
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonalCrea;
    @Column(name = "NOMBRE_EMPRENDIMIENTO")
    private String nombreEmprendimiento;
    @Column(name = "NOMBRE_COMPLETO_EMP")
    private String nombreCompletoEmprendedor;
    @Column(name = "CORREO_INSTITUCIONAL_EMP")
    private String correoInstitucionalEmprendedor;
    @Column(name = "CORREO_PERSONAL_EMP")
    private String correoPersonalEmprendedor;
}
