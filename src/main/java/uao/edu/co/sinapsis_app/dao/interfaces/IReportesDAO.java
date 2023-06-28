package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXEtapa;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXTipoContacto;
import uao.edu.co.sinapsis_app.model.reportes.gestion.EstadoConsultoriaXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMentorXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMesXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXTipoXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedores;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresRutaXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresXModalidad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresXMunicipio;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresRutaXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimiento;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimientoXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimientoXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosXEmprendedor;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;

import java.util.List;

public interface IReportesDAO {
    List<ConsultoriasView> generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters);

    /*
     * Indicadores de gestion - De emprendedores
     */
    NroEmprendedores consultarNroEmprendedores();

    List<NroEmprendedoresRutaXPrograma> consultarNroEmprendedoresRutaXPrograma();

    List<NroEmprendedoresRutaXFacultad> consultarNroEmprendedoresRutaXFacultad();

    List<NroEmprendedoresXMunicipio> consultarNroEmprendedoresXMunicipio();

    List<NroEmprendedoresXModalidad> consultarNroEmprendedoresXModalidad();

    List<NroProyectosXEmprendedor> consultarNroProyectosXEmprendedor();

    /*
     * Indicadores de gestion - De proyectos de emprendimiento
     */
    NroProyectosEmprendimiento consultarNroProyectosEmprendimiento();
    List<NroProyectosEmprendimientoXPrograma> consultarNroProyectosEmprendimientoXPrograma();

    List<NroProyectosEmprendimientoXFacultad> consultarNroProyectosEmprendimientoXFacultad();

    /*
     * Indicadores de gestion - De ruta I&E
     */
    List<NroConsultoriasXTipoXMes> consultarNroConsultoriasXTipoXMes();

    List<NroConsultoriasXMes> consultarNroConsultoriasXMes();
    List<NroConsultoriasXMesXPrograma> consultarNroConsultoriasXMesXPrograma();

    List<NroConsultoriasXMentorXMes> consultarNroConsultoriasXMentorXMes();
    List<EstadoConsultoriaXMes> consultarEstadoConsultoriaXMes();

    List<NroEmprendedoresXEtapa> consultarNroEmprendedoresXEtapa();

    List<NroEmprendedoresXTipoContacto> consultarNroEmprendedoresXTipoContacto();

    List<NroEmprendedoresXPrograma> consultarNroEmprendedoresXPrograma();

    List<NroEmprendedoresXFacultad> consultarNroEmprendedoresXFacultad();
}
