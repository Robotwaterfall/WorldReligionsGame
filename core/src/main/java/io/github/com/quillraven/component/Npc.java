package io.github.com.quillraven.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Npc implements Component{
    public static final ComponentMapper<Npc> MAPPER = ComponentMapper.getFor(Npc.class);

    public String name;
    public String[] dialogue;
    public int currentLine = 0;

    public Npc(String name, String[] dialogue) {
        this.name = name;
        this.dialogue = dialogue;
    }

}
