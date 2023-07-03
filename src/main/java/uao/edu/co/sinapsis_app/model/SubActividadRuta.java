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
@Table(name = "T_SINAPSIS_SUB_ACT_RUTA")
public class SubActividadRuta {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "ACTIVIDADES_RUTAS_ID")
    private Long actividadRutaId;

    @Column(name = "REQUIERE_EVIDENCIA")
    private String requiereEvidencia;

    @Column(name = "TIPO")
    private Long tipo;

    @JsonIgnore
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;

    @JsonIgnore
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
}
