package com.example.microsoft.service;

import com.example.microsoft.dto.request.EmailRequest;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.GraphServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Richa Pokhrel
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private GraphServiceClient<?> graphServiceClient;

    @Override
    public void sendEmail(EmailRequest emailRequest) {
        // Create the message body
        ItemBody body = new ItemBody();
        body.contentType = BodyType.TEXT;
        body.content = emailRequest.getBodyContent();

        // Create the recipient
        Recipient recipient = new Recipient();
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.address = emailRequest.getRecipient();
        recipient.emailAddress = emailAddress;

        // Create the message
        Message message = new Message();
        message.subject = emailRequest.getSubject();
        message.body = body;
        message.toRecipients = Collections.singletonList(recipient);

        // Send the email
        graphServiceClient.me().sendMail(UserSendMailParameterSet.newBuilder()
                        .withMessage(message)
                        .withSaveToSentItems(false)
                        .build())
                .buildRequest()
                .post();
    }
}
