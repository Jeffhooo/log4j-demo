package jeff.demo;


import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class MDCFilter extends Filter {

    private final String key;

    public MDCFilter(String key) {
        this.key = key;
    }

    @Override
    public int decide(LoggingEvent event) {
        Object eventKey = event.getMDC(Log4jUtil.DISCRIMINATOR_KEY);

        if (key.equals(eventKey)) {
            return Filter.ACCEPT;
        }

        return Filter.DENY;
    }

}

