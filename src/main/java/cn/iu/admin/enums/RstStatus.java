package cn.iu.admin.enums;

public enum RstStatus {
    ok(200, "ok", "恭喜你，操作成功"),
    iniCodeError(201, "bad", "注册码失败，请联系管理员"),
    noDataFound(103, "no data found", "对不起，没有找到相关数据"),
    noLogin(401, "no login", "对不起，请先登录"),
    noPermission(402, "no permission", "对不起，您没有权限操作"),
    serviceError(500, "serviceError", "服务内部错误");

    private int code;
    private String msg;
    private String desc;
    RstStatus(int code, String message, String desc) {
        this.code = code;
        this.msg = message;
        this.desc = desc;
    }
    RstStatus(int code) {
        this.code = code;

    }
    RstStatus(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
