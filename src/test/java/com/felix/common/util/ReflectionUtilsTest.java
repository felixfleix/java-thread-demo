package com.felix.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {
    @Test
    void getNickCallClassMethod() {
        String nickCallClassMethod = ReflectionUtils.getNickCallClassMethod();
        assertEquals("DirectMethodHandleAccessor.invoke", nickCallClassMethod);
    }

}