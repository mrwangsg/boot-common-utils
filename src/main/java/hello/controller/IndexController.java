package hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author        :  sgwang
 * @Version       :  1.0
 * @Title         :  IndexController
 * @CreateDate    :  2020/3/7 22:41
 * @Description   :  TODO
 */
@RestController
public class IndexController {

    @RequestMapping
    public String index(){
        return "Index util !";
    }

}
