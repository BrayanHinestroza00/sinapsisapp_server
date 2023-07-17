package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Basic;
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
@Table(name = "T_SINAPSIS_RUT_EMPRENDIMIENTO")
public class RutaProyectoEmprendimiento {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_RUT_EMPRENDI", sequenceName = "SEC_T_SINAPSIS_RUT_EMPRENDI", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_RUT_EMPRENDI")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROYECTOS_EMPRENDIMIENTOS_ID")
    private Long idProyectoEmprendimiento;

    @Column(name = "ETAPAS_ID")
    private Long idEtapa;

    @Column(name = "ESTADO_RUTA")
    private String estadoRuta;

    @Column(name = "FECHA_ESTADO_RUTA")
    private Date fechaEstadoRuta;

    @Column(name = "CREATED_BY")
    private Long creadoPor;

    @Column(name = "CREATED_AT")
    private Date fechaCreacion;

    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;

    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Column(name = "FECHA_FIN")
    private Date fechaFin;
}
