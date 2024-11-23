package com.activities.pricing.domain.repositories;

import com.activities.pricing.domain.entities.Activity;

public interface ActivityRepository {

    Activity findByCode(String activityCode);
}
