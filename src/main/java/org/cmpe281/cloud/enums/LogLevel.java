package org.cmpe281.cloud.enums;

/**
 * @author KH1868
 */
public enum LogLevel {
    FATAL(0),
    ERROR(1),
    WARN(2),
    INFO(3),
    DEBUG(4);
    private final int level;

    LogLevel(int level) {
        this.level = 1 << level;
    }

    public int getLevel() {
        return level;
    }
}
