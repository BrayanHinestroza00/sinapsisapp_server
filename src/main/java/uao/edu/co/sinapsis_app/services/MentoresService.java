package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IMentoresDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IRutaInnovacionDAO;
import uao.edu.co.sinapsis_app.dto.request.EmprendedoresAsignadosFilterDTO;
import uao.edu.co.sinapsis_app.dto.request.FinalizarAcompanamientoDTO;
import uao.edu.co.sinapsis_app.dto.request.MentoresAdmFilterDTO;
import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;
import uao.edu.co.sinapsis_app.model.view.MentoresProyectoEmprendimientoView;
import uao.edu.co.sinapsis_app.model.view.MentoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;
import uao.edu.co.sinapsis_app.services.interfaces.IEmailService;
import uao.edu.co.sinapsis_app.services.interfaces.IMentoresService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MentoresService implements IMentoresService {
    @Autowired
    private IMentoresDAO mentoresDAO;

    @Autowired
    IRutaInnovacionDAO rutaInnovacionDAO;

    @Autowired
    IAppService appService;
    @Autowired
    IEmailService emailService;

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
    public boolean finalizarAcompanamiento(FinalizarAcompanamientoDTO finalizarAcompanamientoDTO) throws Exception {
        HashMap<String, Object> asesoramientoFinalizado = mentoresDAO.finalizarAcompanamiento(
                finalizarAcompanamientoDTO.getIdRutaProyectoEmprendimiento(),
                finalizarAcompanamientoDTO.getIdMentorCrea(),
                finalizarAcompanamientoDTO.getObservaciones());

        if (!asesoramientoFinalizado.isEmpty()) {
            Asesoramiento asesoramiento = (Asesoramiento) asesoramientoFinalizado.get("asesoramiento");

            AsesoramientosView asesoramientosView =
                    rutaInnovacionDAO.buscarAsesoramientoPorIdRutaProyectoEmprendimiento(asesoramiento.getIdRutaEmprendimiento());

            String nombreEmprendedor = asesoramientosView.getNombres() + " " + asesoramientosView.getApellidos();

            String correoEmprendedor = asesoramientosView.getCorreoInstitucional() != null
                    ? asesoramientosView.getCorreoInstitucional() : asesoramientosView.getCorreoPersonal();

            String nombreProyecto = asesoramientosView.getNombreEmprendimiento();

            if ((Integer) asesoramientoFinalizado.get("result") == 1) {
                EtapaRutaInnovacion etapaRutaInnovacion = (EtapaRutaInnovacion) asesoramientoFinalizado.get("etapaRuta");

                emailService.notificarAsignacionEtapaRuta(correoEmprendedor, etapaRutaInnovacion.getNombre());
            } else {
                // Se culmina el proyecto de emprendimiento
                ProyectoEmprendimiento proyectoEmprendimiento = mentoresDAO.obtenerProyectoEmprendimiento(asesoramientosView.getIdProyectoEmprendimiento());

                proyectoEmprendimiento.setEstadoEmprendimiento("TERMINADO");
                proyectoEmprendimiento.setFechaFin(new Date());

                mentoresDAO.almacenarProyectoEmprendimiento(proyectoEmprendimiento);

                String[] destinatarios  = appService.consultarCorreosAdministradores();
                emailService.notificarCulminacionRutaEmprendedor(destinatarios, nombreEmprendedor, correoEmprendedor, nombreProyecto);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Emprendimiento> obtenerEmprendimientos(String idMentor) {
        return mentoresDAO.obtenerEmprendimientos(idMentor);
    }

    @Override
    public List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento) {
        return mentoresDAO.obtenerRedesSocialesEmprendimiento(idEmprendimiento);
    }
}
