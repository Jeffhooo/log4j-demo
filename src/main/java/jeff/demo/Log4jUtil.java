package jeff.demo;


import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jUtil {

    private final static String pattern = "%d{HH:mm:ss.SSS} [%t] %p %c{2}: %m%n";
    public final static String DISCRIMINATOR_KEY = "logFileName";

    public synchronized static boolean addFileAppender(String logFileName, Logger logger) {

        if (logger.getAppender(logFileName) == null) {

            try {

                String logfile = "log/" + logFileName + ".log";
                FileAppender fileAppender = new FileAppender(
                        new PatternLayout(pattern), logfile,true);
                fileAppender.setName(logFileName);
                fileAppender.addFilter(new MDCFilter(logFileName));
                fileAppender.setImmediateFlush(true);

                logger.addAppender(fileAppender);

            } catch (Exception e) {
                logger.warn("[ifx] Failed to add appender to log4j logger", e);
                return false;
            }
        }
        return true;

    }

    public static void stopFileAppender(String logFileName, Logger logger) {

        Appender appender = logger.getAppender(logFileName);
        if (appender != null) {
            appender.close();
        }
    }

}

