package cn.az.code.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Github", url = "${api.github.host}")
public interface GithubFeignApi {

    @GetMapping("/repos/{owner}/{repo}/contributors")
    List<?> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
