package com.replybox.question;

import java.util.List;
import com.replybox.question.Question;
public interface IQuestionDAO {
    List<Question> getAllQuestions();
    Question getQuestionById(Integer questionId);
    void addQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(Integer questionId);
    boolean questionExists(String questionText, Integer categoryId);
} 
