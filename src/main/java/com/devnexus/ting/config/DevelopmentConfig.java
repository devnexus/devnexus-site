/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devnexus.ting.config;

import com.devnexus.ting.common.SpringProfile;
import javax.inject.Inject;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import ro.isdc.wro.http.ConfigurableWroFilter;

/**
 *
 * @author summers
 */
@Configuration
@Profile({SpringProfile.DEVELOPMENT_ENABLED})
@ImportResource("classpath:spring/spring-development-context.xml")
public class DevelopmentConfig {

    @Inject
    private ConfigurableWroFilter filter;

    @Bean
    @Profile({SpringProfile.DEVELOPMENT_ENABLED})
    public FilterRegistrationBean configureWroFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/wro/*");
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }

}
