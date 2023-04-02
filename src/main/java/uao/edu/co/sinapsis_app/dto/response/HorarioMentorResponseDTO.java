package uao.edu.co.sinapsis_app.dto.response;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.HorarioMentor;

import java.util.ArrayList;
import java.util.List;

@Data
public class HorarioMentorResponseDTO {
    private List<HorarioMentor> lunes;
    private List<HorarioMentor> martes;
    private List<HorarioMentor> miercoles;
    private List<HorarioMentor> jueves;
    private List<HorarioMentor> viernes;
    private List<HorarioMentor> sabado;

    public void addLunes(HorarioMentor horarioMentor) {
        if (this.lunes == null) {
            this.lunes = new ArrayList<>();
            this.lunes.add(horarioMentor);
        } else {
            this.lunes.add(horarioMentor);
        }
    }

    public void addMartes(HorarioMentor horarioMentor) {
        if (this.martes == null) {
            this.martes = new ArrayList<>();
            this.martes.add(horarioMentor);
        } else {
            this.martes.add(horarioMentor);
        }
    }

    public void addMiercoles(HorarioMentor horarioMentor) {
        if (this.miercoles == null) {
            this.miercoles = new ArrayList<>();
            this.miercoles.add(horarioMentor);
        } else {
            this.miercoles.add(horarioMentor);
        }
    }

    public void addJueves(HorarioMentor horarioMentor) {
        if (this.jueves == null) {
            this.jueves = new ArrayList<>();
            this.jueves.add(horarioMentor);
        } else {
            this.jueves.add(horarioMentor);
        }
    }

    public void addViernes(HorarioMentor horarioMentor) {
        if (this.viernes == null) {
            this.viernes = new ArrayList<>();
            this.viernes.add(horarioMentor);
        } else {
            this.viernes.add(horarioMentor);
        }
    }

    public void addSabado(HorarioMentor horarioMentor) {
        if (this.sabado == null) {
            this.sabado = new ArrayList<>();
            this.sabado.add(horarioMentor);
        } else {
            this.sabado.add(horarioMentor);
        }
    }


}
