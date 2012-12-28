/*
 * Copyright 2002-2011 the original author or authors.
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

/**
 * The Model package contains the model objects.
 */
@org.hibernate.annotations.GenericGenerators(
	{
	@org.hibernate.annotations.GenericGenerator(
		name="hibseq",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1000")
		}
	 )
	 }
)
package com.devnexus.ting.core.model;
