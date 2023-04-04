package uao.edu.co.sinapsis_app.services.interfaces;

import uao.edu.co.sinapsis_app.model.*;
import uao.edu.co.sinapsis_app.model.view.EmprendimientosEmprendedorView;

import java.util.List;

public interface IAppService {
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
}
