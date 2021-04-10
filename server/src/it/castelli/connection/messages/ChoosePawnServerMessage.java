package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

public class ChoosePawnServerMessage implements Message
{
	private final String pawnURL;
	private final int gameCode;

	public ChoosePawnServerMessage(String pawnURL, int gameCode)
	{
		this.pawnURL = pawnURL;
		this.gameCode = gameCode;
	}

	@Override
	public void onReceive(Connection connection, Player player)
	{

//		player.setPawnPath(pawnURL);
		GameConnectionManager manager = ConnectionManager.getInstance().getGames().get(gameCode);
		manager.sendAll(ServerMessages.UPDATE_PLAYERS_LIST_MESSAGE_NAME,
		                Serializer.toJson(new UpdatePlayersListServerMessage(manager.getGameManager().getPlayers())));
	}
}
