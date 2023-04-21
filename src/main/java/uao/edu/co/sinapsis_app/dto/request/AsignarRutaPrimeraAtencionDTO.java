package uao.edu.co.sinapsis_app.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AsignarRutaPrimeraAtencionDTO implements Serializable {
    @NotNull(message = "El campo 'Proyecto Emprendimiento' no puede estar vacio")
    private Long idProyectoEmprendimiento;
    @NotNull(message = "El campo 'Etapa Ruta' no puede estar vacio")
    private Long idEtapaRuta;
    @NotNull(message = "El campo 'Creado Por' no puede estar vacio")
    private Long creado_por;
    @NotNull(message = "El campo 'Mentor principal' no puede estar vacio")
    private Long idMentorPrincipal;
}
