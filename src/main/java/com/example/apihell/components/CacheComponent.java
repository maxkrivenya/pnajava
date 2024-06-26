package com.example.apihell.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.LinkedHashMap;

@Slf4j
@Component
public class CacheComponent {
    private static final Integer  MAX_CACHE_SIZE    = 5;
    public static final String MULTI_CACHE_KEY      = "list_";
    public static final String STUDENT_CACHE_KEY    = "student";
    public static final String PROFESSOR_CACHE_KEY  = "professor";
    public static final String SUBJECT_CACHE_KEY    = "subject";
    public static final String MARK_CACHE_KEY       = "mark";
    public static final String SKIP_CACHE_KEY       = "skip";
    public static final String GROUP_CACHE_KEY      = "group";
    public static final String GET_ALL              = "getAll";

    private static final Map<String, Object> CACHE = new LinkedHashMap<>(MAX_CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            if(size() > MAX_CACHE_SIZE) {
                return true;
            }
            return false;
        }
    };

    public void put(String key, Object value) {
        CACHE.put(key, value);
    }

    public Object get(String key) {
        return CACHE.get(key);
    }

    public void remove(String key) {
        CACHE.remove(key);
    }

    public void log(){
        for (Map.Entry<String, Object> mapElement : CACHE.entrySet()) {
            String key = mapElement.getKey();
            Object value = mapElement.getValue();
            log.info("key: '" + key + "', value: '" + value + "'");
        }
    }
}
