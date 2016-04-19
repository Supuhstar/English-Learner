package org.bh.tools.being;

/**
 * BeingCommunicationListener, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Listens for a being to communicate
 *
 * @param <ComType> The type of communication this will listen for
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-17
 */
public interface BeingCommunicationListener<ComType> {

    public void beingDidCommunicate(ComType communication);
}
