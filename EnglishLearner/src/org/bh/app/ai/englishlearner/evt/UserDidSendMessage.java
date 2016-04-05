package org.bh.app.ai.englishlearner.evt;

/**
 * UserDidSendMessage, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Called when the user intends to send a message.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-03
 */
public interface UserDidSendMessage {

    /**
     * Called when the user intends to send a message.
     *
     * @param message The message the user intends to send.
     */
    public void userDidSendMessage(CharSequence message);
}
