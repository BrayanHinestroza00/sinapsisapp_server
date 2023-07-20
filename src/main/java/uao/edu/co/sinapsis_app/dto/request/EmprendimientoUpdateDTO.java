package uao.edu.co.sinapsis_app.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import uao.edu.co.sinapsis_app.dto.RedSocialDTO;

import javax.validation.constraints.NotNull;


@Data
public class EmprendimientoUpdateDTO {
    private Long idEmprendimiento;

    // Datos del emprendimiento
    @NotNull(message = "El campo 'Nombre Emprendimiento' no puede estar vacio")
    private String nombreEmprendimiento;
    private String nombreEmpresa;
    private String sectorEmprendimiento;
    @JsonAlias({"sitioWeb"})
    private String sitioWebEmpresa;
    @NotNull(message = "El campo 'Esta Constituida' no puede estar vacio")
    private String estaConstituida;
    private String fechaConstitucion;
    private String nitEmpresa;
    private String razonSocialEmpresa;
    private MultipartFile logoEmpresa;
    private Long logoEmpresaURL;
    @NotNull(message = "El campo 'Descripcion Producto' no puede estar vacio")
    private String descripcionProducto;
    @NotNull(message = "El campo 'Materias Primas' no puede estar vacio")
    private String materiasPrimas;
    @NotNull(message = "El campo 'Descripcion Clientes' no puede estar vacio")
    private String descripcionClientes;
    private String enfoqueSocial;
    @JsonAlias("necesidadesIdentificadas")
    private String necesidadesIdentificadas;
    private RedSocialDTO[] redesSociales;


    public void setRedesSociales(String redesSociales) {
        Gson gson = new Gson();

        RedSocialDTO[] redes = gson.fromJson(redesSociales, RedSocialDTO[].class);

        this.redesSociales = redes;
    }
}

