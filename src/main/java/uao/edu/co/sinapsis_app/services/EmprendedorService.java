package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dto.EmprendedorDTO;
import uao.edu.co.sinapsis_app.dto.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendedorDAO;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.services.interfaces.IEmprendedorService;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

import java.util.List;

@Service
public class EmprendedorService implements IEmprendedorService {
    @Autowired
    IEmprendedorDAO emprendedorDAO;

    @Autowired
    IStorageService storageService;

    @Override
    public EmprendedoresView getInformacionEmprendedor(long idUsuario) {
        List<EmprendedoresView> response = emprendedorDAO.getInformacionEmprendedor(idUsuario);

        if (response.size() > 0 ) {
//            Object data = response.get(0);
//            Object[] dataArray = (Object[]) data;
//            return new Emprendedor(/*dataArray*/);

            return response.get(0);
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
            primeraAtencion.setFotoPerfilURL(filePathLogoEmpresa);
        }

        if (primeraAtencion.getFileAutodiagnostico() != null) {
            String filePathFileAutodiagnostico = storageService.store(primeraAtencion.getFileAutodiagnostico());
            primeraAtencion.setFileAutodiagnosticoURL(filePathFileAutodiagnostico);
        }

        return emprendedorDAO.registrarPrimeraAtencion(primeraAtencion);
    }

    @Override
    public boolean actualizarEmprendedor(EmprendedorDTO emprendedorDTO) throws Exception {
        if (emprendedorDTO.getFotoPerfil() != null) {
            String filePathFotoPerfil = storageService.store(emprendedorDTO.getFotoPerfil());
            emprendedorDTO.setFotoPerfilURL(filePathFotoPerfil);
        }
        return emprendedorDAO.actualizarEmprendedor(emprendedorDTO);
    }
}
