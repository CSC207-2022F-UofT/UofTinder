package com.group80.uoftinder.use_case_layer;

import java.util.List;
import java.util.Set;

public class RecommendationFilterInputData {

    private List<Set<Integer>> filters;
    private int minAge;
    private int maxAge;

    /**
     * Creates an input data structure for the filter recommendation feed functionality.
     * @param filters   A list of sets of integers that represents the wanted criteria.
     *                  Each set in the list corresponds to a filtering question.
     *                  For academic users, filters.size() should be 3 (program of study,
     *                  year of study, and campus). Each integer represents a selected choice.
     *                  {0, 3, 5} means the first, fourth, and sixth choices were checked.
     * @param minAge    The minimum age, inclusive
     * @param maxAge    The maximum age, inclusive
     */
    public RecommendationFilterInputData(List<Set<Integer>> filters, int minAge, int maxAge) {
        this.filters = filters;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    /**
     * Gets the selected filtering criteria.
     * @return  A list of sets of integers that represents the wanted criteria.
     */
    public List<Set<Integer>> getFilters() {
        return filters;
    }

    /**
     * Gets the selected minimum age criterion.
     * @return  The minimum age, inclusive.
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * Gets the selected maximum age criterion.
     * @return  The maximum age, inclusive
     */
    public int getMaxAge() {
        return maxAge;
    }
}
