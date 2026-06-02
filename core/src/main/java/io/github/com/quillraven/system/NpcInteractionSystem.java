package io.github.com.quillraven.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import io.github.com.quillraven.component.Controller;
import io.github.com.quillraven.component.Interaction;
import io.github.com.quillraven.component.Move;
import io.github.com.quillraven.component.Npc;
import io.github.com.quillraven.component.Player;
import io.github.com.quillraven.component.Transform;

public class NpcInteractionSystem extends EntitySystem {
    private ImmutableArray<Entity> playerEntities;
    private ImmutableArray<Entity> npcEntities;

    private Entity activeNpc;
    private boolean dialogueOpen;

    @Override
    public void addedToEngine(Engine engine) {
        playerEntities = engine.getEntitiesFor(
            Family.all(Player.class, Transform.class, Controller.class, Move.class).get()
        );
        npcEntities = engine.getEntitiesFor(
            Family.all(Npc.class, Interaction.class, Transform.class).get()
        );
    }

@Override
public void update(float deltaTime) {
    if (playerEntities == null || playerEntities.size() == 0) return;

        Entity player = playerEntities.first();
        Transform playerTransform = Transform.MAPPER.get(player);
        Controller controller = Controller.MAPPER.get(player);
        Move playerMove = Move.MAPPER.get(player);

        boolean pressedTalk = Gdx.input.isKeyJustPressed(Input.Keys.E);


        if (dialogueOpen) {
            playerMove.getDirection().setZero();
            playerMove.setRooted(true);

            if (pressedTalk && activeNpc != null) {
                Npc npc = Npc.MAPPER.get(activeNpc);
                npc.currentLine++;

                if (npc.currentLine >= npc.dialogue.length) {
                    npc.currentLine = 0;
                    activeNpc = null;
                    dialogueOpen = false;
                    playerMove.setRooted(false);
                }
            }

            controller.getPressedCommands().clear();
            controller.getReleasedCommands().clear();
            return;
        }

        Entity nearbyNpc = findNearbyNpc(playerTransform.getPosition());

        if (nearbyNpc != null && pressedTalk) {
            activeNpc = nearbyNpc;
            dialogueOpen = true;
            playerMove.getDirection().setZero();
            playerMove.setRooted(true);

            Npc npc = Npc.MAPPER.get(activeNpc);
            npc.currentLine = 0;
        }
}



    private Entity findNearbyNpc(Vector2 playerPos) {
        if (npcEntities == null) return null;

        for (Entity npcEntity : npcEntities) {
            Transform npcTransform = Transform.MAPPER.get(npcEntity);
            Interaction interaction = Interaction.MAPPER.get(npcEntity);

            if (playerPos.dst(npcTransform.getPosition()) <= interaction.talkRange) {
                return npcEntity;
            }
        }
        return null;
    }

    public boolean isDialogueOpen() {
        return dialogueOpen;
    }

    public Entity getActiveNpc() {
        return activeNpc;
    }

    public boolean isPlayerNearNpc() {
        if (playerEntities == null || playerEntities.size() == 0) return false;
        return findNearbyNpc(Transform.MAPPER.get(playerEntities.first()).getPosition()) != null;
    }
}
