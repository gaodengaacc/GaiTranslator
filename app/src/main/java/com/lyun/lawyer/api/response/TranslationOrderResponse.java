package com.lyun.lawyer.api.response;

/**
 * Created by ZHAOWEIWEI on 2017/2/21.
 */

public class TranslationOrderResponse {

    /**
     * username : 13838502074
     * userorderid : 8c8754ef-349c-4b1c-a777-c45ca47a797e
     * ordertime : 2017-02-20 16:23:45
     * ordertype : 图文
     * id : 161
     */

    private String username;
    private String userorderid;
    private String ordertime;
    private String ordertype;
    private String language;
    private int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserorderid() {
        return userorderid;
    }

    public void setUserorderid(String userorderid) {
        this.userorderid = userorderid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
