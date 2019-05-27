package jeff.demo;


import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class RunIdFilter extends Filter {

    private final String runId;

    public RunIdFilter(String runId) {
        this.runId = runId;
    }

    @Override
    public int decide(LoggingEvent event) {
        Object mdc = event.getMDC(Log4jUtil.DISCRIMINATOR_KEY);

        if (runId.equals(mdc)) {
            return Filter.ACCEPT;
        }

        return Filter.DENY;
    }

}

