package dev.kunie.oauth2.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2/token")
public class TokenController {

    private final ClientRegistrationRepository clientRegistrationRepository;

    @PostMapping
    public ResponseEntity<?> token(
            @RequestParam String grant_type
            , @RequestParam String code
            , @RequestParam String redirect_uri
            , @RequestParam String client_id
            , @RequestParam String code_verifier
    ) {
        ClientRegistration local = clientRegistrationRepository.findByRegistrationId(client_id);

        ClientRegistration.ProviderDetails providerDetails = local.getProviderDetails();
        String tokenUri = providerDetails.getTokenUri();
        String client_secret = local.getClientSecret();

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", grant_type);
        data.add("code", code);
        data.add("redirect_uri", redirect_uri);
        data.add("client_id", client_id);
        data.add("code_verifier", code_verifier);
        data.add("client_secret", client_secret);

        return restTemplate.postForEntity(tokenUri, data, String.class);
    }
}
