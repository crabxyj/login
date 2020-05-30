package cn.edu.zucc.login.common.error;

/**
 * @author crabxyj
 * @date 2020/5/30 13:50
 */
public enum  CommonErrors implements ServiceErrors{
    /**
     * 常见错误码
     */
    SUCCESS_CODE(0,"成功"),
    SYSTEM_ERROR(1000,"系统繁忙"),
    PARAM_ERROR(10001,"参数错误")
    ;

    private Integer code;
    private String msg;

    CommonErrors(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
