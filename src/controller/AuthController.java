/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import exception.UserException;
import model.User;
import service.AuthService;

/**
 *
 * @author Itadori
 */
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public User login(String username, String password) throws UserException {
        return authService.login(username, password);
    }

    public void logout() {
        authService.logout();
    }

    public User currentUser() {
        return authService.currentUser();
    }
}
