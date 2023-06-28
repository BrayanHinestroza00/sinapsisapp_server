package uao.edu.co.sinapsis_app.model.reportes.gestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NroEmprendedoresRutaXFacultad {
    @Column(name = "NRO_EMPRENDEDORES")
    private String nroEmprendedores;

    @Id
    @Column(name = "FACULTAD")
    private String facultad;
}
