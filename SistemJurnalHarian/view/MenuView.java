package view;

public class MenuView {

    private final String LINE = "====================================================================";
    private final String TITLE = "                             DAILY JOURNAL";
    private final String SUBTITLE = "                     \"Write your story, every day.\"";

    public void showMainMenu(String username) {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Menu Utama - Pengguna : " + username);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("1. Tulis Jurnal Baru");
        System.out.println("2. Lihat Daftar Jurnal");
        System.out.println("3. Kelola Data Jurnal");
        System.out.println("4. Cari Jurnal");
        System.out.println("5. Filter Jurnal");
        System.out.println("6. Sort Jurnal");
        System.out.println("7. Kelola Profil");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Pilih menu (1-9) : ");
    }
}
