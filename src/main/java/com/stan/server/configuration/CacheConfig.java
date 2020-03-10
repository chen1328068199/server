package com.stan.server.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableCaching
@Configuration
public class CacheConfig {

//    @Bean(name="concurrentMapCacheManager")
//    public ConcurrentMapCacheManager cacheManager(ConcurrentMapCacheManager concurrentMapCacheManager) {
//        concurrentMapCacheManager
//    }
}