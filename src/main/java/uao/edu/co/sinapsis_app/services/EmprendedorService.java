package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendedorDAO;
import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;
import uao.edu.co.sinapsis_app.services.interfaces.IEmailService;
import uao.edu.co.sinapsis_app.services.interfaces.IEmprendedorService;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

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
            String filePathFotoPerfil = storageService.store(primeraAtencion.getFotoPerfil());
            primeraAtencion.setFotoPerfilURL(filePathFotoPerfil);
        }

        if (primeraAtencion.getLogoEmpresa() != null) {
            String filePathLogoEmpresa = storageService.store(primeraAtencion.getLogoEmpresa());
            primeraAtencion.setLogoEmpresaURL(filePathLogoEmpresa);
        }

        if (primeraAtencion.getFileAutodiagnostico() != null) {
            String filePathFileAutodiagnostico = storageService.store(primeraAtencion.getFileAutodiagnostico());
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
            String filePathFotoPerfil = storageService.store(emprendedorUpdateDTO.getFotoPerfil());
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
            String filePathLogoEmpresa = storageService.store(emprendimientoUpdateDTO.getLogoEmpresa());
            emprendimientoUpdateDTO.setLogoEmpresaURL(filePathLogoEmpresa);
        }

        return emprendedorDAO.actualizarEmprendimiento(emprendimientoUpdateDTO);
    }
}
