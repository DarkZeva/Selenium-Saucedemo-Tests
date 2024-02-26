package Log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Log4j2 {
    private static Logger logger = LogManager.getLogger(Log4j2.class);

    @Test
    public void loggerTest(){
        System.out.println("Hello");
        logger.info("Info 2137");
        logger.error("Error 2137");

    }
}
