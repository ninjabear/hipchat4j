package hipchat4j.entities;

/**
 * hipchat4j
 * 09/10/14/20:42
 */
public class Emoticon {

   private final String keyOrId,
                        shortcut,
                        audioPath;

    private int width,
                height,
                id;

    public Emoticon(int id, String keyOrId, String shortcut, int width,  int height) {
        this(id, keyOrId, shortcut, width, height, null);
    }

    public Emoticon(int id, String keyOrId, String shortcut, int width,  int height, String audioPath) {
        this.keyOrId = keyOrId;
        this.shortcut = shortcut;
        this.width = width;
        this.audioPath = audioPath;
        this.id = id;
        this.height = height;
    }

    public String getKeyOrId() {
        return keyOrId;
    }

    public String getShortcut() {
        return shortcut;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public String getAudioPath() {
        return audioPath;
    }


}
