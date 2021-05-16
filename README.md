*** STILL IN DEVELOPMENT ***

PROJECT TITLE: "Demo Bot"
I have been developing several Discord bots over the past 6 months or so, and learned so much about software development in the process. This demobot is a way for me to
  continually show the skills I have learned and continue to learn.
This project has been written entirely in Java, and is the first project I have done using the Gradle Wrapper, rather than Maven.
This Discord bot will have some basic functionalities, such as admin commands (ban users, kick, channel management, etc.), a music player, and whatever other fun things I want
  to add later on.
This project takes advantage of the JDA API wrapper, which enables the use of Discord's API in Java. The source code of this project can be found here at 
  https://github.com/DV8FromTheWorld/JDA.
This project also takes advantage of the Lavaplayer project to be used in the Audio and Player managers, to play audio in the voice channels. The project can be found here
  at https://github.com/sedmelluq/lavaplayer.
Both of the above projects take advantage of the Apache License 2.0.

Source Java files are located in the demoBot/src/test/java directory.

IMPORTANT INFORMATION:

In order to run this project, a private.json file is needed with the required information. This file is not included in the commits for maximum privacy, as well as
to increase customization for multiple people to use.
As of right now, the private.json file needs to be in this format:

* {
*   "TOKEN" : <Discord Application API Token>
*   "ADMIN_ROLE" : <Name of the role you want to have admin privileges>
*   "COMMAND_PREFIX" : <Custom command prefix>
*   "PRESENCE" : <Presence description>
*   "VOICE CHANNEL" : <Voice channel name>
*   "BOT_CHANNEL" : <Text channel name>
* }

The token is given to you when you do the following:
- Make a developer account with Discord.
- Create an application in the developer portal, and retrieve the token.
The admin role is created if it does not exist in the server, and this will allow you to select moderators you trust in the server.
The command prefix is what starts the command. For my server, this prefix is "!d". Example command is "!d presence <custom presence>".
The presence is a custom description that appears below the bot's profile in the member list. It appears in the form of "Playing / Watching / Streaming / Listening
  <custom desc.>"
The voice channel is the primary voice channel that the bot will connect to. For simplicity's sake, right now this will be the only vc the bot can join, but functionality will 
  be added to let the bot join any vc.
The bot channel is the primary text channel the bot will send messages in. Just like the voice channel, this is the only channel it'll use, but will be able to use other tc's
  later in development.
Other attributes will be added in the private.json as they are needed.

VERSION HISTORY:

Initial commit 05/16/2021:
- Formatted the private.json file with the initial information:
  - Token
  - Admin role
  - Command prefix
  - Presence
  - Voice channel
  - Bot channel

Future features:
- Add events (triggers that the bot reacts to)
  - Event initialization
  - Say hello back
  - Automatically join / leave voice channel when someone else joins
  - Block crude / racist messages
- Add commands
  - Command initialization
  - Music player (play, stop, skip, back, etc.)
  - Join / leave vc on demand
  - Help (displays either all commands, or requested command)
  - Set custom presence activity (does not replace the presence in private.json)
