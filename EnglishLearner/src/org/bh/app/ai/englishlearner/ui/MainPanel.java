package org.bh.app.ai.englishlearner.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * MainPanel, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * The main interface between the user and the AI.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-03-30
 */
public class MainPanel extends JPanel {

    private final AIOutputArea outputArea = new AIOutputArea();
    private final UserInputArea inputArea = new UserInputArea();

    public MainPanel() {
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());
        add(outputArea, BorderLayout.CENTER);
        add(inputArea, BorderLayout.PAGE_END);
    }
}
