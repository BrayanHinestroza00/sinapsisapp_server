package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendedorDAO;
import uao.edu.co.sinapsis_app.dto.AvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.IniciarAvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dto.request.RegistrarAvanceRutaDTO;
import uao.edu.co.sinapsis_app.dto.response.ResponseDTO;
import uao.edu.co.sinapsis_app.model.ActividadRuta;
import uao.edu.co.sinapsis_app.model.ActividadRutaEmp;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.SubActividadRuta;
import uao.edu.co.sinapsis_app.model.SubActividadRutaEmp;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;
import uao.edu.co.sinapsis_app.services.interfaces.IEmailService;
import uao.edu.co.sinapsis_app.services.interfaces.IEmprendedorService;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmprendedorService implements IEmprendedorService {
    @Autowired
    IEmprendedorDAO emprendedorDAO;

    @Autowired
    IStorageService storageService;

    @Autowired
    IAppService appService;

    @Autowired
    IEmailService emailService;

    @Override
    public EmprendedoresView getInformacionEmprendedor(long idUsuario) {
        List<EmprendedoresView> response = emprendedorDAO.getInformacionEmprendedor(idUsuario);

        if (response.size() > 0 ) {
            EmprendedoresView emprendedor = response.get(0);

            List<AsignaturaEmprendedor> asignaturaEmprendedor = emprendedorDAO.obtenerAsignaturasEmprendedor(idUsuario);

            emprendedor.setAsignaturasEmprendedor(asignaturaEmprendedor);

            return emprendedor;
        } else {
            return null;
        }
    }

    @Override
    public boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception {
        if (primeraAtencion.getFotoPerfil() != null) {
            Long filePathFotoPerfil = storageService.storeDB(primeraAtencion.getFotoPerfil());
            primeraAtencion.setFotoPerfilURL(filePathFotoPerfil);
        }

        if (primeraAtencion.getLogoEmpresa() != null) {
            Long filePathLogoEmpresa = storageService.storeDB(primeraAtencion.getLogoEmpresa());
            primeraAtencion.setLogoEmpresaURL(filePathLogoEmpresa);
        }

        if (primeraAtencion.getFileAutodiagnostico() != null) {
            Long filePathFileAutodiagnostico = storageService.storeDB(primeraAtencion.getFileAutodiagnostico());
            primeraAtencion.setFileAutodiagnosticoURL(filePathFileAutodiagnostico);
        }

        boolean primeraAtencionRegistrada = emprendedorDAO.registrarPrimeraAtencion(primeraAtencion);

        if (primeraAtencionRegistrada) {
            String[] destinatarios  = appService.consultarCorreosAdministradores();

            EmprendedoresView emprendedor = emprendedorDAO.getInformacionEmprendedor(primeraAtencion.getIdEmprendedor()).get(0);

            String documento = emprendedor.getAcronimoTipoDocumento() + " " + emprendedor.getNumeroDocumento();
            String correoInstitucional = emprendedor.getCorreoInstitucional() != null ? emprendedor.getCorreoInstitucional() : "N/A";
            String correoPersonal = emprendedor.getCorreoPersonal() != null ? emprendedor.getCorreoPersonal() : "N/A";

            emailService.notificarSolicitudPrimeraAtencion(
                    destinatarios,
                    emprendedor.getNombreCompleto(), documento, emprendedor.getTipoContacto(), correoInstitucional, correoPersonal,
                    primeraAtencion.getNombreEmprendimiento());

            return true;

        } else {
            return false;

        }
    }

    @Override
    public boolean actualizarEmprendedor(EmprendedorUpdateDTO emprendedorUpdateDTO) throws Exception {
        if (emprendedorUpdateDTO.getFotoPerfil() != null) {
            Long filePathFotoPerfil = storageService.storeDB(emprendedorUpdateDTO.getFotoPerfil());
            emprendedorUpdateDTO.setFotoPerfilURL(filePathFotoPerfil);
        }
        return emprendedorDAO.actualizarEmprendedor(emprendedorUpdateDTO);
    }

    @Override
    public List<Emprendimiento> obtenerEmprendimientos(String idEmprendedor) {
        return emprendedorDAO.obtenerEmprendimientos(idEmprendedor);
    }

    @Override
    public List<Emprendimiento> obtenerEmprendimiento(String idEmprendimiento) {
        return emprendedorDAO.obtenerEmprendimiento(idEmprendimiento);
    }

    @Override
    public List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento) {
        return emprendedorDAO.obtenerRedesSocialesEmprendimiento(idEmprendimiento);
    }

    @Override
    public boolean actualizarEmprendimiento(EmprendimientoUpdateDTO emprendimientoUpdateDTO) throws Exception {
        if (emprendimientoUpdateDTO.getLogoEmpresa() != null) {
            Long filePathLogoEmpresa = storageService.storeDB(emprendimientoUpdateDTO.getLogoEmpresa());
            emprendimientoUpdateDTO.setLogoEmpresaURL(filePathLogoEmpresa);
        }

        return emprendedorDAO.actualizarEmprendimiento(emprendimientoUpdateDTO);
    }

    @Override
    public ResponseDTO obtenerAvanceEnRuta(Long idProyectoEmprendimiento) {
        ResponseDTO responseDTO = new ResponseDTO();

        RutaProyectoEmprendimiento rutaProyectoEmprendimiento = emprendedorDAO.obtenerRutaProyectoEmprendimiento(idProyectoEmprendimiento);

        if (rutaProyectoEmprendimiento != null) {
            AvanceRutaDTO avanceRutaDTO = new AvanceRutaDTO();
            avanceRutaDTO.setRutaProyectoEmprendimiento(rutaProyectoEmprendimiento);

            List<SubActividadRutaEmp> subActividadRutaEmp =
                    emprendedorDAO.obtenerAvanceEnRuta(rutaProyectoEmprendimiento.getId());

            if (subActividadRutaEmp == null) {
                avanceRutaDTO.setSubActividadRutaEmp(new ArrayList<>());
            } else {
                avanceRutaDTO.setSubActividadRutaEmp(subActividadRutaEmp);
            }

            responseDTO.setResponse(avanceRutaDTO);

        } else {
            responseDTO.setCode(0);
            responseDTO.setMessage("Sin etapa en la ruta de innovación y emprendimiento asociada");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO iniciarAvanceEnRuta(IniciarAvanceRutaDTO iniciarAvanceRutaDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        SubActividadRutaEmp subActividadRutaEmp =
                emprendedorDAO.buscarSubActividadRutaEmp(iniciarAvanceRutaDTO.getIdRutaProyecto(), iniciarAvanceRutaDTO.getIdSubActividadRuta());

        if (subActividadRutaEmp == null) {
            SubActividadRutaEmp newSubActividadRutaEmp = new SubActividadRutaEmp();
            newSubActividadRutaEmp.setEstadoActividad("PENDIENTE");
            newSubActividadRutaEmp.setSubActividadRutaId(iniciarAvanceRutaDTO.getIdSubActividadRuta());
            newSubActividadRutaEmp.setRutaEmprendimientoId(iniciarAvanceRutaDTO.getIdRutaProyecto());
            newSubActividadRutaEmp.setFechaEstadoActividad(new Date());
            newSubActividadRutaEmp.setFechaCreacion(new Date());
            newSubActividadRutaEmp.setFechaModificacion(new Date());

            boolean registrado = emprendedorDAO.almacenarAvanceEnRuta(newSubActividadRutaEmp);

            if (registrado) {
                responseDTO.setCode(1);
                responseDTO.setMessage("OK");

            } else {
                responseDTO.setCode(0);
                responseDTO.setMessage("Ha fallado el registro, intente nuevamente");
            }
        } else {
            responseDTO.setCode(0);
            responseDTO.setMessage("Ya iniciado, intente nuevamente");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO continuarAvanceEnRuta(RegistrarAvanceRutaDTO registrarAvanceRutaDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        SubActividadRutaEmp subActividadRutaEmp =
                emprendedorDAO.buscarSubActividadRutaEmp(registrarAvanceRutaDTO.getIdRutaProyecto(), registrarAvanceRutaDTO.getIdSubActividadRuta());

        if (subActividadRutaEmp != null) {
            if (subActividadRutaEmp.getEstadoActividad().equalsIgnoreCase("PENDIENTE")) {
                subActividadRutaEmp.setEstadoActividad("COMPLETADO");
                subActividadRutaEmp.setSubActividadRutaId(registrarAvanceRutaDTO.getIdSubActividadRuta());
                subActividadRutaEmp.setRutaEmprendimientoId(registrarAvanceRutaDTO.getIdRutaProyecto());
                subActividadRutaEmp.setUrlEvidenciaActividad(registrarAvanceRutaDTO.getEvidencia());
                subActividadRutaEmp.setFechaEvidencia(new Date());
                subActividadRutaEmp.setFechaEstadoActividad(new Date());
                subActividadRutaEmp.setFechaModificacion(new Date());

                boolean registrado = emprendedorDAO.almacenarAvanceEnRuta(subActividadRutaEmp);

                if (registrado) {
                    generarNuevoRegistroAvance(subActividadRutaEmp);

                    responseDTO.setCode(1);
                    responseDTO.setMessage("OK");

                } else {
                    responseDTO.setCode(0);
                    responseDTO.setMessage("Ha fallado el registro, intente nuevamente");
                }
            } else {
                responseDTO.setCode(0);
                responseDTO.setMessage("Ya ha sido realizada");
            }
        } else {
            responseDTO.setCode(0);
            responseDTO.setMessage("No encontrado");
        }

        return responseDTO;
    }

    private boolean generarNuevoRegistroAvance(SubActividadRutaEmp subActividadRutaEmp) {
        boolean result = false;
        List<SubActividadRuta> actividadRutas = emprendedorDAO.buscarSubActividadRutas(subActividadRutaEmp.getRutaEmprendimientoId());

        if (actividadRutas != null && !actividadRutas.isEmpty()) {
            if (actividadRutas.get(0).getId() > subActividadRutaEmp.getSubActividadRutaId()) {
                SubActividadRutaEmp newSubActividadRutaEmp = new SubActividadRutaEmp();
                newSubActividadRutaEmp.setEstadoActividad("PENDIENTE");
                newSubActividadRutaEmp.setSubActividadRutaId(subActividadRutaEmp.getSubActividadRutaId() + 1);
                newSubActividadRutaEmp.setRutaEmprendimientoId(subActividadRutaEmp.getRutaEmprendimientoId());
                newSubActividadRutaEmp.setFechaEstadoActividad(new Date());
                newSubActividadRutaEmp.setFechaCreacion(new Date());
                newSubActividadRutaEmp.setFechaModificacion(new Date());

                result = emprendedorDAO.almacenarAvanceEnRuta(newSubActividadRutaEmp);
            } else if (actividadRutas.get(0).getId() == subActividadRutaEmp.getSubActividadRutaId()) {
                RutaProyectoEmprendimiento rutaProyectoEmprendimiento =
                        emprendedorDAO.obtenerRutaProyectoEmprendimientoById(subActividadRutaEmp.getRutaEmprendimientoId());

                rutaProyectoEmprendimiento.setEstadoRuta("PENDIENTE_APROBAR");
                rutaProyectoEmprendimiento.setFechaEstadoRuta(new Date());

                result = emprendedorDAO.almacenarRutaProyectoEmprendimiento(rutaProyectoEmprendimiento);
            }

            if (subActividadRutaEmp.getSubActividadRutaId() == 3L || subActividadRutaEmp.getSubActividadRutaId() == 5L
                || subActividadRutaEmp.getSubActividadRutaId() == 8L || subActividadRutaEmp.getSubActividadRutaId() == 10L
                    || subActividadRutaEmp.getSubActividadRutaId() == 12L || subActividadRutaEmp.getSubActividadRutaId() == 14L
                    || subActividadRutaEmp.getSubActividadRutaId() == 16L || subActividadRutaEmp.getSubActividadRutaId() == 18L) {
                ActividadRutaEmp actividadRutaEmp =
                        emprendedorDAO.buscarActividadRutaEmp(
                                subActividadRutaEmp.getSubActividadRutaId(),
                                subActividadRutaEmp.getRutaEmprendimientoId());

                if (actividadRutaEmp != null) {
                 actividadRutaEmp.setEstado("COMPLETADO");
                 actividadRutaEmp.setFechaModificacion(new Date());

                 emprendedorDAO.almacenarActividadRuta(actividadRutaEmp);

                 if (subActividadRutaEmp.getSubActividadRutaId() != 5L &&
                         subActividadRutaEmp.getSubActividadRutaId() != 8L &&
                         subActividadRutaEmp.getSubActividadRutaId() != 14L &&
                         subActividadRutaEmp.getSubActividadRutaId() != 18L) {
                     ActividadRutaEmp newActividadRutaEmp =
                             new ActividadRutaEmp(
                                     actividadRutaEmp.getActividadRutaEmpId().getIdRutaEmprendimiento(),
                                     actividadRutaEmp.getActividadRutaEmpId().getIdActividad() + 1,
                                     "PENDIENTE",
                                     new Date(),
                                     new Date());
                     emprendedorDAO.almacenarActividadRuta(newActividadRutaEmp);
                 }

                }
            }
        }
        return result;
    }

}
