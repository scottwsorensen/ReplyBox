package com.replybox.selectsurveyform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.replybox.selectsurveyform.Greeting;
import com.replybox.survey.ISurveyService;

@Controller
public class SelectSurveyFormController {
	@Autowired
	private ISurveyService surveyService;

    @GetMapping("/selectsurvey")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/selectsurvey")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
    	// only process selected survey
    	int numSurveysProcessed = surveyService.processSurveys();
        return "result";
    }

}