import functions.basic.*;
import functions.*;
import functions.meta.*;

import java.io.*;

public class main {
    public static void main(String[] args) {
        try {
            System.out.println("=== ТЕСТИРОВАНИЕ МЕТОДОВ TabulatedFunction ===\n");

            // Создаем тестовые данные
            FunctionPoint[] points1 = {
                    new FunctionPoint(0.0, 1.0),
                    new FunctionPoint(1.0, 3.0),
                    new FunctionPoint(2.0, 5.0),
                    new FunctionPoint(3.0, 7.0)
            };

            FunctionPoint[] points2 = {
                    new FunctionPoint(0.0, 0.0),
                    new FunctionPoint(1.0, 2.0),
                    new FunctionPoint(2.0, 4.0),
                    new FunctionPoint(3.0, 6.0)
            };

            FunctionPoint[] points3 = {
                    new FunctionPoint(0.0, 1.0),
                    new FunctionPoint(1.0, 3.0),
                    new FunctionPoint(2.0, 5.0)
            };

            // Создаем объекты для тестирования
            ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);
            ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points1); // такой же как arrayFunc1
            ArrayTabulatedFunction arrayFunc3 = new ArrayTabulatedFunction(points2); // другой

            LinkedListTabulatedFunction linkedListFunc1 = new LinkedListTabulatedFunction(points1);
            LinkedListTabulatedFunction linkedListFunc2 = new LinkedListTabulatedFunction(points1); // такой же как
                                                                                                    // linkedListFunc1
            LinkedListTabulatedFunction linkedListFunc3 = new LinkedListTabulatedFunction(points3); // другой

            // =================================================================
            // ТЕСТИРОВАНИЕ toString()
            // 
            System.out.println("ТЕСТИРОВАНИЕ toString():");
            System.out.println("ArrayTabulatedFunction 1: " + arrayFunc1.toString());
            System.out.println("ArrayTabulatedFunction 2: " + arrayFunc2.toString());
            System.out.println("ArrayTabulatedFunction 3: " + arrayFunc3.toString());
            System.out.println("LinkedListTabulatedFunction 1: " + linkedListFunc1.toString());
            System.out.println("LinkedListTabulatedFunction 2: " + linkedListFunc2.toString());
            System.out.println("LinkedListTabulatedFunction 3: " + linkedListFunc3.toString());
            System.out.println();

            // =================================================================
            // ТЕСТИРОВАНИЕ equals()
            // =================================================================
            System.out.println("ТЕСТИРОВАНИЕ equals():");

            // Сравнение одинаковых объектов одного класса
            System.out.println("arrayFunc1.equals(arrayFunc2) [одинаковые Array]: " + arrayFunc1.equals(arrayFunc2));
            System.out.println("linkedListFunc1.equals(linkedListFunc2) [одинаковые LinkedList]: "
                    + linkedListFunc1.equals(linkedListFunc2));

            // Сравнение разных объектов одного класса
            System.out.println("arrayFunc1.equals(arrayFunc3) [разные Array]: " + arrayFunc1.equals(arrayFunc3));
            System.out.println("linkedListFunc1.equals(linkedListFunc3) [разные LinkedList]: "
                    + linkedListFunc1.equals(linkedListFunc3));

            // Сравнение объектов разных классов с одинаковыми точками
            System.out.println("arrayFunc1.equals(linkedListFunc1) [Array vs LinkedList, одинаковые точки]: "
                    + arrayFunc1.equals(linkedListFunc1));
            System.out.println("linkedListFunc1.equals(arrayFunc1) [LinkedList vs Array, одинаковые точки]: "
                    + linkedListFunc1.equals(arrayFunc1));

            // Сравнение с null
            System.out.println("arrayFunc1.equals(null) [с null]: " + arrayFunc1.equals(null));

            // Сравнение с самим собой
            System.out.println("arrayFunc1.equals(arrayFunc1) [с самим собой]: " + arrayFunc1.equals(arrayFunc1));
            System.out.println();

