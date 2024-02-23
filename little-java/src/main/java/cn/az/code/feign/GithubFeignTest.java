package cn.az.code.feign;

import java.util.List;

import feign.Feign;

public class GithubFeignTest {

    public static void main(String[] args) {
        GithubFeignApi api = Feign.builder()
                // .decoder(new Jackson2JsonDecoder())
                .target(GithubFeignApi.class, "https://api.github.com");

        List<?> list = api.contributors("OpenFeign", "feign");
        System.out.println(list.toString());
    }
}
