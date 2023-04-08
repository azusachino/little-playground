package cn.az.code.mvc.controller;

import cn.az.code.mvc.common.RestResponse;
import cn.az.code.mvc.dto.UserDto;
import cn.az.code.mvc.entity.UserInfo;
import cn.az.code.mvc.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author az
 * @since 09/04/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse list(@RequestBody UserDto userDto) {
        return RestResponse.ok(userService.selectPage(userDto));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse add(@RequestBody UserInfo userInfo) {
        int ret = userService.addUserInfo(userInfo);
        if (ret > 0) {
            return RestResponse.ok(ret);
        } else {
            return RestResponse.err(ret);
        }
    }
}
