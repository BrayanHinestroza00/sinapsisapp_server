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
import uao.edu.co.sinapsis_app.dto.EmprendimientoDTO;
import uao.edu.co.sinapsis_app.dto.request.EmprendimientoUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.model.ActividadRutaEmp;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Emprendedor;
import uao.edu.co.sinapsis_app.model.Emprendimiento;
import uao.edu.co.sinapsis_app.model.PrimeraAtencion;
import uao.edu.co.sinapsis_app.model.ProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.RutaProyectoEmprendimiento;
import uao.edu.co.sinapsis_app.model.SubActividadRuta;
import uao.edu.co.sinapsis_app.model.SubActividadRutaEmp;
import uao.edu.co.sinapsis_app.model.Usuario;
import uao.edu.co.sinapsis_app.model.embeddable.AsignaturaEmprendedorId;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.RedSocialEmprendimientoView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

        EmprendimientoDTO emprendimiento = new EmprendimientoDTO();

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
            emprendimiento.setSitioWebEmpresa(primeraAtencionDTO.getSitioWebEmpresa());
        }

        if (primeraAtencionDTO.getEstaConstituida() != null) {
            emprendimiento.setEstaConstituida(primeraAtencionDTO.getEstaConstituida());
        }
        if (primeraAtencionDTO.getFechaConstitucion() != null) {
            emprendimiento.setFechaConstitucion(getFormatoFecha(primeraAtencionDTO.getFechaConstitucion(), APP_DATE_OUT_FORMAT));
        }
        if (primeraAtencionDTO.getNitEmpresa() != null) {
            emprendimiento.setNitEmpresa(primeraAtencionDTO.getNitEmpresa());
        }

        if (primeraAtencionDTO.getRazonSocialEmpresa() != null) {
            emprendimiento.setRazonSocialEmpresa(primeraAtencionDTO.getRazonSocialEmpresa());
        }

        if (primeraAtencionDTO.getLogoEmpresa() != null) {
            emprendimiento.setLogoEmpresaURL(primeraAtencionDTO.getLogoEmpresaURL());
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
            emprendimiento.setNecesidadIdentificada(primeraAtencionDTO.getNecesidadIdentificada());
        }

        if (primeraAtencionDTO.getRedesSociales() != null && primeraAtencionDTO.getRedesSociales().length > 0 ) {
            emprendimiento.setRedesSociales(primeraAtencionDTO.getRedesSociales());
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

        if (primeraAtencionDTO.getCualOtroDescubrioSinapsis() != null) {
            primeraAtencion.setCualOtroDescubrioSinapsis(primeraAtencionDTO.getCualOtroDescubrioSinapsis());
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
        String sql = "SELECT \n" +
                "    TE.* \n" +
                "FROM T_SINAPSIS_EMPRENDIMIENTOS TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDIMIENTOS_ID = TE.ID " +
                "WHERE TPE.EMPRENDEDORES_ID = " + idEmprendedor;

        Query query = entityManager.createNativeQuery(sql, Emprendimiento.class);

        return (List<Emprendimiento>) query.getResultList();
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

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean actualizarEmprendimiento(EmprendimientoUpdateDTO emprendimientoUpdateDTO) throws Exception {
        // Se actualiza informacion del emprendimiento
        return emprendimientoDAO.actualizarEmprendimiento(emprendimientoUpdateDTO);

//        if (updatedEmprendimiento == null) {
//            throw new Exception("Problema al almacenar el emprendimiento");
//        }
//
//        return true;
    }

    @Override
    public List<SubActividadRutaEmp> obtenerAvanceEnRuta(Long idRutaEmprendimiento) {
        String sql = "SELECT * FROM T_SINAPSIS_SUB_ACT_RUTAS_EMP " +
                "WHERE RUTAS_EMPRENDIMIENTOS_ID = ?1 ORDER BY SUB_ACTIVIDADES_RUTAS_ID DESC";
        Query query = entityManager.createNativeQuery(sql, SubActividadRutaEmp.class);

        query.setParameter(1, idRutaEmprendimiento);

        return (List<SubActividadRutaEmp>) query.getResultList();
    }

    @Override
    public RutaProyectoEmprendimiento obtenerRutaProyectoEmprendimiento(Long idProyectoEmprendimiento) {
        String sql = "SELECT * FROM (\n" +
                "    SELECT TSRE.*,\n" +
                "            ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "    FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                ") WHERE SEQNUM = 1 AND PROYECTOS_EMPRENDIMIENTOS_ID = ?1";

        Query query = entityManager.createNativeQuery(sql, RutaProyectoEmprendimiento.class);

        query.setParameter(1, idProyectoEmprendimiento);

        List<RutaProyectoEmprendimiento> result = query.getResultList();

        if (result.size() > 0 ) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public RutaProyectoEmprendimiento obtenerRutaProyectoEmprendimientoById(Long idRutaProyecto) {
        String sql = "SELECT * FROM T_SINAPSIS_RUT_EMPRENDIMIENTO WHERE ID = ?1";

        Query query = entityManager.createNativeQuery(sql, RutaProyectoEmprendimiento.class);

        query.setParameter(1, idRutaProyecto);

        List<RutaProyectoEmprendimiento> result = query.getResultList();

        if (result.size() > 0 ) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean almacenarAvanceEnRuta(SubActividadRutaEmp subActividadRutaEmp) {
        try {
            entityManager.persist(subActividadRutaEmp);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean almacenarRutaProyectoEmprendimiento(RutaProyectoEmprendimiento rutaProyectoEmprendimiento) {
        try {
            entityManager.persist(rutaProyectoEmprendimiento);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SubActividadRutaEmp buscarSubActividadRutaEmp(Long idRutaProyecto, Long idSubActividadRuta) {
        String sql = "SELECT \n" +
                "    * \n" +
                "FROM T_SINAPSIS_SUB_ACT_RUTAS_EMP \n" +
                "WHERE RUTAS_EMPRENDIMIENTOS_ID = ?1 AND SUB_ACTIVIDADES_RUTAS_ID = ?2";

        Query query = entityManager.createNativeQuery(sql, SubActividadRutaEmp.class);

        query.setParameter(1, idRutaProyecto);
        query.setParameter(2, idSubActividadRuta);

        List<SubActividadRutaEmp> result = query.getResultList();

        if (result.size() > 0 ) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SubActividadRuta> buscarSubActividadRutas(Long idRutaProyecto) {
        String sql = "SELECT \n" +
                "    TSAR.* \n" +
                "FROM T_SINAPSIS_SUB_ACT_RUTA TSAR \n" +
                "JOIN T_SINAPSIS_ACTIVIDADES_RUTA TAR ON TSAR.ACTIVIDADES_RUTAS_ID = TAR.ID\n" +
                "WHERE TAR.ETAPAS_RUTAS_ID = (SELECT ETAPAS_ID FROM T_SINAPSIS_RUT_EMPRENDIMIENTO WHERE ID = ?1)\n" +
                "ORDER BY TSAR.ID DESC";

        Query query = entityManager.createNativeQuery(sql, SubActividadRuta.class);

        query.setParameter(1, idRutaProyecto);

        return query.getResultList();
    }

    @Override
    public ActividadRutaEmp buscarActividadRutaEmp(Long subActividadRutaId, Long rutaEmprendimientoId) {
        String sql = "SELECT TARE.* FROM T_SINAPSIS_SUB_ACT_RUTAS_EMP TSARE \n" +
                "JOIN T_SINAPSIS_SUB_ACT_RUTA TSAR ON TSARE.SUB_ACTIVIDADES_RUTAS_ID = TSAR.ID\n" +
                "JOIN T_SINAPSIS_ACT_RUTA_EMP TARE ON TSAR.ACTIVIDADES_RUTAS_ID = TARE.ACTIVIDADES_RUTAS_ID\n" +
                "WHERE TSARE.RUTAS_EMPRENDIMIENTOS_ID = ?1 AND TSARE.SUB_ACTIVIDADES_RUTAS_ID = ?2 AND TARE.ESTADO = 'PENDIENTE'";

        Query query = entityManager.createNativeQuery(sql, ActividadRutaEmp.class);

        query.setParameter(1, rutaEmprendimientoId);
        query.setParameter(2, subActividadRutaId);

        List<ActividadRutaEmp> result = query.getResultList();

        if (result.size() > 0 ) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void almacenarActividadRuta(ActividadRutaEmp actividadRutaEmp) {
        entityManager.merge(actividadRutaEmp);
    }
}
