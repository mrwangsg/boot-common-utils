package hello.demo;

/**
 * @Author :  sgwang
 * @Version :  1.0
 * @Title :  Pizza
 * @CreateDate :  2020/3/8 16:38
 * @Description :  TODO
 */
public class Pizza {
    private PizzaStatus pizzaStatus;

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " + this.pizzaStatus.getTimeToDelivery());
    }

    public PizzaStatus getPizzaStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }
}
