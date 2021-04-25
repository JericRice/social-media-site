package example.websocket;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

//from https://www.baeldung.com/websockets-spring
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/chat");
        registry.addEndpoint("/chat").withSockJS();
    }

    
}

//from docs examples
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//    /**
//     * Register WebSocketHandlers including SockJS fallback options if desired.
//     *
//     * @param registry
//     */
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//
//        registry.addHandler(myHandler(), "/myHandler");
//    }
//
//    @Bean
//    public WebSocketHandler myHandler() {
//
//        return new MyHandler();
//    }
//}
