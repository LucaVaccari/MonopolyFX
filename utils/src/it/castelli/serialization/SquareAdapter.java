package it.castelli.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.castelli.gameLogic.OwnerPlayer;
import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.contracts.CompanyContract;
import it.castelli.gameLogic.contracts.PropertyColor;
import it.castelli.gameLogic.contracts.PropertyContract;
import it.castelli.gameLogic.contracts.StationContract;
import it.castelli.gameLogic.squares.*;

import java.io.IOException;

public class SquareAdapter extends TypeAdapter<Square>
{
	@Override
	public void write(JsonWriter out, Square square) throws IOException
	{
		// square object
		out.beginObject();
		{
			out.name("Type");
			if (square instanceof ChanceSquare)
				out.value("Chance");
			else if (square instanceof CommunityChestSquare)
				out.value("CommunityChest");
			else if (square instanceof CompanySquare)
			{
				out.value("Company");
				// contract
				out.name("contract");
				out.beginObject();
				{
					out.name("Type");
					out.value("Company");
					out.name("company");
					CompanyContract contract = ((CompanySquare) square).getContract();
					out.value(String.valueOf(contract.getCompany()));

					out.name("id");
					out.value(contract.getId());

					out.name("name");
					out.value(contract.getName());

					out.name("value");
					out.value(contract.getValue());

					out.name("revenue");
					out.value(contract.getRevenue());

					out.name("mortgageValue");
					out.value(contract.getMortgageValue());

					out.name("owner");
					// OwnerPlayer
					out.beginObject();
					{
						if (contract.getOwner() != null)
						{
							out.name("money");
							out.value(contract.getOwner().getMoney());
							out.name("name");
							out.value(contract.getOwner().getName());
							out.name("inPrison");
							out.value(contract.getOwner().isInPrison());
							out.name("position");
							out.value(contract.getOwner().getPosition());
							out.name("previousPosition");
							out.value(contract.getOwner().getPreviousPosition());
							out.name("pawn");
							out.value(String.valueOf(contract.getOwner().getPawn()));
							out.name("randomEventDescription");
							out.value(contract.getOwner().getRandomEventDescription());
							out.name("randomEventType");
							out.value(contract.getOwner().getRandomEventType());
						}
					}
					out.endObject();

					out.name("mortgaged");
					out.value(contract.isMortgaged());
				}
				out.endObject();
			}
			else if (square instanceof GoSquare)
				out.value("Go");
			else if (square instanceof GoToJailSquare)
				out.value("GoToJail");
			else if (square instanceof JustVisitingSquare)
				out.value("JustVisiting");
			else if (square instanceof PropertySquare)
			{
				out.value("Property");
				out.name("contract");
				// contract
				out.beginObject();
				{
					out.name("Type");
					PropertyContract contract = (PropertyContract) square.getContract();
					out.value("Property");

					out.name("revenues");
					out.beginArray();
					for (int revenue : contract.getRevenues())
						out.value(revenue);
					out.endArray();

					out.name("color");
					out.value(String.valueOf(contract.getColor()));

					out.name("houseCost");
					out.value(contract.getHouseCost());

					out.name("numberOfHouses");
					out.value(contract.getNumberOfHouses());

					out.name("colorSetContractNumber");
					out.value(contract.getColorSetContractNumber());

					out.name("id");
					out.value(contract.getId());

					out.name("name");
					out.value(contract.getName());

					out.name("value");
					out.value(contract.getValue());

					out.name("revenue");
					out.value(contract.getRevenue());

					out.name("mortgageValue");
					out.value(contract.getMortgageValue());

					out.name("owner");
					// ownerPlayer
					out.beginObject();
					{
						if (contract.getOwner() != null)
						{
							out.name("money");
							out.value(contract.getOwner().getMoney());
							out.name("name");
							out.value(contract.getOwner().getName());
							out.name("inPrison");
							out.value(contract.getOwner().isInPrison());
							out.name("position");
							out.value(contract.getOwner().getPosition());
							out.name("previousPosition");
							out.value(contract.getOwner().getPreviousPosition());
							out.name("pawn");
							out.value(String.valueOf(contract.getOwner().getPawn()));
							out.name("randomEventDescription");
							out.value(contract.getOwner().getRandomEventDescription());
							out.name("randomEventType");
							out.value(contract.getOwner().getRandomEventType());
						}
					}
					out.endObject();

					out.name("mortgaged");
					out.value(contract.isMortgaged());
				}
				out.endObject();
			}
			else if (square instanceof StationSquare)
			{
				out.value("Station");
				out.name("contract");
				// contract
				out.beginObject();
				{
					out.name("Type");
					out.value("Station");
					StationContract contract = ((StationSquare) square).getContract();

					out.name("id");
					out.value(contract.getId());

					out.name("name");
					out.value(contract.getName());

					out.name("value");
					out.value(contract.getValue());

					out.name("revenue");
					out.value(contract.getRevenue());

					out.name("mortgageValue");
					out.value(contract.getMortgageValue());

					out.name("owner");
					// ownerPlayer
					out.beginObject();
					{
						if (contract.getOwner() != null)
						{
							out.name("money");
							out.value(contract.getOwner().getMoney());
							out.name("name");
							out.value(contract.getOwner().getName());
							out.name("inPrison");
							out.value(contract.getOwner().isInPrison());
							out.name("position");
							out.value(contract.getOwner().getPosition());
							out.name("previousPosition");
							out.value(contract.getOwner().getPreviousPosition());
							out.name("pawn");
							out.value(String.valueOf(contract.getOwner().getPawn()));
							out.name("randomEventDescription");
							out.value(contract.getOwner().getRandomEventDescription());
							out.name("randomEventType");
							out.value(contract.getOwner().getRandomEventType());
						}
					}
					out.endObject();

					out.name("mortgaged");
					out.value(contract.isMortgaged());
				}
				out.endObject();
			}
			else if (square instanceof TaxSquare)
			{
				out.value("Tax");
				out.name("value");
				out.value(((TaxSquare) square).getValue());
				out.name("message");
				out.value(((TaxSquare) square).getMessage());
			}
			else
				throw new IllegalArgumentException("SquareAdapter: the square is not a subclass of Square!");
		}

		out.endObject();
	}

