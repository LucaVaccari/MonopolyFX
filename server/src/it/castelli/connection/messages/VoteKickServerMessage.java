package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.connection.ConnectionManager;
import it.castelli.connection.GameConnectionManager;
import it.castelli.gameLogic.GameManager;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

/**
 * Message that adds a vote to the given player, if the number of votes is high enough the player will be removed
 * (receive only)
 */
public class VoteKickServerMessage implements Message
{
	/**
	 * The player to kick
	 */
	private final Player player;

	/**
	 * Is the request to kick the player? (if false this message will remove the vote previously added)
	 */
	private final boolean kick;

	/**
	 * The game code
	 */
	private final int gameCode;

	/**
	 * Constructor for VotekickServerMessage (do not use)
	 *
	 * @param player   The player to kick
	 * @param kick     Is the request to kick the player? (if false this message will remove the vote previously added)
	 * @param gameCode The game code
	 */
	public VoteKickServerMessage(Player player, boolean kick, int gameCode)
	{
		this.player = player;
		this.kick = kick;
		this.gameCode = gameCode;
	}


	@Override
	public void onReceive(Connection connection, Player player)
	{
		GameConnectionManager gameConnectionManager = ConnectionManager.getInstance().getGames().get(gameCode);
		GameManager gameManager = gameConnectionManager.getGameManager();

		Player playerToKick = gameManager.getSamePlayer(this.player);
		if (kick)
			playerToKick.addKickVote();
		else
			playerToKick.removeKickVote();

		if (playerToKick.getNumberOfKickVotes() >= gameManager.getPlayers().size() - 1 &&
		    gameManager.getPlayers().size() > 2)
		{
			gameConnectionManager.sendAll(ServerMessages.GENERIC_MESSAGE_NAME, Serializer
					.toJson(new GenericServerMessage("Espulsione",
					                                 "Il giocatore " + playerToKick.getName() + " è stato espulso")));
			// TODO: remove next line
			gameConnectionManager.removePlayer(gameConnectionManager.getConnectionFromPlayer(playerToKick));
			// TODO: send votekick callback
		}

		gameConnectionManager.updatePlayers();
	}
}
