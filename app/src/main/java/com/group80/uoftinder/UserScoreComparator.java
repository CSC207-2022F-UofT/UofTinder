package com.group80.uoftinder;
import java.util.Comparator;

public class UserScoreComparator implements Comparator<Integer> {
    private final int answerLen;
    private final boolean[] isMultiSelect;
    private final int[] answerBitLengths;
    private final int scoreLength;

    /**
     * Constructor for the UserScoreComparator class
     * @param isMultiSelect is an array where index i tells us whether question i can
     *                      have multiple answers selected or just one
     * @param answerBitLengths is an array where index i tells us the number of options for
     *                         question i
     */
    public UserScoreComparator(boolean[] isMultiSelect, int[] answerBitLengths) {
        this.answerLen = isMultiSelect.length;
        this.isMultiSelect = isMultiSelect;
        this.answerBitLengths = answerBitLengths;
        this.scoreLength = calculateSum(this.answerBitLengths);
    }

    /**
     * Helper function to calculate sum of array
     * @param myArray is the array to sum
     *
     * @return sum of entries in myArray
     */
    private int calculateSum(int[] myArray) {
        int total = 0;
        for (int elem : myArray) {
            total += elem;
        }
        return total;
    }

    /**
     * @param score1 is the score of the first user
     * @param score2 is the score of the second user
     *
     * @return an integer that represents the similarity of the two scores (higher value signals higher similarity)
     */
    @Override
    public int compare(Integer score1, Integer score2) {
        String score1Binary = String.format("%" + this.scoreLength + "s", Integer.toBinaryString(score1)).replace(' ', '0'); // convert score1 and score2
        // to binary, filling in leading zeroes as necessary padding zeroes may be needed since Integer.parseInt(binaryNum) removes this data
        String score2Binary = String.format("%" + this.scoreLength + "s", Integer.toBinaryString(score2)).replace(' ', '0');

        int compatibilityScore = 0;
        int currentBit = 0;
        for (int i = 0; i < this.answerLen; i++) { // iterate through all bits
            String score1BinaryStringSection = score1Binary.substring(currentBit, currentBit + this.answerBitLengths[i]); // get current bit of score1
            String score2BinaryStringSection = score2Binary.substring(currentBit, currentBit + this.answerBitLengths[i]); // get current bit of score2
            if (this.isMultiSelect[i]) { // if multi-select, add 1 to compatibilityScore for each option that matches
                for (int j = 0; j < this.answerBitLengths[i]; j++) {
                    char score1Char = score1BinaryStringSection.charAt(j);
                    char score2Char = score2BinaryStringSection.charAt(j);
                    compatibilityScore += Character.getNumericValue(score1Char) & Character.getNumericValue(score2Char);
                }
            } else { // if single-select, add 1 to compatibilityScore only if all the bits match (since the bits represent the option number selected)
                boolean addToScore = true;
                for (int j = 0; j < this.answerBitLengths[i]; j++) { // iterate through all bits to see if they match
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
