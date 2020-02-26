package kula.marcin.synapse.demo.answer;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
public class AnswerComponent {

    @Bean
    @SessionScope
    public Map<Integer, Answer> getAnswers() {
        return new LinkedHashMap<Integer, Answer>();
    }

}
