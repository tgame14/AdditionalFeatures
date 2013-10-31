package addfeat.common.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;

public class SampleCommand implements ICommand {

	private List aliases;

	public SampleCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("sample");
		this.aliases.add("sam");
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "sample";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "sample <text>";
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (astring.length == 0) {
			icommandsender
					.sendChatToPlayer(ChatMessageComponent
							.createFromTranslationWithSubstitutions("Invalid Arguments"));
			return;
		}
		icommandsender.sendChatToPlayer(ChatMessageComponent
				.createFromTranslationWithSubstitutions("Sample: ["
						+ astring[0] + "]"));

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender,
			String[] astring) {

		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {

		return false;
	}

}
