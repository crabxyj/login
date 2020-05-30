package cn.edu.zucc.login.common.error;

/**
 * @author crabxyj
 * @date 2020/5/30 13:48
 */
public interface ServiceErrors {
    /**
     * 获取错误码
     * @return Integer
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return String
     */
    String getMsg();
}
