package com.example.apihell.components;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.LinkedHashMap;

@Component
public class CacheComponent {
    private static final Integer  MAX_CACHE_SIZE = 50;
    private final Map<String, Object> cache = new LinkedHashMap<>(MAX_CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }
}
