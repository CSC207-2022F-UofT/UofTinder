package com.group80.uoftinder;
import java.util.Comparator;

public class UserScoreComparator implements Comparator<Integer> {
    private final int answerLen;
    private final boolean[] isMultiSelect;
    private final int[] answerBitLengths;
    private final int scoreLength;

    public UserScoreComparator(boolean[] isMultiSelect, int[] answerBitLengths) {
        this.answerLen = isMultiSelect.length;
        this.isMultiSelect = isMultiSelect;
        this.answerBitLengths = answerBitLengths;
        this.scoreLength = calculateSum(this.answerBitLengths);
    }

    private int calculateSum(int[] myArray) {
        int total = 0;
        for (int elem : myArray) {
            total += elem;
        }
        return total;
    }

    @Override
    public int compare(Integer score1, Integer score2) {
        System.out.println("scoreLength: " + this.scoreLength);
        String score1Binary = String.format("%" + this.scoreLength + "s", Integer.toBinaryString(score1)).replace(' ', '0');
        String score2Binary = String.format("%" + this.scoreLength + "s", Integer.toBinaryString(score2)).replace(' ', '0');

        System.out.println("score1Binary: " + score1Binary);
        System.out.println("score2Binary: " + score2Binary);

        int compatibilityScore = 0;
        int currentBit = 0;
        for (int i = 0; i < this.answerLen; i++) {
            String score1BinaryStringSection = score1Binary.substring(currentBit, currentBit + this.answerBitLengths[i]);
            String score2BinaryStringSection = score2Binary.substring(currentBit, currentBit + this.answerBitLengths[i]);
            if (this.isMultiSelect[i]) {
                for (int j = 0; j < this.answerBitLengths[i]; j++) {
                    char score1Char = score1BinaryStringSection.charAt(j);
                    char score2Char = score2BinaryStringSection.charAt(j);
                    compatibilityScore += Character.getNumericValue(score1Char) & Character.getNumericValue(score2Char);
                }
            } else {
                boolean addToScore = true;
                for (int j = 0; j < this.answerBitLengths[i]; j++) {
                    char score1Char = score1BinaryStringSection.charAt(j);
                    char score2Char = score2BinaryStringSection.charAt(j);
                    if (score1Char != score2Char) {
                        addToScore = false;
                        break;
                    }
                }
                if (addToScore) {
                    compatibilityScore++;
                }
            }
            currentBit += this.answerBitLengths[i];
        }
        return compatibilityScore;
    }
}
