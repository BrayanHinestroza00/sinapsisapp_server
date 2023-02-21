package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "T_SINAPSIS_EMPRENDIMIENTOS")
public class Emprendimiento {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_EMPRENDIMIENTOS", sequenceName = "SEC_T_SINAPSIS_EMPRENDIMIENTOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_EMPRENDIMIENTOS")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE_EMPRENDIMIENTO")
    private String nombreEmprendimiento;
    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;
    @Column(name = "SECTOR_EMPRENDIMIENTO")
    private String sectorEmprendimiento;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "SITIO_WEB")
    private String sitioWeb;
    @Column(name = "ESTA_CONSTITUIDA")
    private String estaConstituida;
    @Column(name = "FECHA_CONSTITUCION")
    private Date fechaConstitucion;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "URL_LOGO_EMPRESA")
    private String urlLogoEmpresa;
    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcionProducto;
    @Column(name = "MATERIAS_PRIMAS")
    private String materiasPrimas;
    @Column(name = "DESCRIPCION_CLIENTES")
    private String descripcionClientes;
    @Column(name = "ENFOQUE_SOCIAL")
    private String enfoqueSocial;
    @Column(name = "TIENE_EQUIPO_TRABAJO")
    private String tieneEquipoTrabajo;
    @Column(name = "EQUIPO_TRABAJO")
    private String equipoTrabajo;
}
