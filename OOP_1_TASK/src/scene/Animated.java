package scene;

/**
 * Объект с анимацией: на каждом тике таймера делает один шаг,
 * если анимация включена.
 */
public interface Animated extends Switchable {

    /** Один тик анимации. При выключенной анимации ничего не меняет. */
    void update();
}
