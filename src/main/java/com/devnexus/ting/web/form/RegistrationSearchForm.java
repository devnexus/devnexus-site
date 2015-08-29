package com.devnexus.ting.web.form;

public class RegistrationSearchForm {

    public RegistrationSearchForm() {
    }

    private Long event;
    private Long eventSignup;
    private String email;
    private String name;

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long getEventSignup() {
        return eventSignup;
    }

    public void setEventSignup(Long eventSignup) {
        this.eventSignup = eventSignup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
