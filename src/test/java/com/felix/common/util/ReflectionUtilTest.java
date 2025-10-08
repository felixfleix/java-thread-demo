package com.felix.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilTest {
    @Test
    void getNickCallClassMethod() {
        String nickCallClassMethod = ReflectionUtil.getNickCallClassMethod();
        assertEquals("DirectMethodHandleAccessor.invoke", nickCallClassMethod);
    }

}