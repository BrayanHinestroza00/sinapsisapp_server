package uao.edu.co.sinapsis_app.util;

public class Constants {
    public static final int STATUS_OK = 1;
    public static final int STATUS_ERROR = -1;
    public static final int STATUS_EMPTY = 0;

    public static final String APP_DATE_IN_FORMAT = "dd/MM/yy";
    public static final String APP_DATE_OUT_FORMAT = "dd/MM/yyyy";
    public static final String APP_INTEGRATION_DATE_INPUT_FORMAT = "dd/MM/yy";

    /**
     * Default values
     */
    public static final String T_SINAPSIS_USUARIOS_DEFAULT_ESTADO = "ACTIVO";
    public static final int T_SINAPSIS_USUARIOS_DEFAULT_ESTADO_CUENTA = 1;
    public static final boolean T_SINAPSIS_USUARIOS_DEFAULT_TTO_DATOS_PERSONALES_INTEGRATION = true;
    public static final int T_SINAPSIS_EMPRENDEDORES_DEFAULT_TIPO_CONTACTO = 4;
    public static final int T_SINAPSIS_EMPRENDEDORES_DEFAULT_PRIMERA_VEZ = 1;
    public static final int T_SINAPSIS_EMPRENDEDORES_PRIMERA_VEZ_FALSE = 0;
    public static final long T_SINAPSIS_ROLES_EMPRENDEDOR = 3L;
    public static final String T_SINAPSIS_PROY_EMPRENDIMIENTO_DEFAULT_ESTADO = "PENDIENTE";

    /**
     * APP States
     */
    public static final int TIPO_CONTACTO_ESTUDIANTE = 1;
    public static final int TIPO_CONTACTO_EGRESADO = 2;
    public static final int TIPO_CONTACTO_COLABORADOR = 3;
    public static final int TIPO_CONTACTO_EXTERNO = 4;

    /**
     * Integration Status
     */
    public static final String INTEGRATION_TIPO_CONTACTO_ESTUDIANTE = "ESTUDIANTE";
    public static final String INTEGRATION_TIPO_CONTACTO_EGRESADO = "EGRESADO";
    public static final String INTEGRATION_TIPO_CONTACTO_COLABORADOR = "COLABORADOR";
    public static final String INTEGRATION_TIPO_CONTACTO_EXTERNO = "EXTERNO";

}
