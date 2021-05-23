package events;

import java.util.Random;

import events.util.EventObject;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

// For every message sent by a human user, there is a 4% chance that Demo Bot will start the "typing" status in the channel the message was sent
public class FakeTyping extends EventObject{

	String user;
	
	public String getName() {
		return "FakeTyping";
	}

	public String getAction() {
		return "Demo Bot started the typing status in response to a message sent by \"" + user + "\"";
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if (!event.getMember().getUser().isBot()) 
		{
			Random rand = new Random();
			int upperBound = 24;
			int randomInt = rand.nextInt(upperBound);
			
			// 4% chance the bot will display the "typing" status in the text channel
			if (randomInt < 1)
			{
				// Sends typing status in the text channel
				event.getChannel().sendTyping().queue();
				
				// Retrieves the author of the incoming message, for use in the dev message
				this.user = event.getAuthor().getName();
				
				// Prints dev message
				super.devMessage(getName(), getAction());
			}
		}
	}
}