            // =================================================================
            // ТЕСТИРОВАНИЕ hashCode()
            // =================================================================
            System.out.println("ТЕСТИРОВАНИЕ hashCode():");
            System.out.println("arrayFunc1.hashCode(): " + arrayFunc1.hashCode());
            System.out.println("arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
            System.out.println("arrayFunc3.hashCode(): " + arrayFunc3.hashCode());
            System.out.println("linkedListFunc1.hashCode(): " + linkedListFunc1.hashCode());
            System.out.println("linkedListFunc2.hashCode(): " + linkedListFunc2.hashCode());
            System.out.println("linkedListFunc3.hashCode(): " + linkedListFunc3.hashCode());

            // Проверка согласованности equals() и hashCode()
            System.out.println("\nПроверка согласованности equals() и hashCode():");
            System.out.println("arrayFunc1.equals(arrayFunc2) && arrayFunc1.hashCode() == arrayFunc2.hashCode(): " +
                    (arrayFunc1.equals(arrayFunc2) && arrayFunc1.hashCode() == arrayFunc2.hashCode()));
            System.out.println(
                    "linkedListFunc1.equals(linkedListFunc2) && linkedListFunc1.hashCode() == linkedListFunc2.hashCode(): "
                            +
                            (linkedListFunc1.equals(linkedListFunc2)
                                    && linkedListFunc1.hashCode() == linkedListFunc2.hashCode()));
            System.out.println(
                    "arrayFunc1.equals(linkedListFunc1) && arrayFunc1.hashCode() == linkedListFunc1.hashCode(): " +
                            (arrayFunc1.equals(linkedListFunc1)
                                    && arrayFunc1.hashCode() == linkedListFunc1.hashCode()));

            // Тестирование изменения хэш-кода при небольшом изменении объекта
            System.out.println("\nТестирование изменения при небольшом изменении координат:");
            double originalY = arrayFunc1.getPointY(1);
            System.out.println("Исходный arrayFunc1.hashCode(): " + arrayFunc1.hashCode());

            // Небольшое изменение (на 0.001)
            arrayFunc1.setPointY(1, originalY + 0.001);
            System.out.println("После изменения Y[1] на +0.001, arrayFunc1.hashCode(): " + arrayFunc1.hashCode());

            // Возвращаем обратно
            arrayFunc1.setPointY(1, originalY);
            System.out.println("После возврата исходного значения, arrayFunc1.hashCode(): " + arrayFunc1.hashCode());
            System.out.println();

            // =================================================================
            // ТЕСТИРОВАНИЕ clone()
            // =================================================================
            System.out.println("ТЕСТИРОВАНИЕ clone():");

            // Клонирование ArrayTabulatedFunction
            ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc1.clone();
            System.out.println("arrayFunc1.clone().equals(arrayFunc1): " + arrayClone.equals(arrayFunc1));

            // Клонирование LinkedListTabulatedFunction
            LinkedListTabulatedFunction linkedListClone = (LinkedListTabulatedFunction) linkedListFunc1.clone();
            System.out.println(
                    "linkedListFunc1.clone().equals(linkedListFunc1): " + linkedListClone.equals(linkedListFunc1));

            // =================================================================
            // ПРОВЕРКА ГЛУБОКОГО КЛОНИРОВАНИЯ
            // =================================================================
            System.out.println("\nПРОВЕРКА ГЛУБОКОГО КЛОНИРОВАНИЯ:");

            // Запоминаем исходные значения
            double originalArrayY = arrayFunc1.getPointY(0);
            double originalLinkedListY = linkedListFunc1.getPointY(0);

            System.out.println("До изменений:");
            System.out.println(
                    "arrayFunc1 Y[0]: " + arrayFunc1.getPointY(0) + ", arrayClone Y[0]: " + arrayClone.getPointY(0));
            System.out.println("linkedListFunc1 Y[0]: " + linkedListFunc1.getPointY(0) + ", linkedListClone Y[0]: "
                    + linkedListClone.getPointY(0));

            // Изменяем оригинальные объекты
            arrayFunc1.setPointY(0, originalArrayY + 10.0);
            linkedListFunc1.setPointY(0, originalLinkedListY + 20.0);

            System.out.println("\nПосле изменения оригиналов:");
            System.out.println(
                    "arrayFunc1 Y[0]: " + arrayFunc1.getPointY(0) + ", arrayClone Y[0]: " + arrayClone.getPointY(0));
            System.out.println("linkedListFunc1 Y[0]: " + linkedListFunc1.getPointY(0) + ", linkedListClone Y[0]: "
                    + linkedListClone.getPointY(0));

        } catch (Exception e) {
            System.err.println("Ошибка при тестировании: " + e.getMessage());
            e.printStackTrace();
        }
    }
}