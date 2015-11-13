package sample;

import javafx.scene.media.AudioClip;

import java.util.HashMap;

/**
 * Manages playing sounds.
 * @author Axel Kennedal
 * @version 1.01
 * Created on 2015-11-13.
 */
public class AudioManager
{
    HashMap<String, AudioClip> shortSounds;

    AudioManager()
    {
        shortSounds = new HashMap<String, AudioClip>();
    }

    /**
     * Add a sound to the manager, in order to play it later.
     * @param fileName URL to the file (can be relative to this class)
     * @param referenceName the name to be used when playing this later.
     * @param isShort true if this sound is relatively short (a couple of seconds), false otherwise.
     */
    public void addSound(String fileName, String referenceName, boolean isShort)
    {
        if (isShort)
        {
            AudioClip sound = new AudioClip(IO.getResource(fileName));
            shortSounds.put(referenceName, sound);
        }
    }

    /**
     * Play a sound that has already been loaded with addSound().
     * @param referenceName the reference name identifying the sound
     *                      (that was used with addSound())
     */
    public void playSound(String referenceName)
    {
        if (shortSounds.containsKey(referenceName))
        {
            shortSounds.get(referenceName).play();
        }
    }
}
