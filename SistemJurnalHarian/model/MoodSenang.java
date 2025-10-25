package model;

public class MoodSenang extends Mood {
    public MoodSenang() {
        super("Senang");
    }

    @Override
    public String getDeskripsi() {
        return "Hari yang cerah dan penuh kebahagiaan.";
    }
}