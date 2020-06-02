package cn.edu.zucc.login.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author crabxyj
 * @date 2020/6/2 19:24
 * 跨域访问配置
 * spring5 采用WebMvcConfigurer 而不是 WebMvcConfigurerAdapter
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 允许跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE");
    }

}
