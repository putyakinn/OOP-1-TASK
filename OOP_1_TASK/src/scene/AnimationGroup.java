package scene;

import java.util.List;

/**
 * Группа переключаемых объектов, которыми управляют как одним целым
 * (например, все облака одной кнопкой).
 */
public final class AnimationGroup implements Switchable {

    private final List<Switchable> members;

    public AnimationGroup(Switchable... members) {
        this.members = List.of(members);
    }

    /** Группа включена, если включены все её участники. */
    @Override
    public boolean isEnabled() {
        return members.stream().allMatch(Switchable::isEnabled);
    }

    @Override
    public void setEnabled(boolean enabled) {
        members.forEach(member -> member.setEnabled(enabled));
    }
}
