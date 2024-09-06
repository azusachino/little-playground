package cn.az.webflux.aeron;

import org.agrona.DirectBuffer;
import org.agrona.concurrent.Agent;
import org.agrona.concurrent.ShutdownSignalBarrier;

import io.aeron.Subscription;
import io.aeron.logbuffer.Header;

public class ReceiveAgent implements Agent {
    private final Subscription subscription;
    private final ShutdownSignalBarrier barrier;
    private final int sendCount;

    public ReceiveAgent(final Subscription subscription, final ShutdownSignalBarrier barrier, final int sendCount) {
        this.subscription = subscription;
        this.barrier = barrier;
        this.sendCount = sendCount;
    }

    @Override
    public int doWork() throws Exception {
        subscription.poll(this::handler, 100);
        return 0;
    }

    private void handler(final DirectBuffer buffer, final int offset, final int length, final Header header) {
        final int lastValue = buffer.getInt(offset);

        if (lastValue >= sendCount) {
            System.out.println("------- received: " + lastValue);
            barrier.signal();
        }
    }

    @Override
    public String roleName() {
        return "receiver";
    }
}
