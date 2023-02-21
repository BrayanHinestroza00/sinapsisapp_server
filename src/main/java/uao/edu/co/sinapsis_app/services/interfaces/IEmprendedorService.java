package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorDTO;
import uao.edu.co.sinapsis_app.dto.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;

public interface IEmprendedorService {
    EmprendedoresView getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorDTO emprendedorDTO) throws Exception;
}
