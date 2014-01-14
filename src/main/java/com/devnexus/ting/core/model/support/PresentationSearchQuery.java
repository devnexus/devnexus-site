package com.devnexus.ting.core.model.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.devnexus.ting.core.model.Event;
import com.devnexus.ting.core.model.PresentationTag;
import com.devnexus.ting.core.model.PresentationType;
import com.devnexus.ting.core.model.SkillLevel;
import com.devnexus.ting.core.model.Track;

public class PresentationSearchQuery {

	private Event event;
	private Track track;
	private SkillLevel skillLevel;
	private PresentationType presentationType;
	private List<PresentationTag> presentationTags = new ArrayList<>(0);

	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}
	public SkillLevel getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}
	public PresentationType getPresentationType() {
		return presentationType;
	}
	public void setPresentationType(PresentationType presentationType) {
		this.presentationType = presentationType;
	}
	public List<PresentationTag> getPresentationTags() {
		return presentationTags;
	}

	public List<String> getPresentationTagNames() {
		List<String> n = new ArrayList<>();

		for (PresentationTag presentationTag : this.presentationTags) {
			n.add(presentationTag.getName());
		}
		return n;
	}

	public void setPresentationTags(List<PresentationTag> presentationTags) {
		this.presentationTags = presentationTags;
	}

	public static PresentationSearchQuery create(Event event, Long trackId,
			String trackName, String presentationTypeName, String skillLevelName, String presentationTagsAsString) {

		if (trackId == null && trackName == null && presentationTypeName == null && skillLevelName == null && presentationTagsAsString == null ) {
			return null;
		}

		final PresentationSearchQuery presentationSearchQuery = new PresentationSearchQuery();
		presentationSearchQuery.setEvent(event);

		if (trackId != null) {
			final Track track = new Track();
			track.setId(trackId);
			presentationSearchQuery.setTrack(track);
		}
		else if (StringUtils.hasText(trackName)) {
			final Track track = new Track();
			track.setName(StringUtils.trimWhitespace(trackName).toLowerCase(Locale.ENGLISH));
			presentationSearchQuery.setTrack(track);
		}

		if (StringUtils.hasText(presentationTypeName)) {
			final PresentationType presentationType = PresentationType.valueOf(StringUtils.trimWhitespace(presentationTypeName).toUpperCase(Locale.ENGLISH));
			presentationSearchQuery.setPresentationType(presentationType);
		}

		if (StringUtils.hasText(skillLevelName)) {
			final SkillLevel skillLevel = SkillLevel.valueOf(StringUtils.trimWhitespace(skillLevelName).toUpperCase(Locale.ENGLISH));
			presentationSearchQuery.setSkillLevel(skillLevel);
		}

		if (StringUtils.hasText(presentationTagsAsString)) {

			final Set<String> tagNames = StringUtils.commaDelimitedListToSet(presentationTagsAsString);

			for (String tagName : tagNames) {
				PresentationTag presentationTag = new PresentationTag();
				presentationTag.setName(tagName);
				presentationSearchQuery.getPresentationTags().add(presentationTag);
			}
		}

		return presentationSearchQuery;
	}
}
