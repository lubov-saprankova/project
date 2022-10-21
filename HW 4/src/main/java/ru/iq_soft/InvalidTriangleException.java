
package ru.iq_soft;
import java.util.*;

public class InvalidTriangleException extends Exception{
    public InvalidTriangleException() {
        super("Треугольник не существует");
    }
}

