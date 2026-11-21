# OOP_4_TASK

Две независимые программы: быстрая сортировка (`sorting.SortingDemo`) и сектор круга с управлением мышью (`sector.SectorApp`).

Требуется JDK 17+.

## Сборка и запуск

```bash
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out sector.SectorApp   # сектор; сортировка: java -cp out sorting.SortingDemo
```
