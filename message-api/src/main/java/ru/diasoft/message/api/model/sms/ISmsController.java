package ru.diasoft.message.api.model.sms;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

public interface ISmsController<T extends ISms> {

    @PostMapping(value = "/sms/send", produces = MediaType.APPLICATION_JSON_VALUE)
    void send(@RequestBody T obj);

    @PostMapping(value = "/sms/cost", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal cost(@RequestBody T obj);

    @GetMapping(value = "/sms/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal balance();

}
