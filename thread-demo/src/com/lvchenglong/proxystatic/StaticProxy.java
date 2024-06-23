package com.lvchenglong.proxystatic;

/**
 * 静态代理模式
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象要代理真实对象
 * 代理对象可以做很多真实对象做不了的事情
 * 真实对象可以专注做自己的事情
 */
public class StaticProxy {
    public static void main(String[] args){
        /*
         WeddingCompany weddingCompany = new WeddingCompany(new You());
         weddingCompany.HappyMarry();
        */
        // 代理对象          真实对象
        new Thread(()-> System.out.println("我爱你！")).start();
        // 代理对象          真实对象
        new WeddingCompany(new You()).HappyMarry();

    }
}

interface Marry {
    void HappyMarry();
}

// 真实对象
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("结婚咯！");
    }
}

// 代理对象
class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();  // 真实角色
        after();
    }

    private void before() {
        System.out.println("结婚之前，布置现场");
    }

    private void after() {
        System.out.println("结婚之后，收尾款");
    }
}
