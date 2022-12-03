package com.group80.uoftinder.entities;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String CURRENT_USER_STRING = "currentUser";
    public static final String SHOULD_FILTER_STRING = "shouldFilter";
    public static final String MIN_AGE_STRING = "minAge";
    public static final String MAX_AGE_STRING = "maxAge";
    public static final String FILTERS_STRING = "filters";

    public static final int MIN_AGE = 13;
    public static final int MAX_AGE = 100;

    public static final String[][] ACADEMIC_ANSWERS = {
            {"First Year", "Second Year", "Third Year", "Fourth Year", "Graduate"},
            {"Computer Science", "Mathematical & Physical Sciences", "Life Sciences",
                    "Social Sciences & Humanities", "Rotman", "Other"},
            {"St. George", "Mississauga", "Scarborough"}
    };
}
