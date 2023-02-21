package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IAppDAO;
import uao.edu.co.sinapsis_app.model.*;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AppDAO implements IAppDAO {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<TipoDocumento> getAllTipoDocumento() {
        String sql = "SELECT * FROM T_SINAPSIS_TIPOS_DOCUMENTO";
        Query q = entityManager.createNativeQuery(sql, TipoDocumento.class);
        return (List<TipoDocumento>) q.getResultList();
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoById(int idTipoDocumento) {
        String sql = "SELECT * FROM T_SINAPSIS_TIPOS_DOCUMENTO WHERE id = " + idTipoDocumento;
        Query q = entityManager.createNativeQuery(sql, TipoDocumento.class);
        return (List<TipoDocumento>) q.getResultList();
    }

    @Override
    public List<UsuarioRol> getRolesByUser(long idUsuario) {
        String sql = "SELECT usu_rol.* FROM T_SINAPSIS_USUARIOS_ROL usu_rol WHERE USUARIOS_ID = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql, UsuarioRol.class);
        return (List<UsuarioRol>) q.getResultList();
    }

    @Override
    public int getPreFetchEmprendedor(long idUsuario) {
        String sql = "SELECT vse.* FROM V_SINAPSIS_EMPRENDEDORES vse WHERE ID = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql, EmprendedoresView.class);
        EmprendedoresView emprendedoresView = (EmprendedoresView) q.getSingleResult();
        return emprendedoresView.getPrimeraVez();
    }

    @Override
    public List<ProyectoEmprendimiento> getProyectosEmprendimientoEmprendedor(int idUsuario) {
        String sql = "SELECT * FROM T_SINAPSIS_PROY_EMPRENDIMIENTO WHERE EMPRENDEDORES_ID = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql, ProyectoEmprendimiento.class);
        return (List<ProyectoEmprendimiento>) q.getResultList();
    }

    @Override
    public List<Departamento> getDepartamentos() {
        String sql = "SELECT * FROM T_SINAPSIS_DEPARTAMENTOS";
        Query q = entityManager.createNativeQuery(sql, Departamento.class);
        return (List<Departamento>) q.getResultList();
    }

    @Override
    public List<Departamento> getDepartamentosByMunicipio(long idMunicipio) {
        String sql = "SELECT departamentos.* FROM T_SINAPSIS_DEPARTAMENTOS departamentos " +
                "JOIN T_SINAPSIS_MUNICIPIOS municipios ON departamentos.id = municipios.departamentos_id " +
                "WHERE municipios.id =" + idMunicipio;
        Query q = entityManager.createNativeQuery(sql, Departamento.class);
        return (List<Departamento>) q.getResultList();
    }

    @Override
    public List<Municipio> getMunicipios() {
        String sql = "SELECT * FROM T_SINAPSIS_MUNICIPIOS";
        Query q = entityManager.createNativeQuery(sql, Municipio.class);
        return (List<Municipio>) q.getResultList();
    }

    @Override
    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        String sql = "SELECT * FROM T_SINAPSIS_MUNICIPIOS WHERE departamentos_id = " + idDepartamento;
        Query q = entityManager.createNativeQuery(sql, Municipio.class);
        return (List<Municipio>) q.getResultList();
    }

    @Override
    public List<Municipio> getMunicipiosById(long idMunicipio) {
        String sql = "SELECT * FROM T_SINAPSIS_MUNICIPIOS WHERE id = " + idMunicipio;
        Query q = entityManager.createNativeQuery(sql, Municipio.class);
        return (List<Municipio>) q.getResultList();
    }

    @Override
    public List<Facultad> getFacultades() {
        String sql = "SELECT * FROM T_SINAPSIS_FACULTADES";
        Query q = entityManager.createNativeQuery(sql, Facultad.class);
        return (List<Facultad>) q.getResultList();
    }

    @Override
    public List<Facultad> getFacultadesByProgramaAcademico(int idPrograma) {
        String sql = "SELECT facultades.* FROM T_SINAPSIS_FACULTADES facultades " +
                "JOIN T_SINAPSIS_PROGRAMAS programas ON facultades.id = programas.facultades_id " +
                "WHERE programas.id =" + idPrograma;
        Query q = entityManager.createNativeQuery(sql, Facultad.class);
        return (List<Facultad>) q.getResultList();
    }

    @Override
    public List<ProgramaAcademico> getProgramasAcademicosByidFacultad(int idFacultad) {
        String sql = "SELECT * FROM T_SINAPSIS_PROGRAMAS WHERE facultades_id = " + idFacultad;
        Query q = entityManager.createNativeQuery(sql, ProgramaAcademico.class);
        return (List<ProgramaAcademico>) q.getResultList();
    }

    @Override
    public List<ProgramaAcademico> getProgramasAcademicosById(long idPrograma) {
        String sql = "SELECT * FROM T_SINAPSIS_PROGRAMAS WHERE id = " + idPrograma;
        Query q = entityManager.createNativeQuery(sql, ProgramaAcademico.class);
        return (List<ProgramaAcademico>) q.getResultList();
    }

    @Override
    public List<ProgramaAcademico> getProgramasAcademicos() {
        String sql = "SELECT * FROM T_SINAPSIS_PROGRAMAS";
        Query q = entityManager.createNativeQuery(sql, ProgramaAcademico.class);
        return (List<ProgramaAcademico>) q.getResultList();
    }
}
