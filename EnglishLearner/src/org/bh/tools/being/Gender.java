package org.bh.tools.being;

/**
 * Gender, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * A mind's gender.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-04-06
 */
public interface Gender {

    /**
     * @return {@code 0} to {@code 1}, indicating a gender with 0% to 100% maleness.
     */
    public double getMaleness();

    /**
     * @return {@code 0} to {@code 1}, indicating a gender with 0% to 100% femaleness.
     */
    public double getFemaleness();

    /**
     * @return {@code 0} to {@code 1}, indicating a gender which is 0% to 100% unknown.
     */
    public double getUnknownness();

    /**
     * @return {@code 0} to {@code 1}, indicating how easily the gender is changed.
     */
    public double getFluidity();
}
