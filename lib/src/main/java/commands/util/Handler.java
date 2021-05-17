package commands.util;

import demoBot.Bot;
import demoBot.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

// Responsible for parsing the command name and delegating it into action
public class Handler {
	public Handler(TextChannel textChannel,  Member member, User user, String [] arg) {
		// Command name
		String cmd = arg[1];
		
		// Variable used to determine if a command is in the CommandObject array
		boolean commandFound = false;
		
		// Traverses through the CommandObject array to search for a match in the command name
		for (CommandObject command : CommandObject.commands)
		{
			// Uses the compare method in CommandObject to compare the user's input with each array entry
			if (command.compare(cmd)) 
			{
				// Command has been found
				commandFound = true;
				
				// If command is an admin command and user has the required admin role, or, command does not require admin privileges, command executes
				if (command.hasAdminRole(member))
				{
					// Executes the command, passing the necessary infomation to the execution method
					command.execute(Bot.guild, textChannel, user, arg);
					
					// Prints the dev message for the executed command
					command.devMessage(command.getName(), command.extraDetails());
				}
				
				// If command is an admin command and user does not have the required privileges, message is sent telling them they don't have the required role
				else 
				{
					textChannel.sendMessage("You do not have the admin privileges required for this command.").queue();
				}
				
				break;
			}
		}
		
		// If command was not found, a message is sent to the user telling them to use the help command
		if (!commandFound) {
			textChannel.sendMessage("I don't know that command. Type in " + Config.get("COMMAND_PREFIX") + " help for infomation on commands").queue();
		}
	}
}
