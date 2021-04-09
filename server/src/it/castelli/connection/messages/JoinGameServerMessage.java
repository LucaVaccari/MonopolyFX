package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

public class JoinGameServerMessage implements Message
{
	private int gameCode;
	private Player player;

	@Override
	public void onReceive(Connection connection, Player player)
	{
		ConnectionManager.getInstance().joinGame(gameCode, connection, this.player);

		// Sending game code to the client
		connection.send(ServerMessages.GAME_CODE_MESSAGE_NAME, Serializer.toJson(new GameCodeServerMessage(gameCode)));

		GameConnectionManager match = ConnectionManager.getInstance().getGames().get(gameCode);
		match.sendAll(ServerMessages.PLAYERS_LIST_MESSAGE_NAME, Serializer
				.toJson(new PlayersListServerMessage(match.getGameManager().getPlayers())));
	}
}
