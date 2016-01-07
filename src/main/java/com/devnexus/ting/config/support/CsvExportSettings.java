package com.devnexus.ting.config.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Global Settings for CSV Exports.
 */
@Configuration
@ConfigurationProperties(prefix="devnexus.csv")
public class CsvExportSettings {
    
    private String  dateFormat;
    private static final String DEFAULT_DATE_FORMAT = "mm/dd/yyyy";

    public String getDateFormat() {
        if(dateFormat == null || dateFormat.isEmpty()) {
            return DEFAULT_DATE_FORMAT;
        }
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    
    
}
