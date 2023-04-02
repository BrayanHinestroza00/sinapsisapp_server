package uao.edu.co.sinapsis_app.model.view;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "V_SINAPSIS_EMPRENDEDORES")
public class ConsultoriasView {
    @Id
    @Column(name = "ID_CONSULTORIA")
    private Long idConsultoria;
    @Column(name = "TITULO")
    private String tituloConsultoria;
    @Column(name = "TIPO_CONSULTORIA")
    private String tipoConsultoria;
    @Column(name = "FECHA_CONSULTORIA")
    private String fechaConsultoria;
    @Column(name = "HORA_INICIO")
    private String horaInicioConsultoria;
    @Column(name = "HORA_FIN")
    private String horaFinConsultoria;
    @Column(name = "ASUNTO_CONSULTORIA")
    private String asuntoConsultoria;
    @Column(name = "ESTADO_CONSULTORIA")
    private String estadoConsultoria;
    @Column(name = "COMENTARIOS_CONSULTORIA")
    private String comentariosConsultoria;
    @Column(name = "ID_PROYECTO_EMPRENDIMIENTO")
    private String idProyectoEmprendimiento;
    @Column(name = "EMPRENDEDORES_ID")
    private String idEmprendedor;
    @Column(name = "ID_MENTOR")
    private String idMentor;
    @Column(name = "FACULTAD_MENTOR")
    private String facultadMentor;
    @Column(name = "DEPENDENCIA_MENTOR")
    private String dependenciaMentor;
    @Column(name = "CARGO_MENTOR")
    private String cargoMentor;
    @Column(name = "ID_SUB_ACT_RUTA")
    private String idSubActRuta;
    @Column(name = "NOMBRE_SUB_ACT_RUTA")
    private String nombreSubActRuta;
    @Column(name = "NOMBRES_MENTOR")
    private String nombreMentor;
    @Column(name = "APELLIDOS_MENTOR")
    private String apellidoMentor;
    @Column(name = "CORREO_INSTITUCIONAL_MENTOR")
    private String correoInstitucionalMentor;

    @Column(name = "NUMERO_DOCUMENTO_EMP")
    private String numeroDocumentoEmprendedor;
    @Column(name = "NOMBRES_EMP")
    private String nombreEmprendedor;
    @Column(name = "APELLIDOS_EMP")
    private String apellidoEmprendedor;
    @Column(name = "CORREO_INSTITUCIONAL_EMP")
    private String correoInstitucionalEmprendedor;
    @Column(name = "TELEFONO_CONTACTO_EMP")
    private String telefonoEmprendedor;
    @Column(name = "FOTO_URL_EMP")
    private String fotoUrlEmprendedor;
    @Column(name = "TIPOS_DOCUMENTO_ID_EMP")
    private String tipoDocumentoEmprendedor;
    @Column(name = "CORREO_PERSONAL_EMP")
    private String correoPersonalEmprendedor;
}
