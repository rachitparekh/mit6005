/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this
     *            method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username, in
     *         the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        assertFalse(tweets.isEmpty());
        List<Tweet> tweetsfromusername = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (tweet.getAuthor().toLowerCase().equals(username.toLowerCase())) {
                tweetsfromusername.add(tweet);
            }

        }
        return tweetsfromusername;
        // throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this
     *            method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the
     *         timespan, in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> tweetsintimeSpan = new ArrayList<>();
        for (Tweet tweet : tweets) {
            // if(start time of tweet >timespan.start and <timespan.end add to
            // tweetsinstimeSpan
            if (tweet.getTimestamp().isBefore(timespan.getEnd()) && tweet.getTimestamp().isAfter(timespan.getStart())) {
                tweetsintimeSpan.add(tweet);
            }
        }
        return tweetsintimeSpan;

        // throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this
     *            method.
     * @param words
     *            a list of words to search for in the tweets. A word is a
     *            nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text
     *         (when represented as a sequence of nonempty words bounded by
     *         space characters and the ends of the string) includes *at least
     *         one* of the words found in the words list. Word comparison is not
     *         case-sensitive, so "Obama" is the same as "obama". The returned
     *         tweets are in the same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<String> loweredList = new ArrayList<>();
        for (String lowered : words) {
            loweredList.add(lowered.toLowerCase());
        }

        List<Tweet> tweetsWithKeyWords = new ArrayList<>();
        for (Tweet tweet : tweets) {
            // compare list words with tweet and if it contains atleast 1 of the
            // words in the List words
            List<String> wordsfromtweet = new ArrayList<String>(Arrays.asList(tweet.getText().split(" ")));
            for (String st : wordsfromtweet) {
                if (loweredList.contains(st.toLowerCase())) {
                    tweetsWithKeyWords.add(tweet);
                    break;
                }

            }
        }
        return tweetsWithKeyWords;
        // throw new RuntimeException("not implemented");
    }

}
