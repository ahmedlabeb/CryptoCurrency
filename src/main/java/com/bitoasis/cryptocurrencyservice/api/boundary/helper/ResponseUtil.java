package com.bitoasis.cryptocurrencyservice.api.boundary.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public class ResponseUtil {

	private ResponseUtil() {
    }

    public static <X> ResponseEntity<X> wrapOrNotFound(X maybeResponse) {
        return wrapOrNotFound(Optional.ofNullable(maybeResponse), null);
    }

    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
        return maybeResponse.map((response) -> (ResponseEntity.ok().headers(header)).body(response)).orElse(new ResponseEntity<X>(HttpStatus.NOT_FOUND));
    }
}
