package com.bitoasis.cryptodemo.api.control.execption;

import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CryptoServiceErrorResponse {
	 /** The error message . */
    private String errorMessage;

    /** The error Code . */
    private String error;

    /** The status. */
    private String status;
    /**
     * Request time Stamp
     */
    private Long timestamp;
}
