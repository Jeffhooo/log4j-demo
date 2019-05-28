package jeff.demo;


import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jUtil {

    private final static String PATTERN = "%d{HH:mm:ss.SSS} [%t] %p %c{2}: %m%n";
    public final static String DISCRIMINATOR_KEY = "logFileName";

    public synchronized static boolean addFileAppender(String logFileName, Logger logger) {

        if (logger.getAppender(logFileName) == null) {
            try {
                // create appender
                String logfile = "log/" + logFileName + ".log";
                FileAppender fileAppender = new FileAppender(new PatternLayout(PATTERN), logfile,true);
                fileAppender.setName(logFileName);
                fileAppender.addFilter(new MDCFilter(logFileName));
                fileAppender.setImmediateFlush(true);

                // add appender
                logger.addAppender(fileAppender);
            } catch (Exception e) {
                logger.warn("Failed to add appender to log4j logger", e);
                return false;
            }
        }
        return true;
    }

    public static void stopFileAppender(String logFileName, Logger logger) {

        Appender appender = logger.getAppender(logFileName);
        if (appender != null) {
            // close appender
            appender.close();
        }
    }
}

