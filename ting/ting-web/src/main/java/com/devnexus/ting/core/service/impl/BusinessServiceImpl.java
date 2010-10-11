/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package com.devnexus.ting.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnexus.ting.core.dao.PresentationDao;
import com.devnexus.ting.core.dao.SpeakerDao;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;
import com.devnexus.ting.core.service.BusinessService;

/**
 *
 * @author Gunnar Hillert
 *
 * @version $Id: UserServiceImpl.java 564 2010-06-08 04:36:23Z ghillert $
 */
@Service("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService {

	@Autowired private PresentationDao presentationDao;
	@Autowired private SpeakerDao      speakerDao;

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

	@Override
	public List<Presentation> getAllPresentations() {
		return presentationDao.getAll();
	}

	@Override
	public List<Speaker> getAllSpeakers() {
		return speakerDao.getAll();
	}

}
