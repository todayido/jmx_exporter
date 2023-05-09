package io.prometheus.jmx;

import io.prometheus.jmx.mo.MyThreadPoll;
import io.prometheus.jmx.mo.WebServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationAPP {
    /**
     * 入口
     * @param args an array of {@link java.lang.String} objects.
     * @author miaoyj
     * @since 2020-07-09
     */
    public static void main(String[] args) {
        WebServer.init();
        new MyThreadPoll();
        SpringApplication app = new SpringApplication(ApplicationAPP.class);
        app.run(args);
    }
}