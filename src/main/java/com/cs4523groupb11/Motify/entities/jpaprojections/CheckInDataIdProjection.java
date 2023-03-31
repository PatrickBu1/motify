package com.cs4523groupb11.Motify.entities.jpaprojections;

import com.cs4523groupb11.Motify.entities.CheckInData;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "checkInDataIdProjection", types = { CheckInData.class })
public interface CheckInDataIdProjection {
    String getId();
}

