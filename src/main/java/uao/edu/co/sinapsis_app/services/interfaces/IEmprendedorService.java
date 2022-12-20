package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.beans.EmprendedorDTO;
import uao.edu.co.sinapsis_app.beans.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.Emprendedor;

public interface IEmprendedorService {
    Emprendedor getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorDTO emprendedorDTO) throws Exception;
}
