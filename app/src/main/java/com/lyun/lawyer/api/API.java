package com.lyun.lawyer.api;

import com.lyun.api.APIBase;
import com.lyun.lawyer.api.service.AuthService;

/**
 * 网络接口服务集合
 *
 * @author 赵尉尉
 * @date 2016/12/20
 */
public class API extends APIBase {

    public static AuthService auth = create(AuthService.class);

}
