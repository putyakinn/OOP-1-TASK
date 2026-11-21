package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример: сортировка цветов по заданным порядковым значениям.
 */
public final class SortingDemo {

    private SortingDemo() {
    }

    public static void main(String[] args) {
        List<Ranked<String>> colors = new ArrayList<>(List.of(
                new Ranked<>("green", 5),
                new Ranked<>("blue", 8),
                new Ranked<>("red", 1),
                new Ranked<>("yellow", 3),
                new Ranked<>("purple", 6)));

        System.out.println("До сортировки:  " + colors);

        Sorter sorter = new QuickSorter();
        sorter.sort(colors, Ranked.byOrder());

        System.out.println("После сортировки: " + colors);
    }
}
