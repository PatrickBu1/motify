package com.cs4523groupb11.Motify.entities.jpaprojections;

import com.cs4523groupb11.Motify.entities.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userIdProjection", types = { User.class })
public interface UserIdProjection {
    String getId();
}
