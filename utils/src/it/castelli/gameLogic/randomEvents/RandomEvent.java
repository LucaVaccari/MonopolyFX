package it.castelli.gameLogic.randomEvents;

import it.castelli.gameLogic.Player;

public abstract class RandomEvent
{
   String message;
   public RandomEvent(String message)
   {
      this.message = message;
   }

   public abstract void applyEffect(Player player);

   public String getMessage()
   {
      return message;
   }
}
