package uao.edu.co.sinapsis_app.util;

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
}
