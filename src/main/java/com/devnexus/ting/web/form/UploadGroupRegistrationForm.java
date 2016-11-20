/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devnexus.ting.web.form;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author summers
 */
public class UploadGroupRegistrationForm {
    
    private MultipartFile registrationFile;
    private Boolean overrideRegistration = Boolean.FALSE;
    private Boolean sendEmail = Boolean.FALSE;

    public MultipartFile getRegistrationFile() {
        return registrationFile;
    }

    public void setRegistrationFile(MultipartFile registrationFile) {
        this.registrationFile = registrationFile;
    }

    public Boolean getOverrideRegistration() {
        return overrideRegistration;
    }

    public void setOverrideRegistration(Boolean overrideRegistration) {
        this.overrideRegistration = overrideRegistration;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }
    
    
    
}
