package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uao.edu.co.sinapsis_app.dao.interfaces.IAppDAO;
import uao.edu.co.sinapsis_app.model.*;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppService implements IAppService {
    @Autowired
    IAppDAO appDAO;

    @Override
    public List<TipoDocumento> getAllTipoDocumento() {
        List<TipoDocumento> response = appDAO.getAllTipoDocumento();
        List<TipoDocumento> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setId(String.valueOf(dataArray[0]));
            tipoDocumento.setNombre(String.valueOf(dataArray[1]));
            tipoDocumento.setAcortado(String.valueOf(dataArray[2]));
            datos.add(tipoDocumento);
        }

        return datos;
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoById(int idTipoDocumento) {
        List<TipoDocumento> response = appDAO.getTipoDocumentoById(idTipoDocumento);
        List<TipoDocumento> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setId(String.valueOf(dataArray[0]));
            tipoDocumento.setNombre(String.valueOf(dataArray[1]));
            tipoDocumento.setAcortado(String.valueOf(dataArray[2]));
            datos.add(tipoDocumento);
        }

        return datos;
    }

    @Override
    public List<UsuarioRol> getRolesByUser(long idUsuario) {
        List<UsuarioRol> response = appDAO.getRolesByUser(idUsuario);
        List<UsuarioRol> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setId(Long.parseLong(String.valueOf(dataArray[0])));
            usuarioRol.setRolId(Integer.parseInt(String.valueOf(dataArray[1])));
            usuarioRol.setUsuarioId(Long.parseLong(String.valueOf(dataArray[2])));

            datos.add(usuarioRol);
        }
        return datos;
    }

    @Override
    public int getPreFetchEmprendedor(long idUsuario) {
        return appDAO.getPreFetchEmprendedor(idUsuario);
    }

    @Override
    public List<Emprendimiento> getProyectosEmprendimientoEmprendedor(int idUsuario) {
        List<Emprendimiento> response = appDAO.getProyectosEmprendimientoEmprendedor(idUsuario);
        List<Emprendimiento> datos = new ArrayList<>();

        for (Object data: response){
            Object[] dataArray = (Object[]) data;
            Emprendimiento emprendimiento = new Emprendimiento();
            emprendimiento.setId(Long.parseLong(String.valueOf(dataArray[0])));
            emprendimiento.setEstadoEmprendimiento(String.valueOf(dataArray[17]));
            emprendimiento.setEmprendedorId(Long.parseLong(String.valueOf(dataArray[18])));
            datos.add(emprendimiento);
        }


        return datos;
    }

    @Override
    public List<Departamento> getDepartamentos() {
        List<Departamento> response = appDAO.getDepartamentos();
        List<Departamento> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            Departamento departamento = new Departamento();
            departamento.setId(Integer.parseInt(String.valueOf(dataArray[0])));
            departamento.setNombre(String.valueOf(dataArray[1]));
            datos.add(departamento);
        }

        return datos;
    }

    @Override
    public List<Departamento> getDepartamentosByMunicipio(long idMunicipio) {
        List<Departamento> response = appDAO.getDepartamentosByMunicipio(idMunicipio);
        List<Departamento> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            Departamento departamento = new Departamento();
            departamento.setId(Integer.parseInt(String.valueOf(dataArray[0])));
            departamento.setNombre(String.valueOf(dataArray[1]));
            datos.add(departamento);
        }

        return datos;
    }

    @Override
    public List<Municipio> getMunicipios() {
        List<Municipio> response = appDAO.getMunicipios();
        List<Municipio> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            Municipio municipio = new Municipio();
            municipio.setId(Long.parseLong(String.valueOf(dataArray[0])));
            municipio.setNombre(String.valueOf(dataArray[1]));
            municipio.setDepartamentoId(Integer.parseInt(String.valueOf(dataArray[2])));
            datos.add(municipio);
        }

        return datos;
    }

    @Override
    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        List<Municipio> response = appDAO.getMunicipiosByDepartamento(idDepartamento);
        List<Municipio> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            Municipio municipio = new Municipio();
            municipio.setId(Long.parseLong(String.valueOf(dataArray[0])));
            municipio.setNombre(String.valueOf(dataArray[1]));
            municipio.setDepartamentoId(Integer.parseInt(String.valueOf(dataArray[2])));
            datos.add(municipio);
        }

        return datos;
    }

    @Override
    public List<Municipio> getMunicipiosById(long idMunicipio) {
        List<Municipio> response = appDAO.getMunicipiosById(idMunicipio);
        List<Municipio> datos = new ArrayList<>();

        for (Object data: response) {
            Object[] dataArray = (Object[]) data;
            Municipio municipio = new Municipio();
            municipio.setId(Long.parseLong(String.valueOf(dataArray[0])));
            municipio.setNombre(String.valueOf(dataArray[1]));
            municipio.setDepartamentoId(Integer.parseInt(String.valueOf(dataArray[2])));
            datos.add(municipio);
        }

        return datos;
    }
}
