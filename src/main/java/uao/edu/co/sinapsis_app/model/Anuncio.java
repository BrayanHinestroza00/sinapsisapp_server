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
@Table(name = "T_SINAPSIS_ANUNCIOS")
public class Anuncio {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_ANUNCIOS", sequenceName = "SEC_T_SINAPSIS_ANUNCIOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_ANUNCIOS")
    @Column(name = "ID_ANUNCIO")
    private Long idAnuncio;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "URL_ANUNCIO")
    private Long urlAnuncio;
    @Column(name = "ESTADO")
    private Integer estado;
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
    @Column(name = "FECHA_HASTA")
    private Date fechaHasta;
    @Column(name = "PERMANENTE")
    private String anuncioPermanente;
    @Column(name = "TITULO")
    private String titulo;
}
