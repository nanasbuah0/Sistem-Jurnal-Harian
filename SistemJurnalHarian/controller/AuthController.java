package controller;

import model.User;
import util.InputUtil;
import view.LoginView;
import view.LogoutView;

public class AuthController {

    private final LoginView loginView = new LoginView();
    private final LogoutView logoutView = new LogoutView();
    private User currentUser;

    // data sementara (dummy)
    private final String DUMMY_USERNAME = "admin";
    private final String DUMMY_PASSWORD = "123";

    // proses login
    public User login() {
        String username = InputUtil.inputString("Masukkan username");
        String password = InputUtil.inputPassword("Masukkan password");

        if (username.equals(DUMMY_USERNAME) && password.equals(DUMMY_PASSWORD)) {
            loginView.showLoginSuccess(username);
            currentUser = new User(username, "Admin", "admin@example.com", password);
        } else {
            loginView.showLoginFailed(username);
            currentUser = null;
        }

        return currentUser;
    }

    // proses logout
    public boolean logout() {
        boolean konfirmasi = logoutView.showLogout();
        if (konfirmasi) {
            logoutView.showLogoutSuccess();
            currentUser = null;
            return true;
        } else {
            logoutView.showLogoutCancelled();
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}