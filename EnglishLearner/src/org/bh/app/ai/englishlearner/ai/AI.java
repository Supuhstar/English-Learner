package org.bh.app.ai.englishlearner.ai;

import org.bh.tools.being.Being;

/**
 * AI, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * An artificial intelligence.
 *
 * @param <ComType> The base type of communication of which this AI is capable.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
public interface AI<ComType> extends Being<ComType> {

    /**
     * Tell the AI to process the given input
     *
     * @param input The input for the AI to process
     */
    public void processInputString(CharSequence input);
}
