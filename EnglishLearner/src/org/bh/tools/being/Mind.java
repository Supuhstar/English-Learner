package org.bh.tools.being;

/**
 * Mind, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * A model of a mind.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
public interface Mind {

    /**
     * @return This mind's gender.
     */
    public Gender getGender();

    /**
     * @return This mind's emotions.
     */
    public Emotions getEmotions();
}
