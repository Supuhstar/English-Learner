package org.bh.app.ai.englishlearner.ai;

import org.bh.tools.being.Emotion;
import org.bh.tools.being.Emotions;
import org.bh.tools.util.ArrayPP;

/**
 * BasicEmotions, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * A basic implementation of an artificial intelligence.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
public class BasicEmotions implements Emotions {

    ArrayPP<Emotion> emotions = new ArrayPP<>();

    public BasicEmotions() {
    }

    @Override
    public Iterable<Emotion> getAllEmotions() {
        return emotions;
    }

    private static class NullEmotion implements Emotion {

        // I don't know what to put here, yet.

        public NullEmotion() {
        }
    }

}
