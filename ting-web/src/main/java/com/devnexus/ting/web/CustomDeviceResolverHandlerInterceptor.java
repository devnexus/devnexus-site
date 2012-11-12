package com.devnexus.ting.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.mobile.device.site.SitePreferenceHandler;
import org.springframework.web.servlet.HandlerInterceptor;

public class CustomDeviceResolverHandlerInterceptor extends
        DeviceResolverHandlerInterceptor {

    private final DeviceResolver deviceResolver;

    /**
     * Create a device resolving {@link HandlerInterceptor} that defaults to a {@link LiteDeviceResolver} implementation.
     */
    public CustomDeviceResolverHandlerInterceptor() {
        this(new LiteDeviceResolver());
    }

    /**
     * Create a device resolving {@link HandlerInterceptor}.
     * @param deviceResolver the device resolver to delegate to in {@link #preHandle(HttpServletRequest, HttpServletResponse, Object)}.
     */
    public CustomDeviceResolverHandlerInterceptor(DeviceResolver deviceResolver) {
        this.deviceResolver = deviceResolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final Device device = deviceResolver.resolveDevice(request);
        request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE, device);


        if (request.getServletPath().startsWith("/mobile")) {
            request.setAttribute("baseSiteUrl", "/mobile");
            request.setAttribute(SitePreferenceHandler.CURRENT_SITE_PREFERENCE_ATTRIBUTE, SitePreference.MOBILE);
        } else if (request.getServletPath().startsWith("/desktop")) {
            request.setAttribute("baseSiteUrl", "/desktop");
            request.setAttribute(SitePreferenceHandler.CURRENT_SITE_PREFERENCE_ATTRIBUTE, SitePreference.NORMAL);
        } else {

            request.setAttribute("baseSiteUrl", "/s");

            if (device.isMobile()) {
                request.setAttribute(SitePreferenceHandler.CURRENT_SITE_PREFERENCE_ATTRIBUTE, SitePreference.MOBILE);
            } else {
                request.setAttribute(SitePreferenceHandler.CURRENT_SITE_PREFERENCE_ATTRIBUTE, SitePreference.NORMAL);
            }

        }

        return true;
    }

    private static class MyDevice implements Device {

        public static final MyDevice MOBILE_INSTANCE = new MyDevice(true);

        public static final MyDevice NOT_MOBILE_INSTANCE = new MyDevice(false);

        private final boolean mobile;

        @Override
        public boolean isMobile() {
            return this.mobile;
        }

        private MyDevice(boolean mobile) {
            this.mobile = mobile;
        }

		@Override
		public boolean isNormal() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isTablet() {
			// TODO Auto-generated method stub
			return false;
		}

    }
}
