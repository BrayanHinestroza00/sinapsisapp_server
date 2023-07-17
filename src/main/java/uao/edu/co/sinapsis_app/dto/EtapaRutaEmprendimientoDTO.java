package uao.edu.co.sinapsis_app.dto;

import lombok.Data;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.view.AsesoramientosView;

import java.util.List;

@Data
public class EtapaRutaEmprendimientoDTO {
    private AsesoramientosView asesoramientosView;
    List<RutaProyectoEmprendimiento> rutaProyectoEmprendimientos;
}
