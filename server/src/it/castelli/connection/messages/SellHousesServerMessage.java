package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class SellHousesServerMessage implements Message
{
    //gameCode (int), contract Contract, numberOfHouseToSell (int)

    @Override
    public void onReceive(Connection connection, Player player)
    {

    }
}
