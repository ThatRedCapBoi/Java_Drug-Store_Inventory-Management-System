package repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
import java.util.Optional;
import model.User;

/**
 *
 * @author Itadori
 */
public interface UserRepo {

    Optional<User> findByUsername(String username);
}
