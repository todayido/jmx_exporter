//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.prometheus.jmx.mo;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.jmx.JmxCollector;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.management.MalformedObjectNameException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebServer {
    private static boolean isInit = false;

    public WebServer() {
    }

    public static synchronized void init() {
        System.out.println(Thread.currentThread().getName() + " WebServer init.");

        try {
            if (isInit) {
                return;
            }

            isInit = true;
            Config config = loadConfig((Map)(new Yaml()).load(WebServer.class.getResourceAsStream("/collector.yml")));
            (new SamplesCollector()).register();
            (new JmxCollector(WebServer.class.getResourceAsStream("/tomcat.yml"))).register();
            DefaultExports.initialize();
            int port = Integer.parseInt(String.valueOf(config.port));
            String host = config.host;
            InetSocketAddress socket = new InetSocketAddress(host, port);
            new HTTPServer(socket, CollectorRegistry.defaultRegistry);
            System.out.println(Thread.currentThread().getName() + " WebServer init success.");
        } catch (MalformedObjectNameException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    private static Config loadConfig(Map<String, Object> yamlConfig) throws MalformedObjectNameException {
        Config cfg = new Config();
        if (yamlConfig == null) {
            yamlConfig = new HashMap();
        }

        if ((yamlConfig).containsKey("port")) {
            try {
                cfg.port = (Integer)((Map)yamlConfig).get("port");
                System.out.println("Httpserver collector port: " + cfg.port);
            } catch (NumberFormatException var3) {
                throw new IllegalArgumentException("Invalid number provided for port", var3);
            }
        }

        return cfg;
    }
}
