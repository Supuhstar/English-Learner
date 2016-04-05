package org.bh.app.ai.englishlearner.evt;

/**
 * UserAIRatingListener, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Implementing classes listen for the user to rate the AI through them, and perform some action via
 * {@link #userDidRateAI(double) userDidRateAI}
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-04
 */
public interface UserAIRatingListener {

    /**
     * Called when the user rates the AI
     *
     * @param userRating The user's rating for the AI's behavior, where, logarithmically, 0 is neutral, 1 is good, -1 is
     *                   bad, Infinity is perfect, and Negative Infinity is the worst.
     */
    public void userDidRateAI(double userRating);
}
