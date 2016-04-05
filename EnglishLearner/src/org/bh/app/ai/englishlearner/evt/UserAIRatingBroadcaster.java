package org.bh.app.ai.englishlearner.evt;

/**
 * MainPanel, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Implementing classes will diligently listen for the user to rate an AI's output through them, and relay that message
 * via {@link #listenForUserAIRating(UserAIRatingListener) listenForUserAIRating}
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-03-30
 */
public interface UserAIRatingBroadcaster {

    /**
     * Tells this broadcaster to call the given listener when the user rates the AI.
     *
     * @param listener The listener to call when appropriate.
     */
    public void listenForUserAIRating(UserAIRatingListener listener);
}
