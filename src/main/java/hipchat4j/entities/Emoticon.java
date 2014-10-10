package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;
import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.json.JsonParser;

/**
 * hipchat4j
 * 09/10/14/20:42
 */
public class Emoticon {

    @SerializedName("emoticon_id_or_key")
    private String keyOrId;

    @SerializedName("shortcut")
    private String shortcut;

    @SerializedName("audio_path")
    private String audioPath;

    @SerializedName("width")
    private Integer width;

    @SerializedName("height")
    private Integer height;

    @SerializedName("id")
    private int id;

    private ConnectorAbstract lazyLoad;


    public Emoticon(int id, String keyOrId, ConnectorAbstract lazyLoad)
    {
        // lazy load constructor
        this.id=id;
        this.keyOrId=keyOrId;

        this.shortcut=null;
        this.audioPath=null;
        this.width=null;
        this.height=null;

        this.lazyLoad=lazyLoad;
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

    private void load() {
        if (height==null||width==null||shortcut==null)
        {
            if (lazyLoad==null)
                throw new IllegalStateException("missing data but no lazy loader");

            String asJson = lazyLoad.get("/v2/emoticon/"+this.id);
            Emoticon loaded = JsonParser.getInstance().fromJson(asJson, Emoticon.class);
            this.width=loaded.getWidth();
            this.height=loaded.getHeight();
            this.shortcut=loaded.getShortcut();
            this.audioPath=loaded.getAudioPath();
            this.keyOrId=loaded.getKeyOrId();
        }
    }


    public int getId() {
        return id;
    }

    public String getKeyOrId() {
        return keyOrId;
    }

    public String getShortcut() {

        if (shortcut==null)
            load();

        return shortcut;
    }

    public Integer getWidth() {

        if (width==null)
            load();

        return width;
    }

    public Integer getHeight() {

        if (height==null)
            load();

        return height;
    }


    public String getAudioPath() {

        if (audioPath==null)
            load();

        return audioPath;
    }

    @Override
    public boolean equals(Object o)
    {
        if (null==o)
            return false;

        if (!(o instanceof Emoticon))
            return false;

        Emoticon e = (Emoticon)o;

        boolean audioOK = (this.getAudioPath()==null && e.getAudioPath()==null) || (this.getAudioPath()!=null && this.getAudioPath().equals(e.getAudioPath())),
                idOK = this.getId()==e.getId(),
                keyOrIdOK = this.getKeyOrId().equals(e.getKeyOrId()),
                widthOK = (this.getWidth() == null && e.getWidth() == null ) || (this.getWidth()!=null && this.getWidth().equals(e.getWidth())),
                heightOK = (this.getHeight() == null && e.getHeight() == null ) || (this.getHeight()!=null && this.getHeight().equals(e.getHeight())),
                shortcutOK = this.getShortcut().equals(e.getShortcut());

        return audioOK && idOK && keyOrIdOK && widthOK && heightOK && shortcutOK;
    }


}
