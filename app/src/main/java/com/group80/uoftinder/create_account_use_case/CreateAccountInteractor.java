package com.group80.uoftinder.create_account_use_case;

import java.util.HashMap;

public class CreateAccountInteractor {
//    private static HashMap<String, int[]> answerSchema;
//    //[5,6,3] (academic) [6,6,3,8,3,3] (romantic) [5,6,8,8] (friendship)
//    private static HashMap<String, boolean[]> isMultiSelect;
    //[false, true, false] (academic) [false, true, false, true, false, false] (romantic)
    // [false, true, false, true, true] (friendship)

    //purely for testing purposes
//    public static void setAnswerSchema(int[] aS, String type) {
//        answerSchema.replace(type, aS);
//    }
//
//    //purely for testing purposes ANSWERSCHEMA AND GETISMULTISELECT SHOULD NOT BE CHANGED OTHERWISE
//    public static void setIsMultiSelect(boolean[] iMS, String type) {
//        isMultiSelect.replace(type, iMS);
//    }

    /**
     * Returns the number of answers to each question
     * @return       returns an array of booleans representing the number of answers for each question in
     *               the correct order of questions
     */
    public static int[] getAnswerSchema(String type) {
        if (type.equals("Academic")) {
            return new int[] {5, 6, 3};
        }
        else if (type.equals("Friendship")) {
            return new int[] {5, 6, 8, 8};
        }
        else if (type.equals("Romantic")) {
            return new int[] {6, 6, 3, 8, 3, 3};
        }

        return null;
    }

    /**
     * Returns whether each question is mulitselect or not
     * @return       returns an array of booleans representing whether each question is multiselect in
     *               the correct order of questions
     */
    public static boolean[] getIsMultiSelect(String type) {
        if (type.equals("Academic")) {
            return new boolean[] {false, true, false};
        }
        else if (type.equals("Friendship")) {
            return new boolean[] {false, true, false, true, true};
        }
        else if (type.equals("Romantic")) {
            return new boolean[] {false, true, false, true, false, false};
        }

        return null;
    }
}