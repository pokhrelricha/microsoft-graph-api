package com.example.microsoft.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Richa Pokhrel
 */
@Getter
@Setter
public class EmailRequest implements Serializable {

    private String subject;
    private String recipient;
    private String bodyContent;
}
