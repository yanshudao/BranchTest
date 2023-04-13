package io.renren.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import io.renren.common.serializer.FastJson2JsonRedisSerializer;
import io.renren.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * Redis配置
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-70 19:22
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private RedisConnectionFactory factory;
    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
    @Bean
    @SuppressWarnings("rawtypes")
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            //* 重写生成key方法
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder(Constant.CACHE_NAMESPACE);
                CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                if (cacheable != null) {
                    String[] cacheNames = cacheable.value();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cachePut != null) {
                    String[] cacheNames = cachePut.value();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cacheEvict != null) {
                    String[] cacheNames = cacheEvict.value();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (cacheConfig != null && sb.toString().equals(Constant.CACHE_NAMESPACE)) {
                    String[] cacheNames = cacheConfig.cacheNames();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (sb.toString().equals(Constant.CACHE_NAMESPACE)) {
                    sb.append(o.getClass().getName());
                }
                sb.append(":");
                if (objects != null) {
                    if (objects.length == 1) {
                        if (objects[0] instanceof Number || objects[0] instanceof String
                                || objects[0] instanceof Boolean) {
                            sb.append(objects[0]);
                        } else {
                            try {
                                sb.append(objects[0].getClass().getMethod("getId").invoke(objects[0]));
                            } catch (Exception e) {
                                if (objects[0] instanceof Map && ((Map<?, ?>) objects[0]).get("id") != null) {
                                    sb.append(((Map<?, ?>) objects[0]).get("id"));
                                } else {
                                    sb.append(JSON.toJSON(objects[0]));
                                }
                            }
                        }
                    } else {
                        sb.append(StringUtils.join(objects, ","));
                    }
                } else {
                    sb.append(method.getName());
                }
                return sb.toString();
            }
        };
    }

}
