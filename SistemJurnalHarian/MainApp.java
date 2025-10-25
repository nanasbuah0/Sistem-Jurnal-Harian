package main;

import model.User;
import model.Jurnal;
import view.LoginView;
import view.MenuView;
import view.JurnalView;
import view.ProfilView;
import view.LogoutView;
import view.ExitView;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    private static final String USERS_FILE = "users.txt";
    private static final Scanner SCANNER = new Scanner(System.in);

    // In-memory
    private static List<User> users = new ArrayList<>();
    private static User currentUser;
    private static List<Jurnal> currentJurnals = new ArrayList<>();

    // Views
    private static final LoginView loginView = new LoginView();
    private static final MenuView menuView = new MenuView();
    private static final JurnalView jurnalView = new JurnalView();
    private static final ProfilView profilView = new ProfilView();
    private static final LogoutView logoutView = new LogoutView();
    private static final ExitView exitView = new ExitView();

    public static void main(String[] args) {
        loadUsers();

        while (true) {
            // LOGIN LOOP
            currentUser = doLogin();
            if (currentUser == null) {
                System.out.println("Keluar aplikasi karena login gagal/ditinggalkan.");
                break;
            }

            // load jurnal user
            currentJurnals = loadJurnalsForUser(currentUser.getUsername());

            // MENU LOOP
            boolean sessionRunning = true;
            while (sessionRunning) {
                menuView.showMainMenu(currentUser);
                int pilihan = readIntSafe("Pilih menu (1-9) : ");

                switch (pilihan) {
                    case 1: // Tulis Jurnal Baru
                        handleTambahJurnal();
                        break;
                    case 2: // Lihat Daftar Jurnal
                        handleLihatDaftar();
                        break;
                    case 3: // Kelola Data Jurnal
                        handleKelolaJurnal();
                        break;
                    case 4: // Cari Jurnal
                        handleCariJurnal();
                        break;
                    case 5: // Filter Jurnal
                        handleFilterJurnal();
                        break;
                    case 6: // Sort Jurnal
                        handleSortJurnal();
                        break;
                    case 7: // Kelola Profil
                        handleProfil();
                        break;
                    case 8: // Logout
                        if (logoutView.showLogout()) {
                            logoutView.showLogoutSuccess();
                            sessionRunning = false; // kembali ke login
                        } else {
                            logoutView.showLogoutCancelled();
                        }
                        break;
                    case 9: // Exit
                        exitView.showExit();
                        saveJurnalsForUser(currentUser.getUsername(), currentJurnals);
                        saveUsers();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi!");
                }

                if (sessionRunning) {
                    System.out.println("\nTekan ENTER untuk kembali ke menu utama...");
                    SCANNER.nextLine();
                }
            }

            // saat logout, save jurnal dan loop ke login lagi
            saveJurnalsForUser(currentUser.getUsername(), currentJurnals);
            saveUsers();
            currentUser = null;
            currentJurnals = new ArrayList<>();
        }

        SCANNER.close();
    }

    // ========================= LOGIN / USER =========================
    private static void loadUsers() {
        users.clear();
        File f = new File(USERS_FILE);
        if (!f.exists()) {
            // buat default admin kalau file tidak ada
            users.add(new User("admin", "123", "Administrator", "admin@example.com"));
            saveUsers();
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                User u = User.fromString(line);
                if (u != null) users.add(u);
            }
            // kalau file kosong, tetap tambahkan admin default
            if (users.isEmpty()) {
                users.add(new User("admin", "123", "Administrator", "admin@example.com"));
                saveUsers();
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file users.txt: " + e.getMessage());
        }
    }

    private static void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User u : users) {
                pw.println(u.toString());
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan users.txt: " + e.getMessage());
        }
    }

    private static User doLogin() {
        while (true) {
            System.out.println(); // agar rapi
            System.out.print("Masukkan Username (atau ketik exit untuk keluar): ");
            String username = SCANNER.nextLine();
            if (username.equalsIgnoreCase("exit")) return null;

            System.out.print("Masukkan Password: ");
            String password = SCANNER.nextLine();

            Optional<User> found = users.stream()
                    .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                    .findFirst();

            if (found.isPresent()) {
                loginView.showLoginSuccess(username);
                return found.get();
            } else {
                loginView.showLoginFailed(username);
                System.out.print("Coba lagi? (Y/T) : ");
                String c = SCANNER.nextLine();
                if (!c.equalsIgnoreCase("Y")) return null;
            }
        }
    }

    // ========================= JURNAL (file per user) =========================
    private static String jurnalFileName(String username) {
        return "jurnal_" + username + ".txt";
    }

    private static List<Jurnal> loadJurnalsForUser(String username) {
        List<Jurnal> list = new ArrayList<>();
        File f = new File(jurnalFileName(username));
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Jurnal j = Jurnal.fromString(line);
                if (j != null) list.add(j);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca jurnal: " + e.getMessage());
        }
        return list;
    }

    private static void saveJurnalsForUser(String username, List<Jurnal> daftar) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(jurnalFileName(username)))) {
            for (Jurnal j : daftar) {
                pw.println(j.toString());
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan jurnal: " + e.getMessage());
        }
    }

    // ========================= MENU HANDLERS =========================
    private static void handleTambahJurnal() {
        jurnalView.showFormTambahJurnal();
        System.out.print("Judul: ");
        String judul = SCANNER.nextLine();
        System.out.print("Tanggal (dd/mm/yyyy): ");
        String tanggal = SCANNER.nextLine();
        System.out.print("Mood: ");
        String mood = SCANNER.nextLine();
        System.out.println("Isi Jurnal (ketik satu baris, ENTER untuk selesai): ");
        String isi = SCANNER.nextLine();

        System.out.print("Simpan jurnal? (Y/T) : ");
        String konfirm = SCANNER.nextLine();
        if (konfirm.equalsIgnoreCase("Y")) {
            Jurnal j = new Jurnal(judul, tanggal, mood, isi);
            currentJurnals.add(j);
            jurnalView.showTambahBerhasil();
            saveJurnalsForUser(currentUser.getUsername(), currentJurnals);
        } else {
            System.out.println("Simpan dibatalkan.");
        }
    }

    private static void handleLihatDaftar() {
        if (currentJurnals.isEmpty()) {
            jurnalView.showDaftarKosong();
            SCANNER.nextLine();
            return;
        }
        jurnalView.showDaftarJurnal(currentJurnals);
        String in = SCANNER.nextLine();
        int pilihan;
        try {
            pilihan = Integer.parseInt(in);
        } catch (NumberFormatException e) {
            pilihan = -1;
        }
        if (pilihan > 0 && pilihan <= currentJurnals.size()) {
            jurnalView.showDetailJurnal(currentJurnals.get(pilihan - 1));
            SCANNER.nextLine();
        }
    }

    private static void handleKelolaJurnal() {
        if (currentJurnals.isEmpty()) {
            jurnalView.showDaftarKosong();
            SCANNER.nextLine();
            return;
        }
        boolean back = false;
        while (!back) {
            jurnalView.showMenuKelolaJurnal();
            int pilih = readIntSafe("Pilih menu : ");
            switch (pilih) {
                case 1: // lihat detail (pilih nomor)
                    jurnalView.showDaftarJurnal(currentJurnals);
                    int nomor = readIntSafe("Input : ");
                    if (nomor > 0 && nomor <= currentJurnals.size()) {
                        jurnalView.showDetailJurnal(currentJurnals.get(nomor - 1));
                        SCANNER.nextLine();
                    }
                    break;
                case 2: // edit jurnal
                    jurnalView.showDaftarJurnal(currentJurnals);
                    int idxEdit = readIntSafe("Pilih nomor untuk edit (0 kembali): ");
                    if (idxEdit > 0 && idxEdit <= currentJurnals.size()) {
                        Jurnal j = currentJurnals.get(idxEdit - 1);
                        jurnalView.showDetailJurnal(j);
                        System.out.print("Judul baru (kosong = tidak diubah): ");
                        String newJudul = SCANNER.nextLine();
                        System.out.print("Tanggal baru (kosong = tidak diubah): ");
                        String newTanggal = SCANNER.nextLine();
                        System.out.print("Mood baru (kosong = tidak diubah): ");
                        String newMood = SCANNER.nextLine();
                        System.out.println("Isi baru (kosong = tidak diubah): ");
                        String newIsi = SCANNER.nextLine();

                        if (!newJudul.isBlank()) j.setJudul(newJudul);
                        if (!newTanggal.isBlank()) j.setTanggal(newTanggal);
                        if (!newMood.isBlank()) j.setMood(newMood);
                        if (!newIsi.isBlank()) j.setIsi(newIsi);

                        saveJurnalsForUser(currentUser.getUsername(), currentJurnals);
                        System.out.println("Update berhasil.");
                    }
                    break;
                case 3: // hapus
                    jurnalView.showDaftarJurnal(currentJurnals);
                    int idxHapus = readIntSafe("Pilih nomor untuk hapus (0 kembali): ");
                    if (idxHapus > 0 && idxHapus <= currentJurnals.size()) {
                        Jurnal j = currentJurnals.get(idxHapus - 1);
                        jurnalView.showHapusKonfirmasi(j);
                        String konf = SCANNER.nextLine();
                        if (konf.equalsIgnoreCase("Y")) {
                            currentJurnals.remove(idxHapus - 1);
                            jurnalView.showHapusBerhasil();
                            saveJurnalsForUser(currentUser.getUsername(), currentJurnals);
                        } else {
                            jurnalView.showHapusBatal();
                        }
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            if (!back) {
                System.out.println("\nTekan ENTER untuk melanjutkan...");
                SCANNER.nextLine();
            }
        }
    }

    private static void handleCariJurnal() {
        System.out.print("Masukkan kata kunci (judul/mood/isi): ");
        String kata = SCANNER.nextLine().toLowerCase();
        List<Jurnal> hasil = currentJurnals.stream()
                .filter(j -> j.getJudul().toLowerCase().contains(kata)
                        || j.getMood().toLowerCase().contains(kata)
                        || j.getIsi().toLowerCase().contains(kata))
                .collect(Collectors.toList());
        if (hasil.isEmpty()) {
            jurnalView.showCariGagal();
            SCANNER.nextLine();
        } else {
            jurnalView.showCariBerhasil(hasil);
            int nomor = readIntSafe("Input : ");
            if (nomor > 0 && nomor <= hasil.size()) {
                jurnalView.showDetailJurnal(hasil.get(nomor - 1));
                SCANNER.nextLine();
            }
        }
    }

    private static void handleFilterJurnal() {
        jurnalView.showMenuFilterJurnal();
        int pilih = readIntSafe("Input : ");
        if (pilih == 1) {
            jurnalView.showFilterTanggal();
            System.out.print("Dari (dd/mm/yyyy) : ");
            String dari = SCANNER.nextLine();
            System.out.print("Sampai (dd/mm/yyyy) : ");
            String sampai = SCANNER.nextLine();
            // sederhana: filter string comparison (lexicographic); for robust use LocalDate
            List<Jurnal> hasil = currentJurnals.stream()
                    .filter(j -> j.getTanggal().compareTo(dari) >= 0 && j.getTanggal().compareTo(sampai) <= 0)
                    .collect(Collectors.toList());
            if (hasil.isEmpty()) {
                jurnalView.showDaftarKosong();
                SCANNER.nextLine();
            } else {
                jurnalView.showFilterTanggalBerhasil(hasil);
                int nomor = readIntSafe("Input : ");
                if (nomor > 0 && nomor <= hasil.size()) {
                    jurnalView.showDetailJurnal(hasil.get(nomor - 1));
                    SCANNER.nextLine();
                }
            }
        } else if (pilih == 2) {
            jurnalView.showFilterMood();
            System.out.print("Masukkan mood: ");
            String mood = SCANNER.nextLine().toLowerCase();
            List<Jurnal> hasil = currentJurnals.stream()
                    .filter(j -> j.getMood().toLowerCase().contains(mood))
                    .collect(Collectors.toList());
            if (hasil.isEmpty()) {
                jurnalView.showDaftarKosong();
                SCANNER.nextLine();
            } else {
                jurnalView.showFilterTanggalBerhasil(hasil);
                int nomor = readIntSafe("Input : ");
                if (nomor > 0 && nomor <= hasil.size()) {
                    jurnalView.showDetailJurnal(hasil.get(nomor - 1));
                    SCANNER.nextLine();
                }
            }
        } else {
            // kembali
        }
    }

    private static void handleSortJurnal() {
        jurnalView.showMenuSortJurnal();
        int pilih = readIntSafe("Input : ");
        switch (pilih) {
            case 1: // tanggal
                jurnalView.showSortTanggal();
                int t = readIntSafe("Input : ");
                if (t == 1) { // terbaru -> terlama
                    List<Jurnal> sorted = new ArrayList<>(currentJurnals);
                    sorted.sort((a, b) -> b.getTanggal().compareTo(a.getTanggal()));
                    jurnalView.showSortTanggalBerhasil(sorted);
                    int num = readIntSafe("Input : ");
                    if (num > 0 && num <= sorted.size()) {
                        jurnalView.showDetailJurnal(sorted.get(num - 1));
                        SCANNER.nextLine();
                    }
                } else if (t == 2) {
                    List<Jurnal> sorted = new ArrayList<>(currentJurnals);
                    sorted.sort(Comparator.comparing(Jurnal::getTanggal));
                    jurnalView.showSortTanggalBerhasil(sorted);
                    int num = readIntSafe("Input : ");
                    if (num > 0 && num <= sorted.size()) {
                        jurnalView.showDetailJurnal(sorted.get(num - 1));
                        SCANNER.nextLine();
                    }
                }
                break;
            case 2: // mood
                jurnalView.showSortMood();
                int m = readIntSafe("Input : ");
                List<Jurnal> sortedMood = new ArrayList<>(currentJurnals);
                if (m == 1) sortedMood.sort(Comparator.comparing(Jurnal::getMood));
                else sortedMood.sort(Comparator.comparing(Jurnal::getMood).reversed());
                jurnalView.showSortTanggalBerhasil(sortedMood);
                int numM = readIntSafe("Input : ");
                if (numM > 0 && numM <= sortedMood.size()) {
                    jurnalView.showDetailJurnal(sortedMood.get(numM - 1));
                    SCANNER.nextLine();
                }
                break;
            case 3: // judul
                jurnalView.showSortJudul();
                int j = readIntSafe("Input : ");
                List<Jurnal> sortedJudul = new ArrayList<>(currentJurnals);
                if (j == 1) sortedJudul.sort(Comparator.comparing(Jurnal::getJudul));
                else sortedJudul.sort(Comparator.comparing(Jurnal::getJudul).reversed());
                jurnalView.showSortTanggalBerhasil(sortedJudul);
                int numJ = readIntSafe("Input : ");
                if (numJ > 0 && numJ <= sortedJudul.size()) {
                    jurnalView.showDetailJurnal(sortedJudul.get(numJ - 1));
                    SCANNER.nextLine();
                }
                break;
            default:
                // kembali
                break;
        }
    }

    private static void handleProfil() {
        boolean back = false;
        while (!back) {
            profilView.showMenuKelolaProfil();
            int pilih = readIntSafe("Pilih menu : ");
            switch (pilih) {
                case 1:
                    profilView.lihatProfil(currentUser.getUsername(), currentUser.getNama(), currentUser.getEmail());
                    break;
                case 2:
                    String[] res = profilView.editProfil();
                    // res = {namaBaru, emailBaru, ubahPassword(Y/T), simpan}
                    String namaBaru = res.length > 0 ? res[0] : "";
                    String emailBaru = res.length > 1 ? res[1] : "";
                    String ubahPassword = res.length > 2 ? res[2] : "T";
                    String simpan = res.length > 3 ? res[3] : "T";

                    if (simpan.equalsIgnoreCase("Y")) {
                        if (!namaBaru.isBlank()) currentUser.setNama(namaBaru);
                        if (!emailBaru.isBlank()) currentUser.setEmail(emailBaru);

                        if (ubahPassword.equalsIgnoreCase("Y")) {
                            // meminta detail password dari ubahPasswordForm (ProfilView sudah melakukan input)
                            String[] pwdData = profilView.ubahPasswordForm();
                            // pwdData = {passwordLama, passwordBaru, konfirmasi, simpan}
                            if (pwdData != null && pwdData.length >= 3) {
                                String oldPwd = pwdData[0];
                                String newPwd = pwdData[1];
                                String confirm = pwdData[2];
                                if (!oldPwd.equals(currentUser.getPassword())) {
                                    System.out.println("Password lama salah. Perubahan password dibatalkan.");
                                } else if (!newPwd.equals(con
