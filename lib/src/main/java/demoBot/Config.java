package demoBot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


// Contains the necessary information to launch and run bot
public class Config {
	
	// Various information about the bot
	private static final String TOKEN = "TOKEN";
	private static final String ADMIN_ROLE = "ADMIN_ROLE";
	private static final String COMMAND_PREFIX = "COMMAND_PREFIX";
	private static final String PRESENCE = "PRESENCE";
	private static final String VOICE_CHANNEL = "VOICE_CHANNEL";
	private static final String BOT_CONTROL_CHANNEL = "BOT_CONTROL_CHANNEL";
	
	// New JSON to store info from private file
	private static JSONObject defaults;
	
	// Private file that stores the bot information
	private static JSONObject privateJsonObject;
	
	private static boolean initialized;
	
	// Returns whether or not the bot successfully initialized its token
	public static boolean isInitialized() {
		return initialized;
	}
	
	// Initialization method
	@SuppressWarnings("unchecked")
	public static boolean init() {
		
		initialized = false;
		
		// Initializes the private.json object to be used for initializing the config file
		initJsonObj();
		
		// JSON Object with the private info
		defaults = new JSONObject();
		defaults.put(TOKEN, getToken());
		defaults.put(ADMIN_ROLE, getAdminRole());
		defaults.put(COMMAND_PREFIX, getCommandPrefix());
		defaults.put(PRESENCE, getPresence());		
		defaults.put(VOICE_CHANNEL, getVoiceChannel());
		defaults.put(BOT_CONTROL_CHANNEL, getBotControlChannel());
		
		// If the token is inputed in the JSON, then the bot can be initialized
		if (defaults.get(TOKEN) != null) {
			initialized = true;
		}
		
		return initialized;
	}
	
	// Initializes the private JSON object
	private static JSONObject initJsonObj() {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader("private.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		privateJsonObject = (JSONObject) obj;
		
		return privateJsonObject;
	}
	
	// Gets the token from the private JSON file
	private static String getToken() {
		String token = privateJsonObject.get(TOKEN).toString();
		
		return token;
	}
	
	// Gets the admin role from the private JSON file
	private static String getAdminRole() {
		String adminRole = privateJsonObject.get(ADMIN_ROLE).toString();
		
		return adminRole;
	}
	
	// Gets the command prefix from the private JSON file
	private static String getCommandPrefix() {
		String commandPrefix = privateJsonObject.get("COMMAND_PREFIX").toString();
		
		return commandPrefix;
	}
	
	// Gets the bot's presence status from the private JSON file
	private static String getPresence() {
		String presence = privateJsonObject.get("PRESENCE").toString();
		
		return presence;
	}
	
	// Returns the voice channel the bot will use
	private static String getVoiceChannel() {
		String voiceChannel = privateJsonObject.get("VOICE_CHANNEL").toString();
		
		return voiceChannel;
	}
	
	// Returns the text channel the users will use to interact with the bot
	private static String getBotControlChannel() {
		String botControl = privateJsonObject.get("BOT_CONTROL_CHANNEL").toString();
		
		return botControl;
	}
	
	// Gets whatever key is requested
	public static String get(String key) {
		return defaults.get(key).toString();
	}
}
