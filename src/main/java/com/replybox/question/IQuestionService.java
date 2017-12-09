package com.replybox.question;

import java.util.List;
import com.replybox.question.Question;
public interface IQuestionService {
     List<Question> getAllQuestions();
     Question getQuestionById(Integer questionId);
     boolean addQuestion(Question question);
     void updateQuestion(Question question);
     void deleteQuestion(Integer questionId);
} 