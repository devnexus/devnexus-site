package com.devnexus.ting.common;

/**
 * Defines if a job was posted by a company (direct-hire) or
 * by a recruiter.
 */
public enum SpringContextMode {

    DemoContextConfiguration("DemoContextConfiguration"),
    ProductionContextConfiguration("ProductionContextConfiguration");

    String code;

    /**
     * Constructor.
     *
     * @param name The name for display puposes.
     * @param descriptionKey Provides description from the resource bundle.
     */
    SpringContextMode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}