package api.v1.Config;

import api.v1.Email.Model.Auth.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {
    @Bean
    public ReactiveRedisOperations<Integer, Auth> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Auth> serializer = new Jackson2JsonRedisSerializer<>(Auth.class);

        RedisSerializationContext.RedisSerializationContextBuilder<Integer, Auth> context =
                RedisSerializationContext.newSerializationContext(serializer);

        return new ReactiveRedisTemplate<>(factory, context.build());
    }
}