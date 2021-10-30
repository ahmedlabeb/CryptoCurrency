
package com.bitoasis.cryptocurrencyservice.api.control.execption;

import org.springframework.http.HttpStatus;

public interface RestError {

	String error();

	HttpStatus httpStatus();

	String desceription();
}
