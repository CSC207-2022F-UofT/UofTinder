package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.HashSet;

public class UserScoreFacade {  // using Facade Design Principle to delegate tasks
    private final UserScoreCalculator usCalc;
    private final UserScoreComparator usComp;
    private ArrayList<HashSet<Integer>> userAnswers; // [{0, 2, 3}, {1, 3}, ...]
    private final int[] answerSchema;
    private final boolean[] isMultiSelect;
    private final int answerLen;

    public UserScoreFacade(User currentUser, CreateAccountInteractor cai) {  // using Dependency Injection Design Principle
        this.userAnswers = currentUser.getAnswers();
        this.answerSchema = cai.getAnswerSchema();
        this.isMultiSelect = cai.getIsMultiSelect();
        this.answerLen = this.userAnswers.size();
        int[] answerBitLengths = getAnswerBitLengths();

//        this.answerLen = 10;
//        this.answerSchema = new int[] {3, 4, 4, 5, 4, 3, 5, 3, 4, 3};
//        this.isMultiSelect = new boolean[] {false, true, true, false, false, true, false, true, true, false};
//        initializeAnswers();

        this.usCalc = new UserScoreCalculator(this.userAnswers, this.isMultiSelect, answerBitLengths);
        this.usComp = new UserScoreComparator(this.isMultiSelect, answerBitLengths);
    }

    public int generateCompatibilityScore() {
        return this.usCalc.generateCompatibilityScore();
    }

    public int compare(int score1, int score2) {
        return this.usComp.compare(score1, score2);
    }

    // for testing
    public void initializeAnswers() {
        this.userAnswers = new ArrayList<>();
//        System.out.println("answerLen: " + this.answerLen);
        for (int i = 0; i < this.answerLen; i++) {
            int optionLen = this.answerSchema[i];
            int numRandSelected;
            if (this.isMultiSelect[i]) {
                numRandSelected = (int) (Math.random() * (optionLen - 1) + 2);
            } else {
                numRandSelected = 1;
            }
            HashSet<Integer> answers = new HashSet<>();
//            System.out.println("numRandSelected: " + numRandSelected);
            for (int j = 0; j < numRandSelected; j++) {
                answers.add((int) (Math.random() * optionLen));
            }
            this.userAnswers.add(answers);
        }
//        for (int j = 0; j < answerLen; j++) {
//            System.out.println("userAnswers: " + Arrays.toString(this.userAnswers[j].toArray()));
//            System.out.println("Total number of options: " + this.answerSchema[j] + "\n");
//        }
    }

    private int[] getAnswerBitLengths() {
        int[] answerBitLengths = new int[this.answerLen];
        for (int i = 0; i < this.answerLen; i++) {
            if (this.isMultiSelect[i]) {
                answerBitLengths[i] = this.answerSchema[i];
            } else {
                answerBitLengths[i] = Integer.toBinaryString((this.answerSchema[i] - 1)).length();
            }
        }
        return answerBitLengths;
    }

    // for testing
//    public static void main(String[] args) {
//        UserScoreFacade usf1 = new UserScoreFacade(new User(), new CreateAccountInteractor());
//        int score1 = usf1.generateCompatibilityScore();
//        System.out.println("User1 score: " + score1);
//        UserScoreFacade usf2 = new UserScoreFacade(new User(), new CreateAccountInteractor());
//        int score2 = usf2.generateCompatibilityScore();
//        System.out.println("User2 score: " + score2);
//        System.out.println("Compatibility score: " + usf1.compare(score1, score2));
//    }
}
