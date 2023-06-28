package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IReportesDAO;
import uao.edu.co.sinapsis_app.dto.ReporteConsultoriasMentorExportExcel;
import uao.edu.co.sinapsis_app.dto.reportes.gestion.ReportesFormacionDTO;
import uao.edu.co.sinapsis_app.dto.reportes.gestion.ReportesGestionDTO;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
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
import uao.edu.co.sinapsis_app.services.interfaces.IReportesService;

import java.util.List;

@Service
public class ReportesService implements IReportesService {
    @Autowired
    private IReportesDAO reportesDAO;

    @Override
    public ReporteConsultoriasMentorExportExcel generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters) {
        List<ConsultoriasView> data = reportesDAO.generarReporteConsultoriasPorMentor(reportesFilters);
        if (data.size() > 0) {
            return new ReporteConsultoriasMentorExportExcel(data);
        }
        return null;
    }

    @Override
    public ResponseDTO consultarIndicadoresGestion() {
        ResponseDTO response = new ResponseDTO();
        NroEmprendedores nroEmprendedores = reportesDAO.consultarNroEmprendedores();
        List<NroEmprendedoresRutaXPrograma> nroEmprendedoresRutaXPrograma = reportesDAO.consultarNroEmprendedoresRutaXPrograma();
        List<NroEmprendedoresRutaXFacultad> nroEmprendedoresRutaXFacultad = reportesDAO.consultarNroEmprendedoresRutaXFacultad();
        List<NroEmprendedoresXMunicipio> nroEmprendedoresXMunicipio = reportesDAO.consultarNroEmprendedoresXMunicipio();
        List<NroEmprendedoresXModalidad> nroEmprendedoresXModalidad = reportesDAO.consultarNroEmprendedoresXModalidad();
        List<NroProyectosXEmprendedor> nroProyectosXEmprendedor = reportesDAO.consultarNroProyectosXEmprendedor();

        NroProyectosEmprendimiento nroProyectosEmprendimiento = reportesDAO.consultarNroProyectosEmprendimiento();
        List<NroProyectosEmprendimientoXPrograma> nroProyectosEmprendimientoXPrograma = reportesDAO.consultarNroProyectosEmprendimientoXPrograma();
        List<NroProyectosEmprendimientoXFacultad> nroProyectosEmprendimientoXFacultad = reportesDAO.consultarNroProyectosEmprendimientoXFacultad();

        List<NroConsultoriasXTipoXMes> nroConsultoriasXTipoXMes = reportesDAO.consultarNroConsultoriasXTipoXMes();
        List<NroConsultoriasXMes> nroConsultoriasXMes = reportesDAO.consultarNroConsultoriasXMes();
        List<NroConsultoriasXMesXPrograma> nroConsultoriasXMesXPrograma = reportesDAO.consultarNroConsultoriasXMesXPrograma();
        List<NroConsultoriasXMentorXMes> nroConsultoriasXMentorXMes = reportesDAO.consultarNroConsultoriasXMentorXMes();
        List<EstadoConsultoriaXMes> estadoConsultoriaXMes = reportesDAO.consultarEstadoConsultoriaXMes();

        ReportesGestionDTO reportesGestionDTO = new ReportesGestionDTO();

        reportesGestionDTO.setNroEmprendedores(nroEmprendedores);
        reportesGestionDTO.setNroEmprendedoresRutaXPrograma(nroEmprendedoresRutaXPrograma);
        reportesGestionDTO.setNroEmprendedoresRutaXFacultad(nroEmprendedoresRutaXFacultad);
        reportesGestionDTO.setNroEmprendedoresXMunicipio(nroEmprendedoresXMunicipio);
        reportesGestionDTO.setNroEmprendedoresXModalidad(nroEmprendedoresXModalidad);
        reportesGestionDTO.setNroProyectosXEmprendedor(nroProyectosXEmprendedor);

        reportesGestionDTO.setNroProyectosEmprendimiento(nroProyectosEmprendimiento);
        reportesGestionDTO.setNroProyectosEmprendimientoXPrograma(nroProyectosEmprendimientoXPrograma);
        reportesGestionDTO.setNroProyectosEmprendimientoXFacultad(nroProyectosEmprendimientoXFacultad);

        reportesGestionDTO.setNroConsultoriasXTipoXMes(nroConsultoriasXTipoXMes);
        reportesGestionDTO.setNroConsultoriasXMes(nroConsultoriasXMes);
        reportesGestionDTO.setNroConsultoriasXMesXPrograma(nroConsultoriasXMesXPrograma);
        reportesGestionDTO.setNroConsultoriasXMentorXMes(nroConsultoriasXMentorXMes);
        reportesGestionDTO.setEstadoConsultoriaXMes(estadoConsultoriaXMes);

        response.setResponse(reportesGestionDTO);
        response.setCode(1);

        return response;
    }

    @Override
    public ResponseDTO consultarIndicadoresFormacion() {
        ResponseDTO response = new ResponseDTO();

        List<NroEmprendedoresXEtapa> nroEmprendedoresXEtapa = reportesDAO.consultarNroEmprendedoresXEtapa();
        List<NroEmprendedoresXTipoContacto> nroEmprendedoresXTipoContacto = reportesDAO.consultarNroEmprendedoresXTipoContacto();
        List<NroEmprendedoresXPrograma> nroEmprendedoresXPrograma = reportesDAO.consultarNroEmprendedoresXPrograma();
        List<NroEmprendedoresXFacultad> nroEmprendedoresXFacultad = reportesDAO.consultarNroEmprendedoresXFacultad();

        ReportesFormacionDTO reportesFormacionDTO = new ReportesFormacionDTO();

        reportesFormacionDTO.setNroEmprendedoresXEtapa(nroEmprendedoresXEtapa);
        reportesFormacionDTO.setNroEmprendedoresXTipoContacto(nroEmprendedoresXTipoContacto);
        reportesFormacionDTO.setNroEmprendedoresXPrograma(nroEmprendedoresXPrograma);
        reportesFormacionDTO.setNroEmprendedoresXFacultad(nroEmprendedoresXFacultad);

        response.setResponse(reportesFormacionDTO);
        response.setCode(1);

        return response;
    }
}
