package cn.edu.zucc.login.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author crabxyj
 * @date 2020/5/10 12:11
 */
@Data
@Accessors(chain = true)
@TableName("account")
public class BeanAccount {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField
    @NotBlank(message = "用户名不能为空")
    private String account;
    @TableField
    @NotBlank(message = "密码不能为空")
    private String password;

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BeanAccount){
            BeanAccount one = (BeanAccount) obj;
            return one.getId() == this.id;
        }
        return false;
    }
}
