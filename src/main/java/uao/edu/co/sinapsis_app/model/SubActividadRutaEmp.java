package uao.edu.co.sinapsis_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "T_SINAPSIS_SUB_ACT_RUTAS_EMP")
public class SubActividadRutaEmp {
    @JsonIgnore
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_SUB_ACT_RUT_EMP", sequenceName = "SEC_T_SINAPSIS_SUB_ACT_RUT_EMP", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_SUB_ACT_RUT_EMP")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ESTADO_ACTIVIDAD")
    private String estadoActividad;

    @Column(name = "FECHA_ESTADO_ACTIVIDAD")
    private Date fechaEstadoActividad;

    @Column(name = "URL_EVIDENCIA_ACTIVIDAD")
    private String urlEvidenciaActividad;

    @Column(name = "FECHA_EVIDENCIA")
    private Date fechaEvidencia;

    @Column(name = "SUB_ACTIVIDADES_RUTAS_ID")
    private Long subActividadRutaId;

    @Column(name = "RUTAS_EMPRENDIMIENTOS_ID")
    private Long rutaEmprendimientoId;

    @JsonIgnore
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;

    @JsonIgnore
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
}
