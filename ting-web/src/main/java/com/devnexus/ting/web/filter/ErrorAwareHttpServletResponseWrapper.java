package com.devnexus.ting.web.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 *
 * @author Gunnnar Hillert
 * @version $Id$
 */
public class ErrorAwareHttpServletResponseWrapper extends HttpServletResponseWrapper {

     int errorCode;

     public ErrorAwareHttpServletResponseWrapper(HttpServletResponse httpServletResponse) {
            super(httpServletResponse);
     }

     @Override
    public void sendError(int errorCode) throws IOException {
        this.errorCode = errorCode;
        super.sendError(errorCode);
    }

    @Override
    public void sendError(int errorCode, String msg) throws IOException {
        this.errorCode = errorCode;
        super.sendError(errorCode, msg);
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

}
