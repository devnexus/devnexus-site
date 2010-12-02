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
package com.devnexus.ting.core.service;

import java.util.List;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.Presentation;
import com.devnexus.ting.core.model.Speaker;

/**
 * Provides user related methods.
 *
 * @author Gunnar Hillert
 *
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface BusinessService {

	/**
	 *
	 * @return
	 */
	public List<Speaker> getAllSpeakers();

	/**
	 *
	 * @return
	 */
	public List<Presentation> getAllPresentations();

	public void saveEvent(Event event);

	public List<Event> getAllEvents();

	public Speaker saveSpeaker(Speaker speaker);

	public void savePresentation(Presentation presentation);

	public Event getEvent(Long id);
	public Speaker getSpeaker(Long id);
	public Presentation getPresentation(Long id);

	public void deleteEvent(Event event);
	public void deleteSpeaker(Speaker speaker);
	public void deletePresentation(Presentation presentation);

	public List<Speaker> getSpeakersForCurrentEvent();
	public List<Presentation> getPresentationsForCurrentEvent();

	public List<Speaker> getSpeakersForEvent(Long eventId);
	public List<Presentation> getPresentationsForEvent(Long eventId);

	public Event getEventByEventKey(String eventKey);

	public List<Event> getAllNonCurrentEvents();

}
