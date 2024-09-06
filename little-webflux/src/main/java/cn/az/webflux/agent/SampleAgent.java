package cn.az.webflux.agent;

import org.agrona.concurrent.Agent;

public class SampleAgent implements Agent {

    @Override
    public int doWork() throws Exception {
        var cnt = 0;
        System.out.println("work done");
        return cnt;
    }

    @Override
    public String roleName() {
        return "sample-agent";
    }
}
