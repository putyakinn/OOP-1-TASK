package scene;

/**
 * Базовый класс для анимированных фигур. Берёт на себя флаг включения,
 * наследники описывают только сам шаг анимации ({@link #step()}).
 */
public abstract class AnimatedShape implements Drawable, Animated {

    private boolean enabled = true;

    @Override
    public final void update() {
        if (enabled) {
            step();
        }
    }

    /** Один шаг анимации; вызывается только при включённой анимации. */
    protected abstract void step();

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
