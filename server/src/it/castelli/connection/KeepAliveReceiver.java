package it.castelli.connection;

/**
 * Keep alive receiver class
 */
public class KeepAliveReceiver implements Runnable
{
	/**
	 * The manager of all connection
	 */
	ConnectionManager connectionManager = ConnectionManager.getInstance();

	/**
	 * Tell if keep alive receiver is running
	 */
	private boolean isRunning;

	/**
	 * The time between two checks of activity of a connection
	 */
	private final int checkTime;

	/**
	 * KeepAliveReceiver Constructor
	 *
	 * @param checkTime the time gap between connection inactivity check in
	 *                  seconds
	 */
	public KeepAliveReceiver(int checkTime)
	{
		this.checkTime = checkTime;
		isRunning = true;
	}

	@Override
	public void run()
	{
		while (isRunning)
		{
			try
			{
				Thread.sleep(checkTime * 1000L);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			removeInactiveConnections();
			resetFlags();
		}
	}

	/**
	 * Resets all connection flags to false
	 */
	private void resetFlags()
	{
		for (Connection connection : connectionManager.getWaitingRoom())
		{
			connection.setKeepAliveFlag(false);
		}

		for (GameConnectionManager gameConnectionManager : connectionManager
				.getGames().values())
		{
			for (Connection connection : gameConnectionManager.getPlayerConnections())
			{
				connection.setKeepAliveFlag(false);
			}

			for (Connection connection : gameConnectionManager.getPlayerConnections())
			{
				connection.setKeepAliveFlag(false);
			}
		}
	}

	/**
	 * Removes the inactive connections from the server
	 */
	private void removeInactiveConnections()
	{
		//remove inactive players from the waiting room
		for (Connection connection : connectionManager.getWaitingRoom())
		{
			if (connection.notConnected())
			{
				connection.interrupt();
				connectionManager.getWaitingRoom().remove(connection);
				System.out.println("Connection removed");
			}
		}

		//remove inactive connections in every game
		for (GameConnectionManager gameConnectionManager : connectionManager.getGames().values())
		{
			//remove inactive connections from players list
			for (Connection connection : gameConnectionManager.getPlayerConnections())
			{
				if (connection.notConnected())
				{
					gameConnectionManager.removePlayer(connection);
					connection.interrupt();
					System.out.println("Connection removed");
				}
			}
		}
	}

	/**
	 * Stop the keep alive receiver
	 */
	public void interrupt()
	{
		isRunning = false;
	}
}
