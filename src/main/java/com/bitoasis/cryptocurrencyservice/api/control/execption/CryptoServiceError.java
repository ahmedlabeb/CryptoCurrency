/*
 * Copyright 2021 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.bitoasis.cryptocurrencyservice.api.control.execption;

import org.springframework.http.HttpStatus;

/**
 * The Enum CryptoServiceError.
 *
 * @author Ahmed Labib
 */
public enum CryptoServiceError implements RestError {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Token is required"),

    INVALID_TOKEN_CONTEXT(HttpStatus.UNAUTHORIZED, "Required User token"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ""),

    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, ""),

    SERVICE_UN_AVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Crypto Service is unavailable"),

    USER_ALREADY_EXIST(HttpStatus.CONFLICT, "User Already exist");

    /**
     * Instantiates a new Crypto Service errors.
     *
     * @param httpStatus  the http status
     * @param description the description
     */
    private CryptoServiceError(final HttpStatus httpStatus, final String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }

    /**
     * The http status.
     */
    private HttpStatus httpStatus;

    /**
     * The description.
     */
    private String description;

    @Override
    public String error() {
        return this.name();
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    @Override
    public String desceription() {
        return this.description;
    }

    public RestException buildExcpetion() {
        return new CryptoServiceException(this);
    }

}