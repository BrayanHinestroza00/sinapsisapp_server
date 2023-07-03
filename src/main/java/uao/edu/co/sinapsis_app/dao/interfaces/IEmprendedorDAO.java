package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.SubActividadRuta;
import uao.edu.co.sinapsis_app.model.SubActividadRutaEmp;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import java.util.List;

public interface IEmprendedorDAO {
    List<EmprendedoresView> getInformacionEmprendedor(long idUsuario);

    boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception;

    boolean actualizarEmprendedor(EmprendedorUpdateDTO emprendedorUpdateDTO) throws Exception;

    List<AsignaturaEmprendedor> obtenerAsignaturasEmprendedor(long idUsuario);

    List<Emprendimiento> obtenerEmprendimientos(String idEmprendedor);

    List<Emprendimiento> obtenerEmprendimiento(String idEmprendimiento);

    List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento);

    boolean actualizarEmprendimiento(EmprendimientoUpdateDTO emprendimientoUpdateDTO) throws Exception;

    List<SubActividadRutaEmp> obtenerAvanceEnRuta(Long idRutaEmprendimiento);

    RutaProyectoEmprendimiento obtenerRutaProyectoEmprendimiento(Long idProyectoEmprendimiento);
    RutaProyectoEmprendimiento obtenerRutaProyectoEmprendimientoById(Long idRutaProyecto);

    boolean almacenarAvanceEnRuta(SubActividadRutaEmp subActividadRutaEmp);
    boolean almacenarRutaProyectoEmprendimiento(RutaProyectoEmprendimiento rutaProyectoEmprendimiento);

    SubActividadRutaEmp buscarSubActividadRutaEmp(Long idRutaProyecto, Long idSubActividadRuta);

    List<SubActividadRuta> buscarSubActividadRutas(Long idRutaProyecto);

}
