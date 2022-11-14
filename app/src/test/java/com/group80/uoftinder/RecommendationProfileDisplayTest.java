package com.group80.uoftinder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;



public class RecommendationProfileDisplayTest {
    User current = new User();
    User test1 = new User();
    User test2 = new User();
    User test3 = new User();
    ArrayList<User> compatibleUser = new ArrayList<User>();


    public RecommendationProfileDisplayTest() {
        test1.setDisplayName("testName1");
        test2.setDisplayName("testName2");
        test3.setDisplayName("testName3");

        compatibleUser.add(test1);
        compatibleUser.add(test2);
        compatibleUser.add(test3);
    }

    @Test
    public void goesToNextUser() {
        User displayedUser = test1;
//        User nextDisplayedUser = setToNextDisplayedUser();

//        assertEquals(nextDisplayedUser, test2);
    }
    @Test
    public void goesToNoNewRecommendationScreen() {


    }

}
