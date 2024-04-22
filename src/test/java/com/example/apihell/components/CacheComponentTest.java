package com.example.apihell.components;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CacheComponentTest {

        private final CacheComponent cache = new CacheComponent();

        @Test
        void testPutAndGet() {
            cache.put("key1", "value1");

            Object value = cache.get("key1");
            assertEquals("value1", value);
        }

        @Test
        void testRemove() {
            cache.put("key1", "value1");

            cache.remove("key1");

            Object value = cache.get("key1");
            assertNull(value);
        }

        @Test
        void testRemoveEldestEntry() {
            for (int i = 0; i < 10; i++) {
                cache.put("key" + i, "value" + i);
            }

            Object value = cache.get("key0");
            assertNull(value);
        }
    }
