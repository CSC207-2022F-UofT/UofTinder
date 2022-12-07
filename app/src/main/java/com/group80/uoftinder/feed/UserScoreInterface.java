package com.group80.uoftinder.feed;

/**
 * Interface class that contains methods to calculate user score and compare two user scores
 */
public interface UserScoreInterface {  // using Facade Design Principle to delegate tasks
    /**
     * Function to compute the compatibility score for the currentUser
     * @return the generated compatibility score
     */
    int generateCompatibilityScore();

    /**
     * @param score2 is the score of the second user
     * @return an integer that represents the similarity of the two scores (higher value signals higher similarity)
     */
    int compare(int score2);
}
