package io.moquette.testembedded;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import io.moquette.interception.InterceptHandler;
import io.moquette.parser.proto.messages.AbstractMessage;
import io.moquette.parser.proto.messages.PublishMessage;
import io.moquette.server.Server;
import io.moquette.server.config.ClasspathConfig;
import io.moquette.server.config.IConfig;
import io.moquette.testembedded.EmbeddedLauncher.PublisherListener;

public class Test {


public static void main(String[] args) throws InterruptedException, IOException {
    final IConfig classPathConfig = new ClasspathConfig();

    final Server mqttBroker = new Server();
    List<? extends InterceptHandler> userHandlers = asList(new PublisherListener());
    mqttBroker.startServer(classPathConfig, userHandlers);
    

    System.out.println("MQTT Broker started press [CTRL+C] to stop");
    //Bind  a shutdown hook
    Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
            System.out.println("Stopping broker");
            mqttBroker.stopServer();
            System.out.println("Broker stopped");
        }
    });

    
}

}