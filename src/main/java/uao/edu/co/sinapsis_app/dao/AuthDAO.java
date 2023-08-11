package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IAuthDAO;
import uao.edu.co.sinapsis_app.dto.EmprendedorSignUpDTO;
import uao.edu.co.sinapsis_app.dto.MentorSignUpDTO;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Emprendedor;
import uao.edu.co.sinapsis_app.model.IntegrationTable;
import uao.edu.co.sinapsis_app.model.Mentor;
import uao.edu.co.sinapsis_app.model.ProgramaAcademico;
import uao.edu.co.sinapsis_app.model.Usuario;
import uao.edu.co.sinapsis_app.model.UsuarioRol;
import uao.edu.co.sinapsis_app.model.embeddable.AsignaturaEmprendedorId;
import uao.edu.co.sinapsis_app.model.embeddable.UsuarioRolId;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_COLABORADOR;
import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_EGRESADO;
import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_ESTUDIANTE;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_ROLES_EMPRENDEDOR;
import static uao.edu.co.sinapsis_app.util.Constants.T_SINAPSIS_ROLES_MENTOR;

@Repository
public class AuthDAO implements IAuthDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Usuario buscarUsuarioPorTipoDocumentoYNumeroDocumento(long tipoDocumento, String numeroDocumento) {
        String sql = "SELECT usuarios.* FROM T_SINAPSIS_USUARIOS usuarios WHERE TIPOS_DOCUMENTO_ID = '"+tipoDocumento+"' AND NUMERO_DOCUMENTO = '"+numeroDocumento+"'";

        Query q = entityManager.createNativeQuery(sql, Usuario.class);

        List<Usuario> resultado = q.getResultList();

        if (resultado.size() > 0) {
            return resultado.get(0);
        }

