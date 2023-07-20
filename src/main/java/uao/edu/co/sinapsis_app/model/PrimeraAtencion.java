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
@Table(name = "T_SINAPSIS_PRIMERA_ATENCION")
public class PrimeraAtencion {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_PRIM_ATENCION", sequenceName = "SEC_T_SINAPSIS_PRIM_ATENCION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_PRIM_ATENCION")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "PROMEDIO_VENTAS")
    private Double promedioVentas;
    @Column(name = "EVIDENCIA_PRODUCTO")
    private String evidenciasProducto;
    @Column(name = "MATERIAS_PRIMAS")
    private String materiasPrimas;
    @Column(name = "EQUIPO_TRABAJO")
    private String equipoTrabajo;
    @Column(name = "CUAL_EQUIPO_TRABAJO")
    private String cualEquipoTrabajo;
    @Column(name = "DEDICACION")
    private String dedicacion;
    @Column(name = "FECHA_EJECUCION")
    private Date desdeFechaEjecucion;
    @Column(name = "HORAS_SEMANALES")
    private Integer horasSemanales;
    @Column(name = "MOTIVACION")
    private String motivacion;
    @Column(name = "DESCUBRIO_SINAPSIS")
    private String descubrioSinapsis;
    @Column(name = "AUTODIAGNOSTICO")
    private Long fileAutodiagnosticoURL;
    @Column(name = "CUAL_OTRO_DESCUBRIO_SINAPSIS")
    private String cualOtroDescubrioSinapsis;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
}
