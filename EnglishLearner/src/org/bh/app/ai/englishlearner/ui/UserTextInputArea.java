package org.bh.app.ai.englishlearner.ui;

import java.awt.BorderLayout;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.bh.app.ai.englishlearner.evt.UserDidSendMessage;
import org.bh.app.ai.englishlearner.evt.UserMessageSendBroadcaster;
import org.bh.tools.ui.BHTextArea;

/**
 * UserTextInputArea, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Allows the user to talk to the AI.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-03-30
 */
public class UserTextInputArea extends JPanel implements UserMessageSendBroadcaster {

    private final BHTextArea userInputArea = new BHTextArea(null, "Say something to your AI");
    private final JButton sendButton = new JButton("Send");
    private final LinkedList<UserDidSendMessage> sendListeners = new LinkedList<>();

    public UserTextInputArea() {
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());
        add(userInputArea, BorderLayout.CENTER);
        sendButton.addActionListener(evt -> {
            send();
        });
        add(sendButton, BorderLayout.LINE_END);
    }

    /**
     * The given non-null listener will be called when the user intends to send a message.
     *
     * @param newSendListener The listener to be called when the user intends to send a message.
     */
    @Override
    public void listenForUserDidSendMessage(UserDidSendMessage newSendListener) {
        if (null != newSendListener) {
            sendListeners.add(newSendListener);
        }
    }

    private void send() {
        final String currentMessage = getCurrentMessage();
        clearMessageArea();
        sendListeners.parallelStream().filter(t -> null != t).forEach(t -> t.userDidSendMessage(currentMessage));
    }

    private String getCurrentMessage() {
        return userInputArea.getText();
    }

    private void clearMessageArea() {
        userInputArea.setText("");
    }
}
