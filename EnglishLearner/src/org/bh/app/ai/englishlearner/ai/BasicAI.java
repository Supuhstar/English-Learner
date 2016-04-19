package org.bh.app.ai.englishlearner.ai;

import java.util.function.Consumer;
import org.bh.tools.being.Being;
import org.bh.tools.being.BeingCommunicationListener;
import org.bh.tools.being.Body;
import org.bh.tools.being.Mind;
import org.bh.tools.util.MutableArrayPP;

/**
 * BasicAI, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * A basic implementation of an artificial intelligence.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
public class BasicAI implements AI<CharSequence> {

    private String name;
    private final Mind mind = new BasicAIMind();
    private final MutableArrayPP<BeingCommunicationListener<CharSequence>> listeners = new MutableArrayPP<>();

    public BasicAI(String name) {
        this.name = name;
    }

    @Override
    public void processInputString(CharSequence input) {
        outputString("I'm not alive, yet.");
    }

    @Override
    public CharSequence getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Mind getMind() {
        return mind;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public Being listenForCommunication(BeingCommunicationListener<CharSequence> newListener) {
        listeners.add(newListener);
        return this;
    }

    private void outputString(CharSequence output) {
        listeners.stream().filter(l -> null != l).forEach(l -> l.beingDidCommunicate(output));
    }

}
