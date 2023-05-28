package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendimientoDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;

public interface IEmprendimientoDAO {
    boolean actualizarEmprendimiento(EmprendimientoUpdateDTO emprendimientoDTO) throws Exception;

    Emprendimiento registrarEmprendimiento(EmprendimientoDTO emprendimiento) throws Exception;

}
