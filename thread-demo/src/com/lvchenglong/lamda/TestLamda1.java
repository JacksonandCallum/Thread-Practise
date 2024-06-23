package com.lvchenglong.lamda;

// 推导lamda表达式
public class TestLamda1 {

    // 3. 静态内部类
    static class Like2 implements ILike{
        @Override
        public void lamda() {
            System.out.println("I like Lamda2");
        }
    }

    public static void main(String[] args){
        ILike like = new Like1();
        like.lamda();

        like = new Like2();
        like.lamda();

        // 4. 局部内部类
        class Like3 implements ILike{
            @Override
            public void lamda() {
                System.out.println("I like Lamda3");
            }
        }

        like = new Like3();
        like.lamda();

        // 5. 匿名内部类，没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lamda() {
                System.out.println("I like Lamda4");
            }
        };
        like.lamda();

        // 6. 用lamda简化
        like = ()->{
            System.out.println("I like Lamda5");
        };
        like.lamda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lamda();
}

// 2.实现类
class Like1 implements ILike{
    @Override
    public void lamda() {
        System.out.println("I like Lamda1");
    }
}