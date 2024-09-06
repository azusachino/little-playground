package cn.az.webflux.agent;

import org.agrona.concurrent.AgentRunner;
import org.agrona.concurrent.BusySpinIdleStrategy;
import org.junit.jupiter.api.Test;

public class AgentRunnerTests {

    @Test
    void run_agent() {
        final var agent = new SampleAgent();
        final var runner = new AgentRunner(new BusySpinIdleStrategy(), e -> {
            e.printStackTrace();
        }, null, agent);

        AgentRunner.startOnThread(runner);

    }
}
