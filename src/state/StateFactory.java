/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

public class StateFactory {

    public static AppState fromRole(String role) {
        if (role == null) {
            return new StaffState();
        }

        String r = role.trim().toUpperCase();

        if ("INVENTORY_MANAGER".equals(r)) {
            return new ManagerState();
        }
        return new StaffState();
    }
}
