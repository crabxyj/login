package cn.edu.zucc.login.common.mybatis;

import cn.edu.zucc.login.common.logs.MybatisSqlLogInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author crabxyj
 * @date 2020/6/2 17:07
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * mybatis 日志记录插件
     * 暂时未完成
     * 找个梳理信息的工具类，解析请求参数等信息
     */
//    @Bean
    public MybatisSqlLogInterceptor mybatisSqlLogInterceptor(){
        return new MybatisSqlLogInterceptor();
    }

    /**
     * 分页插件
     * 必须放到所有插件的最后面
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
