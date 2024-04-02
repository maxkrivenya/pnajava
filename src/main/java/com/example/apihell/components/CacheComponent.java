package com.example.apihell.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.LinkedHashMap;

@Slf4j
@Component
public class CacheComponent {
    private static final Integer  MAX_CACHE_SIZE = 3;
    public final String CACHE_INFO_GET      = "CACHE TAKE   : ";
    public final String CACHE_INFO_REMOVE   = "CACHE REMOVE : ";
    public final String CACHE_INFO_PUT      = "CACHE PUT    : ";

    public final String multiCacheKey       = "list_";
    public final String studentCacheKey     = "student";
    public final String professorCacheKey   = "professor";
    public final String subjectCacheKey     = "subject";
    public final String markCacheKey        = "mark";
    public final String skipCacheKey        = "skip";
    public final String groupCacheKey       = "group";

    private final Map<String, Object> cache = new LinkedHashMap<>(MAX_CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            if(size() > MAX_CACHE_SIZE) {
                log.info(CACHE_INFO_REMOVE + eldest.getKey() + ":" + eldest.getValue());
                return true;
            }
            return false;
        }
    };

    public void put(String key, Object value) {
        log.info(CACHE_INFO_PUT + key);
        cache.put(key, value);
    }

    public Object get(String key) {
        log.info(CACHE_INFO_GET + key);
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void log(){
        for (Map.Entry<String, Object> mapElement :
                cache.entrySet()) {
            String key = mapElement.getKey();
            Object value = mapElement.getValue();
            log.info("key: '" + key + "', value: '" + value + "'");
        }
    }
}
