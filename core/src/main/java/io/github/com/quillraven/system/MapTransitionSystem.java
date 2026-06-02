package io.github.com.quillraven.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.com.quillraven.asset.MapAsset;
import io.github.com.quillraven.component.Player;
import io.github.com.quillraven.component.Trigger;
import io.github.com.quillraven.handler.MapTransitionHandler;

public class MapTransitionSystem extends IteratingSystem {

    private final MapTransitionHandler transitionHandler;

    public MapTransitionSystem(MapTransitionHandler transitionHandler) {
        super(Family.all(Trigger.class).get());
        this.transitionHandler = transitionHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Trigger trigger = Trigger.MAPPER.get(entity);
        Entity triggeringEntity = trigger.getTriggeringEntity();

        if (triggeringEntity == null) {
            return;
        }

        if (Player.MAPPER.get(triggeringEntity) == null) {
            return;
        }

        switch (trigger.getName()) {
            case "cfn_map_entrance" -> transitionHandler.transitionTo(MapAsset.CFN, "cfn_map_spawnpoint");
            case "main_map_entrance" -> transitionHandler.transitionTo(MapAsset.MAIN, "main_map_spawnpoint");
            case "main_map_house_exit" -> transitionHandler.transitionTo(MapAsset.MAIN, "main_map_house_spawnpoint");
            case "main_house_map_entrance" -> transitionHandler.transitionTo(MapAsset.MAIN_HOUSE, "main_house_spawnpoint");
        }

        trigger.setTriggeringEntity(null);
    }

}
