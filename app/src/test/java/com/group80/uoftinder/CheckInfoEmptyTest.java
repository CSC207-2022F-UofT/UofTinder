package com.group80.uoftinder;
import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kotlin.collections.ArrayDeque;

/**
 * Unit tests for testing that ensures the current user trying to create a new account enters all
 * information on the questionnaires.
 */
public class CheckInfoEmptyTest {
    /**
     * Test inputs for checkBasicInput
     */
    @Test
    public void testCheckBasicInput() {
        String name0 = "Milena";
        String age0 = "19";
        String identity0 = "Female";
        String type0 = "Academic";
        boolean test0 = CreateAccountInteractor.checkBasicInput(name0, age0, identity0, type0);

        String name1 = "Lance";
        String age1 = "";
        String identity1 = "Male";
        String type1 = "Friendship";
        boolean test1 = CreateAccountInteractor.checkBasicInput(name1, age1, identity1, type1);

        String name2 = "Jessica";
        String age2 = "18";
        String identity2 = "";
        String type2 = "";
        boolean test2 = CreateAccountInteractor.checkBasicInput(name2, age2, identity2, type2);

        String name3 = "Chloe";
        String age3 = "";
        String identity3 = "";
        String type3 = "Romantic";
        boolean test3 = CreateAccountInteractor.checkBasicInput(name3, age3, identity3, type3);

        String name4 = "Yahya";
        String age4 = "18";
        String identity4 = "Male";
        String type4 = "Friendship";
        boolean test4 = CreateAccountInteractor.checkBasicInput(name4, age4, identity4, type4);

        assert test0;
        assert !test1;
        assert !test2;
        assert !test3;
        assert test4;

    }

    /**
     * Test inputs for checkAcademicInput
     */
    @Test
    public void testCheckAcademicInput() {
        int year0 = 1;
        List<Integer> majors0 = new LinkedList<>();
        majors0.add(0);
        int campus0 = 0;
        boolean test0 = CreateAccountInteractor.checkAcademicInput(year0, majors0, campus0);

        int year1 = 3;
        List<Integer> majors1 = new LinkedList<>();
        int campus1 = 1;
        boolean test1 = CreateAccountInteractor.checkAcademicInput(year1, majors1, campus1);

        int year2 = 4;
        List<Integer> majors2 = new LinkedList<>();
        majors2.add(0);
        majors2.add(1);
        int campus2 = 2;
        boolean test2 = CreateAccountInteractor.checkAcademicInput(year2, majors2, campus2);

        int year3 = -1;
        List<Integer> majors3 = new LinkedList<>();
        majors3.add(0);
        majors3.add(4);
        int campus3 = 0;
        boolean test3 = CreateAccountInteractor.checkAcademicInput(year3, majors3, campus3);

        int year4 = 2;
        List<Integer> majors4 = new LinkedList<>();
        majors4.add(0);
        majors4.add(2);
        majors4.add(5);
        int campus4 = -1;
        boolean test4 = CreateAccountInteractor.checkAcademicInput(year4, majors4, campus4);


        assert test0;
        assert !test1;
        assert test2;
        assert !test3;
        assert !test4;

    }

    /**
     * Test inputs for checkFriendshipInput
     */
    @Test
    public void testCheckFriendshipInput() {
        int year0 = 0;
        List<Integer> majors0 = new LinkedList<>();
        majors0.add(2);
        majors0.add(3);
        int campus0 = 0;
        List<Integer> interests0 = new LinkedList<>();
        interests0.add(0);
        interests0.add(2);
        interests0.add(4);
        List<Integer> colours0 = new LinkedList<>();
        colours0.add(3);
        colours0.add(4);
        boolean test0 = CreateAccountInteractor.checkFriendshipInput(year0, majors0, campus0,
                interests0, colours0);

        int year1 = 3;
        List<Integer> majors1 = new LinkedList<>();
        majors1.add(5);
        int campus1 = 0;
        List<Integer> interests1 = new LinkedList<>();
        List<Integer> colours1 = new LinkedList<>();
        colours1.add(0);
        colours1.add(3);
        colours1.add(4);
        boolean test1 = CreateAccountInteractor.checkFriendshipInput(year1, majors1, campus1,
                interests1, colours1);

        int year2 = 3;
        List<Integer> majors2 = new LinkedList<>();
        int campus2 = 0;
        List<Integer> interests2 = new LinkedList<>();
        interests2.add(6);
        List<Integer> colours2 = new LinkedList<>();
        colours2.add(2);
        colours2.add(3);
        boolean test2 = CreateAccountInteractor.checkFriendshipInput(year2, majors2, campus2,
                interests2, colours2);

        assert test0;
        assert !test1;
        assert !test2;
    }

    /**
     * Test inputs for checkRomanticInput
     */
    @Test
    public void testCheckRomanticInput() {
        int sexuality0 = 0;
        List<Integer> majors0 = new LinkedList<>();
        majors0.add(3);
        int campus0 = 2;
        List<Integer> interests0 = new LinkedList<>();
        interests0.add(2);
        interests0.add(3);
        interests0.add(7);
        int distance0 = 0;
        int relationship0 = 2;
        boolean test0 = CreateAccountInteractor.checkRomanticInput(sexuality0, majors0, campus0,
                interests0, distance0, relationship0);

        int sexuality1 = -1;
        List<Integer> majors1 = new LinkedList<>();
        majors1.add(5);
        int campus1 = 0;
        List<Integer> interests1 = new LinkedList<>();
        interests1.add(3);
        interests1.add(4);
        int distance1 = -1;
        int relationship1 = 1;
        boolean test1 = CreateAccountInteractor.checkRomanticInput(sexuality1, majors1, campus1,
                interests1, distance1, relationship1);

        int sexuality2 = 3;
        List<Integer> majors2 = new LinkedList<>();
        int campus2 = 2;
        List<Integer> interests2 = new LinkedList<>();
        int distance2 = 2;
        int relationship2 = 0;
        boolean test2 = CreateAccountInteractor.checkRomanticInput(sexuality2, majors2, campus2,
                interests2, distance2, relationship2);

        assert test0;
        assert !test1;
        assert !test2;
    }
}