        return null;
    }

    @Override
    public Usuario buscarUsuario(int tipoDocumento, String numeroDocumento, String usuario) {
        String sql = "SELECT usuarios.* FROM T_SINAPSIS_USUARIOS usuarios WHERE TIPOS_DOCUMENTO_ID = '"
                +tipoDocumento+"' AND NUMERO_DOCUMENTO = '"
                +numeroDocumento+"' AND USERNAME = '"+usuario+"'";

        Query q = entityManager.createNativeQuery(sql, Usuario.class);

        List<Usuario> resultado = q.getResultList();

        if (resultado.size() > 0) {
            return resultado.get(0);
        }

        return null;
    }

    @Override
    public Usuario buscarUsuarioByCorreo(String correo) {
        String sql = "SELECT usuarios.* \n" +
                "FROM T_SINAPSIS_USUARIOS usuarios \n" +
                "WHERE CORREO_PERSONAL = '" +
                correo +"' OR CORREO_INSTITUCIONAL = '" +
                correo + "'";

        Query q = entityManager.createNativeQuery(sql, Usuario.class);

        List<Usuario> resultado = q.getResultList();

        if (resultado.size() > 0) {
            return resultado.get(0);
        }

        return null;
    }

    @Override
    public Usuario buscarUsuarioById(Long idUsuario) {
        String sql = "SELECT usuarios.* \n" +
                "FROM T_SINAPSIS_USUARIOS usuarios \n" +
                "WHERE ID = '" +
                idUsuario + "'";

        Query q = entityManager.createNativeQuery(sql, Usuario.class);

        List<Usuario> resultado = q.getResultList();

        if (resultado.size() > 0) {
            return resultado.get(0);
        }

        return null;
    }

    @Override
    public IntegrationTable buscarIntegracionPorUsuarioYTipoDocumentoYNumeroDocumento(String usuario, int tipoDocumento, String numeroDocumento) {
        String sql = "SELECT * FROM T_SINAPSIS_INTEGRATION WHERE UPPER(USERNAME) = UPPER(?1) AND TIPO_DOCUMENTO = ?2  AND NUMERO_DOCUMENTO = ?3";

        Query q = entityManager.createNativeQuery(sql, IntegrationTable.class);
        q.setParameter(1, usuario);
        q.setParameter(2, tipoDocumento);
        q.setParameter(3, numeroDocumento);

        List<IntegrationTable> result =  q.getResultList();

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarEmprendedor(EmprendedorSignUpDTO emprendedorDTO) throws Exception {
        String sqlNextVal = "SELECT SEC_T_SINAPSIS_USUARIOS.nextval FROM dual";

        Query queryNextVal = entityManager.createNativeQuery(sqlNextVal);

        long ID_NEW_USER = ((BigDecimal) queryNextVal.getSingleResult()).longValue();

        Usuario usuario = new Usuario();
        Emprendedor emprendedor = new Emprendedor();

        usuario.setId(ID_NEW_USER);
        usuario.setNumeroDocumento(emprendedorDTO.getNumeroDocumento());
        usuario.setNombres(emprendedorDTO.getNombres());
        usuario.setApellidos(emprendedorDTO.getApellidos());
        usuario.setCorreoInstitucional(emprendedorDTO.getCorreoInstitucional());
        usuario.setCorreoPersonal(emprendedorDTO.getCorreoPersonal());
        usuario.setPassword(emprendedorDTO.getPassword());
        usuario.setUsername(emprendedorDTO.getUsername());
        usuario.setTelefonoContacto(emprendedorDTO.getTelefonoContacto());
        usuario.setEstado(emprendedorDTO.getEstado());
        usuario.setAceptoTratamientoDatos(emprendedorDTO.getAceptoTratamientoDatos());
        usuario.setFotoUrl(emprendedorDTO.getFotoUrl());
        usuario.setTipoDocumento(emprendedorDTO.getIdTipoDocumento());
        usuario.setEstadoCuenta(emprendedorDTO.getEstadoCuenta());

        emprendedor.setIdEmprendedor(ID_NEW_USER);
        emprendedor.setGenero(emprendedorDTO.getGenero());
        emprendedor.setDireccion(emprendedorDTO.getDireccion());
        emprendedor.setMunicipio(emprendedorDTO.getIdMunicipio());
        emprendedor.setFechaNacimiento(emprendedorDTO.getFechaNacimiento());
        emprendedor.setTipoContacto(emprendedorDTO.getTipoContacto());

        // Datos de Estudiante
        if (emprendedorDTO.getTipoContacto() == TIPO_CONTACTO_ESTUDIANTE) {
            emprendedor.setCodigoEstudiantil(emprendedorDTO.getCodigoEstudiantil());
            emprendedor.setNivelAcademico(emprendedorDTO.getNivelAcademico());
            emprendedor.setModalidadTrabajoGrado(emprendedorDTO.getModalidadTrabajoGrado());
            emprendedor.setProgramaAcademico(emprendedorDTO.getIdProgramaAcademico());
        }

        // Datos de Egresado
        if (emprendedorDTO.getTipoContacto() == TIPO_CONTACTO_EGRESADO) {
            emprendedor.setCodigoEstudiantil(emprendedorDTO.getCodigoEstudiantil());
            emprendedor.setNivelAcademico(emprendedorDTO.getNivelAcademico());
            emprendedor.setProgramaAcademico(emprendedorDTO.getIdProgramaAcademico());
        }

        if (emprendedorDTO.getTipoContacto() == TIPO_CONTACTO_COLABORADOR ) {
            emprendedor.setCargo(emprendedorDTO.getCargo());
            emprendedor.setDependencia(emprendedorDTO.getDependencia());
        }

        emprendedor.setPrimeraVez(emprendedorDTO.getPrimeraVez());

        UsuarioRol usuarioRol = new UsuarioRol(new UsuarioRolId(ID_NEW_USER, T_SINAPSIS_ROLES_EMPRENDEDOR));

        entityManager.persist(usuario);
        entityManager.flush();

        entityManager.persist(emprendedor);
        entityManager.flush();

        entityManager.persist(usuarioRol);
        entityManager.flush();

        if (emprendedorDTO.getTipoContacto() == TIPO_CONTACTO_ESTUDIANTE) {
            /**
             * Logica para almacenar Cursos de Emprendimiento
             */
            if (emprendedorDTO.getCursosEmprendimiento() != null &&
                    emprendedorDTO.getCursosEmprendimiento().size() > 0 ) {

                for (String cursoEmprendimiento: emprendedorDTO.getCursosEmprendimiento()) {
                    AsignaturaEmprendedor asignaturaEmprendedor = new AsignaturaEmprendedor();
                    asignaturaEmprendedor.setId(
                            new AsignaturaEmprendedorId(
                                    ID_NEW_USER, cursoEmprendimiento));
                    entityManager.persist(asignaturaEmprendedor);
                }
            }
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarMentor(MentorSignUpDTO mentorSignUpDTO) throws Exception {
        String sqlNextVal = "SELECT SEC_T_SINAPSIS_USUARIOS.nextval FROM dual";

        Query queryNextVal = entityManager.createNativeQuery(sqlNextVal);

        long ID_NEW_USER = ((BigDecimal) queryNextVal.getSingleResult()).longValue();

        Usuario usuario = new Usuario();
        Mentor mentor = new Mentor();

        usuario.setId(ID_NEW_USER);
        usuario.setNumeroDocumento(mentorSignUpDTO.getNumeroDocumento());
        usuario.setNombres(mentorSignUpDTO.getNombres());
        usuario.setApellidos(mentorSignUpDTO.getApellidos());
        usuario.setCorreoInstitucional(mentorSignUpDTO.getCorreoInstitucional());
        usuario.setCorreoPersonal(mentorSignUpDTO.getCorreoPersonal());
        usuario.setPassword(mentorSignUpDTO.getPassword());
        usuario.setUsername(mentorSignUpDTO.getUsername());
        usuario.setTelefonoContacto(mentorSignUpDTO.getTelefonoContacto());
        usuario.setEstado(mentorSignUpDTO.getEstado());
        usuario.setAceptoTratamientoDatos(mentorSignUpDTO.getAceptoTratamientoDatos());
        usuario.setFotoUrl(mentorSignUpDTO.getFotoUrl());
        usuario.setTipoDocumento(mentorSignUpDTO.getIdTipoDocumento());
        usuario.setEstadoCuenta(mentorSignUpDTO.getEstadoCuenta());

        mentor.setIdMentor(ID_NEW_USER);
        mentor.setCargo(mentorSignUpDTO.getCargoMentor());
        mentor.setDependencia(mentorSignUpDTO.getDependenciaMentor());
        mentor.setFacultad(mentorSignUpDTO.getFacultadMentor());
        mentor.setIdEtapaRuta(mentorSignUpDTO.getEtapaRuta());

        UsuarioRol usuarioRol = new UsuarioRol(new UsuarioRolId(ID_NEW_USER, T_SINAPSIS_ROLES_MENTOR));

        entityManager.persist(usuario);
        entityManager.flush();

        entityManager.persist(mentor);
        entityManager.flush();

        entityManager.persist(usuarioRol);
        entityManager.flush();

        return true;
    }

    @Override
    @Transactional
    public boolean actualizarContrasena(Usuario usuarioActualizado) {
        entityManager.merge(usuarioActualizado);
        entityManager.flush();

        return true;
    }

    @Override
    @Transactional
    public boolean restablecerContraseña(Long idUsuario) {
        String sql = "UPDATE T_SINAPSIS_USUARIOS " +
                "SET PASSWORD = (UPPER(SUBSTR(APELLIDOS, 1 ,1)) || LOWER(SUBSTR(APELLIDOS, 2 ,1)) || NUMERO_DOCUMENTO || '.') " +
                "WHERE ID = " + idUsuario;

        Query query = entityManager.createNativeQuery(sql);

        int result = query.executeUpdate();

        return result > 0;
    }

    @Override
    @Transactional
    public boolean desactivarUsuario(Long idUsuario) {
        String sql = "UPDATE T_SINAPSIS_USUARIOS " +
                "SET ESTADO_CUENTA = 0 " +
                "WHERE ID = " + idUsuario;

        Query query = entityManager.createNativeQuery(sql);

        int result = query.executeUpdate();

        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateLastLogin(Usuario usuario) {
        String sql = "UPDATE T_SINAPSIS_EMPRENDEDORES SET ULTIMO_INGRESO = sysdate, NOTIFICADO = 0, FECHA_NOTIFICADO = NULL " +
                "WHERE ID = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, usuario.getId());

        query.executeUpdate();
    }

    @Override
    public ProgramaAcademico buscarProgramaAcademicoPorAcronimo(String acronimoProgramaAcademico) {
        String sql = "SELECT * FROM T_SINAPSIS_PROGRAMAS WHERE UPPER(ACRONIMO) = UPPER(?1)";

        Query q = entityManager.createNativeQuery(sql, ProgramaAcademico.class);
        q.setParameter(1, acronimoProgramaAcademico);

        List<ProgramaAcademico> result =  q.getResultList();

        if (result.size() > 0) {
            return result.get(0);
        }

        return null;
    }
}
