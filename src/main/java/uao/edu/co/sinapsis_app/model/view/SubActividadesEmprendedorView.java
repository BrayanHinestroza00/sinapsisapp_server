package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "V_SINAPSIS_ACT_EMPRENDEDOR")
public class SubActividadesEmprendedorView {
    @Id
    @Column(name = "ID_SUB_ACT_RUTA_EMP")
    private Long id;

    @Column(name = "ID_SUB_ACT_RUTA")
    private Long idSubActividad;

    @Column(name = "NOMBRE_SUB_ACT_RUTA")
    private String nombreSubActividad;

    @Column(name = "ACTIVIDADES_RUTAS_ID")
    private Long idActividad;

    @Column(name = "REQUIERE_EVIDENCIA")
    private String requiereEvidencia;

    @Column(name = "TIPO_SUB_ACTIVIDAD")
    private Long tipoSubActividad;

    @Column(name = "ESTADO_ACTIVIDAD")
    private String estadoActividad;

    @Column(name = "FECHA_ESTADO_ACTIVIDAD")
    private String fechaEstadoActividad;

    @Column(name = "URL_EVIDENCIA_ACTIVIDAD")
    private String urlEstadoActividad;

    @Column(name = "FECHA_EVIDENCIA")
    private String fechaEvidencia;

    @Column(name = "RUTAS_EMPRENDIMIENTOS_ID")
    private Long idRutaEmprendimiento;
}
