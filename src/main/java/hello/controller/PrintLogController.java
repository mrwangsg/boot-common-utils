package hello.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 sgwang
 * @name PrintLogController
 * @user Anti
 * @创建时间 2020/6/14
 * @描述 日志打印等级：TRACE < DEBUG < INFO < WARN < ERROR < FATAL
 */
@RestController
@RequestMapping(value = "/log")
public class PrintLogController {
    private static final Logger logger = LoggerFactory.getLogger(PrintLogController.class);
    private static final String I10001 = "print log of {} ";

    @GetMapping("/trace")
    public String trace() {
        logger.warn(I10001, LogLevel.TRACE);
        return "PrintLogController ! " + LogLevel.TRACE;
    }

    @GetMapping("/debug")
    public String debug() {
        logger.debug(I10001, LogLevel.DEBUG);
        return "PrintLogController ! " + LogLevel.DEBUG;
    }

    @GetMapping("/info")
    public String info() {
        logger.info(I10001, LogLevel.INFO);
        return "PrintLogController ! " + LogLevel.INFO;
    }

    @GetMapping("/warn")
    public String warn() {
        logger.warn(I10001, LogLevel.WARN);
        return "PrintLogController ! " + LogLevel.WARN;
    }

    @GetMapping("/error")
    public String error() {
        logger.error(I10001, LogLevel.ERROR);
        return "PrintLogController ! " + LogLevel.ERROR;
    }

    @GetMapping("/except")
    public String except() {
        try {
            throwException();
        } catch (Exception e) {
            logger.error("except: ", e);
        }

        return "PrintLogController ! " + "self exception";
    }


    private void throwException() throws Exception {
        throw new Exception("抛出异常，看能否打印！");
    }

}
