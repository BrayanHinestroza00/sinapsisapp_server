package uao.edu.co.sinapsis_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uao.edu.co.sinapsis_app.dao.interfaces.IAppDAO;
import uao.edu.co.sinapsis_app.dto.UsuarioUpdateDTO;
import uao.edu.co.sinapsis_app.dto.request.PublicarAnuncioDTO;
import uao.edu.co.sinapsis_app.model.Anuncio;
import uao.edu.co.sinapsis_app.model.Asignatura;
import uao.edu.co.sinapsis_app.model.AsignaturaEmprendedor;
import uao.edu.co.sinapsis_app.model.Departamento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.Facultad;
import uao.edu.co.sinapsis_app.model.Municipio;
import uao.edu.co.sinapsis_app.model.ProgramaAcademico;
import uao.edu.co.sinapsis_app.model.RedSocial;
import uao.edu.co.sinapsis_app.model.TipoContacto;
import uao.edu.co.sinapsis_app.model.TipoDocumento;
import uao.edu.co.sinapsis_app.model.UsuarioRol;
import uao.edu.co.sinapsis_app.model.view.ActividadesEtapaView;
import uao.edu.co.sinapsis_app.model.view.EmprendedoresView;
import uao.edu.co.sinapsis_app.model.view.EmprendimientosEmprendedorView;
import uao.edu.co.sinapsis_app.model.view.UsuariosView;
import uao.edu.co.sinapsis_app.services.interfaces.IAppService;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

import java.text.ParseException;
import java.util.List;

@Service
public class AppService implements IAppService {
    @Autowired
    IAppDAO appDAO;

    @Autowired
    IStorageService storageService;

    @Override
    public List<TipoDocumento> getAllTipoDocumento() {
        return appDAO.getAllTipoDocumento();
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoById(int idTipoDocumento) {
        return appDAO.getTipoDocumentoById(idTipoDocumento);
    }

    @Override
    public List<UsuarioRol> getRolesByUser(long idUsuario) {
        return appDAO.getRolesByUser(idUsuario);
    }

    @Override
    public int getPreFetchEmprendedor(long idUsuario) {
        return appDAO.getPreFetchEmprendedor(idUsuario);
    }

    @Override
    public List<EmprendimientosEmprendedorView> getProyectosEmprendimientoEmprendedor(int idUsuario) {
        return appDAO.getProyectosEmprendimientoEmprendedor(idUsuario);
    }

    @Override
    public List<Departamento> getDepartamentos() {
        return appDAO.getDepartamentos();
    }

    @Override
    public List<Departamento> getDepartamentosByMunicipio(long idMunicipio) {
        return appDAO.getDepartamentosByMunicipio(idMunicipio);
    }

    @Override
    public List<Municipio> getMunicipios() {
        return appDAO.getMunicipios();
    }

    @Override
    public List<Municipio> getMunicipiosByDepartamento(int idDepartamento) {
        return appDAO.getMunicipiosByDepartamento(idDepartamento);
    }

    @Override
    public List<Municipio> getMunicipiosById(long idMunicipio) {
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

    @Override
    public List<Asignatura> getAsignaturas() {
        return appDAO.getAsignaturas();
    }

    @Override
    public List<Asignatura> getAsignaturasById(int idAsignatura) {
        return appDAO.getAsignaturasById(idAsignatura);
    }

    @Override
    public List<RedSocial> obtenerRedesSociales() {
        return appDAO.obtenerRedesSociales();
    }

    @Override
    public List<EtapaRutaInnovacion> obtenerEtapasRutaInnovacionEmprendimiento() {
        return appDAO.obtenerEtapasRutaInnovacionEmprendimiento();
    }

    @Override
    public List<Anuncio> obtenerAnuncios() {
        return appDAO.obtenerAnuncios();
    }

    @Override
    public List<TipoContacto> getAllTipoContacto() {
        return appDAO.getAllTipoContacto();
    }

    @Override
    public List<TipoContacto> getTipoContactoById(long idTipoContacto) {
        return appDAO.getTipoContactoById(idTipoContacto);
    }

    @Override
    public boolean registrarAnuncio(PublicarAnuncioDTO anuncioDTO) throws ParseException {
        if (anuncioDTO.getFlyerAnuncio() != null) {
            Long filePathFlyerAnuncio = storageService.storeDB(anuncioDTO.getFlyerAnuncio());
            anuncioDTO.setFlyerAnuncioURL(filePathFlyerAnuncio);
        }

        return appDAO.registrarAnuncio(anuncioDTO);
    }

    @Override
    public List<ActividadesEtapaView> obtenerTematicasEtapasRutaInnovacionEmprendimiento(Long idEtapa) {
        return appDAO.obtenerTematicasEtapasRutaInnovacionEmprendimiento(idEtapa);
    }

    @Override
    public UsuariosView getInformacionUsuario(Long idUsuario) {

        return appDAO.getInformacionUsuario(idUsuario);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean actualizarPerfilUsuario(UsuarioUpdateDTO usuarioUpdateDTO) throws Exception {
        if (usuarioUpdateDTO.getFotoPerfil() != null) {
            Long filePathFotoPerfil = storageService.storeDB(usuarioUpdateDTO.getFotoPerfil());
            usuarioUpdateDTO.setFotoPerfilURL(filePathFotoPerfil);
        }
        return appDAO.actualizarPerfilUsuario(usuarioUpdateDTO);
    }

    @Override
    public String[] consultarCorreosAdministradores() {
        return appDAO.consultarCorreosAdministradores();
    }
}
