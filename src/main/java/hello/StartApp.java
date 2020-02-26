package hello;

import hello.restTemplate.domain.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @创建人 sgwang
 * @name StartApp
 * @user shiguang.wang
 * @创建时间 2019/7/3
 * @描述
 */
public class StartApp {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        testOptional();

    }

    // 测试使用Optional
    public static void testOptional() {
        Optional<User> opt = Optional.empty();
        User temp = new User("temp", 18, "man");

        if (!opt.isPresent()) {
            System.out.println("此时opt 为空！");
        }


        User user = new User("test", 18, "man");

        opt = Optional.ofNullable(user);
        System.out.println("测试orElse。此时opt有值：" + opt.orElse(temp).getName());

        opt = Optional.ofNullable(user);
        System.out.println("测试orElseGet。此时opt有值：" + opt.orElseGet(() -> temp).getName());

        opt = Optional.ofNullable(null);
        System.out.println("测试ofNullable。此时opt有值：" + opt.orElse(temp).getName());

        // map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中
        opt = Optional.ofNullable(user);
        System.out.println("测试map。此时opt有值：" + opt.map(u -> u.getName()).orElse("mapName"));

        opt = Optional.ofNullable(user);
        System.out.println("测试filter。此时opt有值：" + opt.filter(u -> "test".equals(u.getName())).orElse(temp).getName());

    }

    public static void testFile() throws IOException {
        File file = new File("src/main/resources/in.txt");
        if (file.exists()) {
            System.out.println("找到正确路径！");
        }

        File outFile = new File("src/main/resources/out.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(outFile);

        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
    }

}
