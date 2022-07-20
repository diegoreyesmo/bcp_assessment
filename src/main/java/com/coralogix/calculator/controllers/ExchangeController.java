package com.coralogix.calculator.controllers;

import com.coralogix.calculator.entities.ExchangeRate;
import com.coralogix.calculator.services.ExchangeService;
//import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(path = "/exchange")
//@Log4j2  // TODO: usar lombok
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;
    private static final Logger log = LoggerFactory.getLogger(ExchangeController.class); // TODO: declaración inecesaria si @Log4j2 funciona

    @GetMapping(path = "/getExchangeRate", produces = "application/json")
    public @ResponseBody
    ExchangeRate getExchangeRate(@RequestParam @NotEmpty String originCurrency, @RequestParam @NotEmpty String finalCurrency) {
        log.info(String.format("/getExchangeRate request:[originCurrency:%s,finalCurrency:%s]", originCurrency, finalCurrency));
        ExchangeRate exchangeRate = exchangeService.getExchangeRate(originCurrency, finalCurrency);
        log.info(String.format("/getExchangeRate response:%s", exchangeRate.toString()));
        return exchangeRate;
    }

    @GetMapping(path = "/getAllExchangeRate", produces = "application/json")
    public @ResponseBody
    Iterable<ExchangeRate> getAllExchangeRate() {
        log.info("/getAllExchangeRate request");
        Iterable<ExchangeRate> allExchangeRate = exchangeService.getAllExchangeRate();
        log.info(String.format("/getAllExchangeRate response:%s", allExchangeRate.toString()));
        return allExchangeRate;
    }
}
