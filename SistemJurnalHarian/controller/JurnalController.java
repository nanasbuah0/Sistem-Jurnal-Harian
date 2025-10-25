package controller;

import java.util.ArrayList;
import java.util.Date;
import model.Jurnal;
import util.FormatUtil;
import util.InputUtil;
import view.JurnalView;

public class JurnalController {

    private final ArrayList<Jurnal> daftarJurnal = new ArrayList<>();
    private final JurnalView jurnalView = new JurnalView();

    // tambah jurnal baru
    public void tambahJurnal() {
        jurnalView.showFormTambahBaru();
        String judul = InputUtil.inputString("Judul");
        String isi = InputUtil.inputString("Isi jurnal");
        Date tanggal = new Date(); // otomatis tanggal hari ini

        Jurnal jurnal = new Jurnal(judul, isi, tanggal);
        daftarJurnal.add(jurnal);

        jurnalView.showTambahBerhasil();
    }

    // tampilkan semua jurnal
    public void lihatSemuaJurnal() {
        jurnalView.showDaftarJurnal(daftarJurnal);
        InputUtil.tekanEnterUntukLanjut();
    }

    // hapus jurnal berdasarkan indeks
    public void hapusJurnal() {
        lihatSemuaJurnal();
        int index = InputUtil.inputInt("Pilih nomor jurnal yang ingin dihapus") - 1;
        if (index >= 0 && index < daftarJurnal.size()) {
            daftarJurnal.remove(index);
            jurnalView.showHapusBerhasil();
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }

    public ArrayList<Jurnal> getDaftarJurnal() {
        return daftarJurnal;
    }
}