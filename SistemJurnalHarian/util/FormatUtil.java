package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // =============================================================
    // 1) Format tanggal (Date → String)
    // =============================================================
    public static String formatDate(Date tanggal) {
        if (tanggal == null) return "-";
        return sdf.format(tanggal);
    }

    // =============================================================
    // 2) Parse tanggal (String → Date)
    // =============================================================
    public static Date parseDate(String tanggal) {
        try {
            return sdf.parse(tanggal);
        } catch (ParseException e) {
            System.out.println("Format tanggal salah! Gunakan format dd/MM/yyyy");
            return null;
        }
    }

    // =============================================================
    // 3) Cek apakah teks kosong
    // =============================================================
    public static boolean isEmpty(String teks) {
        return teks == null || teks.trim().isEmpty();
    }

    // =============================================================
    // 4) Cetak garis pemisah (biar tampilan konsisten)
    // =============================================================
    public static void printLine() {
        System.out.println("--------------------------------------------------------------------");
    }

    // =============================================================
    // 5) Bandingkan apakah suatu tanggal ada di antara dua tanggal
    // =============================================================
    public static boolean isBetween(Date target, Date start, Date end) {
        if (target == null || start == null || end == null) return false;
        return !target.before(start) && !target.after(end);
    }
}