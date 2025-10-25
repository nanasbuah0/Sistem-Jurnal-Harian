package model;

public class MoodNetral extends Mood {
    public MoodNetral() {
        super("Netral");
    }

    @Override
    public String getDeskripsi() {
        return "Hari berjalan seperti biasa, tenang tanpa gejolak.";
    }
}