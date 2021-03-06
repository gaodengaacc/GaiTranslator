package com.lyun.lawyer.model;

import com.lyun.api.ErrorParser;
import com.lyun.lawyer.api.API;
import com.lyun.lawyer.api.request.LoginBean;
import com.lyun.lawyer.api.response.LoginResponse;
import com.lyun.library.mvvm.model.Model;
import com.lyun.utils.MD5Util;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZHAOWEIWEI on 2016/12/21.
 */

public class LoginModel extends Model {

    public Observable<LoginResponse> login(String username, String password) {
        LoginBean bean = new LoginBean(username, MD5Util.getStringMD5(password));
        return parseAPIObservable(API.auth.login(bean).onErrorReturn(throwable -> ErrorParser.mockResult(throwable)))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }
}
