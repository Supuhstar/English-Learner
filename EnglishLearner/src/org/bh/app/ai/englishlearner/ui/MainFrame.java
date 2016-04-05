package org.bh.app.ai.englishlearner.ui;

import javax.swing.JFrame;

/**
 * MainFrame, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * The main window frame, containing only the {@link MainApplet}
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-04
 */
public class MainFrame extends JFrame {

    private final MainApplet mainApplet = new MainApplet();

    public MainFrame() {
        initGUI();
    }

    private void initGUI() {
        setContentPane(mainApplet);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setMinimumSize(getSize());
    }
}
