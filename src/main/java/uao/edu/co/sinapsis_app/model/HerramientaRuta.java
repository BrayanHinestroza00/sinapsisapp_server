package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_HERRAMIENTAS")
public class HerramientaRuta {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "URL_MATERIAL_APOYO")
    private String urlMaterialApoyo;
    @Column(name = "ACTIVIDADES_RUTAS_ID")
    private String idSubActividadRuta;
    @Column(name = "ESTADO")
    private String estado;
}
