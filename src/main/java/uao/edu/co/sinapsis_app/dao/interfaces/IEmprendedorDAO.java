package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorDTO;
import uao.edu.co.sinapsis_app.dto.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;

import java.util.List;

public interface IEmprendedorDAO {
    List<EmprendedoresView> getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorDTO emprendedorDTO) throws Exception;
}
