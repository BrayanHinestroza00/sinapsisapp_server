package uao.edu.co.sinapsis_app.dao.interfaces;

import uao.edu.co.sinapsis_app.dto.request.PublicarAnuncioDTO;
import uao.edu.co.sinapsis_app.model.Anuncio;
import uao.edu.co.sinapsis_app.model.Asignatura;
import uao.edu.co.sinapsis_app.model.Departamento;
import uao.edu.co.sinapsis_app.model.EtapaRutaInnovacion;
import uao.edu.co.sinapsis_app.model.Facultad;
import uao.edu.co.sinapsis_app.model.Municipio;
import uao.edu.co.sinapsis_app.model.ProgramaAcademico;
import uao.edu.co.sinapsis_app.model.RedSocial;
import uao.edu.co.sinapsis_app.model.TipoContacto;
import uao.edu.co.sinapsis_app.model.TipoDocumento;
import uao.edu.co.sinapsis_app.model.UsuarioRol;
import uao.edu.co.sinapsis_app.model.view.EmprendimientosEmprendedorView;

import java.text.ParseException;
import java.util.List;

public interface IAppDAO {
    public List<TipoDocumento> getAllTipoDocumento();
    List<TipoDocumento> getTipoDocumentoById(int idTipoDocumento);

    public List<UsuarioRol> getRolesByUser(long idUsuario);

    public int getPreFetchEmprendedor(long idUsuario);

    List<EmprendimientosEmprendedorView> getProyectosEmprendimientoEmprendedor(int idUsuario);

    List<Departamento> getDepartamentos();
    List<Departamento> getDepartamentosByMunicipio(long idMunicipio);

    List<Municipio> getMunicipios();

    List<Municipio> getMunicipiosByDepartamento(int idDepartamento);

    List<Municipio> getMunicipiosById(long idMunicipio);

    List<Facultad> getFacultades();

    List<Facultad> getFacultadesByProgramaAcademico(int idPrograma);

    List<ProgramaAcademico> getProgramasAcademicosByidFacultad(int idFacultad);

    List<ProgramaAcademico> getProgramasAcademicosById(long idPrograma);

    List<ProgramaAcademico> getProgramasAcademicos();

    List<Asignatura> getAsignaturas();

    List<Asignatura> getAsignaturasById(int idAsignatura);

    List<RedSocial> obtenerRedesSociales();

    List<EtapaRutaInnovacion> obtenerEtapasRutaInnovacionEmprendimiento();

    List<Anuncio> obtenerAnuncios();

    List<TipoContacto> getAllTipoContacto();

    List<TipoContacto> getTipoContactoById(long idTipoContacto);

    boolean registrarAnuncio(PublicarAnuncioDTO anuncioDTO) throws ParseException;
}
