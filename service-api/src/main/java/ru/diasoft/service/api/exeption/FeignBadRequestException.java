package ru.diasoft.service.api.exeption;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

public class FeignBadRequestException extends HystrixBadRequestException {

    private int status;
    private HttpHeaders headers;
    private String body;

    public FeignBadRequestException() {
        super("Bad request");
    }

    public FeignBadRequestException(int status, HttpHeaders headers, String body) {
        super("Bad request");
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeignBadRequestException that = (FeignBadRequestException) o;
        return status == that.status &&
                Objects.equals(headers, that.headers) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, headers, body);
    }
}
