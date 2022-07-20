package com.coralogix.calculator.repositories;

import com.coralogix.calculator.entities.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long> {
    ExchangeRate findByOriginCurrencyAndFinalCurrency(String originCurrency, String finalCurrency);

}


