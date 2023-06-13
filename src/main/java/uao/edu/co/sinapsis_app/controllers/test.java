package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.services.interfaces.IEmailService;


@RestController
public class test {

    @Autowired
    IEmailService emailService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTipoDocumento(){
        //emailService.notificarAsignacionEtapaInicialRuta("brayan.hinestroza@uao.edu.co, bahinestroza@uao.edu.co", "PENSAR");
        /*emailService.notificarAsignacionEtapaRuta(new EmailDetails());
        emailService.notificarAsignacionTarea(new EmailDetails());
        emailService.notificarCalificacionTarea(new EmailDetails());
        emailService.notificarEntregaTarea(new EmailDetails());
        emailService.notificarProgramacionConsultoria(new EmailDetails());
        emailService.notificarAsignacionNuevoEmprendedor(new EmailDetails());
        emailService.notificarCulminacionActividadesEmprendedor(new EmailDetails());
        emailService.notificarCulminacionRutaEmprendedor(new EmailDetails());
        emailService.notificarSolicitudPrimeraAtencion(new String[]{"brayan.hinestroza@uao.edu.co", "bahinestroza@uao.edu.co"}, );*/
        return "";
    }
}
