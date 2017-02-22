package com.lyun.lawyer.api.request;

import com.lyun.api.request.BaseRequest;

/**
 * @author 赵尉尉
 * @date 2016/12/20
 */

public class LoginBean extends BaseRequest {

    private String password;
    private String name;
    private String userType = "1";

    public LoginBean(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
