/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.UserException;
import infra.PasswordUtil;
import model.User;
import repository.UserRepo;

/**
 *
 * @author Itadori
 */
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private User current;

    public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User login(String username, String password) throws UserException {
        if (username == null || username.isBlank()) {
            throw new UserException("Username is required.");
        }
        if (password == null || password.isBlank()) {
            throw new UserException("Password is required.");
        }

        User u = userRepo.findByUsername(username.trim())
                .orElseThrow(() -> new UserException("Invalid username or password."));
        boolean ok = PasswordUtil.verify(password, u.getPasswordHash());
        if (!ok) {
            throw new UserException("Invalid username or password.");
        }

        current = u;
        return u;
    }

    @Override
    public void logout() {
        current = null;
    }

    @Override
    public User currentUser() {
        return current;
    }
}
