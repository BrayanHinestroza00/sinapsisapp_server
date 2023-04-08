package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_ANUNCIOS")
public class Anuncio {
    @Id
    @Column(name = "ID_ANUNCIO")
    private String idAnuncio;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "URL_ANUNCIO")
    private String urlAnuncio;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CREATED_AT")
    private String fechaCreacion;
    @Column(name = "FECHA_HASTA")
    private String fechaHasta;
    @Column(name = "PERMANENTE")
    private String anuncioPermanente;
    @Column(name = "TITULO")
    private String titulo;
}
