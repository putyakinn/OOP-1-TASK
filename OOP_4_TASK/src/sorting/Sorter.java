package sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Алгоритм сортировки списка «на месте».
 * Реализации взаимозаменяемы: вызывающий код зависит только от этого интерфейса.
 */
public interface Sorter {

    /**
     * Сортирует список по порядку, заданному компаратором.
     *
     * @throws NullPointerException если список или компаратор равны {@code null}
     */
    <T> void sort(List<T> list, Comparator<? super T> comparator);

    /** Сортировка по естественному порядку элементов. */
    default <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, Comparator.naturalOrder());
    }
}
