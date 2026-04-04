/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author ilhan
 */
public class CategoryException extends Exception {

    public CategoryException() {
        super();
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
