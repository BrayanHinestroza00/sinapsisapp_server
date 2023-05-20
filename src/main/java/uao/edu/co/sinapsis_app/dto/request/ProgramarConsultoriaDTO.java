package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProgramarConsultoriaDTO {
    private String titulo;
    private String tipoConsultoria;
    private Date fechaConsultoria;
    private String horaInicio;
    private String horaFinalizacion;
    private String asuntoConsultoria;
    private Long proyectoEmprendimiento;
    private Long mentor;
    private Long subActividadRuta;
    private Long usuarioCrea;
}
