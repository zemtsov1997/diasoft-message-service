package ru.diasoft.message.service.client;

import com.clivern.racter.BotPlatform;
import com.clivern.racter.receivers.webhook.MessageReceivedWebhook;
import com.clivern.racter.senders.templates.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Spark;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class FacebookClient {

    @Autowired private BotPlatform botPlatform;

    @PostConstruct
    public void init() {
        verifyTokenRoute();
        initListener();
    }

    public void verifyTokenRoute() {
        Spark.get("/", (request, response) -> {
            botPlatform.getVerifyWebhook().setHubMode(( request.queryParams("hub.mode") != null ) ? request.queryParams("hub.mode") : "");
            botPlatform.getVerifyWebhook().setHubVerifyToken(( request.queryParams("hub.verify_token") != null ) ? request.queryParams("hub.verify_token") : "");
            botPlatform.getVerifyWebhook().setHubChallenge(( request.queryParams("hub.challenge") != null ) ? request.queryParams("hub.challenge") : "");

            if( botPlatform.getVerifyWebhook().challenge() ){
                botPlatform.finish();
                response.status(200);
                return ( request.queryParams("hub.challenge") != null ) ? request.queryParams("hub.challenge") : "";
            }

            botPlatform.finish();
            response.status(403);
            return "Verification token mismatch";
        });
    }

    public void initListener() {
        Spark.post("/", (request, response) -> {
            String body = request.body();
            botPlatform.getBaseReceiver().set(body).parse();
            HashMap<String, MessageReceivedWebhook> messages = (HashMap<String, MessageReceivedWebhook>) botPlatform.getBaseReceiver().getMessages();
            for (MessageReceivedWebhook message : messages.values()) {

                String user_id = (message.hasUserId()) ? message.getUserId() : "";
                String page_id = (message.hasPageId()) ? message.getPageId() : "";
                String message_id = (message.hasMessageId()) ? message.getMessageId() : "";
                String message_text = (message.hasMessageText()) ? message.getMessageText() : "";
                String quick_reply_payload = (message.hasQuickReplyPayload()) ? message.getQuickReplyPayload() : "";
                Long timestamp = (message.hasTimestamp()) ? message.getTimestamp() : 0;
                HashMap<String, String> attachments = (message.hasAttachment()) ? (HashMap<String, String>) message.getAttachment() : new HashMap<String, String>();

                botPlatform.getLogger().info("User ID#:" + user_id);
                botPlatform.getLogger().info("Page ID#:" + page_id);
                botPlatform.getLogger().info("Message ID#:" + message_id);
                botPlatform.getLogger().info("Message Text#:" + message_text);
                botPlatform.getLogger().info("Quick Reply Payload#:" + quick_reply_payload);

                for (String attachment : attachments.values()) {
                    botPlatform.getLogger().info("Attachment#:" + attachment);
                }

                String text = message.getMessageText();
                MessageTemplate message_tpl = botPlatform.getBaseSender().getMessageTemplate();
                ButtonTemplate button_message_tpl = botPlatform.getBaseSender().getButtonTemplate();
                ListTemplate list_message_tpl = botPlatform.getBaseSender().getListTemplate();
                GenericTemplate generic_message_tpl = botPlatform.getBaseSender().getGenericTemplate();
                ReceiptTemplate receipt_message_tpl = botPlatform.getBaseSender().getReceiptTemplate();

                if( text.equals("text") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setMessageText("Hello World");
                    message_tpl.setNotificationType("REGULAR");
                    botPlatform.getBaseSender().send(message_tpl);

                }else if( text.equals("image") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("image", "http://techslides.com/demos/samples/sample.jpg", false);
                    message_tpl.setNotificationType("SILENT_PUSH");
                    botPlatform.getBaseSender().send(message_tpl);

                }else if( text.equals("file") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("file", "http://techslides.com/demos/samples/sample.pdf", false);
                    message_tpl.setNotificationType("NO_PUSH");
                    botPlatform.getBaseSender().send(message_tpl);

                }else if( text.equals("video") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("video", "http://techslides.com/demos/samples/sample.mp4", false);
                    botPlatform.getBaseSender().send(message_tpl);

                }else if( text.equals("audio") ){

                    message_tpl.setRecipientId(message.getUserId());
                    message_tpl.setAttachment("audio", "http://techslides.com/demos/samples/sample.mp3", false);
                    botPlatform.getBaseSender().send(message_tpl);

                }
                return "ok";
            }
            return "bla";
        });
    }

}
