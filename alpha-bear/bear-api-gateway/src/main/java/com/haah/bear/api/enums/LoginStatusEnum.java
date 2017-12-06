package com.haah.bear.api.enums;

public enum LoginStatusEnum {
    /**
     * 登入
     */
    LOGIN("LOGIN", 1),
    /**
     * 登出
     */
    LOGOUT("LOGOUT",0),
    /**
     * 冻结
     */
    FROZEN("FROZEN",2)
    ;

    private String desc;

    private Integer code;

    LoginStatusEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
