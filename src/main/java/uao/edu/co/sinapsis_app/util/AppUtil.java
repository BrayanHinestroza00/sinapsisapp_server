package uao.edu.co.sinapsis_app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.UUID.randomUUID;

public class AppUtil {
    public static String generateUUID(){
        return randomUUID().toString();
    }

    public static String genFileName(String originalFileName){
        String name = generateUUID();
        String[] bits = originalFileName.split("\\.");
        String ext = bits[bits.length-1];
        return name + "." + ext;
    }

    public static String formatStringDate(String inFormat, String newFormat, String date) {
        SimpleDateFormat from = new SimpleDateFormat(inFormat);
        SimpleDateFormat to = new SimpleDateFormat(newFormat);

        try {
            return to.format(from.parse(date));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(String newFormat, Date date) {
        SimpleDateFormat to = new SimpleDateFormat(newFormat);

        try {
            return to.format(date);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convierte una cadena de texto en un Date
     * @param inDate Cadena de texto con fecha a convertir
     * @param inFormat Formato de entrada
     * @param outFormat Formato de salida
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDateFormatter(String inDate, String inFormat, String outFormat) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat(inFormat);
        Date date = dt.parse(inDate);

        SimpleDateFormat dt1 = new SimpleDateFormat(outFormat);
        return dt1.parse(dt1.format(date));
    }

    public static Date dateToStringFormatter(Date inDate, String outFormat) throws ParseException {
        SimpleDateFormat dt1 = new SimpleDateFormat(outFormat);
        return dt1.parse(dt1.format(inDate));
    }
}
