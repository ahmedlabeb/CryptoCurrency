
package com.bitoasis.cryptocurrencyservice.api.control.execption;

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
