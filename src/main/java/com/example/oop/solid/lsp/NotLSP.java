package com.example.oop.solid.lsp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Rectangle_
{
    protected int width,height;
    public int getArea() {
        return width * height;
    }
}

class Square_ extends Rectangle_ {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(getWidth());
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(getHeight());
    }
}

public class NotLSP {
    public static void main(String[] args) {
        Rectangle_ rectangle = new Rectangle_();
        rectangle.setWidth(10);
        rectangle.setHeight(5);

        System.out.println(rectangle.getArea());
    }
}

//public class NotLSP {
//    public static void main(String[] args) {
//        Rectangle_ rectangle = new Square_();
//        rectangle.setWidth(10);
//        rectangle.setHeight(5);
//
//        System.out.println(rectangle.getArea());
//    }
//}