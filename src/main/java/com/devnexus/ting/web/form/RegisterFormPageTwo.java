/*
 * Copyright 2002-2014 the original author or authors.
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

import com.devnexus.ting.model.BaseModelObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author summers
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterFormPageTwo extends BaseModelObject {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1071233976549394025L;

    @NotNull
    private Integer ticketCount;

    @NotNull
    private Long ticketGroup;

    private String couponCode;
    
    private List<TickerOrderDetail> orderDetails = new ArrayList<>();

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Long getTicketGroup() {
        return ticketGroup;
    }

    public void setTicketGroup(Long ticketGroup) {
        this.ticketGroup = ticketGroup;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<TickerOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<TickerOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    
    
    public void copyPageOne(RegisterForm registerForm) {
        setCouponCode(registerForm.getCouponCode());
        setTicketCount(registerForm.getTicketCount());
        setTicketGroup(registerForm.getTicketGroup());
        IntStream.range(0,registerForm.getTicketCount()).forEach(ignore -> {orderDetails.add(new TickerOrderDetail());});
    }

    
    
    
}
