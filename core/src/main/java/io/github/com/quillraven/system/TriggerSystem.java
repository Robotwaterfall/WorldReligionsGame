package io.github.com.quillraven.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Timer;

import io.github.com.quillraven.asset.MapAsset;
import io.github.com.quillraven.asset.SoundAsset;
import io.github.com.quillraven.audio.AudioService;
import io.github.com.quillraven.component.Animation2D;
import io.github.com.quillraven.component.Life;
import io.github.com.quillraven.component.Tiled;
import io.github.com.quillraven.component.Trigger;
import io.github.com.quillraven.handler.MapTransitionHandler;

public class TriggerSystem extends IteratingSystem {
    private final AudioService audioService;

    private final MapTransitionHandler mapTransitionHandler;

    public TriggerSystem(AudioService audioService, MapTransitionHandler mapTransitionHandler) {
        super(Family.all(Trigger.class).get());
        this.audioService = audioService;
        this.mapTransitionHandler = mapTransitionHandler;
    }

    /**
     * Processes triggered entities and fires appropriate trigger effects.
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Trigger trigger = Trigger.MAPPER.get(entity);
        if (trigger.getTriggeringEntity() == null) return;

        fireTrigger(trigger.getName(), trigger.getTriggeringEntity());
        trigger.setTriggeringEntity(null);
    }

    private Entity getByTiledId(int tiledId) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Tiled.class).get());
        for (Entity entity : entities) {
            if (Tiled.MAPPER.get(entity).getId() == tiledId) {
                return entity;
            }
        }
        return null;
    }

    /**
     * Routes trigger events to appropriate handlers based on trigger name.
     */
    private void fireTrigger(String triggerName, Entity triggeringEntity) {
        switch (triggerName) {
            case "trap_trigger"      -> trapTrigger(triggeringEntity);
            case "cfn_map_entrance"  -> cfnMapEntrance(triggeringEntity);
            case "main_map_entrance" -> mainMapEntrance(triggeringEntity);
            case "main_house_map_entrance" -> mainHouseMapEntrance(triggeringEntity);
            case "main_map_house_exit" -> mainMapHouseExit(triggeringEntity);
            case "cfn_map_house_entrance" -> cfnMapHouseEntrance(triggeringEntity);
            case "cfn_map_house_exit" -> cfnMapHouseExit(triggeringEntity);
            case "hinduism_map_entrance" -> hinduismMapEntrance(triggeringEntity);
            case "cfn_map_entrance_hinduism" -> hinduismMapExit(triggeringEntity);
            case "hinduism_map_temple_entrance" -> hinduismMapHouseEntrance(triggeringEntity);
            case "hinduism_map_temple_exit" -> hinduismMapHouseExit(triggeringEntity);
            case "buddhism_map_entrance_hinduism" -> buddhismMapEntrance(triggeringEntity);
            case "hinduism_map_entrance_buddhism" -> hinduismMapEntrance(triggeringEntity);
            case "judaism_map_entrance" -> judaismMapEntrance(triggeringEntity);
            case "buddhism_map_entrance_judaism" -> judaismMapExit(triggeringEntity);
            case "christianity_map_entrance" -> christianityMapEntrance(triggeringEntity);
            case "christianity_map_exit_judaism" -> christianityMapExit(triggeringEntity);
            default -> throw new GdxRuntimeException("Unsupported trigger: " + triggerName);
        }
    }  

        /**
         * Handles transition to the CFN map.
         */
    private void cfnMapEntrance(Entity triggeringEntity) {
         mapTransitionHandler.transitionTo(MapAsset.CFN, "cfn_map_spawnpoint");
    }

    private void mainHouseMapEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.MAIN_HOUSE, "main_house_spawnpoint");
    }

    private void hinduismMapEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.HINDU, "hinduism_map_spawnpoint");
    }

    private void hinduismMapExit(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.CFN, "cfn_map_spawnpoint_hinduism");
    }

    private void hinduismMapHouseEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.HINDU_HOUSE, "hinduism_map_house_spawnpoint");
    }   

    private void hinduismMapHouseExit(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.HINDU, "hinduism_map_spawnpoint_temple_exit");
    }

    private void buddhismMapEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.BUDDHISM, "buddhism_map_spawnpoint");
    }

    private void judaismMapEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.JUDAISM, "judaism_map_spawnpoint");
    }

    private void judaismMapExit(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.BUDDHISM, "buddhism_map_spawnpoint");
    }

    private void christianityMapEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.CHRISTIANITY, "christianity_map_spawnpoint");
    }

    private void christianityMapExit(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.JUDAISM, "judaism_map_spawnpoint");
    }

    private void mainMapHouseExit(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.MAIN, "main_map_house_spawnpoint");
    }

    private void cfnMapHouseEntrance(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.CFN_HOUSE, "cfnmap_house_spawnpoint");
    }

    private void cfnMapHouseExit(Entity triggeringEntity) {
        mapTransitionHandler.transitionTo(MapAsset.CFN, "cfn_map_spawnpoint_house_exit");
    }

        /**
         * Handles transition to the main map.
         */
    private void mainMapEntrance(Entity triggeringEntity) {
       mapTransitionHandler.transitionTo(MapAsset.MAIN, "main_map_spawnpoint");
     }

    /**
     * Handles trap trigger effects including animation and damage.
     */
    private void trapTrigger(Entity triggeringEntity) {
        Entity trapEntity = getByTiledId(15);
        if (trapEntity != null) {
            // play trap animation
            Animation2D animation2D = Animation2D.MAPPER.get(trapEntity);
            animation2D.setSpeed(1f);
            animation2D.setPlayMode(Animation.PlayMode.NORMAL);
            audioService.playSound(SoundAsset.TRAP);
            // reset animation
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    animation2D.setSpeed(0f);
                    animation2D.setType(Animation2D.AnimationType.IDLE);
                }
            }, 2.5f);

            // damage player
            Life life = Life.MAPPER.get(triggeringEntity);
            if (life.getLife() > 2) {
                life.addLife(-2f);
            }
        }
    }
}
