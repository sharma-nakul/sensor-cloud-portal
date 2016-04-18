package org.cmpe281.cloud.enums;

/**
 * @author Yassaman
 */

public enum SensorState {
    RUNNING(1),
    STOPPED(0);


    private final int stateCode;

    SensorState(int stateCode) {
        this.stateCode = stateCode;
    }

    public int getStateCode() {
        return this.stateCode;
    }
}
