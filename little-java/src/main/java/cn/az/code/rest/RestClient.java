package cn.az.code.rest;

import org.springframework.web.client.RestTemplate;

/**
 * @author az
 * @date 2020/3/7
 */
public class RestClient {

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();

        String test = template.getForObject("", String.class);
    }
}
