package service.blog.shared.service;

import core.service.service.CRUDServiceConst;

public class BlogServiceConst extends CRUDServiceConst {
	public static final String SERVICE_CONFIG_NAME = "service.blog";
	
	public static final String CATEGORY_SERVICE = "/category";
	public static final String QUESTION_SERVICE = "/question";
	public static final String QUESTION_COMMENT_SERVICE = "/question/comment";
	public static final String ANSWER_SERVICE = "/answer";
	public static final String ANSWER_COMMENT_SERVICE = "/answer/comment";
	
	public static final String PARAM_CATEGORY_ID = "categoryid";

	public static final String ADD_LIKE = "/like/{id}";
	public static final String UPDATE_CONTENT = "/updatecontent/{id}";
}
