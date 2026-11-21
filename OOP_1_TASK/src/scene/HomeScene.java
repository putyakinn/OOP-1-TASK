package scene;

/**
 * Готовая сцена вместе с «ручками» управления её анимациями.
 *
 * @param scene  сама сцена
 * @param sun    переключатель анимации солнца
 * @param clouds переключатель анимации облаков
 */
public record HomeScene(Scene scene, Switchable sun, Switchable clouds) {
}
