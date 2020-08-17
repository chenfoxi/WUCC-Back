package org.wucc.backservice.model;

/**
 * Created by foxi.chen on 17/08/20.
 *
 * @author foxi.chen
 */
public enum EDayOfWeek {
    Monday,
    Tuesday,
    Wendsday,
    Thursday,
    Friday,
    Saturday,
    Sunday;

    public int getValue() {
        return ordinal() + 1;
    }
}
