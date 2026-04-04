/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validation;

/**
 *
 * @author Itadori
 */
public interface Validator<T> {

    void validate(T target);

    Validator<T> linkWith(Validator<T> next);

    abstract class Base<T> implements Validator<T> {

        private Validator<T> next;

        @Override
        public Validator<T> linkWith(Validator<T> next) {
            this.next = next;
            return next;
        }

        protected void next(T target) {
            if (next != null) {
                next.validate(target);
            }
        }
    }
}
