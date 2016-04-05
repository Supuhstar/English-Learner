package org.bh.app.ai.englishlearner.evt;

/**
 * MainPanel, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Implementing classes will diligently listen for the user to send a message through them, and relay that message via
 * {@link #listenForUserDidSendMessage(UserDidSendMessage) listenForUserDidSendMessage}
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-04
 */
public interface UserMessageSendBroadcaster {

    /**
     * Tells this broadcaster to call the given listener when the user intends to send a message.
     *
     * @param listener The listener to call when appropriate.
     */
    public void listenForUserDidSendMessage(UserDidSendMessage newSendListener);
}
