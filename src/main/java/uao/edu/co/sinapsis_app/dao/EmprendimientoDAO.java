package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dto.EmprendimientoDTO;
import uao.edu.co.sinapsis_app.dto.RedSocialDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.RedSocialEmprendimiento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static uao.edu.co.sinapsis_app.util.AppUtil.getFormatoFecha;
import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Repository
public class EmprendimientoDAO implements IEmprendimientoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean actualizarEmprendimiento(EmprendimientoUpdateDTO emprendimientoDTO) throws Exception {
        Emprendimiento emprendimiento = entityManager.find(Emprendimiento.class, emprendimientoDTO.getIdEmprendimiento());

        if (emprendimiento != null) {
            if (emprendimientoDTO.getNombreEmprendimiento() != null) {
                emprendimiento.setNombreEmprendimiento(emprendimientoDTO.getNombreEmprendimiento());
            }
            if (emprendimientoDTO.getNecesidadesIdentificadas() != null) {
                emprendimiento.setNecesidadesIdentificadas(emprendimientoDTO.getNecesidadesIdentificadas());
            }
            if (emprendimientoDTO.getSectorEmprendimiento() != null) {
                emprendimiento.setSectorEmprendimiento(emprendimientoDTO.getSectorEmprendimiento());
            }

            if (emprendimientoDTO.getSectorEmprendimiento() != null) {
                emprendimiento.setSectorEmprendimiento(emprendimientoDTO.getSectorEmprendimiento());
            }
            if (emprendimientoDTO.getSitioWebEmpresa() != null) {
                emprendimiento.setSitioWeb(emprendimientoDTO.getSitioWebEmpresa());
            }
            if (emprendimientoDTO.getEstaConstituida() != null) {
                emprendimiento.setEstaConstituida(emprendimientoDTO.getEstaConstituida());
                if (emprendimiento.getEstaConstituida().equalsIgnoreCase("S")) {
                    if (emprendimientoDTO.getFechaConstitucion() != null) {
                        emprendimiento.setFechaConstitucion(getFormatoFecha(emprendimientoDTO.getFechaConstitucion(), APP_DATE_OUT_FORMAT));
                    }
                    if (emprendimientoDTO.getNitEmpresa() != null) {
                        emprendimiento.setNit(emprendimientoDTO.getNitEmpresa());
                    }
                    if (emprendimientoDTO.getRazonSocialEmpresa() != null) {
                        emprendimiento.setRazonSocial(emprendimientoDTO.getRazonSocialEmpresa());
                    }
                    if (emprendimientoDTO.getLogoEmpresaURL() != null) {
                        emprendimiento.setUrlLogoEmpresa(emprendimientoDTO.getLogoEmpresaURL());
                    }
                    if (emprendimientoDTO.getNombreEmpresa() != null) {
                        emprendimiento.setNombreEmpresa(emprendimientoDTO.getNombreEmpresa());
                    }
                }
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

            if (emprendimientoDTO.getRedesSociales() != null && emprendimientoDTO.getRedesSociales().length > 0 ) {
                deleteRedesSocialesEmprendimiento(emprendimientoDTO.getIdEmprendimiento());

                for (int i = 0; i < emprendimientoDTO.getRedesSociales().length; i++ ) {
                    RedSocialDTO redSocialDTO = emprendimientoDTO.getRedesSociales()[i];
                    if (redSocialDTO.getEnlace().length() > 0) {
                        RedSocialEmprendimiento redSocialEmprendimiento
                                = new RedSocialEmprendimiento(
                                emprendimientoDTO.getIdEmprendimiento(),
                                redSocialDTO.getIdRedSocial(),
                                redSocialDTO.getEnlace());

                        entityManager.persist(redSocialEmprendimiento);
                    }
                }
            }

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
    public Emprendimiento registrarEmprendimiento(EmprendimientoDTO emprendimientoDTO) throws Exception {
        // Se actualiza informacion del emprendimiento

        Emprendimiento emprendimiento = new Emprendimiento();

        if (emprendimientoDTO.getNombreEmprendimiento() != null) {
            emprendimiento.setNombreEmprendimiento(emprendimientoDTO.getNombreEmprendimiento());
        }

        if (emprendimientoDTO.getSectorEmprendimiento() != null) {
            emprendimiento.setSectorEmprendimiento(emprendimientoDTO.getSectorEmprendimiento());
        }

        if (emprendimientoDTO.getSitioWebEmpresa() != null) {
            emprendimiento.setSitioWeb(emprendimientoDTO.getSitioWebEmpresa());
        }

        if (emprendimientoDTO.getEstaConstituida() != null) {
            emprendimiento.setEstaConstituida(emprendimientoDTO.getEstaConstituida());
            if (emprendimiento.getEstaConstituida().equalsIgnoreCase("S")) {
                if (emprendimientoDTO.getFechaConstitucion() != null) {
                    emprendimiento.setFechaConstitucion(emprendimientoDTO.getFechaConstitucion());
                }
                if (emprendimientoDTO.getNitEmpresa() != null) {
                    emprendimiento.setNit(emprendimientoDTO.getNitEmpresa());
                }

                if (emprendimientoDTO.getRazonSocialEmpresa() != null) {
                    emprendimiento.setRazonSocial(emprendimientoDTO.getRazonSocialEmpresa());
                }

                if (emprendimientoDTO.getLogoEmpresaURL() != null) {
                    emprendimiento.setUrlLogoEmpresa(emprendimientoDTO.getLogoEmpresaURL());
                }

                if (emprendimientoDTO.getNombreEmpresa() != null) {
                    emprendimiento.setNombreEmpresa(emprendimientoDTO.getNombreEmpresa());
                }
            }
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

        if (emprendimientoDTO.getNecesidadIdentificada() != null) {
            emprendimiento.setNecesidadesIdentificadas(emprendimientoDTO.getNecesidadIdentificada());
        }

        Emprendimiento isRegistered = entityManager.merge(emprendimiento);

        if (isRegistered == null) {
            throw new Exception("Problema al almacenar el emprendimiento");
        }

        if (emprendimientoDTO.getRedesSociales() != null && emprendimientoDTO.getRedesSociales().length > 0 ) {
            for (int i = 0; i < emprendimientoDTO.getRedesSociales().length; i++ ) {
                RedSocialDTO redSocialDTO = emprendimientoDTO.getRedesSociales()[i];
                if (redSocialDTO.getEnlace().length() > 0) {
                    RedSocialEmprendimiento redSocialEmprendimiento
                            = new RedSocialEmprendimiento(
                            isRegistered.getId(),
                            redSocialDTO.getIdRedSocial(),
                            redSocialDTO.getEnlace());

                    entityManager.persist(redSocialEmprendimiento);
                }
            }
        }

        return isRegistered;
    }

    private boolean deleteRedesSocialesEmprendimiento (Long idEmprendimiento) {
        String sql = "DELETE T_SINAPSIS_EMPREN_RED_SOCIAL WHERE EMPRENDIMIENTOS_ID = " + idEmprendimiento;

        Query query = entityManager.createNativeQuery(sql);

        int result = query.executeUpdate();

        entityManager.flush();

        return result > 0;
    }
}
