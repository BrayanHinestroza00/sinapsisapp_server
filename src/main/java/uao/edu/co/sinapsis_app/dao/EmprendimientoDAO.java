package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dto.EmprendimientoDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static uao.edu.co.sinapsis_app.util.AppUtil.getFormatoFecha;
import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Repository
public class EmprendimientoDAO implements IEmprendimientoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean actualizarEmprendimiento(EmprendimientoDTO emprendimientoDTO) throws Exception {
        Emprendimiento emprendimiento = entityManager.find(Emprendimiento.class, emprendimientoDTO.getIdEmprendimiento());

        if (emprendimiento != null) {
            if (emprendimientoDTO.getNombreEmprendimiento() != null) {
                emprendimiento.setNombreEmprendimiento(emprendimientoDTO.getNombreEmprendimiento());
            }
            if (emprendimientoDTO.getNombreEmpresa() != null) {
                emprendimiento.setNombreEmpresa(emprendimientoDTO.getNombreEmpresa());
            }
            if (emprendimientoDTO.getSectorEmprendimiento() != null) {
                emprendimiento.setSectorEmprendimiento(emprendimientoDTO.getSectorEmprendimiento());
            }
            if (emprendimientoDTO.getSitioWebEmpresa() != null) {
                emprendimiento.setSitioWeb(emprendimientoDTO.getSitioWebEmpresa());
            }
            if (emprendimientoDTO.getEstaConstituida() != null) {
                emprendimiento.setEstaConstituida(emprendimientoDTO.getEstaConstituida());
            }
            if (emprendimientoDTO.getFechaConstitucion() != null) {
                emprendimiento.setFechaConstitucion(getFormatoFecha(emprendimientoDTO.getFechaConstitucion(), APP_DATE_OUT_FORMAT));
            }
            if (emprendimientoDTO.getNitEmpresa() != null) {
                emprendimiento.setNit(emprendimientoDTO.getNitEmpresa());
            }
            if (emprendimientoDTO.getRazonSocialEmpresa() != null) {
                emprendimiento.setRazonSocial(emprendimientoDTO.getRazonSocialEmpresa());
            }
            if (emprendimientoDTO.getDescripcionProducto() != null) {
                emprendimiento.setDescripcionProducto(emprendimientoDTO.getDescripcionProducto());
            }
            if (emprendimientoDTO.getMateriasPrimas() != null) {
                emprendimiento.setMateriasPrimas(emprendimientoDTO.getMateriasPrimas());
            }
            if (emprendimientoDTO.getDescripcionClientes() != null) {
                emprendimiento.setDescripcionClientes(emprendimientoDTO.getDescripcionClientes());
            }
            if (emprendimientoDTO.getEnfoqueSocial() != null) {
                emprendimiento.setEnfoqueSocial(emprendimientoDTO.getEnfoqueSocial());
            }

            if (emprendimientoDTO.getLogoEmpresaURL() != null) {
                emprendimiento.setUrlLogoEmpresa(emprendimientoDTO.getLogoEmpresaURL());
            }
//            if (emprendimientoDTO.getFileAutodiagnosticoURL() != null) {
//                emprendimiento.setUrlFileAutodiagnostico(emprendimientoDTO.getFileAutodiagnosticoURL());
//            }

            Emprendimiento updatedEmprendimiento = entityManager.merge(emprendimiento);

            if (updatedEmprendimiento == null) {
                throw new Exception("Problema al almacenar el emprendimiento");
            }
        } else {
            throw new Exception("Problema al encontrar el emprendimiento");
        }
        return true;
    }

    @Override
    public Emprendimiento registrarEmprendimiento(Emprendimiento emprendimiento) throws Exception {
        Emprendimiento isRegistered = entityManager.merge(emprendimiento);

        if (isRegistered == null) {
            throw new Exception("Problema al almacenar el emprendimiento");
        }

        return isRegistered;
    }
}
