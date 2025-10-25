package controller;

import model.User;
import util.InputUtil;
import view.ProfilView;

public class ProfilController {

    private final ProfilView profilView = new ProfilView();

    public void kelolaProfil(User user) {
        boolean kembali = false;
        while (!kembali) {
            profilView.showMenuKelolaProfil();
            int pilihan = InputUtil.inputInt("Pilih menu");

            switch (pilihan) {
                case 1 -> profilView.lihatProfil(user.getUsername(), user.getNama(), user.getEmail());
                case 2 -> {
                    String[] dataBaru = profilView.editProfil();
                    if (dataBaru[3].equalsIgnoreCase("Y")) {
                        user.setNama(dataBaru[0]);
                        user.setEmail(dataBaru[1]);
                        profilView.showUpdateSuccess();
                    }
                }
                case 3 -> kembali = true;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}