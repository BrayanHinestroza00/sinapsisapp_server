package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Data
public class TipoDocumento {
    private String id;
    private String nombre;
    private String acortado;
}
