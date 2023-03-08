package client;

import config.NbpConfig;
import domain.dto.NbpResponseDto;
import enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class NbpApiClient {

    private final NbpConfig nbpConfig;

    private final RestTemplate restTemplate;

    public double getExchangeRate(Currency currency) {

        if (currency.equals(Currency.PLN)) {
            return 1;
        } else {
            URI uri = UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + currency)
                    .build()
                    .encode()
                    .toUri();

            NbpResponseDto nbpResponseDto = restTemplate.getForObject(uri, NbpResponseDto.class);
            assert nbpResponseDto != null;
            return nbpResponseDto.getRates().get(0).getMid();
        }
    }
}
