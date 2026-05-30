package io.github.com.quillraven.handler;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import io.github.com.quillraven.GdxGame;
import io.github.com.quillraven.asset.MapAsset;
import io.github.com.quillraven.component.Physic;
import io.github.com.quillraven.component.Player;
import io.github.com.quillraven.component.Transform;
import io.github.com.quillraven.tiled.TiledService;

public class MapTransitionHandler {
    private final TiledService tiledService;
    private final Engine engine;

    public MapTransitionHandler(TiledService tiledService, Engine engine) {
        this.tiledService = tiledService;
        this.engine = engine;
    }

    public void transitionTo(MapAsset mapAsset, String spawnName) {
        // load then set (mirrors how your GameScreen does it)
        TiledMap newMap = tiledService.loadMap(mapAsset);
        tiledService.setMap(newMap, true);

        // find the spawn rectangle in the "objects" layer by name
        RectangleMapObject spawn = findSpawn(newMap, spawnName);
        Rectangle rect = spawn.getRectangle();
        float x = rect.x * GdxGame.UNIT_SCALE;
        float y = rect.y * GdxGame.UNIT_SCALE;

        // move the player
        Entity player = engine.getEntitiesFor(
            Family.all(Player.class, Transform.class).get()
        ).first();

        Transform.MAPPER.get(player).getPosition().set(x, y);

        Physic physic = Physic.MAPPER.get(player);
        if (physic != null && physic.getBody() != null) {
            physic.getBody().setTransform(x, y, 0);
            physic.getBody().setLinearVelocity(0, 0);
        }
    }

    private RectangleMapObject findSpawn(TiledMap map, String name) {
        for (MapLayer layer : map.getLayers()) {
            for (MapObject obj : layer.getObjects()) {
                if (name.equals(obj.getName()) && obj instanceof RectangleMapObject rect) {
                    return rect;
                }
            }
        }
        throw new GdxRuntimeException("Spawn point not found in map: " + name);
    }
}
