package view;

import java.util.Scanner;

public class LogoutView {

    private final String LINE = "====================================================================";
    private final String TITLE = "                             DAILY JOURNAL";
    private final String SUBTITLE = "                     \"Write your story, every day.\"";
    private final Scanner scanner = new Scanner(System.in);

    // ========================================================================
    // 1) TAMPILAN KONFIRMASI LOGOUT
    // ========================================================================
    public boolean showLogout() {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Menu : Logout");
        System.out.println("----------------------------------------");
        System.out.println("Anda yakin ingin logout dari aplikasi?");
        System.out.println("(semua sesi akan ditutup)");
        System.out.println("----------------------------------------");
        System.out.print("Konfirmasi (Y/T) : ");
        String konfirmasi = scanner.nextLine();
        System.out.println(LINE);

        // Mengembalikan true jika user memilih Y, false jika T
        return konfirmasi.equalsIgnoreCase("Y");
    }

    // ========================================================================
    // 2) TAMPILAN LOGOUT BERHASIL
    // ========================================================================
    public void showLogoutSuccess() {
        System.out.println("----------------------------------------");
        System.out.println("Logout berhasil.");
        System.out.println("Tekan ENTER untuk kembali ke halaman login.");
        scanner.nextLine(); // menunggu user tekan ENTER
        System.out.println(LINE);
    }

    // ========================================================================
    // 3) TAMPILAN LOGOUT DIBATALKAN
    // ========================================================================
    public void showLogoutCancelled() {
        System.out.println("----------------------------------------");
        System.out.println("Logout dibatalkan.");
        System.out.println("Tekan ENTER untuk kembali ke menu utama.");
        scanner.nextLine(); // menunggu user tekan ENTER
        System.out.println(LINE);
    }
}
