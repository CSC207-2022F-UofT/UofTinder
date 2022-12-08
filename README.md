# Overview and Inspiration 

Introducing UofTinder, a social networking Android application built by a group of UofT students.
Inspired by Tinder, we wanted to create an application with more options in terms of the type of relationships people may want to build with others. 

The program offers users the opportunity to either look for their significant other, friends, or academic partners. 
Then, a compatibility algorithm analyzes certain profile metrics (e.g., hobbies and interests, sexual orientation, academic focuses) to recommend potential matches to people based on their calculated compatibility. 
If two people match with each other through this selection process, they proceed to a chat feature where they have the opportunity to talk and get to know each other more.

Some design patterns that we implemented within our project include Model View Presenter (MVP), Façade, Observer, and Dependency Injection.

# Installation/Execution Instructions
1. Download and install Android studio (https://developer.android.com/studio). Select Android Virtual Device.
2. On first launch, select 'do not import settings' and 'standard installation type' when prompted. Accept both android-sdk-license and android-sdk-preview- license.
3. Navigate to the folder you would like to store the code in a terminal that recognizes git. Type the following command:
    - `git clone https://github.com/CSC207-2022F-UofT/course-project-group-80.git`
    - Optionally, type: `git clone https://github.com/CSC207-2022F-UofT/course-project-group-80.git ./UofTinder`
      - Note: the ./UofTinder part tells git to rename the cloned folder to UofTinder
4. Navigate to the folder and convert to the following branch
```
  cd ./UofTinder
  git checkout main
```
5. Launch Android Studio, and select Open. Navigate to and select the folder where the repo is cloned locally.
6. Once the project is opened, gradle should start syncing the files. Wait until it is done.
7. In the menu bar, Android studio should already selected the app configuration and Pixel_3a_API_33_x86_64 for device (OK to select different device, as long as it's a cellphone and API is not lower than 23). Click the hammer to the left of app to build the project.
8. Open the Device Manager tab to the right of the screen, find your virtual device, and click the triangle in the Actions tab to launch the device.
9. The app should now be launched. Please watch our demo video (https://youtu.be/EKVf1XHtWYk) to see an overview of the app's functionalities and/or continue reading for a written summary.

# How to Run the App
1. If you already have an account, you can enter in your email and password in login [(1:15)](https://youtu.be/EKVf1XHtWYk?t=75). Skip steps 2-5.
2. Else, if you don't have an account, click sign up [(0:06)](https://youtu.be/EKVf1XHtWYk?t=6). 
3. Once you create an account, you will be taken to a page to enter your email and password, as well as a basic information page [(0:30)](https://youtu.be/EKVf1XHtWYk?t=30). This page will prompt you to select one of three users (Academic, Friendship, or Romantic). In the video demo, we selected Academic.
4. Next, based on the User type you selected previously (Academic, Friendship, or Romantic), you will be shown a tailored questionnaire to answer. This is used so that a list of compatible users can be generated. In the video, we are shown the Academic Questionnaire [(0:45)](https://youtu.be/EKVf1XHtWYk?t=45).
5. Once you filled out the questionnaire, you can optionally upload an image as your profile picture [(0:57)](https://youtu.be/EKVf1XHtWYk?t=57).
6. Next, the Recommendation Feed is shown, where you can click 'yes' or 'no' on the profiles that are displayed [(1:38)](https://youtu.be/EKVf1XHtWYk?t=98). Whichever option you decide to click will determine if a match is created.
7. If a match is created, a small pop-up at the bottom will appear [(1:54)](https://youtu.be/EKVf1XHtWYk?t=114).
8. You can enter the chat function at the top right button of the Recommendation Feed [(1:57)](https://youtu.be/EKVf1XHtWYk?t=117). If you have any matches, they will appear in the list and you can send them messages [(2:02)](https://youtu.be/EKVf1XHtWYk?t=122).
9. If you want to filter your matches by age, program of study, year of study, or campus, you can click the middle top button of the Recommendation Feed to select filters [(2:13)](https://youtu.be/EKVf1XHtWYk?t=133). This will filter out your generated compatible matches by the criteria selected.
10. Once you run out of compatible matches, a screen will appear saying that there are no new recommendations [(2:27)](https://youtu.be/EKVf1XHtWYk?t=147).
11. There is also the option to logout of your profile at the top left button of Recommendation View [(1:07)](https://youtu.be/EKVf1XHtWYk?t=67).

# Core Functionalities
**1. User login**
- Login requires an email and password that have been already registered in the database to a ```User``` object
- Incorrect/absent information will trigger failure views that indicate that fields are missing
- Correct information will trigger a success view, and will switch the view from Login to the home feed

**2. Create new account**
- User can choose between three different types of users when registering: Romantic, Academic, and Friendship
- An email and password are required to identify each user

**3. Profile setup/questionnaire**
- The user cannot continue before answering profile setup questions
- Questionnaire answers for each user are later used in generating a recommendation feed for the current user

**4. Generate recommendation feed**
- Based on the user responses to the mandatory questionnaire, a user score is computed for each user
- We retrieve all users from the database and create an ordered compatibility list based on computed similarity scores

**5. Display recommendation feed**
- Each user in the generated compatibility list is displayed on the home feed, including their profile picture and relevant information
- The current user is able to select "Yes" or "No" on the displayed user, indicating whether they would like to match or not

**6. Filter recommendation feed**
- We are able to filter the compatibility list based on answers to any questions on the questionnaire
- Filter options vary by user type, and applying the filter alters the recommendation feed shown

**7. Creating matches**
- Every time the user likes someone displayed on the recommendation feed, we check to see if a match can be created between the two
- If they like each other, we open a chat between the two users and display a success view to indicate that a match has been created

**8. Chat feature**
- Pressing on the chat tab opens up a contact list including all of the people that the current user has matched with
- Users are able to chat with each other through a messaging interface, which is available as soon as they have matched

**9. User persistence**
- As soon as the user has registered their account and answered the questionnaire, a ```User``` object containing their information is pushed to Firebase
- Whenever the current user's internal data is updated as they use the app, we synchronize these changes with the database.

# Unit Tests Implemented for Functionalities
- **Generate Compatibility List**
  - orderCompatibilityListTest1: tests that GenerateCompatibilityList.orderCompatibilityList reorders the compatibility list from most compatible user to least compatible user when there are 3 total users.
  - orderCompatibilityListTest2: tests that GenerateCompatibilityList.orderCompatibilityListTest2 does nothing to the compatibility list when there are no other users in the database (besides the current user).
  - showMostCompUser2UsersTest: tests that GenerateCompatibilityList.showMostCompUser returns the most compatible user when there are 2 other users.
  - showMostCompUserNoUsersTest: tests that GenerateCompatibilityList.showMostCompUser returns null when there are no other compatible users
  - removeMostCompUserTest: tests that GenerateCompatibilityList.removeMostCompUser removes the most compatible user in the compatibility list.
  - removeCurrentUserTest:  tests that GenerateCompatibilityList.removeCurrentUser removes the current user from the current user's compatibility list.
  - removeVisitedUsersTest: tests that GenerateCompatibilityList.removeVisitedUsers removes the visited users from the current user's compatibility list.
- **Match Interactor** 
  - checkMatchListsUpdatedLocal: test to see if the match lists for two users are both updated in the local User classes.
  - checkMatchListsUpdatedRemote: test to see if the match lists for two users are both updated in the database upon match.
  - currUserSkipsDisplayedUserLocal: test to see if the local User viewed and liked lists are updated when the currentUser does not 'like' the displayedUser.
  - currUserLikesDisplayedUserLocal: test to see if the local User viewed and liked lists are updated when the currentUser 'likes' the displayedUser.
  - currUserSkipsDisplayedUserRemote: test to see if the viewed and liked lists of the current user are updated in the database when the currentUser does not 'like' the displayedUser.
  - currUserLikesDisplayedUserRemote: test to see if the viewed and liked lists of the current user are updated in the database when the currentUser does not 'like' the displayedUser.
- **RealTime Database**
  - uploadUser_isCorrect: test if the upload task can be done without error.
  - getAllUsers_isCorrect: test if we can get all users without error.
  - getUser_isCorrect: test that we can get the correct user without error.
- **Filter Recommendation Feed** 
  - filterFeedTestFilters: tests that we can apply filters to the feed.
  - filterFeedTestAge: tests that we can filter by age.
  - filterFeedTestNoFilters: tests that we can see the fee without any filters.
  - filterFeedTestFiltersAndAge: test that we can filter by age and other filters.
- **User Score Facade**
  - generateCompatibilityScoreTest1: tests to see whether the correct compatibility gets generated for current user (User 1).
  - generateCompatibilityScoreTest2: tests to see whether the correct compatibility gets generated for current user (User 2).
  - generateCompatibilityScoreTest3: tests to see whether the correct compatibility gets generated for current user (User 3).
  - compareTest1: tests to see compare method returns the correct similarity scores for two users (User 1 and User 2).
  - compareTest2: tests to see compare method returns the correct similarity scores for two users (User 3 and User 2).
- **User Entity Methods**
  - infoStringTestAcademicUser: tests to get the correct display information string for an academic user.
  - infoStringTestRomanticUser: tests to get the correct display information string for a romantic user. Also tests the case of not providing answers for several questions.
  - infoStringTestFriendshipUser: tests for getting the correct display information string for a friendship user. Also tests the case of not providing an answer for a question between two questions with answers provided.

# Next steps for UofTinder
Given more time, we would create prettier viewing of user profiles (UI changes) and also add a filter UI option for romantic and friendship users.
We would also add the ability to view and edit one's own profile.

[//]: # ()
[//]: # (# Project Template)

[//]: # ()
[//]: # (This is a template repository for CSC 207 projects. )

[//]: # (This repository contains starter code for a gradle project.)

[//]: # (It also contains workflow documents that give instructions on how to manage your Github repository and how to use Github Projects for efficient collaboration.)

[//]: # ()
[//]: # (## Checklist For Your Project)

[//]: # (- [ ] Verify the correct settings for your project repository)

[//]: # (- [ ] Set up Github Projects)

[//]: # (- [ ] Create the implementation plan using issues and Github Projects)

[//]: # (- [ ] Create deveopment branches for your features)

[//]: # (- [ ] Use pull requests to merge finished features into main branch)

[//]: # (- [ ] Conduct code reviews)

[//]: # ()
[//]: # (**If your team has trouble with any of these steps, please ask on Piazza. For example, with how GitHub Classroom works, your team *may* not have permissions to do some of the first few steps, in which case we'll post alternative instructions as needed.**)

[//]: # ()
[//]: # (## Workflow Documents)

[//]: # ()
[//]: # (* Github Workflow: Please refer to the workflow that was introduced in the first lab. You should follow this when working on your code. The following document provides additional details too.)

[//]: # ()
[//]: # (* [Project Planning and Development Guide]&#40;project_plan_dev.md&#41;: This document helps you to understand how to create and maintain a project plan for your class project. **This document helps you to complete the Implementation Plan Milestone.**)

[//]: # ()
[//]: # (## Gradle Project)

[//]: # (Import this project into your Intellij editor. It should automatically recognise this as a gradle repository.)

[//]: # (The starter code was built using SDK version 11.0.1. Ensure that you are using this version for this project. &#40;You can, of course, change the SDK version as per your requirement if your team has all agreed to use a different version&#41;)

[//]: # ()
[//]: # (You have been provided with two starter files for demonstration: HelloWorld and HelloWorldTest.)

[//]: # ()
[//]: # (You will find HelloWorld in `src/main/java/tutorial` directory. Right click on the HelloWorld file and click on `Run HelloWorld.main&#40;&#41;`.)

[//]: # (This should run the program and print on your console.)

[//]: # ()
[//]: # (You will find HelloWorldTest in `src/test/java/tutorial` directory. Right click on the HelloWorldTest file and click on `Run HelloWorldTest`.)

[//]: # (All tests should pass. Your team can remove this sample of how testing works once you start adding your project code to the repo.)

[//]: # ()
[//]: # (Moving forward, we expect you to maintain this project structure. You *should* use Gradle as the build environment, but it is fine if your team prefers to use something else -- just remove the gradle files and push your preferred project setup. Assuming you stick with Gradle, your source code should go into `src/main/java` &#40;you can keep creating more subdirectories as per your project requirement&#41;. Every source class can auto-generate a test file for you. For example, open HelloWorld.java file and click on the `HelloWorld` variable as shown in the image below. You should see an option `Generate` and on clicking this your should see an option `Test`. Clicking on this will generate a JUnit test file for `HelloWorld` class. This was used to generate the `HelloWorldTest`.)

[//]: # ()
[//]: # (![image]&#40;https://user-images.githubusercontent.com/5333020/196066655-d3c97bf4-fdbd-46b0-b6ae-aeb8dbcf351d.png&#41;)

[//]: # ()
[//]: # (You can create another simple class and try generating a test for this class.)
