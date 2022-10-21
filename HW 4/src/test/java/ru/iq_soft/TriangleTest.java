
package ru.iq_soft;

import java.text.MessageFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest{
    Logger logger = LoggerFactory.getLogger("Unit test's");

    @Test
    @DisplayName("Тест 1: проверка сходимости формулы на существующем треугольнике")
    void test1() throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 1: проверка сходимости формулы на существующем треугольнике");
        assertFalse(HeronTriangle.getSquare(3,6,7)==0);
        logger.info("\tТест успешен");
    }

    @ParameterizedTest
    @CsvSource({"3,6,7,8.94427190999916","-3,-6,-7,8.94427190999916","3,-6,7,8.94427190999916","7,6,7,18.973665961010276"})
    @DisplayName("Тест 2: проверка корректности вычисления площади на существующих треугольниках")
    void test2(int a, int b, int c, double d) throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 2: проверка корректности вычисления площади на существующих треугольниках");
        logger.info("\tВходящие параметры - (a,b,c) - " +a+", "+b+", "+c);
        assertEquals(HeronTriangle.getSquare(a,b,c),d);
        logger.info("\tТест успешен");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/InvalidTriangles.csv")
    @DisplayName("Test 3: проверка корректности срабатывания исключнения при вырождении в точку или отрезок")
    void test3(int a, int b, int c) throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 3: проверка корректности срабатывания исключнения при вырождении в точку или отрезок");
        DegenerateTriangleException thrown = assertThrows(
                DegenerateTriangleException.class,
                ()->HeronTriangle.getSquare(a, b, c));
        assertTrue(thrown.getMessage().contains("Вырожденный в отрезок или точку треугольник не имеет площади"));

        logger.info("\tТест успешен");
    }

    @Test
    @DisplayName("Тест 4: проверка корректности срабатывания исключения, когда треугольник не существует")
    void test4() throws InvalidTriangleException, DegenerateTriangleException {
        logger.info("Тест 4: проверка корректности срабатывания исключения, когда треугольник не существует");
        assertThrows(InvalidTriangleException.class, ()->HeronTriangle.getSquare(233, 6, 7));
        logger.info("\tТест успешен");
    }
}
