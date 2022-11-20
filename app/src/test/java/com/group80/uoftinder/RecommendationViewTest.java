package com.group80.uoftinder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class RecommendationViewTest {
    User current = new User();
    User test1 = new User();
    User test2 = new User();
    User test3 = new User();
    List compatibleUser = new ArrayList<User>();

    @Test
    public void RecommendationViewTest() {
        test1.setName("testName1");
        test2.setName("testName2");
        test3.setName("testName3");

        compatibleUser.add(test1);
        compatibleUser.add(test2);
        compatibleUser.add(test3);
    }

//    @Test
//    public void goesToNextUser() {
//        User displayedUser = test1;
////        User nextDisplayedUser = setToNextDisplayedUser();
//
////        assertEquals(nextDisplayedUser, test2);
//    }
//    @Test
//    public void goesToNoNewRecommendationScreen() {
//
//
//    }

}
