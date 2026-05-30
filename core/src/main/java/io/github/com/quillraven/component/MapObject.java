package io.github.com.quillraven.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class MapObject implements Component {
    public static final ComponentMapper<MapObject> MAPPER =
        ComponentMapper.getFor(MapObject.class);
}
