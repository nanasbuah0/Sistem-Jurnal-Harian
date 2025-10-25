package view;

import java.util.List;
import model.Jurnal;

public class JurnalView {

    private final String LINE = "====================================================================";
    private final String TITLE = "                             DAILY JOURNAL";
    private final String SUBTITLE = "                     \"Write your story, every day.\"";

    // ========================================================================
    // HEADER & FOOTER
    // ========================================================================
    private void header(String menu) {
        System.out.println(LINE);
        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(LINE);
        System.out.println("Menu : " + menu);
        System.out.println("--------------------------------------------------------------------");
    }

    private void footer() {
        System.out.println(LINE);
    }

    // ========================================================================
    // 1) FORM INPUT JURNAL BARU
    // ========================================================================
    public void showFormTambahJurnal() {
        header("Tulis Jurnal Baru");
        System.out.print("Judul       : ");
        System.out.print("\nTanggal     : ");
        System.out.print("\nMood        : ");
        System.out.println("\nIsi Jurnal  : ");
        System.out.println("(ketik isi di sini, tekan ENTER untuk baris baru)");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Simpan jurnal? (Y/T) : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 2) BERHASIL DISIMPAN
    // ========================================================================
    public void showTambahBerhasil() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Jurnal berhasil disimpan.");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Tekan ENTER untuk kembali ke menu.");
        footer();
    }

    // ========================================================================
    // 3) LIHAT DAFTAR JURNAL (ADA DATA)
    // ========================================================================
    public void showDaftarJurnal(List<Jurnal> daftar) {
        header("Lihat Daftar Jurnal");
        for (int i = 0; i < daftar.size(); i++) {
            Jurnal j = daftar.get(i);
            System.out.printf("[%d]  %-12s |  %-8s |  %s\n",
                    (i + 1), j.getTanggal(), j.getMood(), j.getJudul());
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Pilih nomor jurnal untuk melihat detail");
        System.out.println("atau ketik 0 untuk kembali.");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 4) LIHAT DAFTAR KOSONG
    // ========================================================================
    public void showDaftarKosong() {
        header("Lihat Daftar Jurnal");
        System.out.println("Tidak ada jurnal yang tersimpan.");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Tekan ENTER untuk kembali.");
        footer();
    }

    // ========================================================================
    // 5) MENU KELOLA JURNAL
    // ========================================================================
    public void showMenuKelolaJurnal() {
        header("Kelola Data Jurnal");
        System.out.println("[1] Lihat Detail Jurnal");
        System.out.println("[2] Edit Jurnal");
        System.out.println("[3] Hapus Jurnal");
        System.out.println("[0] Kembali");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Pilih menu : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 6) DETAIL JURNAL
    // ========================================================================
    public void showDetailJurnal(Jurnal j) {
        header("Detail Jurnal");
        System.out.println("Judul     : " + j.getJudul());
        System.out.println("Tanggal   : " + j.getTanggal());
        System.out.println("Mood      : " + j.getMood());
        System.out.println("Isi       : ");
        System.out.println(j.getIsi());
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Tekan ENTER untuk kembali.");
        footer();
    }

    // ========================================================================
    // 7) KONFIRMASI HAPUS
    // ========================================================================
    public void showHapusKonfirmasi(Jurnal j) {
        header("Hapus Jurnal");
        System.out.println("Anda yakin ingin menghapus jurnal ini?");
        System.out.println("Judul   : " + j.getJudul());
        System.out.println("Tanggal : " + j.getTanggal());
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Konfirmasi hapus (Y/T) : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 8) HAPUS BERHASIL
    // ========================================================================
    public void showHapusBerhasil() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Jurnal berhasil dihapus.");
        System.out.println("Tekan ENTER untuk kembali.");
        footer();
    }

    // ========================================================================
    // 9) HAPUS BATAL
    // ========================================================================
    public void showHapusBatal() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Penghapusan dibatalkan.");
        System.out.println("Tekan ENTER untuk kembali.");
        footer();
    }

    // ========================================================================
    // 10) CARI BERHASIL
    // ========================================================================
    public void showCariBerhasil(List<Jurnal> hasil) {
        header("Cari Jurnal");
        System.out.print("Masukkan kata kunci (judul / mood / isi):\nInput : ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Hasil Pencarian :");
        for (int i = 0; i < hasil.size(); i++) {
            Jurnal j = hasil.get(i);
            System.out.printf("[%d]  %-12s |  %-8s |  %s\n",
                    (i + 1), j.getTanggal(), j.getMood(), j.getJudul());
        }
        System.out.println("----------------------------------------");
        System.out.println("Pilih nomor jurnal untuk melihat detail");
        System.out.println("atau ketik 0 untuk kembali.");
        System.out.print("Input : ");
        footer();
    }

    // ========================================================================
    // 11) CARI GAGAL
    // ========================================================================
    public void showCariGagal() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Tidak ada jurnal yang sesuai dengan kata kunci.");
        System.out.println("Tekan ENTER untuk kembali.");
        footer();
    }

    // ========================================================================
    // 12) MENU FILTER
    // ========================================================================
    public void showMenuFilterJurnal() {
        header("Filter Jurnal");
        System.out.println("Pilih jenis filter:");
        System.out.println("[1] Berdasarkan Tanggal");
        System.out.println("[2] Berdasarkan Mood");
        System.out.println("[3] Kembali");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 13) FILTER TANGGAL
    // ========================================================================
    public void showFilterTanggal() {
        header("Filter Jurnal (Tanggal)");
        System.out.println("Masukkan rentang tanggal");
        System.out.print("Dari (dd/mm/yyyy) : ");
        System.out.print("\nSampai (dd/mm/yyyy) : ");
        System.out.println("\n--------------------------------------------------------------------");
        System.out.print("Tampilkan hasil? (Y/T) : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 14) HASIL FILTER TANGGAL
    // ========================================================================
    public void showFilterTanggalBerhasil(List<Jurnal> daftar) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Hasil Filter (Tanggal)");
        for (int i = 0; i < daftar.size(); i++) {
            Jurnal j = daftar.get(i);
            System.out.printf("[%d]  %-12s |  %-8s |  %s\n",
                    (i + 1), j.getTanggal(), j.getMood(), j.getJudul());
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Pilih nomor jurnal untuk melihat detail");
        System.out.println("atau ketik 0 untuk kembali.");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 15) FILTER MOOD
    // ========================================================================
    public void showFilterMood() {
        header("Filter Jurnal (Mood)");
        System.out.print("Masukkan mood yang dicari : ");
        System.out.println("\n--------------------------------------------------------------------");
        System.out.print("Tampilkan hasil? (Y/T) : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 16) SORT MENU UTAMA
    // ========================================================================
    public void showMenuSortJurnal() {
        header("Sort Jurnal");
        System.out.println("Pilih metode pengurutan:");
        System.out.println("[1] Berdasarkan Tanggal");
        System.out.println("[2] Berdasarkan Mood");
        System.out.println("[3] Berdasarkan Judul");
        System.out.println("[4] Kembali");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 17) SORT TANGGAL
    // ========================================================================
    public void showSortTanggal() {
        header("Sort Jurnal (Tanggal)");
        System.out.println("Pilih urutan:");
        System.out.println("[1] Terbaru ke Terlama");
        System.out.println("[2] Terlama ke Terbaru");
        System.out.println("[3] Kembali");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 18) HASIL SORT TANGGAL
    // ========================================================================
    public void showSortTanggalBerhasil(List<Jurnal> daftar) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Hasil Sort (Tanggal)");
        for (int i = 0; i < daftar.size(); i++) {
            Jurnal j = daftar.get(i);
            System.out.printf("[%d]  %-12s | %-8s | %s\n",
                    (i + 1), j.getTanggal(), j.getMood(), j.getJudul());
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Pilih nomor jurnal untuk melihat detail");
        System.out.println("atau ketik 0 untuk kembali.");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 19) SORT MOOD
    // ========================================================================
    public void showSortMood() {
        header("Sort Jurnal (Mood)");
        System.out.println("Pilih urutan:");
        System.out.println("[1] A - Z");
        System.out.println("[2] Z - A");
        System.out.println("[3] Kembali");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }

    // ========================================================================
    // 20) SORT JUDUL
    // ========================================================================
    public void showSortJudul() {
        header("Sort Jurnal (Judul)");
        System.out.println("Pilih urutan:");
        System.out.println("[1] A - Z");
        System.out.println("[2] Z - A");
        System.out.println("[3] Kembali");
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Input : ");
        System.out.println();
        footer();
    }
}
