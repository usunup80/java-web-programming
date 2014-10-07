package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.Controller;

public class AddAnswerController implements Controller{
	
	private AnswerDao AnswerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		Answer answer = new Answer(writer, contents, questionId);
		
		AnswerDao.insert(answer);
		questionDao.addCountOfComment(questionId);
		
		return "api";
	}

}
