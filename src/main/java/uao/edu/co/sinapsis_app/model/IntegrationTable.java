package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static uao.edu.co.sinapsis_app.util.Constants.INTEGRATION_TIPO_CONTACTO_COLABORADOR;
import static uao.edu.co.sinapsis_app.util.Constants.INTEGRATION_TIPO_CONTACTO_EGRESADO;
import static uao.edu.co.sinapsis_app.util.Constants.INTEGRATION_TIPO_CONTACTO_ESTUDIANTE;
import static uao.edu.co.sinapsis_app.util.Constants.INTEGRATION_TIPO_CONTACTO_EXTERNO;
import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_COLABORADOR;
import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_EGRESADO;
import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_ESTUDIANTE;
import static uao.edu.co.sinapsis_app.util.Constants.TIPO_CONTACTO_EXTERNO;


@Entity
@Data
@Table(name = "T_SINAPSIS_INTEGRATION")
public class IntegrationTable {
    //Datos de usuario
    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "CORREO_INSTITUCIONAL")
    private String correoInstitucional;
    @Column(name = "CORREO_PERSONAL")
    private String correoPersonal;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefono_contacto;
    @Column(name = "TIPO_DOCUMENTO")
    private Long tipoDocumento;

    // #Datos de emprendedor#
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "MUNICIPIO_RESIDENCIA")
    private String municipioResidencia;
    @Column(name = "DEPARTAMENTO_RESIDENCIA")
    private String departamentoResidencia;
    @Column(name = "FECHA_NACIMIENTO")
    private String fechaNacimiento;
    @Column(name = "VINCULO_CON_U")
    private String tipoContacto;
    // Estudiante
    @Column(name = "CODIGO_ESTUDIANTIL")
    private String codigoEstudiantil;
    @Column(name = "TIPO_ESTUDIANTE")
    private String nivelAcademico;
    @Column(name = "MOD_TRABAJO_GRADO")
    private Integer modalidadTrabajoGrado;
    @Column(name = "CURSOS_EMPRENDIMIENTO")
    private String cursosEmprendimiento;
    @Column(name = "PROGRAMA_ACADEMICO")
    private String programaAcademico;

    //Egresado
    @Column(name = "TIPO_ESTUDIANTE_EGRESADO")
    private String nivelAcademicoEgresado;
    @Column(name = "PROFESION_EGRESADO")
    private String programaAcademicoEgresado;

    //Colaborador
    @Column(name = "CARGO_COLABORADOR")
    private String cargo;
    @Column(name = "DEPENDENCIA_COLABORADOR")
    private String dependencia;
    @Column(name = "FACULTAD_COLABORADOR")
    private String facultad;


    public void setFechaNacimiento(String fechaNacimiento) {
        if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
            this.fechaNacimiento = fechaNacimiento.substring(0,8);
        }
    }

    public String getFechaNacimiento() {
        if (fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
            return fechaNacimiento.substring(0,8);
        }
        return null;
    }

    public int getTipoContacto() {
        switch (tipoContacto) {
            case INTEGRATION_TIPO_CONTACTO_ESTUDIANTE :
                return TIPO_CONTACTO_ESTUDIANTE;
            case INTEGRATION_TIPO_CONTACTO_EGRESADO :
                return TIPO_CONTACTO_EGRESADO;
            case INTEGRATION_TIPO_CONTACTO_COLABORADOR :
                return TIPO_CONTACTO_COLABORADOR;
            case INTEGRATION_TIPO_CONTACTO_EXTERNO :
                return TIPO_CONTACTO_EXTERNO;
            default:
                return 0;
        }
    }
}
