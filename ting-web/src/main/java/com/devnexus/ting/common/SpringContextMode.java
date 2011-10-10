package com.devnexus.ting.common;

/**
 * Defines if a job was posted by a company (direct-hire) or
 * by a recruiter.
 */
public enum SpringContextMode {

    DemoContextConfiguration("default"),
    ProductionContextConfiguration("standalone");

    String profile;

    /**
     * Constructor.
     *
     * @param code The Spring context profile identifier.
     */
    SpringContextMode(final String profile) {
        this.profile = profile;
    }

    public String getCode() {
        return profile;
    }

}