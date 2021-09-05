/*
 * Copyright 2021 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.bitoasis.cryptodemo.api.control.execption;

import lombok.Getter;

/*
*
* @author Ahmed Labib
*/
@Getter
public class CryptoServiceException extends RestException{

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3102904573968573206L;

    /** The Crypto Service errors. */
    private CryptoServiceError cryptoServiceError;

    /**
     * Instantiates a new CryptoService exception.
     *
     * @param cryptoServiceError the CryptoService error
     */
    public CryptoServiceException(final CryptoServiceError cryptoServiceError) {
        super(cryptoServiceError);
        this.cryptoServiceError = cryptoServiceError;
    }
}
