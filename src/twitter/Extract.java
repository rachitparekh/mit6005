/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;
import java.lang.Object;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertFalse;
import java.util.regex.*;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */    
    
    public static Instant getStart(List<Tweet> tweets) {
        //assertFalse(tweets.isEmpty());
        if (tweets.isEmpty()){
            return Instant.now();
        }
        Instant starttime = Instant.MAX;
        for (Tweet tweet : tweets) {
            if (tweet.getTimestamp().isBefore(starttime)) {
                starttime = tweet.getTimestamp();
            }
        }
        return starttime;
    }

    public static Instant getEnd(List<Tweet> tweets) {
        //assertFalse(tweets.isEmpty());
        if (tweets.isEmpty()){
            return Instant.now();
        }
        Instant endtime = Instant.MIN;
        for (Tweet tweet : tweets) {
            if (tweet.getTimestamp().isAfter(endtime)) {
                endtime = tweet.getTimestamp();
            }
        }
        return endtime;
    }

    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            return new Timespan(Instant.now(), Instant.now());
        } else {
            Instant start = getStart(tweets);
            Instant end = getEnd(tweets);
            return new Timespan(start, end);

        }

    }

    // throw new RuntimeException("not implemented");

    private static void assertFalse(boolean empty) {
        // TODO Auto-generated method stub

    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec). The username-mention cannot
     *         be immediately preceded or followed by any character valid in a
     *         Twitter username. For this reason, an email address like
     *         bitdiddle@mit.edu does NOT contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    //http://www.tutorialspoint.com/java/java_regular_expressions.htm
    
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        Pattern pattern = Pattern.compile("@(\\w+|\\W+)");
        Set<String> mentionedusers = new HashSet<String>();
        for (Tweet tweet : tweets) {
            String substring = tweet.getText();
            Matcher matcher = pattern.matcher(substring.toLowerCase());
            List<String> mentionedusersLowerCase = new ArrayList<String>();
            while(matcher.find()){
                System.out.println(matcher.group(1));
                mentionedusersLowerCase.add(matcher.group(1)); 
                }
            mentionedusers.addAll(mentionedusersLowerCase);
            }
        return mentionedusers;
        
                
    }

}
