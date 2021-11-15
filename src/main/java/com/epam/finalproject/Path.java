package com.epam.finalproject;

public final class Path {
    //pages
    public static final String PAGE_LOGIN = "/index.jsp";
    public static final String PAGE_REGISTRATION = "/registration.jsp";
    public static final String PAGE_MAIN = "/WEB-INF/jsp/main.jsp";
    public static final String PAGE_QUIZZES = "/WEB-INF/jsp/quizzes.jsp";
    public static final String PAGE_EDIT_SUBJECT = "/WEB-INF/jsp/admin/editSubject.jsp";
    public static final String PAGE_ADD_NEW_SUBJECT = "/WEB-INF/jsp/admin/addNewSubject.jsp";
    public static final String PAGE_ADD_NEW_QUIZ = "/WEB-INF/jsp/admin/addNewQuiz.jsp";
    public static final String PAGE_QUESTION_MANAGER = "/WEB-INF/jsp/admin/questionManagerPage.jsp";
    public static final String PAGE_QUESTION_EDIT_FORM = "/WEB-INF/jsp/admin/questionEditForm.jsp";
    public static final String PAGE_EDIT_QUIZ = "/WEB-INF/jsp/admin/editQuiz.jsp";





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



}
