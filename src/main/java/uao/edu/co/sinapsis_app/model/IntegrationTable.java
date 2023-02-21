package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.*;

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

    //    public IntegrationTable(Object[] obj) {
//        this.id = Long.parseLong(Objects.toString(obj[0], String.valueOf(0)));
//        this.numeroDocumento = Objects.toString(obj[1],null);
//        this.nombres = Objects.toString(obj[2],null);
//        this.apellidos = Objects.toString(obj[3],null);
//        this.correo = Objects.toString(obj[4],null);
//        this.username = Objects.toString(obj[5],null);
//        this.celular = Objects.toString(obj[6],null);
//        this.tipoDocumento = Integer.parseInt(Objects.toString(obj[7], String.valueOf(0)));
//        this.fechaNacimiento = LocalDate.parse(Objects.toString(obj[8],null));
//        this.genero = Objects.toString(obj[9],null);
//        this.direccion = Objects.toString(obj[10],null);
//        this.vinculoConU = Objects.toString(obj[11],null);
//        this.codigoEstudiantil = Objects.toString(obj[12],null);
//        this.tipoEstudiante = Objects.toString(obj[13],null);
//        this.modTrabajoGrado = Integer.parseInt(Objects.toString(obj[14], String.valueOf(0)));
//        this.cursosEmprendimiento = Objects.toString(obj[15],null);
//        this.cargo = Objects.toString(obj[16],null);
//        this.dependencia = Objects.toString(obj[17],null);
//        this.tipoEstudianteEgresado = Objects.toString(obj[18],null);
//        this.programaAcademico = Long.parseLong(Objects.toString(obj[19], String.valueOf(0)));
//        this.profesionEgresado = Long.parseLong(Objects.toString(obj[20], String.valueOf(0)));
//    }
}
