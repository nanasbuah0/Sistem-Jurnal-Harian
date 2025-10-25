package model;

/**
 * Representasi data jurnal harian.
 * Disimpan di file journals.txt dalam format:
 * username|judul|tanggal|mood|isi
 */
public class Jurnal {
    private String username;
    private String judul;
    private String tanggal;
    private String mood;
    private String isi;

    public Jurnal(String username, String judul, String tanggal, String mood, String isi) {
        this.username = username;
        this.judul = judul;
        this.tanggal = tanggal;
        this.mood = mood;
        this.isi = isi;
    }

    // ================= GETTER =================
    public String getUsername() { return username; }
    public String getJudul() { return judul; }
    public String getTanggal() { return tanggal; }
    public String getMood() { return mood; }
    public String getIsi() { return isi; }

    // ================= FILE I/O =================
    @Override
    public String toString() {
        // Ganti newline biar gak rusak format di file
        return username + "|" + judul + "|" + tanggal + "|" + mood + "|" + isi.replace("\n", "\\n");
    }

    public static Jurnal fromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 5) {
            String isi = parts[4].replace("\\n", "\n");
            return new Jurnal(parts[0], parts[1], parts[2], parts[3], isi);
        }
        return null;
    }
}