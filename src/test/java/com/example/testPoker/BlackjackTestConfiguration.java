package com.example.testPoker;

import com.example.testPoker.service.game.ScoreCalculator;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class BlackjackTestConfiguration {

    @Bean
    public ScoreCalculator scoreCalculator() {
        return Mockito.mock(ScoreCalculator.class);
    }
}
