package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;
import java.util.HashSet;

public class UserScoreCalculator {
    private User currentUser; // user.getAnswers(), user.getAnswerSchema()
    private int answerLen;
    private HashSet<Integer>[] userAnswers; // [{0, 2, 3}, {1, 3}, ...]
    private int[] answerSchema; // [5, 7, 2, 4]

    public UserScoreCalculator(User currentUser, CreateAccountInteractor cai) {
        this.currentUser = currentUser;
        this.userAnswers = this.currentUser.getAnswers();
        this.answerLen = this.userAnswers.length;
        this.answerSchema = cai.getAnswerSchema();
    }

    public boolean[] getIsMultiSelectArray() {
        boolean[] isMultiSelect = new boolean[answerLen];
        for (int i = 0; i < answerLen; i ++) {
            isMultiSelect[i] = userAnswers[i].size() > 1;
        }
        return isMultiSelect;
    }

    public int[] getAnswerSchema() {
        return answerSchema;
    }
    // order questions by importance?


    public int generateCompatibilityScore() {
        StringBuilder binaryScore = new StringBuilder();
        for (int i = 0; i < userAnswers.length; i++) {
            String bundledBinary = answerSetToBinary(userAnswers[i], answerSchema[i]);
            binaryScore.append(bundledBinary);
        }
        System.out.println("binaryScore: " + binaryScore.toString());
        return Integer.parseInt(binaryScore.toString(), 2);
    }

    private String answerSetToBinary(HashSet<Integer> currentSet, int numOptions) {
        StringBuilder binarySection = new StringBuilder();
        if (currentSet.size() > 1) {
            // length of binary section equals number of options
            for (int curr = 0; curr < numOptions; curr++) {
                if (currentSet.contains(curr)) {
                    binarySection.append("1");
                } else {
                    binarySection.append("0");
                }
            }
            return binarySection.toString();
        } else {
            // length of binary section equals number of options (in binary)
            int binarySectionLen = Integer.toBinaryString(numOptions - 1).length();
            int optionSelected = (int) currentSet.toArray()[0];
            String binaryRepresentation = Integer.toBinaryString(optionSelected);
            String binarySectionString = String.format("%0" + binarySectionLen + "d", Integer.parseInt(binaryRepresentation));
            return binarySectionString;
        }
    }

    // for userAnswers:
    // would like to know the number of options for each
    // would like to know selected options
    // would like each answer as a set of integers
}
