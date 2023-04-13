package io.renren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .maxAge(3600);
    }
    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        List<String> allowedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
        List<String> allowedOrigins = Arrays.asList("http://localhost:4000","http://219.155.7.244:19080","https://fxself.crtvup.com.cn","https://fx.crtvup.com.cn","https://fxtest.crtvup.com.cn");

        //允许HTTP请求中的携带哪些Header信息
        config.setAllowedHeaders(allowedHeaders);

        //开放哪些Http方法，允许跨域访问
        config.setAllowedMethods(allowedMethods);

        //开放哪些ip、端口、域名的访问权限，星号表示开放所有域,这里允许8101的应用访问
        config.setAllowedOrigins(allowedOrigins);

        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息)
        config.setExposedHeaders(exposedHeaders);

        //是否允许发送Cookie信息
        config.setAllowCredentials(true);

        //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);

    }
}