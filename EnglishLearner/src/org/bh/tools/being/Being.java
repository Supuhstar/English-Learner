package org.bh.tools.being;

/**
 * Being, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * A being.
 *
 * @param <ComType> The base type of communication of which this being is capable.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
public interface Being<ComType> {

    public CharSequence getName();

    public Mind getMind();

    public Body getBody();

    public Being listenForCommunication(BeingCommunicationListener<ComType> newListener);
}
