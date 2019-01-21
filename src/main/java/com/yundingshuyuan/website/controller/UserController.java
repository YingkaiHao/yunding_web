/**
 * FileName:UserController
 * author:leeyf
 * Date:19-1-20 上午11:03
 * Description:用户控制
 */
package com.yundingshuyuan.website.controller;

import com.yundingshuyuan.website.controller.support.BaseController;
import com.yundingshuyuan.website.form.UserLoginForm;
import com.yundingshuyuan.website.form.UserRegisterForm;
import com.yundingshuyuan.website.service.UserService;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.Valid;

@Api(tags = "用户接口")
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultWrapper register(@Valid/*校验表单*/ UserRegisterForm userRegisterForm,
                                  BindingResult bindingResult){
        /*对表单验证或者赋值*/
        validateParams(bindingResult);

        userService.registerUser(userRegisterForm);

        return ResultWrapper.success();
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResultWrapper<String> login(UserLoginForm userLoginForm,BindingResult bindingResult){

        validateParams(bindingResult);

        String accessToken = userService.auth(userLoginForm);

        return ResultWrapper.success(accessToken);

    }


}
