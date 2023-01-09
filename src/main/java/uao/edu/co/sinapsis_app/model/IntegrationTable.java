package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
public class IntegrationTable {
    @Id
    private long id;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String correo;
    private String username;
    private String celular;
    private int tipoDocumento;
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
    private long programaAcademico;
    private long profesionEgresado;

    public IntegrationTable(Object[] obj) {
        this.id = Long.parseLong(Objects.toString(obj[0], String.valueOf(0)));
        this.numeroDocumento = Objects.toString(obj[1],null);
        this.nombres = Objects.toString(obj[2],null);
        this.apellidos = Objects.toString(obj[3],null);
        this.correo = Objects.toString(obj[4],null);
        this.username = Objects.toString(obj[5],null);
        this.celular = Objects.toString(obj[6],null);
        this.tipoDocumento = Integer.parseInt(Objects.toString(obj[7], String.valueOf(0)));
        this.fechaNacimiento = LocalDate.parse(Objects.toString(obj[8],null));
        this.genero = Objects.toString(obj[9],null);
        this.direccion = Objects.toString(obj[10],null);
        this.vinculoConU = Objects.toString(obj[11],null);
        this.codigoEstudiantil = Objects.toString(obj[12],null);
        this.tipoEstudiante = Objects.toString(obj[13],null);
        this.modTrabajoGrado = Integer.parseInt(Objects.toString(obj[14], String.valueOf(0)));
        this.cursosEmprendimiento = Objects.toString(obj[15],null);
        this.cargo = Objects.toString(obj[16],null);
        this.dependencia = Objects.toString(obj[17],null);
        this.tipoEstudianteEgresado = Objects.toString(obj[18],null);
        this.programaAcademico = Long.parseLong(Objects.toString(obj[19], String.valueOf(0)));
        this.profesionEgresado = Long.parseLong(Objects.toString(obj[20], String.valueOf(0)));
    }
}
