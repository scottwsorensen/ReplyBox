package com.replybox;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.replybox.question.IQuestionService;
import com.replybox.question.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTests {
	@Autowired
	private IQuestionService questionService;    

	@Test
	public void contexLoads() throws Exception {
		assert(questionService != null);
	}
	@Test
	public void getAllQuestionsTest() {
		List<Question> questions = questionService.getAllQuestions();
		assert(questions.get(0).getQuestionText().contains("On"));	
	}
	@Test
	public void questionServiceCRUDTest() {
		// Add question
		Question question = new Question();
		question.setCategoryId(1);
		question.setQuestionText("test");
		question.setTestData(1);
		boolean questionAdded = questionService.addQuestion(question);
		assert(questionAdded == true);
		// Add it again to make sure it can't be added twice
		boolean questionAddedAgain = questionService.addQuestion(question);
		assert(questionAddedAgain == false);
		// Update the question.
		question.setQuestionText("test update");
		questionService.updateQuestion(question);
		// Get question by questionId and test for update.
		question = questionService.getQuestionById(question.getQuestionId());
		assert(question.getQuestionText().contains("test update"));
		// Delete question
		if((questionAdded == true) || (questionAddedAgain == true)){
			questionService.deleteQuestion(question.getQuestionId());
			question = questionService.getQuestionById(question.getQuestionId());
			assert(question == null);
		} 
	}
}
