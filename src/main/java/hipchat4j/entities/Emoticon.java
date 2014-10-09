package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 09/10/14/20:42
 */
public class Emoticon {

    @SerializedName("emoticon_id_or_key")
    private final String keyOrId;

    @SerializedName("shortcut")
    private final String shortcut;

    @SerializedName("audio_path")
    private final String audioPath;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("id")
    private int id;

    public Emoticon() {
        this(0, null, null, 0, 0, null);
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (null==o)
            return false;

        if (o instanceof Emoticon == false)
            return false;

        Emoticon e = (Emoticon)o;

        boolean audioOK = (this.getAudioPath()==null && e.getAudioPath()==null) || (this.getAudioPath()!=null && this.getAudioPath().equals(e.getAudioPath())),
                idOK = this.getId()==e.getId(),
                keyOrIdOK = this.getKeyOrId().equals(e.getKeyOrId()),
                widthOK = this.getWidth() == e.getWidth(),
                heightOK = this.getHeight() == e.getHeight(),
                shortcutOK = this.getShortcut().equals(e.getShortcut());

        return audioOK && idOK && keyOrIdOK && widthOK && heightOK && shortcutOK;
    }


}
