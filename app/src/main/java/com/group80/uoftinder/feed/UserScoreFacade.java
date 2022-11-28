package com.group80.uoftinder.feed;

import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import com.group80.uoftinder.entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Facade class that contains methods to calculate user score and compare two user scores
 */
public class UserScoreFacade {  // using Facade Design Principle to delegate tasks
    private final UserScoreCalculator usCalc; // instance of class designed for calculating user scores
    private final UserScoreComparator usComp; // instance of class designed for comparing user scores
    private List<List<Integer>> userAnswers; // List of HashSets, where index i is a
    // HashSet representing the indices of the answers selected by the currentUser for question i
    private final int[] answerSchema; // array where index i tells us the number of options for
    // question i
    private final boolean[] isMultiSelect; // array where index i tells us whether question i can
    // have multiple answers selected or just one
    private final int answerLen; // helper int for the number of questions

    /**
     * Constructor for the UserScoreFacade class
     * @param currentUser is the user whose score needs to be calculated
     */
    public UserScoreFacade(User currentUser) {  // using Dependency Injection Design Principle
        this.userAnswers = currentUser.getAnswers();
        this.answerSchema = CreateAccountInteractor.getAnswerSchema(); // static method that returns number of options for each question
        this.isMultiSelect = CreateAccountInteractor.getIsMultiSelect(); // static method that returns whether each question is multi-select
        this.answerLen = this.userAnswers.size();
        int[] answerBitLengths = getAnswerBitLengths(); // array where index i tells us the number of bits
        // allocated for question i in the user score

        this.usCalc = new UserScoreCalculator(this.userAnswers, this.isMultiSelect, answerBitLengths);
        this.usComp = new UserScoreComparator(currentUser, this.isMultiSelect, answerBitLengths);
    }

    /**
     * Function that uses the UserScoreCalculator instance to compute the compatibility score
     *
     * @return the generated compatibility score
     */
    public int generateCompatibilityScore() {
        return this.usCalc.generateCompatibilityScore();
    }

    /**
     * @param score2 is the score of the second user
     *
     * @return an integer that represents the similarity of the two scores (higher value signals higher similarity)
     */
    public int compare(int score2) {
        return this.usComp.compare(score2);
    }

    /**
     * Function that returns an array containing the number of bits for each question in the binary
     * representation of the user score. Each index i of the returned array dictates how many bits
     * to analyze for question i when comparing two scores.
     *
     * @return the array of answer bit lengths
     */
    private int[] getAnswerBitLengths() {
        int[] answerBitLengths = new int[this.answerLen];
        for (int i = 0; i < this.answerLen; i++) {
            if (this.isMultiSelect[i]) {
                answerBitLengths[i] = this.answerSchema[i]; // for multi-select questions, each bit
                // represents an option selection
            } else {
                answerBitLengths[i] = Integer.toBinaryString((this.answerSchema[i] - 1)).length();
                // for single-select questions, the number of bits is equal to the number of
                // options - 1 in binary
            }
        }
        return answerBitLengths;
    }
}
