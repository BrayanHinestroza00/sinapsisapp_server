package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;
import uao.edu.co.sinapsis_app.dto.HorarioMentorDTO;

import javax.validation.constraints.NotNull;

@Data
public class HorarioMentorRequestDTO {
    @NotNull
    private Long idMentor;
    private HorarioMentorDTO horarioMentor;
}
