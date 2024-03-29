package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.IniciarAvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.RegistrarAvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import java.util.List;

public interface IEmprendedorService {
    EmprendedoresView getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorUpdateDTO emprendedorUpdateDTO) throws Exception;

    List<Emprendimiento> obtenerEmprendimientos(String idEmprendedor);
    List<Emprendimiento> obtenerEmprendimiento(String idEmprendimiento);
    List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento);
    boolean actualizarEmprendimiento(EmprendimientoUpdateDTO emprendimientoUpdateDTO) throws Exception;

    ResponseDTO obtenerAvanceEnRuta(Long idProyectoEmprendimiento);

    ResponseDTO iniciarAvanceEnRuta(IniciarAvanceRutaDTO iniciarAvanceRutaDTO);

    ResponseDTO continuarAvanceEnRuta(RegistrarAvanceRutaDTO registrarAvanceRutaDTO);
}
