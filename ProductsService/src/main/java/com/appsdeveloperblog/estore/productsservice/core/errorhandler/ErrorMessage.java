package com.appsdeveloperblog.estore.productsservice.core.errorhandler;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorMessage {
    private final Date timeStamp;
    private final String errorMessage;
}
