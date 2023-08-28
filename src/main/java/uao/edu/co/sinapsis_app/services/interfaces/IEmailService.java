package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.beans.EmailDetails;

public interface IEmailService {
    boolean notificarAsignacionEtapaInicialRuta(String destinatario, String etapaAsignada);

    boolean notificarAsignacionEtapaRuta(String destinatario, String etapaAsignada);

    boolean notificarAsignacionTarea(String destinatario, String titulo, /*String fechaLimiteEntrega,*/ String usuarioRegistra, String nombreEmprendimiento);

    boolean notificarCalificacionTarea(String destinatario, String titulo, String fechaEntrega, String usuarioCalifica, String calificacion, String nombreEmprendimiento);

    boolean notificarEntregaTarea(String destinatarios[], String titulo, /*String fechaLimiteEntrega,*/ String fechaEntrega, String emprendedorEntrega, String nombreEmprendimiento);

    // Para emprendedor y (Mentor o Administrador)
    boolean notificarProgramacionConsultoria(String[] destinatarios, String asuntoConsultoria, String fechaConsultoria, String horaInicioProgramada,
                                             String horaFinProgramada, String nombreEmprendedor, String nombreCrea, String nombreProyecto);

    boolean notificarAsignacionNuevoEmprendedor(String destinatario, String nombreEmprendedor, String correoEmprendedor, String nombreProyecto, String etapaRuta);

    boolean notificarAsignacionNuevoMentor(String destinatario, String nombreMentor, String correoMentor, String cargoMentor, String nombreProyecto, String etapaRuta);

    // Notifica al emprendedor que se ha completado todas las actividades
    boolean notificarCulminacionActividadesMentor(EmailDetails emailDetails);

    boolean notificarCulminacionRutaEmprendedor(String[] destinatario, String nombreEmprendedor, String correoEmprendedor, String nombreProyecto);

    boolean notificarSolicitudPrimeraAtencion(String[] destinatarios, String nombreEmprendedor, String documentoEmprendedor, String vinculoConU, String correoInstitucional, String correoPersonal, String nombreEmprendimiento);

}
