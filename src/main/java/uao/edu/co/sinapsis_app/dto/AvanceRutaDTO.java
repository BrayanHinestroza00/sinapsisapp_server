package uao.edu.co.sinapsis_app.dto;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.SubActividadRutaEmp;

import java.util.List;

@Data
public class AvanceRutaDTO {
    RutaProyectoEmprendimiento rutaProyectoEmprendimiento;
    List<SubActividadRutaEmp> subActividadRutaEmp;
}
