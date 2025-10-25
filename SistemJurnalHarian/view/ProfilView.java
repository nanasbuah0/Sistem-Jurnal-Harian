package view;

import java.util.Scanner;

public class ProfilView {

    private final String LINE = "====================================================================";
    private final String TITLE = "                             DAILY JOURNAL";
    private final String SUBTITLE = "                     \"Write your story, every day.\"";
    private final Scanner scanner = new Scanner(System.in);

    // ========================================================================
    // HEADER & FOOTER
    // ========================================================================
    private void header(String menu) {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Menu : " + menu);
        System.out.println("----------------------------------------");
    }

    private void footer() {
        System.out.println(LINE);
    }

    // ========================================================================
    // 1) MENU KELOLA PROFIL
    // ========================================================================
    public void showMenuKelolaProfil() {
        header("Kelola Profil");
        System.out.println("1. Lihat Profil");
        System.out.println("2. Edit Profil");
        System.out.println("3. Kembali");
        System.out.println("----------------------------------------");
        System.out.print("Pilih menu : ");
        footer();
    }

    // ========================================================================
    // 2) TAMPILAN LIHAT PROFIL
    // ========================================================================
    public void lihatProfil(String username, String nama, String email) {
        header("Lihat Profil");
        System.out.println("Username   : " + username);
        System.out.println("Nama       : " + nama);
        System.out.println("Email      : " + email);
        System.out.println("----------------------------------------");
        System.out.println("Tekan ENTER untuk kembali.");
        scanner.nextLine();
        footer();
    }

    // ========================================================================
    // 3) TAMPILAN EDIT PROFIL
    // ========================================================================
    public String[] editProfil() {
        header("Edit Profil");
        System.out.print("Nama baru    : ");
        String namaBaru = scanner.nextLine();

        System.out.print("Email baru   : ");
        String emailBaru = scanner.nextLine();

        System.out.print("Ubah password (Y/T) : ");
        String ubahPassword = scanner.nextLine();

        String[] passwordData = null;
        if (ubahPassword.equalsIgnoreCase("Y")) {
            passwordData = ubahPasswordForm();
        }

        System.out.print("----------------------------------------\n");
        System.out.print("Simpan perubahan? (Y/T) : ");
        String simpan = scanner.nextLine();

        footer();
        return new String[]{namaBaru, emailBaru, ubahPassword, simpan};
    }

    // ========================================================================
    // 4) FORM UBAH PASSWORD
    // ========================================================================
    private String[] ubahPasswordForm() {
        header("Ubah Password");
        System.out.print("Password lama     : ");
        String passwordLama = scanner.nextLine();

        System.out.print("Password baru     : ");
        String passwordBaru = scanner.nextLine();

        System.out.print("Konfirmasi ulang  : ");
        String konfirmasi = scanner.nextLine();

        System.out.print("----------------------------------------\n");
        System.out.print("Simpan perubahan? (Y/T) : ");
        String simpan = scanner.nextLine();

        footer();
        return new String[]{passwordLama, passwordBaru, konfirmasi, simpan};
    }

    // ========================================================================
    // 5) NOTIFIKASI PROFIL BERHASIL DIPERBARUI
    // ========================================================================
    public void showUpdateSuccess() {
        System.out.println("----------------------------------------");
        System.out.println("Profil berhasil diperbarui.");
        System.out.println("Tekan ENTER untuk kembali.");
        scanner.nextLine();
        footer();
    }
}
