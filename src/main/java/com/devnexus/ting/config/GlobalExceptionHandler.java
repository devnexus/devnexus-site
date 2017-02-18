/*
 * Copyright 2017 the original author or authors.
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
package com.devnexus.ting.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.devnexus.ting.web.controller.ScheduleController;

/**
 * Global Exception Handler
 *
 * @author Gunnar Hillert
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@Autowired
	private MultipartProperties multipartProperties;

	@ExceptionHandler(value = MultipartException.class)
	public String multipartErrorHandler(MultipartException multipartException,
			RedirectAttributes redirectAttributes) throws Exception {

		String errorMessage = String.format(
				  "Please double-check the file that you are trying to upload. It may be too big. Uploaded files cannot"
				  + "be bigger than <strong>%s</strong>."
				+ " Please go back and upload a smaller image file.", multipartProperties.getMaxFileSize());
		redirectAttributes.addFlashAttribute("error", errorMessage);
		return "redirect:/s/uploadError";
	}

	@ExceptionHandler(value = Exception.class)
	public String fallbackErrorHandler(HttpServletRequest request, Exception exception,
			RedirectAttributes redirectAttributes) throws Exception {

		String errorMessage = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
		LOGGER.error("Caught Global Error", exception);
		redirectAttributes.addFlashAttribute("error", errorMessage);
		return "redirect:/s/handleGlobaleErrors";
	}
}
