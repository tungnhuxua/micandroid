package org.light.portal.social;

import org.light.portal.model.Entity;
import org.light.portal.model.SocialActivity;

public interface ActivityUpdater {
	public void addActivity(SocialActivity activity);
	public void addActivity(Entity entity);
	public void deleteActivity(Entity entity);
}
