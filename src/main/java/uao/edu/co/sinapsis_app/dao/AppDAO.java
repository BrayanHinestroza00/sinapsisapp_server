package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IAppDAO;
import uao.edu.co.sinapsis_app.model.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AppDAO implements IAppDAO {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<TipoDocumento> getAllTipoDocumento() {
        String sql = "SELECT * FROM tipos_documento";
        Query q = entityManager.createNativeQuery(sql);
        return (List<TipoDocumento>) q.getResultList();
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoById(int idTipoDocumento) {
        String sql = "SELECT * FROM tipos_documento WHERE id = " + idTipoDocumento;
        Query q = entityManager.createNativeQuery(sql);
        return (List<TipoDocumento>) q.getResultList();
    }

    @Override
    public List<UsuarioRol> getRolesByUser(long idUsuario) {
        String sql = "SELECT * FROM usuarios_rol WHERE usuarios_id = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql);
        return (List<UsuarioRol>) q.getResultList();
    }

    @Override
    public int getPreFetchEmprendedor(long idUsuario) {
        String sql = "SELECT primeraVez FROM emprendedores WHERE idEmprendedor = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql);
        return Integer.parseInt(String.valueOf(q.getSingleResult()));
    }

    @Override
    public List<Emprendimiento> getProyectosEmprendimientoEmprendedor(int idUsuario) {
        String sql = "SELECT * FROM emprendimientos WHERE emprendedor_id = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql);
        return (List<Emprendimiento>) q.getResultList();
    }

    @Override
    public List<Departamento> getDepartamentos() {
        String sql = "SELECT * FROM departamentos";
        Query q = entityManager.createNativeQuery(sql);
        return (List<Departamento>) q.getResultList();
    }

    @Override
    public List<Departamento> getDepartamentosByMunicipio(long idMunicipio) {
        String sql = "SELECT departamentos.* FROM departamentos " +
                "JOIN municipios ON departamentos.id = municipios.departamentos_id " +
                "WHERE municipios.id =" + idMunicipio;
        Query q = entityManager.createNativeQuery(sql);
        return (List<Departamento>) q.getResultList();
    }

    @Override
    public List<Municipio> getMunicipios() {
        String sql = "SELECT * FROM municipios";
        Query q = entityManager.createNativeQuery(sql);
        return (List<Municipio>) q.getResultList();
    }

    @Override
    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        String sql = "SELECT * FROM municipios WHERE departamentos_id = " + idDepartamento;
        Query q = entityManager.createNativeQuery(sql);
        return (List<Municipio>) q.getResultList();
    }

    @Override
    public List<Municipio> getMunicipiosById(long idMunicipio) {
        String sql = "SELECT * FROM municipios WHERE id = " + idMunicipio;
        Query q = entityManager.createNativeQuery(sql);
        return (List<Municipio>) q.getResultList();
    }
}
