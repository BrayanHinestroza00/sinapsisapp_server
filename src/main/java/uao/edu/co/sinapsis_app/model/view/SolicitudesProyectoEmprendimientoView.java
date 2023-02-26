package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SINAPSIS_SOLICITUDES_PA")
@Data
public class SolicitudesProyectoEmprendimientoView {
    @Id
    @Column(name = "PROYECTO_EMPRENDIMIENTO_ID")
    private Long proyectoEmprendimientoId;
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
}
