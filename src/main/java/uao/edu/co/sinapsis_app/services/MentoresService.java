package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.dto.HorarioMentorDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.FinalizarAcompanamientoDTO;
import uao.edu.co.sinapsis_app.dto.request.HorarioMentorRequestDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.model.HorarioMentor;
import uao.edu.co.sinapsis_app.model.Mentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.services.interfaces.IMentoresService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentoresService implements IMentoresService {
    @Autowired
    private IMentoresDAO mentoresDAO;

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorRutaEmprendimiento(Long idRutaEmprendimiento) {
        return mentoresDAO.obtenerMentoresPorRutaEmprendimiento(idRutaEmprendimiento);
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return mentoresDAO.obtenerMentoresPorProyectoEmprendimiento(idProyectoEmprendimiento);
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerMentorPrincipalPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return mentoresDAO.obtenerMentorPrincipalPorProyectoEmprendimiento(idProyectoEmprendimiento);
    }

    @Override
    public List<MentoresProyectoEmprendimientoView> obtenerHistoricoMentoresPorProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        return mentoresDAO.obtenerHistoricoMentoresPorProyectoEmprendimiento(idProyectoEmprendimiento);
    }

    @Override
    public List<AsesoramientosView> obtenerEmprendedoresPorMentor(EmprendedoresAsignadosFilterDTO emprendedoresAsignadosFilterDTO) {
        return mentoresDAO.obtenerEmprendedoresPorMentor(emprendedoresAsignadosFilterDTO);
    }

    @Override
    public HorarioMentorDTO obtenerHorarioMentor(Long idMentor) {
        HorarioMentorDTO horarioMentorDTO = new HorarioMentorDTO();
        List<HorarioMentor> horariosMentor = mentoresDAO.obtenerHorarioMentor(idMentor);

        if (horariosMentor.size() > 0) {
            for (HorarioMentor horarioMentor: horariosMentor ) {
                switch (horarioMentor.getDia()) {
                    case "1":
                        horarioMentorDTO.addLunes(horarioMentor);
                        break;
                    case "2":
                        horarioMentorDTO.addMartes(horarioMentor);
                        break;
                    case "3":
                        horarioMentorDTO.addMiercoles(horarioMentor);
                        break;
                    case "4":
                        horarioMentorDTO.addJueves(horarioMentor);
                        break;
                    case "5":
                        horarioMentorDTO.addViernes(horarioMentor);
                        break;
                    case "6":
                        horarioMentorDTO.addSabado(horarioMentor);
                        break;
                }
            }
        }

        return horarioMentorDTO;
    }

    @Override
    public List<MentoresView> obtenerMentores(MentoresAdmFilterDTO mentoresAdmFilterDTO) {
        return mentoresDAO.obtenerMentores(mentoresAdmFilterDTO);
    }

    @Override
    public List<MentoresView> obtenerMentoresPorId(Long idMentor) {
        return mentoresDAO.obtenerMentoresPorId(idMentor);
    }

    @Override
    public List<MentoresView> obtenerMentoresPorEtapaRutaInnovacion(Long idEtapaRutaInnovacion) {
        return mentoresDAO.obtenerMentoresPorEtapaRutaInnovacion(idEtapaRutaInnovacion);
    }

    @Override
    public boolean actualizarHorarioMentor(HorarioMentorRequestDTO horarioMentorDTO) throws Exception {
        List<HorarioMentor> horarios = new ArrayList<>();

        if (horarioMentorDTO.getHorarioMentor().getLunes() != null) {
            horarios.addAll(horarioMentorDTO.getHorarioMentor().getLunes());
        }
        if (horarioMentorDTO.getHorarioMentor().getMartes() != null) {
            horarios.addAll(horarioMentorDTO.getHorarioMentor().getMartes());
        }
        if (horarioMentorDTO.getHorarioMentor().getMiercoles() != null) {
            horarios.addAll(horarioMentorDTO.getHorarioMentor().getMiercoles());
        }
        if (horarioMentorDTO.getHorarioMentor().getJueves() != null) {
            horarios.addAll(horarioMentorDTO.getHorarioMentor().getJueves());
        }
        if (horarioMentorDTO.getHorarioMentor().getViernes() != null) {
            horarios.addAll(horarioMentorDTO.getHorarioMentor().getViernes());
        }
        if (horarioMentorDTO.getHorarioMentor().getSabado() != null) {
            horarios.addAll(horarioMentorDTO.getHorarioMentor().getSabado());
        }

        return mentoresDAO.actualizarHorarioMentor(horarioMentorDTO.getIdMentor(), horarios);
    }

    @Override
    public boolean finalizarAcompanamiento(FinalizarAcompanamientoDTO finalizarAcompanamientoDTO) throws Exception {
        return mentoresDAO.finalizarAcompanamiento(
                finalizarAcompanamientoDTO.getIdRutaProyectoEmprendimiento(),
                finalizarAcompanamientoDTO.getIdMentorCrea(),
                finalizarAcompanamientoDTO.getObservaciones());
    }
}
