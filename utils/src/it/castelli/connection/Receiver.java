package it.castelli.connection;

import it.castelli.connection.messages.Message;
import it.castelli.gameLogic.Player;
import it.castelli.serialization.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Receiver class
 */
public class Receiver implements Runnable
{
	/**
	 * A socket of a connection which receives the message
	 */
	private final Socket connectionSocket;

	/**
	 * Connection which receive the message
	 */
	private final Connection connection;

	/**
	 * Tell if Receiver is working
	 */
	private boolean isRunning = true;

	/**
	 * The player who receive the message
	 */
	private Player player;

	/**
	 * Constructor for the receiver class
	 *
	 * @param connection Connection which receive the message
	 */
	public Receiver(Connection connection)
	{
		this.connection = connection;
		this.connectionSocket = connection.getSocket();
	}

	/**
	 * Interrupt the work of the receiver
	 */
	public void interrupt()
	{
		isRunning = false;
		try
		{
			connectionSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Get the player who receive the message
	 *
	 * @return The player who receive the message
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * Set a player who receive the message
	 *
	 * @param player The player who receive the message
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}

	/**
	 * Start the receiver
	 */
	@Override
	public void run()
	{
		System.out.println("Receiver is working");
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			while (isRunning)
			{
				String messageString;
				while ((messageString = in.readLine()) != null)
				{
					String[] tokens = messageString.strip().split("\\s\\|\\s");
					String classType = tokens[0];
					String jsonObject = tokens[1];
					try
					{
						Message message = (Message) Serializer.fromJson(jsonObject, classType);
						message.onReceive(this.connection, this.player);
					}
					catch (RuntimeException e)
					{
						System.out.println("Exception in Receiver reading the object type: " + classType);
						System.out.println("Exception in Receiver reading the object: " + jsonObject);
						e.printStackTrace();
					}

				}
			}
		}
		catch (IOException e)
		{
			System.out.println("The connection has been interrupted");
		}

	}
}
