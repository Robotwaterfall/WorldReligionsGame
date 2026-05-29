package io.github.com.quillraven.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Interaction implements Component{
    public static final ComponentMapper<Interaction> MAPPER = ComponentMapper.getFor(Interaction.class);

    public float talkRange;

    public Interaction(float talkRange) {
        this.talkRange = talkRange;
    }

}
