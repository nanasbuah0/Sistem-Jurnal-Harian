package view;

public class LoginView {

    private final String LINE = "====================================================================";
    private final String TITLE = "                             DAILY JOURNAL";
    private final String SUBTITLE = "                     \"Write your story, every day.\"";

    public void showLoginSuccess(String username) {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Username : " + username);
        System.out.println("Password : ****");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Login berhasil.");
        System.out.println("Selamat datang kembali. Selamat menulis jurnal hari ini.");
        System.out.println(LINE);
    }

    public void showLoginFailed(String username) {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Username : " + username);
        System.out.println("Password : ****");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Login gagal.");
        System.out.println("Username atau password salah.");
        System.out.println("Silakan coba lagi.");
        System.out.println(LINE);
    }
}
