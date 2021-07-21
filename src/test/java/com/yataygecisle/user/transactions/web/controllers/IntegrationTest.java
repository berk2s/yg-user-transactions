package com.yataygecisle.user.transactions.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yataygecisle.user.transactions.web.models.AddBasketItemDto;
import com.yataygecisle.user.transactions.web.models.BasketDto;
import com.yataygecisle.user.transactions.web.models.BasketItemDto;
import com.yataygecisle.user.transactions.web.models.CreateBasketDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.UUID;

@Getter
@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    ObjectMapper objectMapper;

    public String createAccessToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/spring-rest/foos";
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("client_id", "clientWithSecret");
        map.add("client_secret", "clientSecret");
        map.add("grant_type", "password");
        map.add("username", "username");
        map.add("password", "password");
        map.add("scope", "openid");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);


        ResponseEntity<String> response
                = restTemplate.postForEntity("http://localhost:8080/token",
                request,
                String.class);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode token = root.path("access_token");
        return token.asText();
    }

    public String parseTokenToUserId(String token) throws JsonProcessingException {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JsonNode root = objectMapper.readTree(payload);
        JsonNode sub = root.path("sub");

        return sub.asText();
    }

    protected BasketDto triggerToCreatingBasket(String token, String userId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity entity = new HttpEntity(headers);


        ResponseEntity<BasketItemDto[]> basketItemResponse = restTemplate
                .exchange("http://localhost:8081/basketitem",
                        HttpMethod.GET,
                        entity,
                        BasketItemDto[].class);

        BasketItemDto[] basketItemDtos = basketItemResponse.getBody();

        AddBasketItemDto addBasketItemDto = new AddBasketItemDto();
        addBasketItemDto.setBasketItemId(basketItemDtos[0].getBasketItemId());

        CreateBasketDto createBasketDto = new CreateBasketDto();
        createBasketDto.setOwnerId(userId);
        createBasketDto.setBasketName("my basket");
        createBasketDto.getBasketItems().add(addBasketItemDto);

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(createBasketDto), headers);

        ResponseEntity<BasketDto> response
                = restTemplate.postForEntity("http://localhost:8081/basket",
                request,
                BasketDto.class);

        return response.getBody();
    }

}
