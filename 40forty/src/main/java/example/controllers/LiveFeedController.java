package example.controllers;

import example.models.LiveFeed;
import example.models.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("/livefeed")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class LiveFeedController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(LiveFeed liveFeed) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(liveFeed.getFrom(), liveFeed.getText(), time);
    }
}
