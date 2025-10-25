package util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    // ========================================================================
    // 1) INPUT STRING (teks biasa)
    // ========================================================================
    public static String inputString(String label) {
        System.out.print(label + " : ");
        return scanner.nextLine().trim();
    }

    // ========================================================================
    // 2) INPUT ANGKA (integer)
    // ========================================================================
    public static int inputInt(String label) {
        while (true) {
            try {
                System.out.print(label + " : ");
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka yang benar.");
            }
        }
    }

    // ========================================================================
    // 3) INPUT KONFIRMASI (Y/T)
    // ========================================================================
    public static boolean inputKonfirmasi(String label) {
        while (true) {
            System.out.print(label + " (Y/T) : ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) return true;
            if (input.equalsIgnoreCase("T")) return false;
            System.out.println("Input tidak valid. Masukkan Y atau T saja.");
        }
    }

    // ========================================================================
    // 4) INPUT ENTER UNTUK LANJUT
    // ========================================================================
    public static void tekanEnterUntukLanjut() {
        System.out.println("Tekan ENTER untuk melanjutkan...");
        scanner.nextLine();
    }

    // ========================================================================
    // 5) INPUT PASSWORD (tanpa tampil di layar)
    // ========================================================================
    // Catatan: Java console bisa nonaktif di IDE, jadi fallback ke input biasa
    public static String inputPassword(String label) {
        try {
            System.out.print(label + " : ");
            return new String(System.console().readPassword());
        } catch (Exception e) {
            // fallback kalau console ga tersedia
            System.out.print(label + " : ");
            return scanner.nextLine().trim();
        }
    }
}
