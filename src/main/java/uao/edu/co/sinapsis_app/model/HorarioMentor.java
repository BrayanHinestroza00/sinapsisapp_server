package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_SINAPSIS_HORARIOS_MENTOR")
public class HorarioMentor {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_HORARIO_MENTOR", sequenceName = "SEC_T_SINAPSIS_HORARIO_MENTOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_HORARIO_MENTOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "HORA_INICIO")
    private String horaInicio;
    @Column(name = "HORA_FIN")
    private String horaFin;
    @Column(name = "DIA")
    private String dia;
    @Column(name = "MENTORES_ID")
    private Long idMentor;
}
