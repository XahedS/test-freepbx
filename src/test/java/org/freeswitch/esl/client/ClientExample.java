package org.freeswitch.esl.client;

import com.google.common.base.Throwables;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.IEslEventListener;
import org.freeswitch.esl.client.internal.Context;
import org.freeswitch.esl.client.internal.IModEslApi.EventFormat;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ClientExample {
    private static final Logger L = LoggerFactory.getLogger(ClientExample.class);

    public static void main(String[] args) {
        try {
//            if (args.length < 1) {
//                System.out.println("Usage: java ClientExample PASSWORD");
//                return;
//            }

//            String password = args[0];

            Client client = new Client();

//            client.addEventListener((ctx, event) -> L.info("Received event: {}", event.getEventName()));
            client.addEventListener(new IEslEventListener() {
                @Override
                public void onEslEvent(Context ctx, EslEvent event) {
                    L.info("Received event: {}", event.getEventName());
                }
            });

//            client.connect(new InetSocketAddress("localhost", 8021), password, 10);
            client.connect(new InetSocketAddress("localhost", 8021), "ClueCon", 10);
            client.setEventSubscriptions(EventFormat.PLAIN, "all");

        } catch (Throwable t) {
            Throwables.propagate(t);
        }
    }
}
