package uao.edu.co.sinapsis_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Data
@Entity
@Table(name = "T_SINAPSIS_EMPRENDEDORES")
public class Emprendedor {
    @Id
    @Column(name = "ID")
    private Long idEmprendedor;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "DIRECCION_RESIDENCIA")
    private String direccion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;
    @Column(name = "CODIGO_ESTUDIANTIL")
    private String codigoEstudiantil;
    @Column(name = "PROGRAMA_ACADEMICO_ID")
    private Long programaAcademico;
    @Column(name = "TIPO_CONTACTO")
    private Integer tipoContacto;
    @Column(name = "NIVEL_ACADEMICO")
    private String nivelAcademico;
    @Column(name = "MODALIDAD_TRABAJO_GRADO")
    private Integer modalidadTrabajoGrado;
    @Column(name = "DEPENDENCIA_COLABORADOR")
    private String dependencia;
    @Column(name = "CARGO_COLABORADOR")
    private String cargo;
    @Column(name = "ULTIMO_INGRESO")
    private Date ultimoIngreso;
    @Column(name = "MUNICIPIOS_ID")
    private Long municipio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "PRIMERA_VEZ")
    private Integer primeraVez;
    @Column(name = "OTRO_PROGRAMA_ACADEMICO")
    private String otroProgramaAcademico;
}
