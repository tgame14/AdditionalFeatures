package addfeat.common.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class FlyCommand extends CommandBase {

	private List aliases;
	
	public FlyCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("fly");
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	
	@Override
	public String getCommandName() {
		return "fly";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "fly";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		
	}

}
