package com.example.oop.solid.lsp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Shape
{
    protected int width, height;
    public int getArea() {
        return width * height;
    }
}

class Rectangle extends Shape {
    public Rectangle(int width, int height) {
        setWidth(width);
        setHeight(height);
    }
}

class Square extends Shape {
    public Square(int length) {
        setWidth(length);
        setHeight(length);
    }
}

@Setter
public class LSP {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(5);
        System.out.println(rectangle.getArea());
        System.out.println(square.getArea());
    }
}

