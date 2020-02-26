package kula.marcin.synapse.demo.answer;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Answer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String question;

    private String selectedAnswer;

    private String correctAnswer;

    public Answer(String question, String selectedAnswer, String correctAnswer) {
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
    }

    public Answer() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Answer [questionId=" + question + ", selectedAnswer=" + selectedAnswer
                + ", correctAnswer=" + correctAnswer + "]";
    }
}
