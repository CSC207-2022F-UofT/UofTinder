package com.group80.uoftinder;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores static final variables that represent constants shared across more than one class.
 * This reduces the clutter, repetition, and redundancy of
 * having to define the same final variables in each class.
 */
public class Constants {
    public static final String CURRENT_USER_STRING = "currentUser";
    public static final String SHOULD_FILTER_STRING = "shouldFilter";
    public static final String MIN_AGE_STRING = "minAge";
    public static final String MAX_AGE_STRING = "maxAge";
    public static final String FILTERS_STRING = "filters";

    public static final int MIN_AGE = 13;
    public static final int MAX_AGE = 100;

    public static final Map<String,String[][]> USER_ANSWERS = new HashMap<String, String[][]>()
    {
        {
            put("Academic", new String[][] {
                    {"First Year", "Second Year", "Third Year", "Fourth Year", "Graduate"},
                    {"Computer Science", "Mathematical & Physical Sciences", "Life Sciences",
                            "Social Sciences & Humanities", "Rotman", "Other"},
                    {"St. George", "Mississauga", "Scarborough"}
            });
            put("Friendship", new String[][] {
                    {"First Year", "Second Year", "Third Year", "Fourth Year", "Graduate"},
                    {"Computer Science", "Mathematical & Physical Sciences", "Life Sciences",
                            "Social Sciences & Humanities", "Rotman", "Other"},
                    {"St. George", "Mississauga", "Scarborough"},
                    {"Art", "Fashion", "Food", "Gaming", "Movies & TV & Literature", "Music",
                            "Technology", "Sports & Athletics"},
                    {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Black & White & Gray"}
            });
            put("Romantic", new String[][] {
                    {"Heterosexual", "Homosexual", "Bisexual", "Pansexual",
                            "Asexual", "Prefer not to answer sexuality"},
                    {"Computer Science", "Mathematical & Physical Sciences", "Life Sciences",
                            "Social Sciences & Humanities", "Rotman", "Other"},
                    {"St. George", "Mississauga", "Scarborough"},
                    {"Art", "Fashion", "Food", "Gaming", "Movies & TV & Literature", "Music",
                            "Technology", "Sports & Athletics"},
                    {"Willing about long distance", "Unwilling about long distance",
                            "Unsure about long distance"},
                    {"Casual relationship", "Long term relationship", "Unsure about relationship"}
            });
        }
    };
}


