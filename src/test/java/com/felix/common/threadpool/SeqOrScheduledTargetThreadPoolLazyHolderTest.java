package com.felix.common.threadpool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeqOrScheduledTargetThreadPoolLazyHolderTest {

    @Test
    void testShutdownHookThread() {
        new SeqOrScheduledTargetThreadPoolLazyHolder();
    }

}