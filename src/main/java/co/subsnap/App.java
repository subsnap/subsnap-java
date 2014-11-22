package co.subsnap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {


        ApplicationContext ctx = SpringApplication.run(new Object[] { App.class }, args);
        logger.info("======================================================");
        logger.info("Started Running SubSnap");
        logger.info("======================================================");
    }

}
