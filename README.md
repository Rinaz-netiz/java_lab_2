# Дерево Фенвика (Fenwick Tree)

Реализация дерева Фенвика (Binary Indexed Tree) на Java для эффективного вычисления префиксных сумм и обновления элементов массива.

## Описание

Дерево Фенвика позволяет:

- Вычислять префиксные суммы за O(log n)
- Обновлять элементы за O(log n)
- Вычислять суммы на отрезках за O(log n)
- Строить дерево за O(n) или O(n log n)

## Структура проекта

fenwick-tree/
├── src/
│ ├── FenwickTree.java # Основной класс
│ ├── FenwickTreeDemo.java # Демонстрация работы
│ └── FenwickTreeTest.java # Тесты
├── README.md
└── .gitignore

## Компиляция и запуск

```bash
# Компиляция
javac src/*.java

# Запуск демонстрации
java -cp src FenwickTreeDemo

# Запуск тестов
java -cp src FenwickTreeTest


