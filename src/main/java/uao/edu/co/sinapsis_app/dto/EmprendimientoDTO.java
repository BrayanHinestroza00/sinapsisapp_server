package uao.edu.co.sinapsis_app.dto;

import lombok.Data;

@Data
public class EmprendimientoDTO {
    private Long idEmprendimiento;
    private String nombreEmprendimiento;
    private String nombreEmpresa;
    private String sectorEmprendimiento;
    private String fechaCreacionEmpresa;
    private String sitioWebEmpresa;
    private String estaConstituida;
    private String fechaConstitucion;
    private String nitEmpresa;
    private String razonSocialEmpresa;
    private String descripcionProducto;
    private String materiasPrimas;
    private String descripcionClientes;
    private String enfoqueSocial;
    private String necesidadIdentificada;
    private String logoEmpresaURL;
    private String fileAutodiagnosticoURL;
}
