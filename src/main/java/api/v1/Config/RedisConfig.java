package api.v1.Config;

import api.v1.Email.Model.Auth.AuthResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public ReactiveRedisOperations<String, AuthResponse> redisOperations(ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<AuthResponse> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(AuthResponse.class);

        RedisSerializationContext<String, AuthResponse> context = RedisSerializationContext
                .<String, AuthResponse>newSerializationContext()
                .key(stringRedisSerializer).value(jackson2JsonRedisSerializer)
                .hashKey(stringRedisSerializer).hashValue(jackson2JsonRedisSerializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}