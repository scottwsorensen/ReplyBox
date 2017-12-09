package com.replybox.question;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.replybox.question.IQuestionDAO;
import com.replybox.question.Question;
@Service
public class QuestionService implements IQuestionService {
	@Autowired
	private IQuestionDAO questionDAO;
	@Override
	public Question getQuestionById(Integer questionId) {
		Question obj = questionDAO.getQuestionById(questionId);
		return obj;
	}	
	@Override
	public List<Question> getAllQuestions(){
		return questionDAO.getAllQuestions();
	}
	@Override
	public synchronized boolean addQuestion(Question question){
                if (questionDAO.questionExists(question.getQuestionText(), question.getCategoryId())) {
    	            return false;
                } else {
    	            questionDAO.addQuestion(question);
    	            return true;
                }
	}
	@Override
	public void updateQuestion(Question question) {
		questionDAO.updateQuestion(question);
	}
	@Override
	public void deleteQuestion(Integer questionId) {
		questionDAO.deleteQuestion(questionId);
	}
} 