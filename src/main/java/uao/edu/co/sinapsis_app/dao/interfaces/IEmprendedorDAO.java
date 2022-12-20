package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.beans.EmprendedorDTO;
import uao.edu.co.sinapsis_app.beans.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.Emprendedor;

import java.util.List;

public interface IEmprendedorDAO {
    List<Emprendedor> getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorDTO emprendedorDTO) throws Exception;
}
