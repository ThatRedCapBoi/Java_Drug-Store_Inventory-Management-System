package state;

/**
 *
 * @author Itadori
 */
public interface AppState {

    void apply(Object view);
}
