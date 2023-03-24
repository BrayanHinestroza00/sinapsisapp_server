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
    @Column(name = "ID_SUB_ACT_EMPRENDEDOR")
    private Long id;
    @Column(name = "ID_ACTIVIDAD_RUTA")
    private String idActividad;
    @Column(name = "NOMBRE_ACTIVIDAD_RUTA")
    private String nombreActividad;
    @Column(name = "ID_SUB_ACT_RUTA")
    private String idSubActividad;
    @Column(name = "NOMBRE_SUB_ACT_RUTA")
    private String nombreSubActividad;
    @Column(name = "ESTADO_ACTIVIDAD")
    private String estadoActividad;
    @Column(name = "ID_HERRAMIENTA")
    private String idHerramienta;
    @Column(name = "NOMBRE_HERRAMIENTA")
    private String nombreHerramienta;
    @Column(name = "ESTADO_HERRAMIENTA")
    private String estadoHerramienta;
}
