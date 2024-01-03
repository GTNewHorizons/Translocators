package codechicken.translocator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.vec.Vector3;

public class ItemTranslocatorRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        final CCRenderState state = CCRenderState.instance();
        Vector3 d = new Vector3();
        if (type != ItemRenderType.EQUIPPED && type != ItemRenderType.EQUIPPED_FIRST_PERSON) d.add(-0.5, -0.5, -0.5);
        else d.add(0, -0.2, -0.2);
        d.add(0, 0, 0.5);

        GL11.glPushMatrix();
        GL11.glScaled(1.5, 1.5, 1.5);

        state.changeTexture("translocator:textures/tex.png");
        state.pullLightmap();
        state.setColour(-1);
        state.useNormals = true;
        state.startDrawing(4);
        TileTranslocatorRenderer.renderAttachment(2, item.getItemDamage(), 1, 0, d.x, d.y, d.z);
        state.draw();

        GL11.glPopMatrix();
    }
}
