package uao.edu.co.sinapsis_app.dao;

import org.springframework.stereotype.Repository;
import uao.edu.co.sinapsis_app.dao.interfaces.IReportesDAO;
import uao.edu.co.sinapsis_app.dto.request.ReporteConsultoriasMentorDTO;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXEtapa;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.formacion.NroEmprendedoresXTipoContacto;
import uao.edu.co.sinapsis_app.model.reportes.gestion.EstadoConsultoriaXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMentorXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXMesXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroConsultoriasXTipoXMes;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedores;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresRutaXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresXModalidad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresXMunicipio;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroEmprendedoresRutaXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimiento;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimientoXFacultad;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosEmprendimientoXPrograma;
import uao.edu.co.sinapsis_app.model.reportes.gestion.NroProyectosXEmprendedor;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ReportesDAO implements IReportesDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ConsultoriasView> generarReporteConsultoriasPorMentor(ReporteConsultoriasMentorDTO reportesFilters) {
        String sql = "SELECT * \n" +
                "FROM V_SINAPSIS_CONSULTORIAS \n" +
                "WHERE ID_MENTOR =:id \n" +
                "    AND FECHA_CONSULTORIA >= TO_DATE('"+reportesFilters.getFechaInicio()+"', 'YYYY-MM-DD') \n" +
                "    AND FECHA_CONSULTORIA <= TO_DATE('"+reportesFilters.getFechaFin()+"', 'YYYY-MM-DD')";
        Query query = entityManager.createNativeQuery(sql, ConsultoriasView.class);

        query.setParameter("id", reportesFilters.getIdMentor());
//        query.setParameter("desde", reportesFilters.getFechaInicio());
//        query.setParameter("hasta", reportesFilters.getFechaFin());

        return query.getResultList();
    }

    @Override
    public NroEmprendedores consultarNroEmprendedores() {
        String sql = "SELECT COUNT(*) NRO_EMPRENDEDORES FROM (SELECT DISTINCT TE.* FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "    SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "            ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "    FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                ") TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO'\n" +
                "ORDER BY ID_RUTA_EMPRENDIMIENTO DESC)";

        String sql2 = "SELECT COUNT(*) NRO_EMPRENDEDORES FROM (SELECT TE.* FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "    SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "            ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "    FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                ") TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO'\n" +
                "ORDER BY ID_RUTA_EMPRENDIMIENTO DESC)";

        Query query = entityManager.createNativeQuery(sql);
        Query query2 = entityManager.createNativeQuery(sql2);

        BigDecimal result1 = (BigDecimal) query.getSingleResult();
        BigDecimal result2 = (BigDecimal) query2.getSingleResult();

        return new NroEmprendedores(result1.toString(), result2.toString());
    }

    @Override
    public List<NroEmprendedoresRutaXPrograma> consultarNroEmprendedoresRutaXPrograma() {
        String sql = "SELECT COUNT(ID) NRO_EMPRENDEDORES, PROOGRAMA_ACADEMICO  FROM (SELECT DISTINCT \n" +
                "        TE.ID, \n" +
                "        CASE \n" +
                "        WHEN TIPO_CONTACTO NOT IN (1,2) THEN\n" +
                "            'N/A'\n" +
                "        WHEN TP.ID = 0 THEN\n" +
                "            NVL(TE.OTRO_PROGRAMA_ACADEMICO, 'OTRO PROGRAMA ACADEMICO')\n" +
                "        ELSE \n" +
                "            NVL(TP.NOMBRE, 'NO REGISTRA')\n" +
                "        END PROOGRAMA_ACADEMICO\n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "    JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "        SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "                ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "        FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                "    ) TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID\n" +
                "    WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO' --AND TIPO_CONTACTO IN (1,2)\n" +
                "    ORDER BY VPE.ID_RUTA_EMPRENDIMIENTO DESC)\n" +
                "GROUP BY PROOGRAMA_ACADEMICO";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresRutaXPrograma.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresRutaXFacultad> consultarNroEmprendedoresRutaXFacultad() {
        String sql = "SELECT COUNT(ID) NRO_EMPRENDEDORES, FACULTAD  FROM (SELECT DISTINCT\n" +
                "        TE.ID, \n" +
                "        CASE " +
                "        WHEN TIPO_CONTACTO NOT IN (1,2) THEN\n" +
                "            'N/A'" +
                "        WHEN TP.ID = 0 OR TP.ID IS NULL THEN\n" +
                "            'NO REGISTRA'\n" +
                "        ELSE \n" +
                "            NVL(TF.NOMBRE, 'NO REGISTRA')\n" +
                "        END FACULTAD\n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "    JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "        SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "                ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "        FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                "    ) TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID\n" +
                "    WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO' --AND TIPO_CONTACTO IN (1,2)\n" +
                "    ORDER BY VPE.ID_RUTA_EMPRENDIMIENTO DESC)\n" +
                "GROUP BY FACULTAD";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresRutaXFacultad.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresXMunicipio> consultarNroEmprendedoresXMunicipio() {
        String sql = "SELECT \n" +
                "    COUNT(TE.ID) NRO_EMPRENDEDORES,  NVL(TM.NOMBRE, 'NO REGISTRA') NOMBRE_MUNICIPIO\n" +
                "FROM T_SINAPSIS_EMPRENDEDORES TE JOIN T_SINAPSIS_USUARIOS TU ON TE.ID = TU.ID\n" +
                "LEFT JOIN T_SINAPSIS_MUNICIPIOS TM ON TE.MUNICIPIOS_ID = TM.ID\n" +
                "GROUP BY TM.NOMBRE";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresXMunicipio.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresXModalidad> consultarNroEmprendedoresXModalidad() {
        String sql = "SELECT \n" +
                "    COUNT(ID) NRO_EMPRENDEDORES,  MODALIDAD_TRABAJO_GRADO\n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        ID, \n" +
                "        CASE \n" +
                "            WHEN TIPO_CONTACTO NOT IN (1,2) THEN\n" +
                "                'N/A'\n" +
                "            ELSE \n" +
                "                DECODE(MODALIDAD_TRABAJO_GRADO, 0, 'NO', 'SI')\n" +
                "        END MODALIDAD_TRABAJO_GRADO\n" +
                "FROM  T_SINAPSIS_EMPRENDEDORES\n" +
                ")\n" +
                "GROUP BY MODALIDAD_TRABAJO_GRADO";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresXModalidad.class);

        return query.getResultList();
    }

    @Override
    public List<NroProyectosXEmprendedor> consultarNroProyectosXEmprendedor() {
        String sql = "SELECT \n" +
                "    COUNT(ID) NRO_PROYECTOS_EMPRENDIMIENTO, \n" +
                "    NOMBRE_COMPLETO\n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        TPE.ID,\n" +
                "        TE.ID ID_EMP,\n" +
                "        (TU.NOMBRES || ' ' || TU.APELLIDOS) NOMBRE_COMPLETO\n" +
                "    FROM T_SINAPSIS_PROY_EMPRENDIMIENTO TPE JOIN T_SINAPSIS_EMPRENDEDORES TE ON TPE.EMPRENDEDORES_ID = TE.ID\n" +
                "    JOIN T_SINAPSIS_USUARIOS TU ON TE.ID=TU.ID)\n" +
                "GROUP BY NOMBRE_COMPLETO, ID_EMP";

        Query query = entityManager.createNativeQuery(sql, NroProyectosXEmprendedor.class);

        return query.getResultList();
    }

    @Override
    public NroProyectosEmprendimiento consultarNroProyectosEmprendimiento() {
        String sql = "SELECT COUNT(TE.ID) NRO_PROYECTOS_EMPRENDIMIENTO FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "ORDER BY TE.ID DESC";

        String sql2 = "SELECT COUNT(ID_RUTA_EMPRENDIMIENTO) NRO_PROYECTOS_EMPRENDIMIENTO FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "    SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "            ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "    FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                ") TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO'\n" +
                "ORDER BY ID_RUTA_EMPRENDIMIENTO DESC";

        Query query = entityManager.createNativeQuery(sql);
        Query query2 = entityManager.createNativeQuery(sql2);

        BigDecimal result1 = (BigDecimal) query.getSingleResult();
        BigDecimal result2 = (BigDecimal) query2.getSingleResult();

        return new NroProyectosEmprendimiento(result1.toString(), result2.toString());
    }

    @Override
    public List<NroProyectosEmprendimientoXPrograma> consultarNroProyectosEmprendimientoXPrograma() {
        String sql = "SELECT \n" +
                "COUNT(ID) NRO_PROYECTOS_EMPRENDIMIENTO, PROOGRAMA_ACADEMICO\n" +
                "FROM     \n" +
                "    (SELECT \n" +
                "        TPE.ID, \n" +
                "        CASE \n" +
                "        WHEN TIPO_CONTACTO NOT IN (1,2) THEN\n" +
                "            'N/A'\n" +
                "        WHEN TP.ID = 0 THEN\n" +
                "            NVL(TE.OTRO_PROGRAMA_ACADEMICO, 'OTRO PROGRAMA ACADEMICO')\n" +
                "        ELSE \n" +
                "            NVL(TP.NOMBRE, 'NO REGISTRA')\n" +
                "        END PROOGRAMA_ACADEMICO\n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "    JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "        SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "                ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "        FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                "    ) TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID\n" +
                "    WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO'\n" +
                "    ORDER BY VPE.ID_RUTA_EMPRENDIMIENTO DESC)\n" +
                "GROUP BY PROOGRAMA_ACADEMICO";

        Query query = entityManager.createNativeQuery(sql, NroProyectosEmprendimientoXPrograma.class);

        return query.getResultList();
    }

    @Override
    public List<NroProyectosEmprendimientoXFacultad> consultarNroProyectosEmprendimientoXFacultad() {
        String sql = "SELECT \n" +
                "COUNT(ID) NRO_PROYECTOS_EMPRENDIMIENTO, FACULTAD\n" +
                "FROM     \n" +
                "    (SELECT \n" +
                "        TPE.ID, \n" +
                "        CASE \n" +
                "        WHEN TIPO_CONTACTO NOT IN (1,2) THEN\n" +
                "            'N/A'\n" +
                "        WHEN TP.ID = 0 OR TP.ID IS NULL THEN\n" +
                "            'NO REGISTRA'\n" +
                "        ELSE \n" +
                "            NVL(TF.NOMBRE, 'NO REGISTRA')\n" +
                "        END FACULTAD\n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "    JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID ETAPAS_RUTA_ID FROM (\n" +
                "        SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "                ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "        FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                "    ) TSRE JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON TSRE.ETAPAS_ID = TSER.ID WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID\n" +
                "    WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO'\n" +
                "    ORDER BY VPE.ID_RUTA_EMPRENDIMIENTO DESC)\n" +
                "GROUP BY FACULTAD";

        Query query = entityManager.createNativeQuery(sql, NroProyectosEmprendimientoXFacultad.class);

        return query.getResultList();
    }

    @Override
    public List<NroConsultoriasXTipoXMes> consultarNroConsultoriasXTipoXMes() {
        String sql = "SELECT \n" +
                "    COUNT(ID) NRO_CONSULTORIAS, TIPO_CONSULTORIA, MES_CONSULTORIA\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        ID,\n" +
                "        TO_CHAR(TO_DATE(fecha_consultoria, 'DD-MM-YYYY'), 'Month') MES_CONSULTORIA,\n" +
                "        TO_CHAR(TO_DATE(fecha_consultoria, 'DD-MM-YYYY'), 'MM') MES,\n" +
                "        DECODE(tipo_consultoria, 'N', 'NORMAL', 'ESPECIALIZADA') TIPO_CONSULTORIA\n" +
                "    FROM T_SINAPSIS_CONSULTORIAS) \n" +
                "GROUP BY TIPO_CONSULTORIA, MES_CONSULTORIA, MES\n" +
                "ORDER BY MES ASC";

        Query query = entityManager.createNativeQuery(sql, NroConsultoriasXTipoXMes.class);

        return query.getResultList();
    }

    @Override
    public List<NroConsultoriasXMes> consultarNroConsultoriasXMes() {
        String sql = "SELECT \n" +
                "    COUNT(ID) NRO_CONSULTORIAS, MES_CONSULTORIA\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        ID,\n" +
                "        DECODE(tipo_consultoria, 'N', 'NORMAL', 'ESPECIALIZADA') TIPO_CONSULTORIA,\n" +
                "        TO_CHAR(TO_DATE(fecha_consultoria, 'DD-MM-YYYY'), 'MM') MES,\n" +
                "        TO_CHAR(TO_DATE(fecha_consultoria, 'DD-MM-YYYY'), 'Month') MES_CONSULTORIA,\n" +
                "        ESTADO_CONSULTORIA\n" +
                "    FROM T_SINAPSIS_CONSULTORIAS) \n" +
                "GROUP BY MES_CONSULTORIA, MES\n" +
                "ORDER BY MES ASC";

        Query query = entityManager.createNativeQuery(sql, NroConsultoriasXMes.class);

        return query.getResultList();
    }

    @Override
    public List<NroConsultoriasXMesXPrograma> consultarNroConsultoriasXMesXPrograma() {
        String sql = "SELECT \n" +
                "    COUNT(ID) NRO_CONSULTORIAS, PROOGRAMA_ACADEMICO\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        TC.ID,\n" +
                "        CASE \n" +
                "            WHEN TE.TIPO_CONTACTO NOT IN (1,2) THEN\n" +
                "                'N/A'\n" +
                "            WHEN TP.ID = 0 THEN\n" +
                "                    NVL(TE.OTRO_PROGRAMA_ACADEMICO, 'OTRO PROGRAMA ACADEMICO') \n" +
                "        ELSE \n" +
                "            NVL(TP.NOMBRE, 'NO REGISTRA')\n" +
                "        END PROOGRAMA_ACADEMICO\n" +
                "    FROM T_SINAPSIS_CONSULTORIAS TC JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TC.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "    JOIN T_SINAPSIS_EMPRENDEDORES TE ON TPE.EMPRENDEDORES_ID = TE.ID\n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID\n" +
                "    )\n" +
                "GROUP BY PROOGRAMA_ACADEMICO";

        Query query = entityManager.createNativeQuery(sql, NroConsultoriasXMesXPrograma.class);

        return query.getResultList();
    }

    @Override
    public List<NroConsultoriasXMentorXMes> consultarNroConsultoriasXMentorXMes() {
        String sql = "SELECT \n" +
                "    count(ID_MENTOR) NRO_CONSULTORIAS, NOMBRE_COMPLETO, MES_CONSULTORIA\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        TO_CHAR(TO_DATE(fecha_consultoria, 'DD-MM-YYYY'), 'Month') MES_CONSULTORIA,\n" +
                "        ESTADO_CONSULTORIA,\n" +
                "        TM.ID ID_MENTOR,\n" +
                "        (TU.NOMBRES || ' ' || TU.APELLIDOS) NOMBRE_COMPLETO\n" +
                "    FROM T_SINAPSIS_CONSULTORIAS TC JOIN T_SINAPSIS_MENTORES TM ON TC.MENTORES_ID = TM.ID\n" +
                "    JOIN T_SINAPSIS_USUARIOS TU ON TM.ID = TU.ID\n" +
                "    ) \n" +
                "GROUP BY MES_CONSULTORIA, NOMBRE_COMPLETO";

        Query query = entityManager.createNativeQuery(sql, NroConsultoriasXMentorXMes.class);

        return query.getResultList();
    }

    @Override
    public List<EstadoConsultoriaXMes> consultarEstadoConsultoriaXMes() {
        String sql = "SELECT \n" +
                "    count(ESTADO_CONSULTORIA) NRO_CONSULTORIAS, MES_CONSULTORIA, ESTADO_CONSULTORIA\n" +
                "FROM\n" +
                "    (SELECT \n" +
                "        TO_CHAR(TO_DATE(fecha_consultoria, 'DD-MM-YYYY'), 'Month') MES_CONSULTORIA,\n" +
                "        ESTADO_CONSULTORIA\n" +
                "    FROM T_SINAPSIS_CONSULTORIAS) \n" +
                "GROUP BY MES_CONSULTORIA, ESTADO_CONSULTORIA";

        Query query = entityManager.createNativeQuery(sql, EstadoConsultoriaXMes.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresXEtapa> consultarNroEmprendedoresXEtapa() {
        String sql = "SELECT COUNT(ID_RUTA_EMPRENDIMIENTO) NRO_PROYECTOS_EMPRENDIMIENTO, TSER.NOMBRE ETAPA_RUTA, TSER.ID FROM (\n" +
                "    SELECT \n" +
                "        ID_RUTA_EMPRENDIMIENTO, ETAPAS_ID \n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    JOIN T_SINAPSIS_PROY_EMPRENDIMIENTO TPE ON TPE.EMPRENDEDORES_ID = TE.ID \n" +
                "    JOIN (SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ESTADO_RUTA, TSRE.ID_RUTA_EMPRENDIMIENTO, TSRE.ETAPAS_ID FROM (\n" +
                "        SELECT TSRE.PROYECTOS_EMPRENDIMIENTOS_ID, TSRE.ETAPAS_ID, TSRE.ESTADO_RUTA, TSRE.ID ID_RUTA_EMPRENDIMIENTO,\n" +
                "                ROW_NUMBER() OVER (PARTITION BY PROYECTOS_EMPRENDIMIENTOS_ID ORDER BY ETAPAS_ID DESC) SEQNUM\n" +
                "        FROM T_SINAPSIS_RUT_EMPRENDIMIENTO TSRE\n" +
                "    ) TSRE \n" +
                "    WHERE TSRE.SEQNUM = 1 AND TSRE.ESTADO_RUTA != 'COMPLETADO') VPE ON VPE.PROYECTOS_EMPRENDIMIENTOS_ID = TPE.ID\n" +
                "    WHERE TPE.ESTADO_EMPRENDIMIENTO = 'APROBADO'\n" +
                "    ORDER BY ID_RUTA_EMPRENDIMIENTO DESC\n" +
                ") VPE RIGHT JOIN T_SINAPSIS_ETAPAS_RUTA TSER ON VPE.ETAPAS_ID = TSER.ID\n" +
                "\n" +
                "GROUP BY TSER.NOMBRE, TSER.ID ORDER BY TSER.ID";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresXEtapa.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresXTipoContacto> consultarNroEmprendedoresXTipoContacto() {
        String sql = "SELECT \n" +
                "    COUNT(TE.id) nro_emprendedores, TC.nombre vinculo_con_u     \n" +
                "FROM T_SINAPSIS_EMPRENDEDORES TE RIGHT JOIN T_SINAPSIS_TIPOS_CONTACTO TC ON TE.tipo_contacto = TC.ID\n" +
                "GROUP BY TC.nombre";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresXTipoContacto.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresXPrograma> consultarNroEmprendedoresXPrograma() {
        String sql = "SELECT COUNT(ID) NRO_EMPRENDEDORES, PROOGRAMA_ACADEMICO  FROM (SELECT \n" +
                "        TE.ID, \n" +
                "        CASE WHEN TP.ID = 0 THEN\n" +
                "            NVL(TE.OTRO_PROGRAMA_ACADEMICO, 'OTRO PROGRAMA ACADEMICO')\n" +
                "        ELSE \n" +
                "        NVL(TP.NOMBRE, 'N/A')\n" +
                "        END PROOGRAMA_ACADEMICO\n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID)\n" +
                "GROUP BY PROOGRAMA_ACADEMICO";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresXPrograma.class);

        return query.getResultList();
    }

    @Override
    public List<NroEmprendedoresXFacultad> consultarNroEmprendedoresXFacultad() {
        String sql = "SELECT COUNT(ID) NRO_EMPRENDEDORES, FACULTAD  FROM (SELECT \n" +
                "        TE.ID, \n" +
                "        CASE WHEN TP.ID IS NULL THEN\n" +
                "            'N/A'\n" +
                "        ELSE \n" +
                "            NVL(TF.NOMBRE, 'NO REGISTRA')\n" +
                "        END FACULTAD\n" +
                "    FROM T_SINAPSIS_EMPRENDEDORES TE \n" +
                "    LEFT JOIN T_SINAPSIS_PROGRAMAS TP ON TE.PROGRAMA_ACADEMICO_ID = TP.ID\n" +
                "    LEFT JOIN T_SINAPSIS_FACULTADES TF ON TP.FACULTADES_ID = TF.ID)\n" +
                "GROUP BY FACULTAD";

        Query query = entityManager.createNativeQuery(sql, NroEmprendedoresXFacultad.class);

        return query.getResultList();
    }
}
