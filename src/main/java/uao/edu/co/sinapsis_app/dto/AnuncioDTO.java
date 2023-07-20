package uao.edu.co.sinapsis_app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AnuncioDTO {
    private Long idAnuncio;
    private String descripcion;
    private Integer estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaHasta;
    private String anuncioPermanente;
    private String titulo;
    private Long urlAnuncio;
}
