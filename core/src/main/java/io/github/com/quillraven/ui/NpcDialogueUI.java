package io.github.com.quillraven.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import io.github.com.quillraven.component.Npc;
import io.github.com.quillraven.system.NpcInteractionSystem;

public class NpcDialogueUI {
    private final Label interactionLabel;
    private final Label dialogueNameLabel;
    private final Label dialogueTextLabel;
    private final Table dialogueRoot;

    public NpcDialogueUI(Stage stage, Skin skin) {
        interactionLabel = new Label("Press E to talk", skin);
        interactionLabel.setVisible(false);
        interactionLabel.setPosition(20, 110);
        stage.addActor(interactionLabel);

        dialogueNameLabel = new Label("", skin);
        dialogueNameLabel.setAlignment(Align.left);

        dialogueTextLabel = new Label("", skin);
        dialogueTextLabel.setWrap(true);
        dialogueTextLabel.setAlignment(Align.topLeft);

        dialogueRoot = new Table();
        dialogueRoot.setFillParent(true);
        dialogueRoot.setVisible(false);
        dialogueRoot.bottom().left();

        Table box = new Table();
        box.left().top();
        box.pad(10);

        // remove this line if your skin doesn't support it
        // box.setBackground("default-round");

        box.add(dialogueNameLabel)
            .left()
            .width(260)
            .padBottom(6)
            .row();

        box.add(dialogueTextLabel)
            .left()
            .width(260)
            .top()
            .row();

        dialogueRoot.add(box)
            .left()
            .bottom()
            .padLeft(20)
            .padBottom(20)
            .width(280);

        stage.addActor(dialogueRoot);
}


    public void update(NpcInteractionSystem npcInteractionSystem) {
    interactionLabel.setVisible(false);
    dialogueRoot.setVisible(false);

    if (npcInteractionSystem.isDialogueOpen() && npcInteractionSystem.getActiveNpc() != null) {
        Entity activeNpc = npcInteractionSystem.getActiveNpc();
        Npc npc = Npc.MAPPER.get(activeNpc);

        if (npc != null && npc.dialogue != null && npc.dialogue.length > 0) {
            dialogueNameLabel.setText(npc.name);
            dialogueTextLabel.setText(npc.dialogue[npc.currentLine]);
            dialogueTextLabel.invalidateHierarchy();
            dialogueRoot.setVisible(true);
        }
    } else if (npcInteractionSystem.isPlayerNearNpc()) {
        interactionLabel.setVisible(true);
    }
}

}

