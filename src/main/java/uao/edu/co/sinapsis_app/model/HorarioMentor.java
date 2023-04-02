package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_HORARIOS_MENTOR")
public class HorarioMentor {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "HORA_INICIO")
    private String horaInicio;
    @Column(name = "HORA_FIN")
    private String horaFin;
    @Column(name = "DIA")
    private String dia;
}
