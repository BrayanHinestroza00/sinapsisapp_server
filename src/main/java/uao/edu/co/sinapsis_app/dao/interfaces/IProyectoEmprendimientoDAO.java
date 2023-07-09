package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.model.Asesoramiento;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;

public interface IProyectoEmprendimientoDAO {
    ProyectoEmprendimiento registrarProyectoEmprendimiento(ProyectoEmprendimiento proyectoEmprendimiento) throws Exception;

    ProyectoEmprendimiento find(Long idProyectoEmprendimiento);

    boolean updateProyecto(ProyectoEmprendimiento proyectoEmprendimiento);

    Asesoramiento buscarAsesoramiento(Long idRutaProyecto, Long idMentorPrincipal);
}
