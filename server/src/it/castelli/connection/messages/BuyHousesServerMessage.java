package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class BuyHousesServerMessage implements Message
{

    //gameCode (int), contract Contract, numberOfHouseToBuy (int)

    @Override
    public void onReceive(Connection connection, Player player)
    {

    }
}
