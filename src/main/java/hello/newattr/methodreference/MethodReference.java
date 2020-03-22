package hello.newattr.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @创建人 sgwang
 * @name MethodReference
 * @user Anti
 * @创建时间 2020/3/22
 * @描述 方法引用 ::
 */
public class MethodReference {


    public static MethodReference create(Supplier<MethodReference> supplier) {
        return supplier.get();
    }

    public static void collide(MethodReference methodReference) {
        System.out.println("Collided " + methodReference.toString());
    }

    public void follow(MethodReference another) {
        System.out.println("Following the " + another.toString());
    }

    //
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        // 构造器引用：   Class::new
        MethodReference car = MethodReference.create(MethodReference::new);
        List<MethodReference> cars = Arrays.asList(car);

        // 静态方法引用：  Class::static_method
        cars.forEach(MethodReference::collide);

        // 特定对象的方法引用   instance::method
        MethodReference police = MethodReference.create(MethodReference::new);
        cars.forEach(police::follow);

        // 特定类 的任意对象所有的方法引用：  Class::method
        cars.forEach(MethodReference::repair);
    }

}
