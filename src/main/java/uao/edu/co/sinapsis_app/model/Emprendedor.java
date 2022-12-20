package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
public class Emprendedor extends Usuario{
    private long idEmprendedor;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String vinculoConU;
    private String codigoEstudiantil;
    private String tipoEstudiante;
    private int modTrabajoGrado;
    private String cursosEmprendimiento;
    private String cargo;
    private String dependencia;
    private String tipoEstudianteEgresado;
    private int primeraVez;
    private LocalDate lastLogin;
    private long municipioId;
    private long programaAcademicoId;
    private long profesionEgresadoId;

    public Emprendedor() {
    }
    public Emprendedor(Object[] obj) {
        super(
        Long.parseLong(Objects.toString(obj[0],null)),
                Objects.toString(obj[1],null),
                Objects.toString(obj[2],null),
                Objects.toString(obj[3],null),
                Objects.toString(obj[4],null),
                Objects.toString(obj[5],null),
                Objects.toString(obj[6],null),
                Objects.toString(obj[7],null),
                Objects.toString(obj[8],null),
        Boolean.parseBoolean(Objects.toString(obj[9],null)),
                Objects.toString(obj[10],null),
                Objects.toString(obj[11],null),
        Integer.parseInt(Objects.toString(obj[12],null)),
        Integer.parseInt(Objects.toString(obj[13],null))
        );

        if (!(String.valueOf(obj[14]).equalsIgnoreCase("null"))) {
            this.idEmprendedor = Long.parseLong(Objects.toString(obj[14],null));
        }
        if (!(String.valueOf(obj[15]).equalsIgnoreCase("null"))) {
            this.fechaNacimiento = LocalDate.parse(Objects.toString(obj[15],null));
        }
        if (!(String.valueOf(obj[16]).equalsIgnoreCase("null"))) {
            this.genero = Objects.toString(obj[16],null);
        }
        if (!(String.valueOf(obj[17]).equalsIgnoreCase("null"))) {
            this.direccion = Objects.toString(obj[17],null);
        }
        if (!(String.valueOf(obj[18]).equalsIgnoreCase("null"))) {
            this.vinculoConU = Objects.toString(obj[18],null);
        }
        if (!(String.valueOf(obj[19]).equalsIgnoreCase("null"))) {
            this.codigoEstudiantil = Objects.toString(obj[19],null);
        }
        if (!(String.valueOf(obj[20]).equalsIgnoreCase("null"))) {
            this.tipoEstudiante = Objects.toString(obj[20],null);
        }
        if (!(String.valueOf(obj[21]).equalsIgnoreCase("null"))){
            this.modTrabajoGrado = Integer.parseInt(Objects.toString(obj[21],null));
        }
        if (!(String.valueOf(obj[22]).equalsIgnoreCase("null"))) {
            this.cursosEmprendimiento = Objects.toString(obj[22],null);
        }
        if (!(String.valueOf(obj[23]).equalsIgnoreCase("null"))) {
            this.cargo = Objects.toString(obj[23],null);
        }
        if (!(String.valueOf(obj[24]).equalsIgnoreCase("null"))) {
            this.dependencia = Objects.toString(obj[24],null);
        }
        if (!(String.valueOf(obj[25]).equalsIgnoreCase("null"))) {
            this.tipoEstudianteEgresado = Objects.toString(obj[25],null);
        }
        if (!(String.valueOf(obj[26]).equalsIgnoreCase("null"))) {
            this.primeraVez = Integer.parseInt(Objects.toString(obj[26],null));
        }
        if (!(String.valueOf(obj[27]).equalsIgnoreCase("null"))) {
            this.lastLogin = LocalDate.parse(Objects.toString(obj[27],null));
        }
        if (!(String.valueOf(obj[28]).equalsIgnoreCase("null"))) {
            this.municipioId = Long.parseLong(Objects.toString(obj[28],null));
        }
        if (!(String.valueOf(obj[29]).equalsIgnoreCase("null"))) {
            this.programaAcademicoId = Long.parseLong(Objects.toString(obj[29],null));
        }
        if (!(String.valueOf(obj[30]).equalsIgnoreCase("null"))) {
            this.profesionEgresadoId = Long.parseLong(Objects.toString(obj[30],null));
        }
    }
}
