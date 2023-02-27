package com.example.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisClientConfig {
    @Bean
    RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.1.62.165:6379").setPassword("Redis@456_$%^");
        RedissonClient redissonClient = Redisson.create(config);
        redissonClient.getSemaphore("limitTest").trySetPermits(3);
        return redissonClient;
    }
}
