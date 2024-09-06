package cn.az.webflux.aeron;

import org.agrona.concurrent.AgentRunner;
import org.agrona.concurrent.BusySpinIdleStrategy;
import org.agrona.concurrent.ShutdownSignalBarrier;

import io.aeron.Aeron;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;

public class IPCSetup {

    public static void main(String[] args) {
        var channel = "aeron:ipc";
        var stream = 10;
        var sendCnt = 1_000_000;
        var sendIdle = new BusySpinIdleStrategy();
        var rcvIdle = new BusySpinIdleStrategy();
        var sigBarrier = new ShutdownSignalBarrier();

        // construct the media driver
        var mediaCtx = new MediaDriver.Context()
                .dirDeleteOnStart(true)
                .dirDeleteOnShutdown(true)
                .sharedIdleStrategy(new BusySpinIdleStrategy())
                .threadingMode(ThreadingMode.SHARED);
        var mediaDriver = MediaDriver.launchEmbedded(mediaCtx);

        // construct the aeron, pointing at the media driver's folder
        var aeronCtx = new Aeron.Context()
                .aeronDirectoryName(mediaDriver.aeronDirectoryName());
        var aeron = Aeron.connect(aeronCtx);

        // construct the pub/sub
        var sub = aeron.addSubscription(channel, stream);
        var pub = aeron.addPublication(channel, stream);

        // construct the agents
        var sendAgent = new SendAgent(pub, sendCnt);
        var rcvAgent = new ReceiveAgent(sub, sigBarrier, sendCnt);

        // runners
        var sendRun = new AgentRunner(sendIdle, e -> e.printStackTrace(), null, sendAgent);
        var rcvRun = new AgentRunner(rcvIdle, e -> e.printStackTrace(), null, rcvAgent);

        System.out.println("starting.....");

        AgentRunner.startOnThread(sendRun);
        AgentRunner.startOnThread(rcvRun);

        sigBarrier.await();

        rcvRun.close();
        sendRun.close();
        aeron.close();
        mediaDriver.close();
    }
}
