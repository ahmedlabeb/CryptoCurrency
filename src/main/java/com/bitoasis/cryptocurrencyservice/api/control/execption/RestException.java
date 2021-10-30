
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
