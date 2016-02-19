/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:25:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:45:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T11:47:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T10:47:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "randomuser", "can we please pass this test??", d3);
    private static final Tweet tweet4 = new Tweet(4, "randomuser", "@test1 is this another one of these stupid tests?",d4);
    private static final Tweet tweet5 = new Tweet(5, "randomuser2", "@test1 @test2 can't believe I'm doing another",d5);
    private static final Tweet tweet6 = new Tweet(6, "randomuser2", "@test1 @test1 can't believe I'm doing another",d6);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    @Test
    public void testGetTimespanThreeTweets1() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
        
        assertEquals(d1, timespan.getStart());
        assertEquals(d3, timespan.getEnd());
    }
    
    @Test
    public void testGetTimespanTwoTweets1() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals(d1, timespan.getStart());
        assertEquals(d2, timespan.getEnd());
    }
    //This test should give the same start and end for the single tweet
    @Test
    public void testGetTimespanOneTweet() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        assertEquals(d1, timespan.getStart());
        assertEquals(d1, timespan.getEnd());        
    }
    
    @Test
    public void testGetTimespanEmptyTweet() {
        Timespan timespan = Extract.getTimespan(new ArrayList<Tweet>());
        assertEquals(timespan.getEnd(), timespan.getStart());         
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersOneMentionOneTweet() {         
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.contains("test1"));
    }
    
    public void testGetMentionedUsersTwoeMentionOneTweet() {         
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.containsAll(Arrays.asList("test1", "test2")));
    }
    
    public void testGetMentionedUsersTwoeMentionOneTweetrepeateduser() {         
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.contains("test1"));
    }
        
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */


