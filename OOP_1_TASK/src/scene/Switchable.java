package scene;

/**
 * Объект, который можно включать и выключать.
 */
public interface Switchable {

    boolean isEnabled();

    void setEnabled(boolean enabled);

    /**
     * Переключает состояние на противоположное.
     *
     * @return новое состояние
     */
    default boolean toggle() {
        setEnabled(!isEnabled());
        return isEnabled();
    }
}
