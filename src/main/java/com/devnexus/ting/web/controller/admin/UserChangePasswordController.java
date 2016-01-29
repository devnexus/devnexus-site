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
package com.devnexus.ting.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jasypt.digest.StringDigester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devnexus.ting.core.service.UserService;
import com.devnexus.ting.model.User;
import com.devnexus.ting.web.form.UserChangePasswordForm;

/**
 *
 * @author Gunnar Hillert
 *
 */
@Controller
public class UserChangePasswordController {

	@Autowired private UserService userService;

	@Autowired
	private StringDigester stringDigester;

	@RequestMapping(value="/s/admin/change-password", method=RequestMethod.GET)
	public String prepareChangePassword(ModelMap model) {

		UserChangePasswordForm userChangePasswordForm = new UserChangePasswordForm();

		model.addAttribute("passwordForm", userChangePasswordForm);

		return "/admin/user-change-password";
	}

	@RequestMapping(value="/s/admin/change-password", method=RequestMethod.POST)
	public String changePassword(
			@Valid UserChangePasswordForm userChangePasswordForm,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (request.getParameter("cancel") != null) {
			return "redirect:/s/admin/index";
		}

		if (result.hasErrors()) {
			return "/admin/user-change-password";
		}


		if (userChangePasswordForm.getPassword1().equals(userChangePasswordForm.getPassword2())) {
			userService.changePassword(userChangePasswordForm.getPassword1());
		}
		else {
			result.reject("Passwords did not match.");
			return "/admin/user-change-password";
		}

		redirectAttributes.addFlashAttribute("successMessage", "The password was changed successfully.");

		return "redirect:/s/admin/index";
	}

}
