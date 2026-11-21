package ui;

import javax.swing.JButton;

import scene.Switchable;

/**
 * Кнопка, переключающая {@link Switchable} и отражающая его состояние в тексте.
 */
public final class AnimationToggleButton extends JButton {

    private final Switchable target;
    private final String enabledText;
    private final String disabledText;

    public AnimationToggleButton(Switchable target, String enabledText, String disabledText) {
        this.target = target;
        this.enabledText = enabledText;
        this.disabledText = disabledText;
        refreshText();
        addActionListener(e -> {
            target.toggle();
            refreshText();
        });
    }

    private void refreshText() {
        setText(target.isEnabled() ? enabledText : disabledText);
    }
}
