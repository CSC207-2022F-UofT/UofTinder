package com.group80.uoftinder.create_account_use_case;

import java.util.LinkedHashMap;

public class CreateAccountPresenter {
    public String account_error(String email, String password1, String password2) {
        CreateAccountInteractor interactor = new CreateAccountInteractor();
        if(!(interactor.checkPasswords(password1, password2)))
            return "Your passwords do not match!";
        if(!(interactor.checkEmail(email)))
            return "This email already has an account";
        return "Sorry something is wrong, please try again later :(";

    }

    public String[] academicQuestionnaire() {
//        LinkedHashMap<String, String[]> questionnaire = new LinkedHashMap<>();
//        questionnaire.put("What is your year of Study?", new String[]{"1", "2", "3", "4",
//                "Graduate"});
//        questionnaire.put("What is your main major?", new String[]{"Computer Science",
//                "Life Sciences", "Mathematical and Physical Sciences", "Social Sciences/Humanities",
//                "Rotman/Economics", "Other"});
//        questionnaire.put("What campus are you on?", new String[]{"St. George", "Mississauga",
//                "Scarborough"});
//        return questionnaire;
        return new String[]{"What is your year of study?", "What is your main major?,",
                "What campus are you on?"};
    }

    public String[][] academicAnswers() {
        return new String[][] {{"1", "2", "3", "4", "Graduate"},
                {"Computer Science", "Life Sciences", "Mathematical and Physical Sciences",
                "Social Sciences/Humanities", "Rotman/Economics", "Other"},
                {"St. George", "Mississauga", "Scarborough"} };
//        answers = [{1}, {0}, {0}]
    }
}
