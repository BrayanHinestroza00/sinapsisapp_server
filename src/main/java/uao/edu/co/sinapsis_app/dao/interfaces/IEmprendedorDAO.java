package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;

import java.util.List;

public interface IEmprendedorDAO {
    List<EmprendedoresView> getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorUpdateDTO emprendedorUpdateDTO) throws Exception;

    List<AsignaturaEmprendedor> obtenerAsignaturasEmprendedor(long idUsuario);
}
