/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.web.form;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.devnexus.ting.model.BaseModelObject;
import com.devnexus.ting.model.TicketGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Size;

/**
 *
 * @author Summers Pittman
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterForm extends BaseModelObject {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1071233976549394025L;

    @NotNull
    @Size(min = 1, max = 255)
    private String contactName;

    @NotNull
    @Size(min = 1, max = 255)
    private String contactEmailAddress;

    @NotNull
    @Size(min = 1, max = 255)
    private String contactPhoneNumber;

    private List<TicketGroupRegistration> ticketGroupRegistrations = new ArrayList<>();

    public RegisterForm(List<TicketGroup> groups) {
        groups.stream().forEach((group) -> {ticketGroupRegistrations.add(new TicketGroupRegistration(group));});
    }

    
    public RegisterForm() {
    }


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public List<TicketGroupRegistration> getTicketGroupRegistrations() {
        return ticketGroupRegistrations;
    }

    public void setTicketGroupRegistrations(List<TicketGroupRegistration> ticketGroupRegistrations) {
        this.ticketGroupRegistrations = ticketGroupRegistrations;
    }

    
    
    public final static class TicketGroupRegistration {
        
        private Integer ticketCount = 0;
        
        @NotNull
        private Long ticketGroupId;
        
        private String couponCode = "";

        private TicketGroup group;
        
        public TicketGroupRegistration(TicketGroup group) {
            this.ticketGroupId = group.getId();
            this.group = group;
        }

        public TicketGroupRegistration() {
        }

        
        public Integer getTicketCount() {
            return ticketCount;
        }

        public void setTicketCount(Integer ticketCount) {
            this.ticketCount = ticketCount;
        }

        public Long getTicketGroupId() {
            return ticketGroupId;
        }

        public void setTicketGroupId(Long ticketGroupId) {
            this.ticketGroupId = ticketGroupId;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public TicketGroup getGroup() {
            return group;
        }

        public void setGroup(TicketGroup group) {
            this.group = group;
        }

        
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 79 * hash + Objects.hashCode(this.ticketCount);
            hash = 79 * hash + Objects.hashCode(this.ticketGroupId);
            hash = 79 * hash + Objects.hashCode(this.couponCode);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TicketGroupRegistration other = (TicketGroupRegistration) obj;
            if (!Objects.equals(this.ticketCount, other.ticketCount)) {
                return false;
            }
            if (!Objects.equals(this.ticketGroupId, other.ticketGroupId)) {
                return false;
            }
            if (!Objects.equals(this.couponCode, other.couponCode)) {
                return false;
            }
            return true;
        }
        
        
        
    }
    
}
