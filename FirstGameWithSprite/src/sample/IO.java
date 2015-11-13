package sample;

/**
 * Takes care of basic IO.
 * @author Axel Kennedal
 * @version 1.0
 * Created on 2015-11-13.
 */
public class IO
{
    /**
     * Return the URL of the resource for the specified filename.
     * @param filename the name of the file to get the URL for.
     * @return a string representation of the URL for the resource.
     */
    public static String getResource(String filename)
    {
        return IO.class.getResource(filename).toString();
    }
}