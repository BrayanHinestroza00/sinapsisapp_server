package uao.edu.co.sinapsis_app.dto;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Base64Utils;
import uao.edu.co.sinapsis_app.model.view.ConsultoriasView;
import uao.edu.co.sinapsis_app.util.AppUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReporteConsultoriasMentorExportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ConsultoriasView> list;

    /**
     * Construye un ReporteSolicitudExportExcel haciendo uso de una Lista de Objetos
     * @param list
     */
    public ReporteConsultoriasMentorExportExcel(List<ConsultoriasView> list) {
        try {
            this.list = list;
            workbook = new XSSFWorkbook(
                    new ClassPathResource("templates/ReporteConsultoriasMentor.xlsx")
                            .getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que inserta los datos en el documento de Excel
     */
    private void writeDataLines() {
        sheet = workbook.getSheetAt(0);

        int filaSeleccionada = 2;
        for (ConsultoriasView consultoria: list ) {
            Row fila = sheet.getRow(filaSeleccionada);

            if (fila == null) {
                int rowNumb = sheet.getLastRowNum();
                sheet.shiftRows(rowNumb, rowNumb, 1, true, true);
                fila = sheet.createRow(rowNumb);
            }

            createCell(fila, 0, consultoria.getTipoConsultoria());
            createCell(fila, 1, consultoria.getTipoConsultoria().equalsIgnoreCase("N") ? "N/A" : consultoria.getNombreSubActRuta());
            createCell(fila, 2, consultoria.getTipoDocumentoEmprendedor());
            createCell(fila, 3, consultoria.getNumeroDocumentoEmprendedor());
            createCell(fila, 4, consultoria.getNombreEmprendedor() + " " +  consultoria.getApellidoEmprendedor());
            createCell(fila, 5, consultoria.getCorreoInstitucionalEmprendedor() != null ? consultoria.getCorreoInstitucionalEmprendedor() : consultoria.getCorreoPersonalEmprendedor());
            createCell(fila, 6, AppUtil.formatStringDate("yyyy-MM-dd HH:mm", "dd/MM/yyyy", consultoria.getFechaConsultoria()));
            createCell(fila, 7, consultoria.getHoraInicioConsultoria());
            createCell(fila, 8, consultoria.getHoraFinConsultoria());
            createCell(fila, 9, consultoria.getFechaInicioReal() != null ? AppUtil.formatStringDate("yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", consultoria.getFechaInicioReal()) : "");
            createCell(fila, 10, consultoria.getFechaFinalizacionReal() != null ? AppUtil.formatStringDate("yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", consultoria.getFechaFinalizacionReal()) : "");
            createCell(fila, 11, consultoria.getEstadoConsultoria());
            createCell(fila, 12, consultoria.getComentariosConsultoria() != null ? consultoria.getComentariosConsultoria() : "");
            createCell(fila, 13, consultoria.getNombreMentor() + " " +  consultoria.getApellidoMentor());
            createCell(fila, 14, consultoria.getCorreoInstitucionalMentor() != null ? consultoria.getCorreoInstitucionalMentor() : "No Registra");
            createCell(fila, 15, consultoria.getFacultadMentor() != null ? consultoria.getFacultadMentor() : consultoria.getDependenciaMentor());
            createCell(fila, 16, consultoria.getCargoMentor());

            filaSeleccionada++;
        }
    }

    /**
     * Método que inserta los datos en una celda de Excel
     *
     * @param row fila actual
     * @param columnCount número de columna
     * @param value valor a insertar
     */
    private void createCell(Row row, int columnCount, Object value) {
        Cell cell = row.createCell(columnCount);
        if (columnCount == 2) {
            int valor = Integer.parseInt(String.valueOf(value));

            if (valor == 1) {
                cell.setCellValue("CÉDULA DE CIUDADANÍA");
            } else if (valor == 2) {
                cell.setCellValue("TARJETA DE IDENTIDAD");
            }else if (valor == 3) {
                cell.setCellValue("CÉDULA DE EXTRANJERÍA");
            } else {
                cell.setCellValue("N/A");
            }

        } else if (columnCount == 0) {
            if (String.valueOf(value).equalsIgnoreCase("N")) {
                cell.setCellValue("NORMAL");
            } else {
                cell.setCellValue("ESPECIALIZADA");
            }
        } else if (value instanceof BigDecimal){
            cell.setCellValue(Integer.parseInt(String.valueOf(value)));
        } else if (value instanceof Boolean) {
            cell.setCellValue(Boolean.parseBoolean(String.valueOf(value)));
        } else if (value instanceof Date) {
//            if (columnCount == 5) {
//                Date fecha = (Date) value;
//                Cell celda = row.createCell(27);
//                if (fecha.compareTo(new Date()) > 0) {
//                    celda.setCellValue("SÍ");
//                }else {
//                    celda.setCellValue("NO");
//                }
//            }

            cell.setCellValue(
                    new SimpleDateFormat("dd/MM/yyyy hh:mm")
                            .format((Date) value));
        }else {
            cell.setCellValue(String.valueOf(value).toUpperCase());
        }
    }

    /**
     * Genera archivo en Excel
     * @param response
     * @throws IOException
     */
    public void export(HttpServletResponse response) throws IOException {
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    public String generateExcel() throws IOException {
        writeDataLines();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        return new String(Base64Utils.encode(bos.toByteArray()));
    }
}
