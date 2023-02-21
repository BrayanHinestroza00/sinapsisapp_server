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
        //        List<TipoDocumento> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            TipoDocumento tipoDocumento = new TipoDocumento();
//            tipoDocumento.setId(String.valueOf(dataArray[0]));
//            tipoDocumento.setNombre(String.valueOf(dataArray[1]));
//            tipoDocumento.setAcortado(String.valueOf(dataArray[2]));
//            datos.add(tipoDocumento);
//        }

        return appDAO.getAllTipoDocumento();
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoById(int idTipoDocumento) {
        //        List<TipoDocumento> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            TipoDocumento tipoDocumento = new TipoDocumento();
//            tipoDocumento.setId(String.valueOf(dataArray[0]));
//            tipoDocumento.setNombre(String.valueOf(dataArray[1]));
//            tipoDocumento.setAcortado(String.valueOf(dataArray[2]));
//            datos.add(tipoDocumento);
//        }

        return appDAO.getTipoDocumentoById(idTipoDocumento);
    }

    @Override
    public List<UsuarioRol> getRolesByUser(long idUsuario) {
        //        List<UsuarioRol> datos = new ArrayList<>();

//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            UsuarioRol usuarioRol = new UsuarioRol();
////            usuarioRol.setId(Long.parseLong(String.valueOf(dataArray[0])));
////            usuarioRol.setRolId(Integer.parseInt(String.valueOf(dataArray[1])));
////            usuarioRol.setUsuarioId(Long.parseLong(String.valueOf(dataArray[2])));
//
//            datos.add(usuarioRol);
//        }
        return appDAO.getRolesByUser(idUsuario);
    }

    @Override
    public int getPreFetchEmprendedor(long idUsuario) {
        return appDAO.getPreFetchEmprendedor(idUsuario);
    }

    @Override
    public List<ProyectoEmprendimiento> getProyectosEmprendimientoEmprendedor(int idUsuario) {
        //        List<Emprendimiento> datos = new ArrayList<>();
//
//        for (Object data: response){
//            Object[] dataArray = (Object[]) data;
//            Emprendimiento emprendimiento = new Emprendimiento();
//            emprendimiento.setId(Long.parseLong(String.valueOf(dataArray[0])));
//            emprendimiento.setNombreEmprendimiento(String.valueOf(dataArray[1]));
////            emprendimiento.setEstadoEmprendimiento(String.valueOf(dataArray[17]));
////            emprendimiento.setEmprendedorId(Long.parseLong(String.valueOf(dataArray[19])));
//            datos.add(emprendimiento);
//        }


        return appDAO.getProyectosEmprendimientoEmprendedor(idUsuario);
    }

    @Override
    public List<Departamento> getDepartamentos() {
        //        List<Departamento> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            Departamento departamento = new Departamento();
//            departamento.setId(Integer.parseInt(String.valueOf(dataArray[0])));
//            departamento.setNombre(String.valueOf(dataArray[1]));
//            datos.add(departamento);
//        }

        return appDAO.getDepartamentos();
    }

    @Override
    public List<Departamento> getDepartamentosByMunicipio(long idMunicipio) {
        //        List<Departamento> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            Departamento departamento = new Departamento();
//            departamento.setId(Integer.parseInt(String.valueOf(dataArray[0])));
//            departamento.setNombre(String.valueOf(dataArray[1]));
//            datos.add(departamento);
//        }

        return appDAO.getDepartamentosByMunicipio(idMunicipio);
    }

    @Override
    public List<Municipio> getMunicipios() {
        //        List<Municipio> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            Municipio municipio = new Municipio();
//            municipio.setId(Long.parseLong(String.valueOf(dataArray[0])));
//            municipio.setNombre(String.valueOf(dataArray[1]));
//            municipio.setDepartamentoId(Integer.parseInt(String.valueOf(dataArray[2])));
//            datos.add(municipio);
//        }

        return appDAO.getMunicipios();
    }

    @Override
    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        //        List<Municipio> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            Municipio municipio = new Municipio();
//            municipio.setId(Long.parseLong(String.valueOf(dataArray[0])));
//            municipio.setNombre(String.valueOf(dataArray[1]));
//            municipio.setDepartamentoId(Integer.parseInt(String.valueOf(dataArray[2])));
//            datos.add(municipio);
//        }

        return appDAO.getMunicipiosByDepartamento(idDepartamento);
    }

    @Override
    public List<Municipio> getMunicipiosById(long idMunicipio) {
        //        List<Municipio> datos = new ArrayList<>();
//
//        for (Object data: response) {
//            Object[] dataArray = (Object[]) data;
//            Municipio municipio = new Municipio();
//            municipio.setId(Long.parseLong(String.valueOf(dataArray[0])));
//            municipio.setNombre(String.valueOf(dataArray[1]));
//            municipio.setDepartamentoId(Integer.parseInt(String.valueOf(dataArray[2])));
//            datos.add(municipio);
//        }

        return appDAO.getMunicipiosById(idMunicipio);
    }

    @Override
    public List<Facultad> getFacultades() {
        return appDAO.getFacultades();
    }

    @Override
    public List<Facultad> getFacultadesByProgramaAcademico(int idPrograma) {
        return appDAO.getFacultadesByProgramaAcademico(idPrograma);
    }

    @Override
    public List<ProgramaAcademico> getProgramasAcademicosByidFacultad(int idFacultad) {
        return appDAO.getProgramasAcademicosByidFacultad(idFacultad);
    }

    @Override
    public List<ProgramaAcademico> getProgramasAcademicosById(long idPrograma) {
        return appDAO.getProgramasAcademicosById(idPrograma);
    }

    @Override
    public List<ProgramaAcademico> getProgramasAcademicos() {
        return appDAO.getProgramasAcademicos();
    }
}
