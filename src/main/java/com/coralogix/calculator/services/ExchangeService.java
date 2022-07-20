package com.coralogix.calculator.services;

import com.coralogix.calculator.entities.ApiResponse;
import com.coralogix.calculator.entities.ExchangeRate;
import com.coralogix.calculator.mappers.ApiMapper;
import com.coralogix.calculator.repositories.ExchangeRepository;
//import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

//@Log4j // TODO: usar lombok
@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ApiMapper mapper;

    @Value("${url.api-currency:http://localhost:8080/fixer/latest}")
    private String apiCurrencyUrl;

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(ExchangeService.class); // TODO: declaración inecesaria si @Log4j2 funciona

    public ExchangeRate getExchangeRate(String originCurrency, String finalCurrency) {
        try {
            ExchangeRate byOriginCurrencyAndFinalCurrency = exchangeRepository.findByOriginCurrencyAndFinalCurrency(originCurrency, finalCurrency);
            if (byOriginCurrencyAndFinalCurrency == null) {
                log.info("registro no encontrado en DB local");
                String urlWithParams = String.format("%s?base=%s&symbols=%s", apiCurrencyUrl, originCurrency, finalCurrency);
                log.info(String.format("consulando servicio %s", urlWithParams));
                ApiResponse response = WebClient.create()
                        .get()
                        .uri(urlWithParams)
                        .retrieve()
                        .bodyToMono(ApiResponse.class).onErrorResume(e -> {
                            log.error(e);
                            return null;
                        }).block(); // es necesario esperar respuesta si queremos devolver registro obtenido de forma síncrona
                log.info(String.format("respuesta obtenida:%s", response.toString()));
                if (response.getSuccess()) {
                    ExchangeRate target = mapper.simpleMapper(response);
                    target.setFinalCurrency(finalCurrency);
                    target.setValue(response.getRates().get(finalCurrency));
                    log.info("guardando registro obtenido en H2");
                    exchangeRepository.save(target);
                }


            }

            return exchangeRepository.findByOriginCurrencyAndFinalCurrency(originCurrency, finalCurrency);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Iterable<ExchangeRate> getAllExchangeRate() {
        return exchangeRepository.findAll();
    }
}
