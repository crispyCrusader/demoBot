package commands;

import commands.util.CommandObject;
import demoBot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class ShutDown extends CommandObject {

	String user;
	
	public String getName() {
		return "shutdown";
	}

	public String extraDetails() {
		return (user + " shut down bot;");
	}

	public String getArgs() {
		return "";
	}

	public String getType() {
		return "Dev";
	}

	public String getDesc() {
		return "shuts down the bot";
	}

	public boolean getAdmin() {
		return true;
	}

	public void execute(Guild guild, TextChannel textChannel, User user, String[] arg) {
		this.user = user.getName();
		
		super.devMessage(getName(), getArgs());
		
		Bot.shutdown(textChannel);
	}

}
