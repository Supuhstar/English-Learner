package org.bh.app.ai.englishlearner.ui;

import java.awt.Desktop;
import java.util.HashMap;

/**
 * UIController, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Controls the user interface
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-17
 */
public class UIController {

    private static final HashMap<Object, Boolean> IDS_AND_RUNNING_STATES = new HashMap<>();

    /**
     * Starts the UI with the given ID. If it's already running, this returns immediately. This will decide whether a
     * textual or graphical UI is best for the current environment.
     *
     * @param id   The ID of the UI to start.
     * @param args Any command-line arguments used to start this program.
     */
    public static void start(Object id, CharSequence... args) {
        if (null != args && args.length > 0) {

        }

        boolean isAlreadyRunning = IDS_AND_RUNNING_STATES.get(id);
        if (isAlreadyRunning) {
            return;
        }
        // TODO: else, start the UI
        if (canUseSwing()) {
            new MainFrame().setVisible(true);
        } else {
            System.err.println("Sorry, this requires a desktop environment to run.");
        }
    }

    private static boolean canUseSwing() {
        return Desktop.isDesktopSupported();
    }
}
