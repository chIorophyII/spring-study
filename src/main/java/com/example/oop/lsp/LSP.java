package com.example.oop.lsp;

class Shape
{
    protected int width, height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Rectangle extends Shape {
    public Rectangle(int width, int height)
    {
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

public class LSP {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(5);
        System.out.println(rectangle.getArea());
        System.out.println(square.getArea());
    }
}