	@Override
	public Square read(JsonReader in) throws IOException
	{
		Square square;

		JsonToken token;
		// square
		in.beginObject();
		{
			in.nextName();
			String type = in.nextString();

			switch (type)
			{
				case "Chance" -> square = new ChanceSquare();
				case "CommunityChest" -> square = new CommunityChestSquare();
				case "Company" -> {
					CompanyContract contract;
					CompanyContract.Company company;
					int id;
					String name;
					int value;
					OwnerPlayer owner = null;
					int money;
					String ownerName;
					int position, previousPosition;
					Pawn pawn = null;
					boolean inPrison;
					String randomEventType = "", randomEventDescription = "";
					boolean mortgaged;

					in.nextName();
					in.beginObject();
					{
						in.nextName();
						in.nextString();
						in.nextName();
						company = CompanyContract.Company.valueOf(in.nextString());

						in.nextName();
						id = in.nextInt();

						in.nextName();
						name = in.nextString();

						in.nextName();
						value = in.nextInt();

						in.nextName();
						in.nextInt(); // revenue

						in.nextName();
						in.nextInt(); // mortgageValue

						// OWNER
						in.nextName();
						in.beginObject();
						{
							token = in.peek();
							if (!token.equals(JsonToken.END_OBJECT))
							{
								in.nextName();
								money = in.nextInt();

								in.nextName();
								ownerName = in.nextString();

								in.nextName();
								inPrison = in.nextBoolean();

								in.nextName();
								position = in.nextInt();

								in.nextName();
								previousPosition = in.nextInt();

								in.nextName();
								String pawnString = in.nextString();
								if (!pawnString.equals("null"))
									pawn = Pawn.valueOf(pawnString);

								in.nextName();
								token = in.peek();
								if (!token.equals(JsonToken.NULL))
									randomEventDescription = in.nextString();
								else
									in.nextNull();

								in.nextName();
								token = in.peek();
								if (!token.equals(JsonToken.NULL))
									randomEventType = in.nextString();
								else
									in.nextNull();

								owner = new OwnerPlayer(ownerName, money, position, inPrison, pawn, randomEventType,
										randomEventDescription,
										previousPosition);
							}
						}
						in.endObject();

						in.nextName();
						mortgaged = in.nextBoolean();

						in.endObject();

					}
					contract = new CompanyContract(id, name, company, value);

					if (owner != null)
						contract.setOwner(owner.toPlayer());

					contract.setMortgaged(mortgaged);
					square = new CompanySquare(contract);
				}
				case "Go" -> square = new GoSquare();
				case "GoToJail" -> square = new GoToJailSquare();
				case "JustVisiting" -> square = new JustVisitingSquare();
				case "Property" -> {
					int[] revenues = new int[6];
					PropertyColor color;
					int houseCost, numberOfHouses, colorSetContractNumber;
					int id;
					String name;
					int value;
					OwnerPlayer owner = null;
					int money;
					String ownerName;
					int position, previousPosition;
					Pawn pawn = null;
					boolean inPrison;
					String randomEventType = "", randomEventDescription = "";
					boolean mortgaged;

					PropertyContract contract;

					in.nextName();
					// contract
					in.beginObject();
					{
						in.nextName();
						in.nextString();
						in.nextName();
						in.beginArray();
						for (int i = 0; i < 6; i++)
						{
							revenues[i] = in.nextInt();
						}
						in.endArray();

						in.nextName();
						color = PropertyColor.valueOf(in.nextString());

						in.nextName();
						houseCost = in.nextInt();

						in.nextName();
						numberOfHouses = in.nextInt();

						in.nextName();
						colorSetContractNumber = in.nextInt();

						in.nextName();
						id = in.nextInt();

						in.nextName();
						name = in.nextString();

						in.nextName();
						value = in.nextInt();

						in.nextName();
						in.nextInt(); // revenue

						in.nextName();
						in.nextInt(); // mortgageValue

						// OWNER
						in.nextName();
						in.beginObject();
						{
							token = in.peek();
							if (!token.equals(JsonToken.END_OBJECT))
							{
								in.nextName();
								money = in.nextInt();

								in.nextName();
								ownerName = in.nextString();

								in.nextName();
								inPrison = in.nextBoolean();

								in.nextName();
								position = in.nextInt();

								in.nextName();
								previousPosition = in.nextInt();

								in.nextName();
								String pawnString = in.nextString();
								if (!pawnString.equals("null"))
									pawn = Pawn.valueOf(pawnString);

								in.nextName();
								token = in.peek();
								if (!token.equals(JsonToken.NULL))
									randomEventDescription = in.nextString();
								else
									in.nextNull();

								in.nextName();
								token = in.peek();
								if (!token.equals(JsonToken.NULL))
									randomEventType = in.nextString();
								else
									in.nextNull();

								owner = new OwnerPlayer(ownerName, money, position, inPrison, pawn, randomEventType,
										randomEventDescription,
										previousPosition);
							}
						}
						in.endObject();

						in.nextName();
						mortgaged = in.nextBoolean();

						contract = new PropertyContract(id, name, value, revenues[0], revenues[1], revenues[2],
								revenues[3], revenues[4], revenues[5], houseCost, color,
								colorSetContractNumber);

						if (owner != null)
							contract.setOwner(owner.toPlayer());

						contract.setMortgaged(mortgaged);
						contract.addHouses(numberOfHouses);
						square = new PropertySquare(contract);
					}
					in.endObject();
				}
				case "Station" -> {
					int id;
					String name;
					int value, revenue;
					OwnerPlayer owner = null;
					int money;
					String ownerName;
					int position, previousPosition;
					Pawn pawn = null;
					boolean inPrison;
					String randomEventType = "", randomEventDescription = "";
					boolean mortgaged;

					StationContract contract;

					in.nextName();
					// contract
					in.beginObject();
					{
						in.nextName();
						in.nextString();

						in.nextName();
						id = in.nextInt();

						in.nextName();
						name = in.nextString();

						in.nextName();
						value = in.nextInt();

						in.nextName();
						revenue = in.nextInt(); // revenue

						in.nextName();
						in.nextInt(); // mortgageValue

						// OWNER
						in.nextName();
						in.beginObject();
						{
							token = in.peek();
							if (!token.equals(JsonToken.END_OBJECT))
							{
								in.nextName();
								money = in.nextInt();

								in.nextName();
								ownerName = in.nextString();

								in.nextName();
								inPrison = in.nextBoolean();

								in.nextName();
								position = in.nextInt();

								in.nextName();
								previousPosition = in.nextInt();

								in.nextName();
								String pawnString = in.nextString();
								if (!pawnString.equals("null"))
									pawn = Pawn.valueOf(pawnString);

								in.nextName();
								token = in.peek();
								if (!token.equals(JsonToken.NULL))
									randomEventDescription = in.nextString();
								else
									in.nextNull();

								in.nextName();
								token = in.peek();
								if (!token.equals(JsonToken.NULL))
									randomEventType = in.nextString();
								else
									in.nextNull();

								owner = new OwnerPlayer(ownerName, money, position, inPrison, pawn, randomEventType,
										randomEventDescription,
										previousPosition);
							}
						}
						in.endObject();

						in.nextName();
						mortgaged = in.nextBoolean();

						contract = new StationContract(id, name, value, revenue);

						if (owner != null)
							contract.setOwner(owner.toPlayer());

						contract.setMortgaged(mortgaged);
						square = new StationSquare(contract);
					}
					in.endObject();
				}
				case "Tax" -> {
					int value;
					String message;

					in.nextName();
					value = in.nextInt();

					in.nextName();
					message = in.nextString();

					square = new TaxSquare(value, message);
				}
				default -> throw new IllegalStateException("Unexpected square type: " + type);
			}
		}
		in.endObject();

		return square;
	}
}
