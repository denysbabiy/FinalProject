package com.epam.finalproject;

public final class Path {




    private Path(){
    }
    //pages
    public static final String PAGE_LOGIN = "/index.jsp";
    public static final String PAGE_BLOCKED = "/blockedPage.jsp";
    public static final String PAGE_NOT_LOGIN = "/notLogin.jsp";
    public static final String PAGE_REGISTRATION = "/registration.jsp";
    public static final String PAGE_MAIN = "/WEB-INF/jsp/main.jsp";
    public static final String PAGE_QUIZZES = "/WEB-INF/jsp/quizzes.jsp";
    public static final String PAGE_EDIT_SUBJECT = "/WEB-INF/jsp/admin/editSubject.jsp";
    public static final String PAGE_ADD_NEW_SUBJECT = "/WEB-INF/jsp/admin/addNewSubject.jsp";
    public static final String PAGE_ADD_NEW_QUIZ = "/WEB-INF/jsp/admin/addNewQuiz.jsp";
    public static final String PAGE_QUESTION_MANAGER = "/WEB-INF/jsp/admin/questionManagerPage.jsp";
    public static final String PAGE_QUESTION_EDIT_FORM = "/WEB-INF/jsp/admin/questionEdit.jsp";
    public static final String PAGE_QUESTION_ADD_FORM = "/WEB-INF/jsp/admin/addQuestion.jsp";
    public static final String PAGE_EDIT_QUIZ = "/WEB-INF/jsp/admin/editQuiz.jsp";
    public static final String PAGE_TESTING = "/WEB-INF/jsp/testingPage.jsp";
    public static final String PAGE_TEST_RESULT = "/WEB-INF/jsp/result.jsp";
    public static final String PAGE_PROFILE = "/WEB-INF/jsp/my-profile.jsp";
    public static final String PAGE_QUIZ_RESULT_STATISTIC = "/WEB-INF/jsp/quiz-result-statistic.jsp";
    public static final String PAGE_EDIT_PERSONAL_INFORMATION_FORM = "/WEB-INF/jsp/edit-personal-information.jsp";
    public static final String PAGE_EDIT_PASS_FORM = "/WEB-INF/jsp/edit-pass-form.jsp";
    public static final String PAGE_USER_MANAGER = "/WEB-INF/jsp/admin/user-manager.jsp";
    public static final String PAGE_USER_EDIT_FORM ="/WEB-INF/jsp/admin/edit-user-form.jsp" ;
    public static final String PAGE_ERROR = "/error.jsp";






    //commands
    public static final String COMMAND_SHOW_MAIN_PAGE = "/controller?command=showMainPage";
    public static final String COMMAND_LOGIN = "/controller?command=login";
    public static final String COMMAND_REGISTRATION = "/controller?command=registration";
    public static final String COMMAND_SHOW_QUIZZES = "/controller?command=showQuizzes";
    public static final String COMMAND_LOGOUT = "/controller?command=logout";
    public static final String COMMAND_SHOW_SUBJECT_EDIT_FORM = "/controller?command=showSubjectEditForm";
    public static final String COMMAND_ADD_NEW_SUBJECT = "/controller?command=addNewSubject";
    public static final String COMMAND_UPDATE_SUBJECT = "/controller?command=updateSubject";
    public static final String COMMAND_SHOW_QUESTION_MANAGER = "/controller?command=showQuestionManager";
    public static final String COMMAND_SHOW_QUESTION_EDIT_FORM = "/controller?command=showQuestionEditForm";
    public static final String COMMAND_SHOW_QUIZ_RESULT = "/controller?command=showQuizResult";
    public static final String COMMAND_SHOW_MY_PROFILE="/controller?command=showMyProfile";
    public static final String COMMAND_SHOW_EDIT_PASS_FORM="/controller?command=showEditPassForm";
    public static final String COMMAND_EDIT_PASS = "/controller?command=editPass";
    public static final String COMMAND_SHOW_EDIT_PERSONAL_INFORMATION_FORM="/controller?command=showEditPersonalInformationForm";
    public static final String COMMAND_SHOW_USER_MANAGER = "/controller?command=showUserManager";
    public static final String COMMAND_SHOW_EDIT_USER_FORM= "/controller?command=showEditUserForm";






}
