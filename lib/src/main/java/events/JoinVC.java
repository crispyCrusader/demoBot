package events;

import demoBot.Bot;
import events.util.EventObject;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;

// If someone joins the bot's voice channel, Demo Bot will automatically join the channel
public class JoinVC extends EventObject{

	String user;
	
	public String getName() {
		return "JoinVC";
	}

	public String getAction() {
		return "Demo Bot joined its voice channel because \"" + user + "\" has also joined";
	}

	// Executes if someone joins a voice channel
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		
		if (!(event.getMember().getUser().isBot()))
		{
			// Retrieves the channel the user joined
			VoiceChannel channel = event.getChannelJoined();
			
			// If the channel the user joined is equal to the bot's voice channel, then execute the event
			if (channel == Bot.botVoiceChannel)
			{
				// Executes the joinVC() method in the Bot class
				Bot.joinVC(Bot.botControlChannel);
				
				// Retrieves the user for use in the dev message
				this.user = event.getMember().getUser().getName();
				
				// Prints the dev message
				super.devMessage(getName(), getAction());
			}
		}
	}
}
