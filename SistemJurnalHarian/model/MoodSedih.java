package model;

public class MoodSedih extends Mood {
    public MoodSedih() {
        super("Sedih");
    }

    @Override
    public String getDeskripsi() {
        return "Hari yang terasa berat dan penuh perasaan mendalam.";
    }
}