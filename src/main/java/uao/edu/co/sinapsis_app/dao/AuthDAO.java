package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IAuthDAO;
import uao.edu.co.sinapsis_app.model.Emprendedor;
import uao.edu.co.sinapsis_app.model.IntegrationTable;
import uao.edu.co.sinapsis_app.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthDAO implements IAuthDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Usuario findByTipoDocumentoAndNumeroDocumento(int tipoDocumento, String numeroDocumento) {
        String sql = "SELECT * FROM USUARIOS WHERE tipoDocumento = '"+tipoDocumento+"' AND numeroDocumento = '"+numeroDocumento+"'";

        Query q = entityManager.createNativeQuery(sql);

        List<Object> result =  q.getResultList();

        if (result.size() > 0) {
            return new Usuario(result.get(0));
        }

        return null;
    }

    @Override
    public IntegrationTable findByUserNameInITB(String usuario) {
        String sql = "SELECT * FROM integration_table WHERE UPPER(username) = UPPER('"+usuario+"')";

        Query q = entityManager.createNativeQuery(sql);

        List<Object> result =  q.getResultList();

        if (result.size() > 0) {
            return new IntegrationTable((Object[]) result.get(0));
        }

        return null;
    }

    @Override
    public IntegrationTable findByDocumentoInITB(int tipoDocumento, String numeroDocumento) {
        String sql = "SELECT * FROM integration_table WHERE tipoDocumento = '"+tipoDocumento+"' AND numeroDocumento = '"+numeroDocumento+"' "  +
                "ORDER BY vinculoConU ASC";

        Query q = entityManager.createNativeQuery(sql);

        List<Object> result =  q.getResultList();

        if (result.size() > 0) {
            return new IntegrationTable((Object[]) result.get(0));
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarEmprendedor(Emprendedor emprendedor) throws Exception {
        String sqlUsuario = "INSERT INTO usuarios SET ";
        boolean areThereFields = false;

        if (emprendedor.getNumeroDocumento() != null) {
            sqlUsuario+= "numeroDocumento = '" + emprendedor.getNumeroDocumento() + "'";
            areThereFields = true;
        }

        if (emprendedor.getTipoDocumento() != 0) {
            if (areThereFields){
                sqlUsuario+= ", tipoDocumento = '" + emprendedor.getTipoDocumento() + "'";
            } else {
                sqlUsuario+= "tipoDocumento = '" + emprendedor.getTipoDocumento() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getNombres() != null) {
            if (areThereFields){
                sqlUsuario+= ", nombres = '" + emprendedor.getNombres() + "'";
            } else {
                sqlUsuario+= "nombres = '" + emprendedor.getNombres() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getApellidos() != null) {
            if (areThereFields){
                sqlUsuario+= ", apellidos = '" + emprendedor.getApellidos() + "'";
            } else {
                sqlUsuario+= "apellidos = '" + emprendedor.getApellidos() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getCorreo() != null) {
            if (areThereFields){
                sqlUsuario+= ", correo = '" + emprendedor.getCorreo() + "'";
            } else {
                sqlUsuario+= "correo = '" + emprendedor.getCorreo() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getPassword() != null) {
            if (areThereFields){
                sqlUsuario+= ", password = '" + emprendedor.getPassword() + "'";
            } else {
                sqlUsuario+= "password = '" + emprendedor.getPassword() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getUsername() != null) {
            if (areThereFields){
                sqlUsuario+= ", username = '" + emprendedor.getUsername() + "'";
            } else {
                sqlUsuario+= "username = '" + emprendedor.getUsername() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getTelefono() != null) {
            if (areThereFields){
                sqlUsuario+= ", telefono = '" + emprendedor.getTelefono() + "'";
            } else {
                sqlUsuario+= "telefono = '" + emprendedor.getTelefono() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getCelular() != null) {
            if (areThereFields){
                sqlUsuario+= ", celular = '" + emprendedor.getCelular() + "'";
            } else {
                sqlUsuario+= "celular = '" + emprendedor.getCelular() + "'";
                areThereFields = true;
            }
        }

        if (!areThereFields) {
            throw new Exception("No se ingresaron campos del usuario");
        }

        sqlUsuario += ", aceptoTratamientoDatos = 1";

        System.out.println(sqlUsuario);

        Query queryUsuario = entityManager.createNativeQuery(sqlUsuario);
        int resultUsuario = queryUsuario.executeUpdate();

        if (resultUsuario != 1) {
            throw new Exception("Problema al almacenar el usuario");
        }

        Query queryIdResultUsuario = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()");
        long idResultUsuario =  Long.parseLong(String.valueOf(queryIdResultUsuario.getSingleResult()));

        String sqlUsuariosRol = "INSERT INTO usuarios_rol SET roles_id = 3, usuarios_id = " + idResultUsuario;

        Query queryUsuariosRol = entityManager.createNativeQuery(sqlUsuariosRol);
        int resultUsuarioRol = queryUsuariosRol.executeUpdate();

        if (resultUsuarioRol != 1) {
            throw new Exception("Problema al almacenar usuarios_rol");
        }

        // Se actualiza informacion del emprendedor
        areThereFields = false;
        String sqlEmprendedor = "UPDATE emprendedores SET ";

        if (emprendedor.getGenero() != null) {
            sqlEmprendedor+= "genero = '" + emprendedor.getGenero() + "'";
            areThereFields = true;
        }

        if (emprendedor.getDireccion() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", direccion = '" + emprendedor.getDireccion() + "'";
            } else {
                sqlEmprendedor+= "direccion = '" + emprendedor.getDireccion() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getFechaNacimiento() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", fechaNacimiento = '" + emprendedor.getFechaNacimiento() + "'";
            } else {
                sqlEmprendedor+= "fechaNacimiento = '" + emprendedor.getFechaNacimiento() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getCodigoEstudiantil() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", codigoEstudiantil = '" + emprendedor.getCodigoEstudiantil() + "'";
            } else {
                sqlEmprendedor+= "codigoEstudiantil = '" + emprendedor.getCodigoEstudiantil() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getVinculoConU() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", vinculoConU = '" + emprendedor.getVinculoConU() + "'";
            } else {
                sqlEmprendedor+= "vinculoConU = '" + emprendedor.getVinculoConU() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getTipoEstudiante() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", tipoEstudiante = '" + emprendedor.getTipoEstudiante() + "'";
            } else {
                sqlEmprendedor+= "tipoEstudiante = '" + emprendedor.getTipoEstudiante() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getModTrabajoGrado() != 0) {
            if (areThereFields){
                sqlEmprendedor+= ", modTrabajoGrado = '" + emprendedor.getModTrabajoGrado() + "'";
            } else {
                sqlEmprendedor+= "modTrabajoGrado = '" + emprendedor.getModTrabajoGrado() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getDependencia() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", dependencia = '" + emprendedor.getDependencia() + "'";
            } else {
                sqlEmprendedor+= "dependencia = '" + emprendedor.getDependencia() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getCargo() != null) {
            if (areThereFields){
                sqlEmprendedor+= ", cargo = '" + emprendedor.getCargo() + "'";
            } else {
                sqlEmprendedor+= "cargo = '" + emprendedor.getCargo() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getMunicipioId() != 0) {
            if (areThereFields){
                sqlEmprendedor+= ", municipio_id = '" + emprendedor.getMunicipioId() + "'";
            } else {
                sqlEmprendedor+= "municipio_id = '" + emprendedor.getMunicipioId() + "'";
                areThereFields = true;
            }
        }

        if (emprendedor.getProgramaAcademicoId() != 0) {
            if (areThereFields){
                sqlEmprendedor+= ", programaAcademico_id = '" + emprendedor.getProgramaAcademicoId() + "'";
            } else {
                sqlEmprendedor+= "programaAcademico_id = '" + emprendedor.getProgramaAcademicoId() + "'";
                areThereFields = true;
            }
        }
        if (areThereFields) {
            sqlEmprendedor += " WHERE idEmprendedor = " + idResultUsuario;
            Query query = entityManager.createNativeQuery(sqlEmprendedor);
            int result = query.executeUpdate();

            if (result != 1) {
                throw new Exception("Problema al almacenar el emprendedor");
            }
        }
        return true;
    }
}
