package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_MENTORES")
public class Mentor {
    @Id
    @Column(name = "ID")
    private Long idMentor;
    @Column(name = "FACULTAD")
    private String facultad;
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "ETAPA_RUTA")
    private Long idEtapaRuta;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Usuario usuario;
}
