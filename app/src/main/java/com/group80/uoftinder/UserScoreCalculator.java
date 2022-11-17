package com.group80.uoftinder;

import java.util.ArrayList;
import java.util.HashSet;

public class UserScoreCalculator {
    private ArrayList<HashSet<Integer>> userAnswers;
    private final boolean[] isMultiSelect;
    private final int[] answerBitLengths;
    private final int answerLen;

    public UserScoreCalculator(ArrayList<HashSet<Integer>> userAnswers, boolean[] isMultiSelect, int[] answerBitLengths) {
        this.userAnswers = userAnswers;
        this.isMultiSelect = isMultiSelect;
        this.answerBitLengths = answerBitLengths;
        this.answerLen = isMultiSelect.length;
    }

    public int generateCompatibilityScore() {
        StringBuilder binaryScore = new StringBuilder();
        StringBuilder binaryScoreSeparated = new StringBuilder();
        for (int i = 0; i < this.answerLen; i++) {
            String bundledBinary = answerSetToBinary(i);
            binaryScore.append(bundledBinary);
            if (this.isMultiSelect[i]) {
                binaryScoreSeparated.append(bundledBinary).append("*");
            } else{
                binaryScoreSeparated.append(bundledBinary).append("-");
            }
        }
        System.out.println("binaryScore: " + binaryScoreSeparated.toString());
        return Integer.parseInt(binaryScore.toString(), 2);
    }

    private String answerSetToBinary(int currIndex) {
        StringBuilder binarySection = new StringBuilder();
        if (this.isMultiSelect[currIndex]) {
            // length of binary section equals number of options
            for (int curr = 0; curr < this.answerBitLengths[currIndex]; curr++) {
                if (this.userAnswers.get(currIndex).contains(curr)) {
                    binarySection.append("1");
                } else {
                    binarySection.append("0");
                }
            }
            return binarySection.toString();
        } else {
            // retrieve the only value in HashSet since there is only one answer
            int optionSelected = (int) this.userAnswers.get(currIndex).toArray()[0];
            String binaryRepresentation = Integer.toBinaryString(optionSelected);
            return String.format("%0" + this.answerBitLengths[currIndex] + "d", Integer.parseInt(binaryRepresentation));
        }
    }
}
