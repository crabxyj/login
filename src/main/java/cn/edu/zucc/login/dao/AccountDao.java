package cn.edu.zucc.login.dao;

import cn.edu.zucc.login.pojo.BeanAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author crabxyj
 * @date 2020/5/10 12:20
 */
@Mapper
@Repository("accountDao")
public interface AccountDao extends BaseMapper<BeanAccount> {

}
