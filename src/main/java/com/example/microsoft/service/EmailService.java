package com.example.microsoft.service;

import com.example.microsoft.dto.request.EmailRequest;

/**
 * @author Richa Pokhrel
 */
public interface EmailService {

    /**
     * Sends an email using Microsoft Graph API.
     *
     * @param emailRequest The recipient's and sender's information.
     */
    void sendEmail(EmailRequest emailRequest);
}
