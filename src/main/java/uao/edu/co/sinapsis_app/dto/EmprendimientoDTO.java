package uao.edu.co.sinapsis_app.dto;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Date;

@Data
public class EmprendimientoDTO {
    private Long idEmprendimiento;
    private String nombreEmprendimiento;
    private String nombreEmpresa;
    private String sectorEmprendimiento;
    private String fechaCreacionEmpresa;
    private String sitioWebEmpresa;
    private String estaConstituida;
    private Date fechaConstitucion;
    private String nitEmpresa;
    private String razonSocialEmpresa;
    private String descripcionProducto;
    private String materiasPrimas;
    private String descripcionClientes;
    private String enfoqueSocial;
    private String necesidadIdentificada;
    private Long logoEmpresaURL;
    private Long fileAutodiagnosticoURL;
    private RedSocialDTO[] redesSociales;
}
