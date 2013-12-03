package addfeat.common.client.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import addfeat.common.ModInfo;
import addfeat.common.blocks.BlockMonitorME;
import addfeat.common.logic.LogicBase;
import addfeat.common.tileentities.AEBaseMachine;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ATBlockRendererHelper implements ISimpleBlockRenderingHandler {

	public static int myRenderID = RenderingRegistry.getNextAvailableRenderId();
	public static ATBlockRendererHelper instance;

	Icon faceFull;
	Icon faceEmpty;
	Icon faceNearFull;
	Icon faceNearEmpty;
	Icon sideIcon;
	Icon botIcon;

	LogicBase logic;
	float heatValue;
	int stageIndicator = 0;

	public ATBlockRendererHelper() {
		instance = this;
	}

	public void GetMonitorTextureFaces(RenderBlocks renderer, Block block) {
		BlockMonitorME blk = (BlockMonitorME) block;

		faceFull = blk.frontIconFull;
		faceEmpty = blk.frontIconEmpty;
		faceNearFull = blk.frontIconNearFull;
		faceNearEmpty = blk.frontIconNearEmpty;
		sideIcon = blk.sideIcon;
		botIcon = blk.bottomIcon;

	}

	private int setStageIndicator(float heatValue) {
		if (heatValue == 0)
			return 0;
		if (heatValue <= 0.25)
			return 1;
		if (heatValue <= 0.6)
			return 2;
		return 3;

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		AEBaseMachine te = (AEBaseMachine) world.getBlockTileEntity(y, y, z);

		logic = (LogicBase) te.getGrid().getCacheByID(ModInfo.heatCacheID);
		heatValue = logic.getFinalHeat();

		if (block instanceof BlockMonitorME) {
			
			GetMonitorTextureFaces(renderer, block);
			stageIndicator = setStageIndicator(heatValue);
			
			ForgeDirection dir;
			
			switch(world.getBlockMetadata(x, y, z)) {
			
			case 2: dir = ForgeDirection.WEST;
			}
			
			switch (stageIndicator) {
			case 0: 
				renderFace(ForgeDirection.WEST, block, x, y, z, faceEmpty, renderer);
			}
			
		}

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return myRenderID;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {

	}

	public void renderFace(ForgeDirection side, Block block, double x,
			double y, double z, Icon icon, RenderBlocks renderer) {
		switch (side) {
		case UP:
			renderer.renderFaceYPos(block, x, y, z, icon);
			break;
		case DOWN:
			renderer.renderFaceYNeg(block, x, y, z, icon);
			break;
		case NORTH:
			renderer.renderFaceZNeg(block, x, y, z, icon);
			break;
		case EAST:
			renderer.renderFaceXPos(block, x, y, z, icon);
			break;
		case SOUTH:
			renderer.renderFaceZPos(block, x, y, z, icon);
			break;
		case WEST:
			renderer.renderFaceXNeg(block, x, y, z, icon);
			break;
		case UNKNOWN:
			break;
		}
	}
}
