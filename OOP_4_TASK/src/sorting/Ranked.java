package sorting;

import java.util.Comparator;

/**
 * Элемент вместе с его порядковым значением. Заменяет пару «параллельных массивов»
 * (данные + ключи), которые приходилось бы переставлять синхронно: данные и ключ
 * теперь неразделимы, а для сортировки достаточно обычного компаратора.
 *
 * @param item  сам элемент
 * @param order значение, по которому элементы упорядочиваются
 */
public record Ranked<T>(T item, int order) {

    /** Компаратор по возрастанию {@link #order()}. */
    public static <T> Comparator<Ranked<T>> byOrder() {
        return Comparator.comparingInt(Ranked::order);
    }

    @Override
    public String toString() {
        return item + "(" + order + ")";
    }
}
