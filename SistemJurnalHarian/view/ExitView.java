package view;

public class ExitView {

    private final String LINE = "====================================================================";
    private final String TITLE = "                             DAILY JOURNAL";
    private final String SUBTITLE = "                     \"Write your story, every day.\"";

    // ========================================================================
    // TAMPILAN EXIT APLIKASI
    // ========================================================================
    public void showExit() {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Terima kasih telah menggunakan Daily Journal.");
        System.out.println("Semoga harimu menyenangkan.");
        System.out.println("Sampai jumpa kembali.");
        System.out.println(LINE);
    }
}
