package hello.demo;

/**
 * @Author :  sgwang
 * @Version :  1.0
 * @Title :  PizzaStatus
 * @CreateDate :  2020/3/8 16:33
 * @Description :  TODO
 */
public enum PizzaStatus {

    // 可以覆盖枚举类定义的方法
    ORDERED(5) {
        @Override
        public boolean isOrdered() {
            return true;
        }
    },
    READY(2) {
        @Override
        public boolean isReady() {
            return true;
        }
    },
    DELIVERED(0) {
        @Override
        public boolean isDelivered() {
            return true;
        }
    };

    // 截至发货时间
    private int timeToDelivery;

    PizzaStatus(Integer timeToDelivery) {
        this.timeToDelivery = timeToDelivery;
    }

    public boolean isOrdered() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public boolean isDelivered() {
        return false;
    }

    public int getTimeToDelivery() {
        return timeToDelivery;
    }

}
