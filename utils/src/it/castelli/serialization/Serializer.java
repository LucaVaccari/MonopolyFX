package it.castelli.serialization;

import java.io.*;

/**
 * Class for serializing objects
 */
public class Serializer
{
    /**
     * Convert an obj to a byte array
     * @param obj The object to serialize
     * @return The serialized object (in form of byte array)
     */
    public static byte[] serialize(Serializable obj)
    {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(byteOut))
        {
            objOut.writeObject(obj);
            return byteOut.toByteArray();
        }
        catch (IOException e)
        {
            System.out.println("Error serializing object: " + obj.getClass().getName());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert a byte array in a serializable object
     * @param arr The byte array in which the object is contained
     * @return The deserialized object
     */
    public static Serializable deserialize(byte[] arr)
    {
        Serializable object = null;
        try (ByteArrayInputStream byteIn = new ByteArrayInputStream(arr);
             ObjectInputStream objIn = new ObjectInputStream(byteIn))
        {
           object = (Serializable) objIn.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error deserializing an object");
            e.printStackTrace();
        }
        return object;
    }

}
