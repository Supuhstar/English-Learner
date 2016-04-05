package org.bh.app.ai.englishlearner.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.bh.app.ai.englishlearner.evt.UserAIRatingBroadcaster;
import org.bh.app.ai.englishlearner.evt.UserAIRatingListener;
import org.bh.app.ai.englishlearner.evt.UserDidSendMessage;
import org.bh.app.ai.englishlearner.evt.UserMessageSendBroadcaster;

/**
 * MainPanel, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * The panel by which the user give the AI input.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-03-30
 */
public class UserInputArea extends JPanel implements UserAIRatingBroadcaster, UserMessageSendBroadcaster {

    private final UserTextInputArea userTextInputArea = new UserTextInputArea();
    private final UserFeedbackInputArea userFeedbackInputArea = new UserFeedbackInputArea();

    public UserInputArea() {
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());
        add(userTextInputArea, BorderLayout.PAGE_START);

        userFeedbackInputArea.setEnabled(false);
        add(userFeedbackInputArea, BorderLayout.PAGE_END);
    }

    /**
     * The given non-null listener will be called when the user intends to send a message.
     *
     * @param newSendListener The listener to be called when the user intends to send a message.
     */
    @Override
    public void listenForUserDidSendMessage(UserDidSendMessage newSendListener) {
        userTextInputArea.listenForUserDidSendMessage(newSendListener);
    }

    @Override
    public void listenForUserAIRating(UserAIRatingListener newRatingListener) {
        userFeedbackInputArea.listenForUserAIRating(newRatingListener);
    }
}
