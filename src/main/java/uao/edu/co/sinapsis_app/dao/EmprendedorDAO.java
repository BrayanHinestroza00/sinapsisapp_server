package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendedorDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IPrimeraAtencionDAO;
import uao.edu.co.sinapsis_app.dao.interfaces.IProyectoEmprendimientoDAO;
import uao.edu.co.sinapsis_app.dto.EmprendedorUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Emprendedor;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.PrimeraAtencion;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.Usuario;
import uao.edu.co.sinapsis_app.model.embeddable.AsignaturaEmprendedorId;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.AppUtil.getFormatoFecha;
import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_EMPRENDEDORES_PRIMERA_VEZ_FALSE;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_PROY_EMPRENDIMIENTO_DEFAULT_ESTADO;

@Repository
public class EmprendedorDAO implements IEmprendedorDAO {
    @Autowired
    private IEmprendimientoDAO emprendimientoDAO;

    @Autowired
    private IPrimeraAtencionDAO primeraAtencionDAO;

    @Autowired
    private IProyectoEmprendimientoDAO proyectoEmprendimientoDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EmprendedoresView> getInformacionEmprendedor(long idUsuario) {
        String sql = "SELECT vse.* FROM V_SINAPSIS_EMPRENDEDORES vse WHERE vse.ID = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql, EmprendedoresView.class);
        return (List<EmprendedoresView>) q.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencionDTO) throws Exception {
        EmprendedorUpdateDTO emprendedorUpdateDTO = new EmprendedorUpdateDTO();

        emprendedorUpdateDTO.setIdEmprendedor(primeraAtencionDTO.getIdEmprendedor());
        // Datos de usuario
        if (primeraAtencionDTO.getTelefonoContacto() != null) {
            emprendedorUpdateDTO.setTelefonoContacto(primeraAtencionDTO.getTelefonoContacto());
        }
        if (primeraAtencionDTO.getFotoPerfil() != null) {
            emprendedorUpdateDTO.setFotoPerfilURL(primeraAtencionDTO.getFotoPerfilURL());
        }
        if (primeraAtencionDTO.getCorreoPersonal() != null) {
            emprendedorUpdateDTO.setCorreoPersonal(primeraAtencionDTO.getCorreoPersonal());
        }

        // Datos de Emprendedor
        if (primeraAtencionDTO.getGenero() != null) {
            emprendedorUpdateDTO.setGenero(primeraAtencionDTO.getGenero());
        }

        if (primeraAtencionDTO.getDireccion() != null) {
            emprendedorUpdateDTO.setDireccion(primeraAtencionDTO.getDireccion());
        }

        if (primeraAtencionDTO.getFechaNacimiento() != null) {
            emprendedorUpdateDTO.setFechaNacimiento(primeraAtencionDTO.getFechaNacimiento());
        }

        if (primeraAtencionDTO.getMunicipio() != null) {
            emprendedorUpdateDTO.setMunicipio(primeraAtencionDTO.getMunicipio());
        }

        if (primeraAtencionDTO.getTipoContacto() != null) {
            emprendedorUpdateDTO.setTipoContacto(primeraAtencionDTO.getTipoContacto());
        }

        if (primeraAtencionDTO.getCodigoEstudiantil() != null) {
            emprendedorUpdateDTO.setCodigoEstudiantil(primeraAtencionDTO.getCodigoEstudiantil());
        }

        if (primeraAtencionDTO.getNivelAcademico() != null) {
            emprendedorUpdateDTO.setNivelAcademico(primeraAtencionDTO.getNivelAcademico());
        }

        if (primeraAtencionDTO.getProgramaAcademico() != null) {
            emprendedorUpdateDTO.setProgramaAcademico(primeraAtencionDTO.getProgramaAcademico());
        }

        if (primeraAtencionDTO.getOtroProgramaAcademico() != null) {
            emprendedorUpdateDTO.setOtroProgramaAcademico(primeraAtencionDTO.getOtroProgramaAcademico());
        }

        if (primeraAtencionDTO.getModalidadTrabajoGrado() != null) {
            emprendedorUpdateDTO.setModalidadTrabajoGrado(primeraAtencionDTO.getModalidadTrabajoGrado());
        }

        if (primeraAtencionDTO.getCursosEmprendimiento() != null &&
                primeraAtencionDTO.getCursosEmprendimiento().size() > 0) {
            emprendedorUpdateDTO.setCursosEmprendimiento(primeraAtencionDTO.getCursosEmprendimiento());
        }

        if (primeraAtencionDTO.getDependenciaColaborador() != null) {
            emprendedorUpdateDTO.setDependenciaColaborador(primeraAtencionDTO.getDependenciaColaborador());
        }

        if (primeraAtencionDTO.getCargoColaborador() != null) {
            emprendedorUpdateDTO.setCargoColaborador(primeraAtencionDTO.getCargoColaborador());
        }

        emprendedorUpdateDTO.setPrimeraVez(T_SINAPSIS_EMPRENDEDORES_PRIMERA_VEZ_FALSE);

        boolean usuarioUpdated = actualizarEmprendedor(emprendedorUpdateDTO);


        if (!usuarioUpdated) {
            throw new Exception("Problema al actualizar el emprendedor");
        }

        // Se actualiza informacion del emprendimiento

        Emprendimiento emprendimiento = new Emprendimiento();

        if (primeraAtencionDTO.getNombreEmprendimiento() != null) {
            emprendimiento.setNombreEmprendimiento(primeraAtencionDTO.getNombreEmprendimiento());
        }

        if (primeraAtencionDTO.getNombreEmpresa() != null) {
            emprendimiento.setNombreEmpresa(primeraAtencionDTO.getNombreEmpresa());
        }

        if (primeraAtencionDTO.getSectorEmprendimiento() != null) {
            emprendimiento.setSectorEmprendimiento(primeraAtencionDTO.getSectorEmprendimiento());
        }

        if (primeraAtencionDTO.getSitioWebEmpresa() != null) {
            emprendimiento.setSitioWeb(primeraAtencionDTO.getSitioWebEmpresa());
        }

        if (primeraAtencionDTO.getEstaConstituida() != null) {
            emprendimiento.setEstaConstituida(primeraAtencionDTO.getEstaConstituida());
        }
        if (primeraAtencionDTO.getFechaConstitucion() != null) {
            emprendimiento.setFechaConstitucion(getFormatoFecha(primeraAtencionDTO.getFechaConstitucion(), APP_DATE_OUT_FORMAT));
        }
        if (primeraAtencionDTO.getNitEmpresa() != null) {
            emprendimiento.setNit(primeraAtencionDTO.getNitEmpresa());
        }

        if (primeraAtencionDTO.getRazonSocialEmpresa() != null) {
            emprendimiento.setRazonSocial(primeraAtencionDTO.getRazonSocialEmpresa());
        }

        if (primeraAtencionDTO.getLogoEmpresa() != null) {
            emprendimiento.setNombreEmprendimiento(primeraAtencionDTO.getLogoEmpresaURL());
        }

        if (primeraAtencionDTO.getDescripcionProducto() != null) {
            emprendimiento.setDescripcionProducto(primeraAtencionDTO.getDescripcionProducto());
        }

        if (primeraAtencionDTO.getMateriasPrimas() != null) {
            emprendimiento.setMateriasPrimas(primeraAtencionDTO.getMateriasPrimas());
        }

        if (primeraAtencionDTO.getDescripcionClientes() != null) {
            emprendimiento.setDescripcionClientes(primeraAtencionDTO.getDescripcionClientes());
        }

        if (primeraAtencionDTO.getEnfoqueSocial() != null) {
            emprendimiento.setEnfoqueSocial(primeraAtencionDTO.getEnfoqueSocial());
        }

        if (primeraAtencionDTO.getNecesidadIdentificada() != null) {
            emprendimiento.setNecesidadesIdentificadas(primeraAtencionDTO.getNecesidadIdentificada());
        }

        Emprendimiento updatedEmprendimiento = emprendimientoDAO.registrarEmprendimiento(emprendimiento);

        if (updatedEmprendimiento == null) {
            throw new Exception("Problema al almacenar el emprendimiento");
        }

        // Datos de primera atencion
        PrimeraAtencion primeraAtencion = new PrimeraAtencion();

        if (primeraAtencionDTO.getNombreProducto() != null) {
            primeraAtencion.setNombreProducto(primeraAtencionDTO.getNombreProducto());
        }

        if (primeraAtencionDTO.getPromedioVentas() != null) {
            primeraAtencion.setPromedioVentas(primeraAtencionDTO.getPromedioVentas());
        }

        if (primeraAtencionDTO.getEvidenciasProducto() != null) {
            primeraAtencion.setEvidenciasProducto(primeraAtencionDTO.getEvidenciasProducto());
        }

        if (primeraAtencionDTO.getObtencionMateriasPrimas() != null) {
            primeraAtencion.setMateriasPrimas(primeraAtencionDTO.getObtencionMateriasPrimas());
        }

        if (primeraAtencionDTO.getEquipoTrabajo() != null) {
            primeraAtencion.setEquipoTrabajo(primeraAtencionDTO.getEquipoTrabajo());
        }

        if (primeraAtencionDTO.getCualEquipoTrabajo() != null) {
            primeraAtencion.setCualEquipoTrabajo(primeraAtencionDTO.getCualEquipoTrabajo());
        }

        if (primeraAtencionDTO.getDedicacion() != null) {
            primeraAtencion.setDedicacion(primeraAtencionDTO.getDedicacion());
        }

        if (primeraAtencionDTO.getDesdeFechaEjecucion() != null) {
            primeraAtencion.setDesdeFechaEjecucion(primeraAtencionDTO.getDesdeFechaEjecucion());
        }

        if (primeraAtencionDTO.getHorasSemanales() != null) {
            primeraAtencion.setHorasSemanales(primeraAtencionDTO.getHorasSemanales());
        }

        if (primeraAtencionDTO.getMotivacion() != null) {
            primeraAtencion.setMotivacion(primeraAtencionDTO.getMotivacion());
        }

        if (primeraAtencionDTO.getDescubrioSinapsis() != null) {
            primeraAtencion.setDescubrioSinapsis(primeraAtencionDTO.getDescubrioSinapsis());
        }

        if (primeraAtencionDTO.getFileAutodiagnosticoURL() != null) {
            primeraAtencion.setFileAutodiagnosticoURL(primeraAtencionDTO.getFileAutodiagnosticoURL());
        }

        primeraAtencion.setCreatedAt(new Date());
        primeraAtencion.setUpdatedAt(new Date());


        PrimeraAtencion updatedPrimeraAtencion = primeraAtencionDAO.registrarPrimeraAtencion(primeraAtencion);

        if (updatedPrimeraAtencion == null) {
            throw new Exception("Problema al registrar la primera atencion");
        }

        ProyectoEmprendimiento proyectoEmprendimiento = new ProyectoEmprendimiento();

        proyectoEmprendimiento.setEmprendimiento(updatedEmprendimiento.getId());
        proyectoEmprendimiento.setEmprendedor(emprendedorUpdateDTO.getIdEmprendedor());
        proyectoEmprendimiento.setPrimeraAtencion(updatedPrimeraAtencion.getId());
        proyectoEmprendimiento.setEstadoEmprendimiento(T_SINAPSIS_PROY_EMPRENDIMIENTO_DEFAULT_ESTADO);
        proyectoEmprendimiento.setFechaCreacion(new Date());
        proyectoEmprendimiento.setFechaModificacion(new Date());

        ProyectoEmprendimiento updatedProyectoEmprendimiento = proyectoEmprendimientoDAO.registrarProyectoEmprendimiento(proyectoEmprendimiento);

        if (updatedProyectoEmprendimiento == null) {
            throw new Exception("Problema al registrar el proyecto de emprendimiento");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean actualizarEmprendedor(EmprendedorUpdateDTO emprendedorUpdateDTO) throws Exception {
        Usuario usuario = entityManager.find(Usuario.class, emprendedorUpdateDTO.getIdEmprendedor());

        if (usuario != null) {
            if (emprendedorUpdateDTO.getTelefonoContacto() != null) {
                usuario.setTelefonoContacto(emprendedorUpdateDTO.getTelefonoContacto());
            }
            if (emprendedorUpdateDTO.getFotoPerfil() != null) {
                usuario.setFotoUrl(emprendedorUpdateDTO.getFotoPerfilURL());
            }
            if (emprendedorUpdateDTO.getCorreoPersonal() != null) {
                usuario.setCorreoPersonal(emprendedorUpdateDTO.getCorreoPersonal());
            }

            Usuario updatedUser = entityManager.merge(usuario);

            if (updatedUser == null) {
                throw new Exception("Problema al almacenar el usuario");
            }
        } else {
            throw new Exception("Problema al encontrar el usuario");
        }

        Emprendedor emprendedor = entityManager.find(Emprendedor.class, emprendedorUpdateDTO.getIdEmprendedor());

        if (emprendedor != null) {
            if (emprendedorUpdateDTO.getGenero() != null) {
                emprendedor.setGenero(emprendedorUpdateDTO.getGenero());
            }

            if (emprendedorUpdateDTO.getDireccion() != null) {
                emprendedor.setDireccion(emprendedorUpdateDTO.getDireccion());
            }

            if (emprendedorUpdateDTO.getFechaNacimiento() != null) {
                emprendedor.setFechaNacimiento(getFormatoFecha(emprendedorUpdateDTO.getFechaNacimiento(), APP_DATE_OUT_FORMAT));
            }

            if (emprendedorUpdateDTO.getCodigoEstudiantil() != null) {
                emprendedor.setCodigoEstudiantil(emprendedorUpdateDTO.getCodigoEstudiantil());
            }

            if (emprendedorUpdateDTO.getTipoContacto() != null) {
                emprendedor.setTipoContacto(emprendedorUpdateDTO.getTipoContacto());
            }

            if (emprendedorUpdateDTO.getNivelAcademico() != null) {
                emprendedor.setNivelAcademico(emprendedorUpdateDTO.getNivelAcademico());
            }

            if (emprendedorUpdateDTO.getModalidadTrabajoGrado() != null) {
                emprendedor.setModalidadTrabajoGrado(emprendedorUpdateDTO.getModalidadTrabajoGrado());
            }

            if (emprendedorUpdateDTO.getDependenciaColaborador() != null) {
                emprendedor.setDependencia(emprendedorUpdateDTO.getDependenciaColaborador());
            }

            if (emprendedorUpdateDTO.getCargoColaborador() != null) {
                emprendedor.setCargo(emprendedorUpdateDTO.getCargoColaborador());
            }

            if (emprendedorUpdateDTO.getMunicipio() != null) {
                emprendedor.setMunicipio(emprendedorUpdateDTO.getMunicipio());
            }

            if (emprendedorUpdateDTO.getProgramaAcademico() != null) {
                emprendedor.setProgramaAcademico(emprendedorUpdateDTO.getProgramaAcademico());
            }

            if (emprendedorUpdateDTO.getOtroProgramaAcademico() != null) {
                emprendedor.setOtroProgramaAcademico(emprendedorUpdateDTO.getOtroProgramaAcademico());
            }

            if (emprendedorUpdateDTO.getPrimeraVez() != null) {
                emprendedor.setPrimeraVez(emprendedorUpdateDTO.getPrimeraVez());
            }

            Emprendedor updatedEmprendedor = entityManager.merge(emprendedor);

            if (updatedEmprendedor == null) {
                throw new Exception("Problema al almacenar el Emprendedor");
            }

            /**
             * Logica para almacenar Cursos de Emprendimiento
             */
            if (emprendedorUpdateDTO.getCursosEmprendimiento() != null &&
                    emprendedorUpdateDTO.getCursosEmprendimiento().size() > 0 ) {
                String sqlDelete = "DELETE FROM T_SINAPSIS_ASIG_EMPRENDEDOR WHERE EMPRENDEDORES_ID = " + emprendedorUpdateDTO.getIdEmprendedor();
                Query queryDelete = entityManager.createNativeQuery(sqlDelete);

                if (queryDelete.executeUpdate() == 1)  {
                    for (String cursoEmprendimiento: emprendedorUpdateDTO.getCursosEmprendimiento()) {
                        AsignaturaEmprendedor asignaturaEmprendedor = new AsignaturaEmprendedor();
                        asignaturaEmprendedor.setId(
                                new AsignaturaEmprendedorId(
                                        emprendedorUpdateDTO.getIdEmprendedor(), cursoEmprendimiento));
                        entityManager.persist(asignaturaEmprendedor);
                        entityManager.flush();
                    }
                } else {
                    throw new Exception("Problema al registrar asignaturas del Emprendedor");
                }
            }

        } else {
            throw new Exception("Problema al encontrar el Emprendedor");
        }

        return true;
    }

    @Override
    public List<AsignaturaEmprendedor> obtenerAsignaturasEmprendedor(long idUsuario) {
        String sql = "SELECT * FROM T_SINAPSIS_ASIG_EMPRENDEDOR WHERE EMPRENDEDORES_ID = " + idUsuario;
        Query query = entityManager.createNativeQuery(sql, AsignaturaEmprendedor.class);

        return (List<AsignaturaEmprendedor>) query.getResultList();
    }

    @Override
    public List<Emprendimiento> obtenerEmprendimientos(String idEmprendedor) {
        List<Emprendimiento> datos = new ArrayList<>();
        String sql = "SELECT \n" +
                "    TE.* \n" +
                "FROM T_SINAPSIS_EMPRENDIMIENTOS TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDIMIENTOS_ID = TE.ID " +
                "WHERE TPE.EMPRENDEDORES_ID = " + idEmprendedor;

        Query query = entityManager.createNativeQuery(sql, Emprendimiento.class);

        datos = (List<Emprendimiento>) query.getResultList();

        return datos;
    }

    @Override
    public List<Emprendimiento> obtenerEmprendimiento(String idEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_EMPRENDIMIENTOS WHERE ID = " + idEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, Emprendimiento.class);

        return (List<Emprendimiento>) query.getResultList();
    }

    @Override
    public List<RedSocialEmprendimientoView> obtenerRedesSocialesEmprendimiento(String idEmprendimiento) {
        String sql = "SELECT * FROM V_SINAPSIS_EMPRENDI_RED_SOCIAL WHERE ID_EMPRENDIMIENTO = " + idEmprendimiento;
        Query query = entityManager.createNativeQuery(sql, RedSocialEmprendimientoView.class);

        return (List<RedSocialEmprendimientoView>) query.getResultList();
    }
}
