package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendimientoDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;

public interface IEmprendimientoDAO {
    boolean actualizarEmprendimiento(EmprendimientoDTO emprendimientoDTO) throws Exception;

    Emprendimiento registrarEmprendimiento(Emprendimiento emprendimiento) throws Exception;

}
