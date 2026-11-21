package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Быстрая сортировка (Хоар) с трёхсторонним разбиением.
 *
 * <ul>
 *   <li>Опорный элемент — медиана трёх (первый, средний, последний): отсортированные
 *       и «почти отсортированные» входы не приводят к квадратичной сложности.</li>
 *   <li>Трёхстороннее разбиение (меньше / равно / больше) эффективно работает
 *       при большом числе одинаковых ключей.</li>
 *   <li>Рекурсивно обрабатывается меньшая часть, большая — в цикле, поэтому
 *       глубина стека не превышает O(log n).</li>
 * </ul>
 *
 * Сортировка <b>не устойчива</b>: порядок элементов с равными ключами не гарантируется.
 * Среднее время O(n log n), память O(log n).
 */
public final class QuickSorter implements Sorter {

    @Override
    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        Objects.requireNonNull(list, "list");
        Objects.requireNonNull(comparator, "comparator");
        quickSort(list, comparator, 0, list.size() - 1);
    }

    private static <T> void quickSort(List<T> list, Comparator<? super T> comparator, int low, int high) {
        while (low < high) {
            T pivot = medianOfThree(list.get(low), list.get((low + high) >>> 1), list.get(high), comparator);

            // Инвариант: [low, lt) < pivot, [lt, i) == pivot, (gt, high] > pivot
            int lt = low;
            int gt = high;
            int i = low;
            while (i <= gt) {
                int cmp = comparator.compare(list.get(i), pivot);
                if (cmp < 0) {
                    Collections.swap(list, lt++, i++);
                } else if (cmp > 0) {
                    Collections.swap(list, i, gt--);
                } else {
                    i++;
                }
            }

            // Меньшую часть — рекурсией, большую — продолжением цикла
            if (lt - low < high - gt) {
                quickSort(list, comparator, low, lt - 1);
                low = gt + 1;
            } else {
                quickSort(list, comparator, gt + 1, high);
                high = lt - 1;
            }
        }
    }

    private static <T> T medianOfThree(T a, T b, T c, Comparator<? super T> comparator) {
        if (comparator.compare(a, b) < 0) {
            if (comparator.compare(b, c) < 0) {
                return b;
            }
            return comparator.compare(a, c) < 0 ? c : a;
        }
        if (comparator.compare(a, c) < 0) {
            return a;
        }
        return comparator.compare(b, c) < 0 ? c : b;
    }
}
