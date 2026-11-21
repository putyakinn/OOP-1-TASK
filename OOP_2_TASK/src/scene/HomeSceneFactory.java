package scene;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import scene.Cloud.Puff;

/**
 * Собирает сцену «домик на лужайке».
 */
public final class HomeSceneFactory {

    private static final Color SKY = new Color(135, 206, 235);
    private static final Color GRASS = new Color(34, 139, 34);
    private static final Color PATH = new Color(180, 180, 180);

    private static final List<Puff> LARGE_CLOUD_PUFFS = List.of(
            new Puff(0, 50, 80, 50),
            new Puff(40, 30, 80, 50),
            new Puff(80, 50, 80, 50),
            new Puff(20, 60, 100, 40));

    private static final List<Puff> SMALL_CLOUD_PUFFS = List.of(
            new Puff(0, 80, 70, 40),
            new Puff(30, 60, 70, 40),
            new Puff(60, 80, 70, 40));

    private HomeSceneFactory() {
    }

    public static Scene create() {
        return new Scene(SKY)
                .add(ColoredShape.filled(new Rectangle(0, 450, 800, 150), GRASS))
                .add(new House(new Rectangle(200, 300, 250, 180)))
                .add(new Sun(650, 50))
                .add(new Cloud(50, LARGE_CLOUD_PUFFS))
                .add(new Cloud(450, SMALL_CLOUD_PUFFS))
                .add(ColoredShape.outlined(
                        new Polygon(new int[]{380, 430, 460, 410}, new int[]{480, 480, 580, 580}, 4),
                        PATH, Color.BLACK))
                .add(new Flower(120, 470, Color.RED))
                .add(new Flower(550, 480, Color.MAGENTA))
                .add(new Signature("Илья Путякин", 30));
    }
}
