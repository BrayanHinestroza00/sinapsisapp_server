package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SINAPSIS_LISTADO_PE")
@Data
public class ListadoProyectoEmprendimientoView {
    @Id
    @Column(name = "PROYECTO_EMPRENDIMIENTO_ID")
    private Long proyectoEmprendimientoId;
    @Column(name = "EMPRENDIMIENTOS_ID")
    private Long emprendimientoId;
    @Column(name = "EMPRENDEDORES_ID")
    private Long emprendedorId;
    @Column(name = "TIPOS_DOCUMENTO_ID")
    private Long tipoDocumentoId;
    @Column(name = "CORREO_EMPRENDEDOR")
    private String correoEmprendedor;
    @Column(name = "DOCUMENTO_EMPRENDEDOR")
    private String documentoEmprendedor;
    @Column(name = "FECHA_REGISTRO_EMP")
    private String fechaRegistroEmp;
    @Column(name = "FECHA_REGISTRO_PA")
    private String fechaRegistroPA;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreEmprendedor;
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "NOMBRE_EMPRENDIMIENTO")
    private String nombreEmprendimiento;
    @Column(name = "ETAPA_RUTA")
    private String etapaRuta;
    @Column(name = "ESTADO_RUTA")
    private String estadoRuta;
    @Column(name = "ETAPAS_RUTA_ID")
    private String idEstadoRuta;
    @Column(name = "ASESORAMIENTOS_ID")
    private Long asesoramientoId;
    @Column(name = "MENTOR_ASESORAMIENTOS_ID")
    private Long mentorAsesoramientoId;
}
