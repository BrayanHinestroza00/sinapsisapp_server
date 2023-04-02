package uao.edu.co.sinapsis_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uao.edu.co.sinapsis_app.dto.ReporteConsultoriasMentorExportExcel;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.services.interfaces.IReportesService;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
    @Autowired
    private IReportesService reportesService;

    @CrossOrigin( origins = "http://localhost:3000")
    @RequestMapping(value = "/consultorias_por_mentor", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseDTO> generarReporteConsultoriasPorMentor(@RequestBody ReporteConsultoriasMentorDTO reportesFilters, HttpServletResponse servletResponse){
        ResponseDTO response = new ResponseDTO();
        try {
            ReporteConsultoriasMentorExportExcel exportExcel = reportesService.generarReporteConsultoriasPorMentor(reportesFilters);

            if (exportExcel != null) {
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");
                String currentDateTime = dateFormatter.format(new Date());
                servletResponse.setContentType("attachment; filename=Reporte_consultorias_" + currentDateTime + ".xlsx");
                servletResponse.setHeader(
                        "Content-Disposition",
                        "attachment; filename=Reporte_consultorias_" + currentDateTime + ".xlsx");

                exportExcel.export(servletResponse);

                response.setCode(1);
                response.setMessage("OK");
            }else {
                response.setCode(0);
                response.setMessage("Sin datos");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            response.setCode(-1);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
