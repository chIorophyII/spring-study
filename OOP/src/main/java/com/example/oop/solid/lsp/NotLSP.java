package com.example.oop.solid.lsp;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.ranges.RangeException;

//@Getter
//@Setter
//class Rectangle_
//{
//    protected int width,height;
//    public int getArea() {
//        return width * height;
//    }
//}
//
//class Square_ extends Rectangle_ {
//    @Override
//    public void setWidth(int width) {
//        super.setWidth(width);
//        super.setHeight(getWidth());
//    }
//
//    @Override
//    public void setHeight(int height) {
//        super.setHeight(height);
//        super.setWidth(getHeight());
//    }
//}
//
//public class NotLSP {
//    public static void main(String[] args) {
//        Rectangle_ rectangle = new Rectangle_();
//        rectangle.setWidth(10);
//        rectangle.setHeight(5);
//
//        System.out.println(rectangle.getArea());
//    }
//}

//public class NotLSP {
//    public static void main(String[] args) {
//        Rectangle_ rectangle = new Square_();
//        rectangle.setWidth(10);
//        rectangle.setHeight(5);
//
//        System.out.println(rectangle.getArea());
//    }
//}

//class Content {
//    public void WriteLog() {
//        System.out.println("good");
//    }
//}
//
//class Parent {
//    public Content getContent() {
//        Content content = new Content();
//        return content;
//    }
//}
//
//class Child extends Parent {
//    @Override
//    public Content getContent() {
//        return null;
//    }
//}
//
//public class NotLSP {
//    public static void main(String[] args) {
//        Parent parent = new Child();
//        Content content = parent.getContent();
//        content.WriteLog();
//    }
//}

class Parents {
    void method(int data) {
        if (data < 0) {
            throw new RangeException((short) data, "음수가 아니어야 합니다.");
        }
        System.out.println("good");
    }
}

class Children extends Parents{
    @Override
    public void method(int data) {
        if (data <= 0) {
            throw new RangeException((short) data, "0보다 커야 합니다");
        }
    }
}

public class NotLSP {
    public static void main(String[] args) {
        Parents parents = new Children();
        parents.method(0);
    }
}