package org.bh.app.ai.englishlearner.ui;

import java.awt.HeadlessException;
import javax.swing.JApplet;

/**
 * MainApplet, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * The main applet, containing only the {@link MainPanel}
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-04
 */
public class MainApplet extends JApplet {

    private final MainPanel mainPanel = new MainPanel();

    public MainApplet() throws HeadlessException {
        initGUI();
    }

    private void initGUI() {
        setContentPane(mainPanel);
    }
}
