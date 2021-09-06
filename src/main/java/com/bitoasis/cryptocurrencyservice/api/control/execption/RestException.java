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

import lombok.Getter;

@Getter
public class RestException extends RuntimeException{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The crypto Service Error errors. */
    private RestError restError;

    /**
     * Instantiates a new crypto Service exception.
     *
     * @param resError
     */
    public RestException(final RestError restError) {
        super(restError.desceription());
        this.restError = restError;
    }
}
