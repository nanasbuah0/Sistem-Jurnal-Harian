package model;

/**
 * Representasi data pengguna (user) untuk sistem Daily Journal.
 * Disimpan di file users.txt dalam format: username|password|nama|email
 */
public class User {
    private String username;
    private String password;
    private String nama;
    private String email;

    public User(String username, String password, String nama, String email) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.email = email;
    }

    // ================= GETTER =================
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }

    // ================= SETTER =================
    public void setPassword(String password) { this.password = password; }
    public void setNama(String nama) { this.nama = nama; }
    public void setEmail(String email) { this.email = email; }

    // ================= FILE I/O =================
    @Override
    public String toString() {
        return username + "|" + password + "|" + nama + "|" + email;
    }

    public static User fromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            return new User(parts[0], parts[1], parts[2], parts[3]);
        }
        return null;
    }
}