package uao.edu.co.sinapsis_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static uao.edu.co.sinapsis_app.util.Constants.APP_DATE_OUT_FORMAT;

@Data
@Entity
@Table(name = "T_SINAPSIS_PROY_EMPRENDIMIENTO")
public class ProyectoEmprendimiento {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_PROY_EMPRENDI", sequenceName = "SEC_T_SINAPSIS_PROY_EMPRENDI", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_PROY_EMPRENDI")
    @Column(name = "ID")
    private Long id;
    @Column(name = "ESTADO_EMPRENDIMIENTO")
    private String estadoEmprendimiento;
    @Column(name = "EMPRENDEDORES_ID")
    private Long emprendedor;
    @Column(name = "EMPRENDIMIENTOS_ID")
    private Long emprendimiento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "CREATED_AT")
    private Date fechaCreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = APP_DATE_OUT_FORMAT)
    @Column(name = "UPDATED_AT")
    private Date fechaModificacion;
}
