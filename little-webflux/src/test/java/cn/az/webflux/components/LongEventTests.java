package cn.az.webflux.components;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @author haru
 * @date 2024/07/12 16:13
 */
class LongEventTests {

    @Test
    void test() throws Exception {
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor =
            new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith((event, sequence, endOfBatch) ->
                System.out.println("Event: " + event))
            // clear status
            .then((a, b, c) -> a.clear());
        disruptor.start();


        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(1);
        }
        Assertions.assertNotNull(ringBuffer);
    }

    public static class LongEvent {
        public long value;

        public void set(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "LongEvent{" + "value=" + value + '}';
        }

        public void clear() {
            this.value = 0L;
        }
    }

}
