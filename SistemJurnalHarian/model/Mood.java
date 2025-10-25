package model;

/**
 * Kelas abstrak untuk mendefinisikan suasana hati (mood) pada jurnal.
 */
public abstract class Mood {
    protected String nama;

    public Mood(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public abstract String getDeskripsi();
}