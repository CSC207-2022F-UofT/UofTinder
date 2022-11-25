package com.group80.uoftinder.feed;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserScoreCalculator {
    private List<List<Integer>> userAnswers; // List of HashSets, where index i is a
    // HashSet representing the indices of the answers selected by the currentUser for question i
    private final boolean[] isMultiSelect; // array where index i tells us whether question i can
    // have multiple answers selected or just one
    private final int[] answerBitLengths; // array where index i tells us the number of bits
    // allocated for question i in the user score
    private final int answerLen; // helper int for the number of questions
    private final int BASE_TWO = 2; // constant representation for base two

    /**
     * Constructor for the UserScoreCalculator class
     *
     * @param userAnswers is an List of HashSets, where index i is a HashSet representing the
     *                    indices of the answers selected by the currentUser for question i
     * @param isMultiSelect is an array where index i tells us whether question i can
     *                      have multiple answers selected or just one
     * @param answerBitLengths array where index i tells us the number of bits
     *                         allocated for question i in the user score
     */
    public UserScoreCalculator(List<List<Integer>> userAnswers, boolean[] isMultiSelect, int[] answerBitLengths) {
        this.userAnswers = userAnswers;
        this.isMultiSelect = isMultiSelect;
        this.answerBitLengths = answerBitLengths;
        this.answerLen = isMultiSelect.length;
    }

    /**
     * Function that computes and returns a compatibility score based on userAnswers
     * @return the generated compatibility score
     */
    public int generateCompatibilityScore() {
        StringBuilder binaryScore = new StringBuilder();
        for (int i = 0; i < this.answerLen; i++) {
            String bundledBinary = answerSetToBinary(i); // calls helper method to compute the binary bits for question i
            binaryScore.append(bundledBinary); // append this to binary score to create one binary number
        }
        return Integer.parseInt(binaryScore.toString(), this.BASE_TWO);
    }

    /**
     * Function that returns a String of the binary bits representing question currIndex
     *
     * For a multi-select question with n options:
     *      - we return n bits, where each bit i is 1 if the user selected option i, 0 otherwise
     * For a single-select question with n options:
     *      - we return bin(n - 1) bits, where the binary section is equal to the binary
     *      representation of the option number selected
     *
     * @param currIndex is the index of the current question being translated into bits
     * @return a String of the binary section for question currIndex
     */
    private String answerSetToBinary(int currIndex) {
        StringBuilder binarySection = new StringBuilder();
        if (this.isMultiSelect[currIndex]) { // if multi-select question, length of binary section equals number of options
            for (int curr = 0; curr < this.answerBitLengths[currIndex]; curr++) {
                if (this.userAnswers.get(currIndex).contains(curr)) { // we check to see if the curr option number is within userAnswers
                    // each bit i dictates whether the user selected option i or not
                    binarySection.append("1"); // a 1 tells us that the user selected this option
                } else {
                    binarySection.append("0"); // a 0 tells us that the user did not select this option
                }
            }
            return binarySection.toString();
        } else {
            // retrieve the only value in HashSet since there is only one answer
            int optionSelected = (int) this.userAnswers.get(currIndex).toArray()[0];
            String binaryRepresentation = Integer.toBinaryString(optionSelected); // save the binaryRepresentation of the option selected
            return String.format("%0" + this.answerBitLengths[currIndex] + "d", Integer.parseInt(binaryRepresentation)); // pad with zeroes
        }
    }
}
