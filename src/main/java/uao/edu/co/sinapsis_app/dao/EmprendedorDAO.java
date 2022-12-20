package uao.edu.co.sinapsis_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.beans.EmprendedorDTO;
import uao.edu.co.sinapsis_app.beans.PrimeraAtencionDTO;
import uao.edu.co.sinapsis_app.dao.interfaces.IEmprendedorDAO;
import uao.edu.co.sinapsis_app.model.Emprendedor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmprendedorDAO implements IEmprendedorDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Emprendedor> getInformacionEmprendedor(long idUsuario) {
        String sql = "SELECT * FROM USUARIOS U JOIN EMPRENDEDORES E ON U.ID = E.IDEMPRENDEDOR WHERE U.ID = " + idUsuario;
        Query q = entityManager.createNativeQuery(sql);
        return (List<Emprendedor>) q.getResultList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registrarPrimeraAtencion(PrimeraAtencionDTO primeraAtencion) throws Exception {
        boolean updateUsuario = false;
        String sqlUsuario = "UPDATE usuarios SET ";

        if (primeraAtencion.getTelefono() != null) {
            sqlUsuario+= "telefono = '" + primeraAtencion.getTelefono() + "'";
            updateUsuario = true;
        }
        if (primeraAtencion.getCelular() != null) {
            if (updateUsuario){
                sqlUsuario+= ", celular = '" + primeraAtencion.getCelular() + "'";
            } else {
                sqlUsuario+= "celular = '" + primeraAtencion.getCelular() + "'";
                updateUsuario = true;
            }
        }
        if (primeraAtencion.getFotoPerfil() != null) {
            if (updateUsuario){
                sqlUsuario+= ", fotoUrl = '" + primeraAtencion.getFotoPerfilURL() + "'";
            } else {
                sqlUsuario+= "fotoUrl = '" + primeraAtencion.getFotoPerfilURL() + "'";
                updateUsuario = true;
            }
        }
        if (updateUsuario) {
            sqlUsuario += " WHERE id = " + primeraAtencion.getIdEmprendedor();
            Query queryUsuario = entityManager.createNativeQuery(sqlUsuario);
            int resultUsuario = queryUsuario.executeUpdate();

            if (resultUsuario != 1) {
                throw new Exception("Problema al almacenar el usuario");
            }
        }

        // Se actualiza informacion del emprendedor
        boolean updateEmprendedor = false;
        String sqlEmprendedor = "UPDATE emprendedores SET ";

        if (primeraAtencion.getGenero() != null) {
            sqlEmprendedor+= "genero = '" + primeraAtencion.getGenero() + "'";
            updateEmprendedor = true;
        }

        if (primeraAtencion.getDireccion() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", direccion = '" + primeraAtencion.getDireccion() + "'";
            } else {
                sqlEmprendedor+= "direccion = '" + primeraAtencion.getDireccion() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getFechaNacimiento() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", fechaNacimiento = '" + primeraAtencion.getFechaNacimiento() + "'";
            } else {
                sqlEmprendedor+= "fechaNacimiento = '" + primeraAtencion.getFechaNacimiento() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getCodigoEstudiantil() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", codigoEstudiantil = '" + primeraAtencion.getCodigoEstudiantil() + "'";
            } else {
                sqlEmprendedor+= "codigoEstudiantil = '" + primeraAtencion.getCodigoEstudiantil() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getVinculoConU() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", vinculoConU = '" + primeraAtencion.getVinculoConU() + "'";
            } else {
                sqlEmprendedor+= "vinculoConU = '" + primeraAtencion.getVinculoConU() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getTipoEstudiante() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", tipoEstudiante = '" + primeraAtencion.getTipoEstudiante() + "'";
            } else {
                sqlEmprendedor+= "tipoEstudiante = '" + primeraAtencion.getTipoEstudiante() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getModTrabajoGrado() != 0) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", modTrabajoGrado = '" + primeraAtencion.getModTrabajoGrado() + "'";
            } else {
                sqlEmprendedor+= "modTrabajoGrado = '" + primeraAtencion.getModTrabajoGrado() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getDependenciaColaborador() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", dependencia = '" + primeraAtencion.getDependenciaColaborador() + "'";
            } else {
                sqlEmprendedor+= "dependencia = '" + primeraAtencion.getDependenciaColaborador() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getCargoColaborador() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", cargo = '" + primeraAtencion.getCargoColaborador() + "'";
            } else {
                sqlEmprendedor+= "cargo = '" + primeraAtencion.getCargoColaborador() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getMunicipio() != 0) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", municipio_id = '" + primeraAtencion.getMunicipio() + "'";
            } else {
                sqlEmprendedor+= "municipio_id = '" + primeraAtencion.getMunicipio() + "'";
                updateEmprendedor = true;
            }
        }

        if (primeraAtencion.getProgramaAcademico() != 0) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", programaAcademico_id = '" + primeraAtencion.getProgramaAcademico() + "'";
            } else {
                sqlEmprendedor+= "programaAcademico_id = '" + primeraAtencion.getProgramaAcademico() + "'";
                updateEmprendedor = true;
            }
        }
        if (updateEmprendedor) {
            sqlEmprendedor += ", primeraVez = 0 WHERE idEmprendedor = " + primeraAtencion.getIdEmprendedor();
            Query query = entityManager.createNativeQuery(sqlEmprendedor);
            int result = query.executeUpdate();

            if (result != 1) {
                throw new Exception("Problema al almacenar el emprendedor");
            }
        }

        // Se actualiza informacion del emprendimiento
        boolean updateEmprendimiento = false;
        String sqlEmprendimiento = "INSERT INTO emprendimientos SET ";

        if (primeraAtencion.getNombreEmprendimiento() != null) {
            sqlEmprendimiento+= "nombreEmprendimiento = '" + primeraAtencion.getNombreEmprendimiento() + "'";
            updateEmprendimiento = true;
        }

        if (primeraAtencion.getNombreEmpresa() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", nombreEmpresa = '" + primeraAtencion.getNombreEmpresa() + "'";
            } else {
                sqlEmprendimiento+= "nombreEmpresa = '" + primeraAtencion.getNombreEmpresa() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getSectorEmprendimiento() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", sectorEmprendimiento = '" + primeraAtencion.getSectorEmprendimiento() + "'";
            } else {
                sqlEmprendimiento+= "sectorEmprendimiento = '" + primeraAtencion.getSectorEmprendimiento() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getFechaCreacionEmpresa() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", fechaCreacion = '" + primeraAtencion.getFechaCreacionEmpresa() + "'";
            } else {
                sqlEmprendimiento+= "fechaCreacion = '" + primeraAtencion.getFechaCreacionEmpresa() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getSitioWebEmpresa() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", sitioWeb = '" + primeraAtencion.getSitioWebEmpresa() + "'";
            } else {
                sqlEmprendimiento+= "sitioWeb = '" + primeraAtencion.getSitioWebEmpresa() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getEstaConstituida() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", estaConstituida = '" + primeraAtencion.getEstaConstituida() + "'";
            } else {
                sqlEmprendimiento+= "estaConstituida = '" + primeraAtencion.getEstaConstituida() + "'";
                updateEmprendimiento = true;
            }
        }
        if (primeraAtencion.getFechaConstitucion() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", fechaConstitucion = '" + primeraAtencion.getFechaConstitucion() + "'";
            } else {
                sqlEmprendimiento+= "fechaConstitucion = '" + primeraAtencion.getFechaConstitucion() + "'";
                updateEmprendimiento = true;
            }
        }
        if (primeraAtencion.getNitEmpresa() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", nit = '" + primeraAtencion.getNitEmpresa() + "'";
            } else {
                sqlEmprendimiento+= "nit = '" + primeraAtencion.getNitEmpresa() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getRazonSocialEmpresa() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", razonSocial = '" + primeraAtencion.getRazonSocialEmpresa() + "'";
            } else {
                sqlEmprendimiento+= "razonSocial = '" + primeraAtencion.getRazonSocialEmpresa() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getLogoEmpresa() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", logoEmpresa = '" + primeraAtencion.getLogoEmpresaURL() + "'";
            } else {
                sqlEmprendimiento+= "logoEmpresa = '" + primeraAtencion.getLogoEmpresaURL() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getDescripcionProducto() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", descripcionProducto = '" + primeraAtencion.getDescripcionProducto() + "'";
            } else {
                sqlEmprendimiento+= "descripcionProducto = '" + primeraAtencion.getDescripcionProducto() + "'";
                updateEmprendimiento = true;
            }
        }
        if (primeraAtencion.getMateriasPrimas() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", materiasPrimas = '" + primeraAtencion.getMateriasPrimas() + "'";
            } else {
                sqlEmprendimiento+= "materiasPrimas = '" + primeraAtencion.getMateriasPrimas() + "'";
                updateEmprendimiento = true;
            }
        }
        if (primeraAtencion.getClienteEmprendimiento() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", descripcionClientes = '" + primeraAtencion.getClienteEmprendimiento() + "'";
            } else {
                sqlEmprendimiento+= "descripcionClientes = '" + primeraAtencion.getClienteEmprendimiento() + "'";
                updateEmprendimiento = true;
            }
        }

        if (primeraAtencion.getEnfoqueSocial() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", enfoqueSocial = '" + primeraAtencion.getEnfoqueSocial() + "'";
            } else {
                sqlEmprendimiento+= "enfoqueSocial = '" + primeraAtencion.getEnfoqueSocial() + "'";
                updateEmprendimiento = true;
            }
        }
        if (primeraAtencion.getTieneEquipoTrabajo() != 0) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", tieneEquipoTrabajo = '" + primeraAtencion.getTieneEquipoTrabajo() + "'";
            } else {
                sqlEmprendimiento+= "tieneEquipoTrabajo = '" + primeraAtencion.getTieneEquipoTrabajo() + "'";
                updateEmprendimiento = true;
            }
        }
        if (primeraAtencion.getEquipoTrabajo() != null) {
            if (updateEmprendedor){
                sqlEmprendimiento+= ", equipoTrabajo = '" + primeraAtencion.getEquipoTrabajo() + "'";
            } else {
                sqlEmprendimiento+= "equipoTrabajo = '" + primeraAtencion.getEquipoTrabajo() + "'";
                updateEmprendimiento = true;
            }
        }

        if (updateEmprendimiento) {
            sqlEmprendimiento += " , emprendedor_id = " + primeraAtencion.getIdEmprendedor();
            Query query = entityManager.createNativeQuery(sqlEmprendimiento);
            int result = query.executeUpdate();

            if (result != 1) {
                throw new Exception("Problema al almacenar el emprendimiento");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean actualizarEmprendedor(EmprendedorDTO emprendedorDTO) throws Exception {
        boolean updateUsuario = false;
        String sqlUsuario = "UPDATE usuarios SET ";

        if (emprendedorDTO.getTelefono() != null) {
            sqlUsuario+= "telefono = '" + emprendedorDTO.getTelefono() + "'";
            updateUsuario = true;
        }
        if (emprendedorDTO.getCelular() != null) {
            if (updateUsuario){
                sqlUsuario+= ", celular = '" + emprendedorDTO.getCelular() + "'";
            } else {
                sqlUsuario+= "celular = '" + emprendedorDTO.getCelular() + "'";
                updateUsuario = true;
            }
        }
        if (emprendedorDTO.getFotoPerfil() != null) {
            if (updateUsuario){
                sqlUsuario+= ", fotoUrl = '" + emprendedorDTO.getFotoPerfilURL() + "'";
            } else {
                sqlUsuario+= "fotoUrl = '" + emprendedorDTO.getFotoPerfilURL() + "'";
                updateUsuario = true;
            }
        }
        if (updateUsuario) {
            sqlUsuario += " WHERE id = " + emprendedorDTO.getIdEmprendedor();
            Query queryUsuario = entityManager.createNativeQuery(sqlUsuario);
            int resultUsuario = queryUsuario.executeUpdate();

            if (resultUsuario != 1) {
                throw new Exception("Problema al almacenar el usuario");
            }
        }

        // Se actualiza informacion del emprendedor
        boolean updateEmprendedor = false;
        String sqlEmprendedor = "UPDATE emprendedores SET ";

        if (emprendedorDTO.getGenero() != null) {
            sqlEmprendedor+= "genero = '" + emprendedorDTO.getGenero() + "'";
            updateEmprendedor = true;
        }

        if (emprendedorDTO.getDireccion() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", direccion = '" + emprendedorDTO.getDireccion() + "'";
            } else {
                sqlEmprendedor+= "direccion = '" + emprendedorDTO.getDireccion() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getFechaNacimiento() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", fechaNacimiento = '" + emprendedorDTO.getFechaNacimiento() + "'";
            } else {
                sqlEmprendedor+= "fechaNacimiento = '" + emprendedorDTO.getFechaNacimiento() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getCodigoEstudiantil() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", codigoEstudiantil = '" + emprendedorDTO.getCodigoEstudiantil() + "'";
            } else {
                sqlEmprendedor+= "codigoEstudiantil = '" + emprendedorDTO.getCodigoEstudiantil() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getVinculoConU() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", vinculoConU = '" + emprendedorDTO.getVinculoConU() + "'";
            } else {
                sqlEmprendedor+= "vinculoConU = '" + emprendedorDTO.getVinculoConU() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getTipoEstudiante() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", tipoEstudiante = '" + emprendedorDTO.getTipoEstudiante() + "'";
            } else {
                sqlEmprendedor+= "tipoEstudiante = '" + emprendedorDTO.getTipoEstudiante() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getModTrabajoGrado() != 0) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", modTrabajoGrado = '" + emprendedorDTO.getModTrabajoGrado() + "'";
            } else {
                sqlEmprendedor+= "modTrabajoGrado = '" + emprendedorDTO.getModTrabajoGrado() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getDependenciaColaborador() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", dependencia = '" + emprendedorDTO.getDependenciaColaborador() + "'";
            } else {
                sqlEmprendedor+= "dependencia = '" + emprendedorDTO.getDependenciaColaborador() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getCargoColaborador() != null) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", cargo = '" + emprendedorDTO.getCargoColaborador() + "'";
            } else {
                sqlEmprendedor+= "cargo = '" + emprendedorDTO.getCargoColaborador() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getMunicipioId() != 0) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", municipio_id = '" + emprendedorDTO.getMunicipioId() + "'";
            } else {
                sqlEmprendedor+= "municipio_id = '" + emprendedorDTO.getMunicipioId() + "'";
                updateEmprendedor = true;
            }
        }

        if (emprendedorDTO.getProgramaAcademicoId() != 0) {
            if (updateEmprendedor){
                sqlEmprendedor+= ", programaAcademico_id = '" + emprendedorDTO.getProgramaAcademicoId() + "'";
            } else {
                sqlEmprendedor+= "programaAcademico_id = '" + emprendedorDTO.getProgramaAcademicoId() + "'";
                updateEmprendedor = true;
            }
        }
        if (updateEmprendedor) {
            sqlEmprendedor += " WHERE idEmprendedor = " + emprendedorDTO.getIdEmprendedor();
            Query query = entityManager.createNativeQuery(sqlEmprendedor);
            int result = query.executeUpdate();

            if (result != 1) {
                throw new Exception("Problema al almacenar el emprendedor");
            }
        }
        return true;
    }
}
