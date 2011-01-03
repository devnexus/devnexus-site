package com.devnexus.ting.web.sitemesh;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.mvc.DeviceResolverHandlerInterceptor;

import java.util.Properties;

/**
 * This mapper is based on the ParameterDecoratorMapper that comes with SiteMesh.
 * This Mapper targets Spring Mobile and will apply a mobile decorator only if
 * the devices this request originates from is in fact a mobile device.
 *
 * As a result this mapper has a hard dependency onto Spring Mobile.
 *
 * @author Gunnar Hillert
 * @version $id$
 *
 * @see com.opensymphony.module.sitemesh.DecoratorMapper
 */
public class SpringMobileParameterDecoratorMapper extends AbstractDecoratorMapper {
    private String decoratorName = null;

    public void init(Config config, Properties properties, DecoratorMapper parent) throws InstantiationException {
        super.init(config, properties, parent);
        decoratorName = properties.getProperty("decorator.name", "mobile");
    }

    public Decorator getDecorator(final HttpServletRequest request, final Page page) {

        final Device device = DeviceResolverHandlerInterceptor.getCurrentDevice(request);

        if (device != null && device.isMobile()) {
        	return getNamedDecorator(request, decoratorName);
        } else {
        	return super.getDecorator(request, page);
        }

    }
}
