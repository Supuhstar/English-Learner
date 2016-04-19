package org.bh.app.ai.englishlearner.ai;

import org.bh.tools.being.Emotions;
import org.bh.tools.being.Gender;
import org.bh.tools.being.Mind;

/**
 * BasicAIMind, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * A basic implementation of an artificial intelligence's mind.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
class BasicAIMind implements Mind {

    private Gender gender = new BasicGender();
    private Emotions emotions = new BasicEmotions();

    public BasicAIMind() {
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Emotions getEmotions() {
        return emotions;
    }

}
