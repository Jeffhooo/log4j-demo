package jeff.demo;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class Log4jDemo {

    private static final Logger logger = LogManager.getLogger(Log4jDemo.class);

    public static void main(String[] args) {

        new Thread(() -> {
                Log4jUtil.addFileAppender("thread-0", logger);
                MDC.put("logFileName", "thread-0");

                logger.trace("thread-0: Hello World!");
                logger.debug("thread-0: How are you today?");
                logger.info("thread-0: I am fine.");
                logger.warn("thread-0: I love programming.");
                logger.error("thread-0: I am programming.");

                MDC.remove("logFileName");
                Log4jUtil.stopFileAppender("thread-0", logger);
        }).start();

        new Thread(() -> {
                Log4jUtil.addFileAppender("thread-1", logger);
                MDC.put("logFileName", "thread-1");

                logger.trace("thread-1: Hello World!");
                logger.debug("thread-1: How are you today?");
                logger.info("thread-1: I am fine.");
                logger.warn("thread-1: I love programming.");
                logger.error("thread-1: I am programming.");

                MDC.remove("logFileName");
                Log4jUtil.stopFileAppender("thread-1", logger);
        }).start();

        Log4jUtil.addFileAppender("main", logger);
        MDC.put("logFileName", "main");

        logger.trace("main: Hello World!");
        logger.debug("main: How are you today?");
        logger.info("main: I am fine.");
        logger.warn("main: I love programming.");
        logger.error("main: I am programming.");

        MDC.remove("logFileName");
        Log4jUtil.stopFileAppender("main", logger);
    }

}
