package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "T_SINAPSIS_ASESORAMIENTO")
public class Asesoramiento {
    @Id
    @Column(name = "ID_ASESORAMIENTO")
    private Long idAsesoramiento;
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @Column(name = "RUTA_EMPRENDIMIENTO_EMP_ID")
    private Long idRutaEmprendimiento;
    @Column(name = "MENTOR_ID")
    private Long idMentor;
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
}
