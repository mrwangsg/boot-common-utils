package demo;

import hello.demo.Pizza;
import hello.demo.PizzaStatus;

/**
 * @创建人 sgwang
 * @name PizzaTest
 * @user Anti
 * @创建时间 2020/3/8
 * @描述
 */
public class PizzaTest {
    public static void main(String[] args) {
        Pizza testPz = new Pizza();
        testPz.setPizzaStatus(PizzaStatus.READY);
        testPz.printTimeToDeliver();
    }
}
