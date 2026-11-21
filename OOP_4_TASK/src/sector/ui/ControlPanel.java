package sector.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import sector.model.Sector;

/**
 * Панель с числовыми полями радиуса и угла раствора и кнопкой сброса.
 * Поля двусторонне связаны с моделью: правка поля меняет модель,
 * а изменение модели (например, колесом мыши) обновляет поле.
 */
public final class ControlPanel extends JPanel {

    private static final Dimension SPINNER_SIZE = new Dimension(80, 26);

    private final Sector sector;
    private final SpinnerNumberModel radiusModel;
    private final SpinnerNumberModel sweepModel;

    public ControlPanel(Sector sector) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 6));
        this.sector = Objects.requireNonNull(sector);

        radiusModel = new SpinnerNumberModel(
                (int) sector.getRadius(), (int) Sector.MIN_RADIUS, (int) Sector.MAX_RADIUS, 10);
        sweepModel = new SpinnerNumberModel(
                (int) sector.getSweepAngle(), (int) Sector.MIN_SWEEP_ANGLE, (int) Sector.MAX_SWEEP_ANGLE, 5);

        add(new JLabel("Радиус:"));
        add(createSpinner(radiusModel));
        add(new JLabel("Угол сектора:"));
        add(createSpinner(sweepModel));
        add(createResetButton());
        add(createHint());

        bindModel();
    }

    private static JSpinner createSpinner(SpinnerNumberModel model) {
        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(SPINNER_SIZE);
        return spinner;
    }

    private JButton createResetButton() {
        JButton reset = new JButton("Сброс");
        reset.addActionListener(e -> sector.reset());
        return reset;
    }

    private static JLabel createHint() {
        JLabel hint = new JLabel("  ЛКМ — перемещение | ПКМ — вращение | Колесо — радиус");
        hint.setForeground(Color.GRAY);
        return hint;
    }

    private void bindModel() {
        // Поле -> модель
        radiusModel.addChangeListener(e -> sector.setRadius(radiusModel.getNumber().doubleValue()));
        sweepModel.addChangeListener(e -> sector.setSweepAngle(sweepModel.getNumber().doubleValue()));

        // Модель -> поле. Повторная запись того же значения событий не порождает, цикла нет.
        sector.addPropertyChangeListener(Sector.PROP_RADIUS,
                e -> radiusModel.setValue((int) Math.round(sector.getRadius())));
        sector.addPropertyChangeListener(Sector.PROP_SWEEP_ANGLE,
                e -> sweepModel.setValue((int) Math.round(sector.getSweepAngle())));
    }
}
