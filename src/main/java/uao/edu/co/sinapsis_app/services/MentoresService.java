package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.dto.response.HorarioMentorResponseDTO;
import uao.edu.co.sinapsis_app.model.HorarioMentor;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IMentoresService;

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
    public List<AsesoramientosView> obtenerEmprendedoresPorMentor(Long idMentor) {
        return mentoresDAO.obtenerEmprendedoresPorMentor(idMentor);
    }

    @Override
    public HorarioMentorResponseDTO obtenerHorarioMentor(Long idMentor) {
        HorarioMentorResponseDTO horarioMentorDTO = new HorarioMentorResponseDTO();
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
}
