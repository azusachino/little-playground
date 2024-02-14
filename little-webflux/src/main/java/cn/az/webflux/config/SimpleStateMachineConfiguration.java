package cn.az.webflux.config;

import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
@EnableStateMachineFactory
public class SimpleStateMachineConfiguration extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        states.withStates()
                .initial("SI")
                .end("SF")
                .states(Set.of("S1", "S2", "S3"));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions.withExternal()
                .source("SI").target("S1").event("E1")
                .and()
                .withExternal()
                .source("S1").target("S2").event("E2")
                .and()
                .withExternal()
                .source("S2").target("SF").event("END");
    }

}
