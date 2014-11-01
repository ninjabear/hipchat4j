package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 26/10/14/17:47
 */
public class Message {

    /*
     * https://www.hipchat.com/docs/apiv2/method/get_room_message
     * somehow the "from" can be either a string or an object

                                                                                          `@@@@@@@@@@;
                                                                                    ,@@@@@@@@@@@@@@@@@@@@@@@@'
                                                                                @@@@@@@@@@@@        @@@@@@@@@@@@@
                                                                              @@@@@@@@                      `@@@@@@@`
                                                                            @@@@@                               @@@@@@@
                                                                     @@@@@@@@@@                                    ,@@@@@@
                                                                   @@@@@@@@@;                                         @@@@@@
                                                                 `@@@@@@@@                                               @@@@@
                                                                @@@@@@                                                     @@@@@
                                                               @@@                          @                                @@@@@
                                                             #@@@                          '@                      @           @@@@@
                                                            @@@@                           @                       @@      @     @@@@@
                                                           @@@`                            @                        @@     @       @@@@
                                                         @@@@                              @@@                      @@     @ @       @@@@
                                                        @@@#                                                         @@      @        @@@@@
                                                       @@@                                                           @@      `          @@@@:
                                                      @@@                                                            @@                   @@@@
                                                     @@@                                                              @@                   .@@@
                                                   +@@@                                                     @'        @@      @@             @@@#
                                                  @@@+                                                       #@       @@@     @ @,            @@@@
                                                  @@                                                           @       @@    @@  `@@            @@@,
                                                 @@#                                                            @      @@    @, '                @@@@
                                                 @@                                                              @      @      @@@@@               @@@
                                                @@,                                                               @     @     @        ;@@          @@@
                                                @@                                                                @'    @    @       ;@@             @@:
                                               @@@                                                                 @    @:          @@@              `@@
                                               @@                                                                   @   ,@         @@.                @@@
                                              .@@                                                                   @+   @        @@                   @@@
                                              @@                                                        :            @   @       @@                     @@#
                                              @@                                                         @            @   @     @@                       @@
                                             @@@                                             @@@@@@       @            @  #    ;@                        @@@
                                             @@                                                   @@@`    @@            @      @                          @@@
                                             @@                                                     ;@@    @@            @    @                            @@
                                            @@@                                                       @@`   @@#           @ ' @                            @@@
                                            @@                                                         `@,  '@@@          .@ .                              @@
                                            @@                                                `@@@@      @   @@@@#         @ @              @               :@@
                                           @@`                                              @@#           @   @@@@@        @ @             +.                @@`
                                           @@                                             @@,              @  ,@@@@@@      @@@            .@                  @@
                                          @@@                                            +@     @.         +   @@@@@@@@   @ @@          @@@                   @@`
                                          @@                                             @    @@@@@         @   @@@@@`   @   #@       @@@@@                   `@@
                                          @@                                             @    @@@@@@            `              @@@@@@@@@@@@@@@@                @@
                                         @@@                                             @   '@@@@@@:        @   @@   @@@        @@@@@@@      .@@;             @@@
                                         @@                                              ,    @@@@@@         @   @@@@@@@@@        @@@@            @             @@
                                         @@                                                   @@@@@@             @@           @    @@@              @           @@@
                                        '@@                                              +    `@@@@              @@@@@             `      @@@@@@@@   @           @@
                                        @@                                               @      @@              @@@@@:             @    @@        @   ,          @@
                                        @@                                                @            @@      @@@@@@@            @@   @@@         @             @@,
                                        @@                                                  @           @     '@@    @@@@         @@  @             @             @@
                                       :@@                                                    @@@ @@@        #@@     @   @@   @    @ +@       @@@@   @            @@
                                       @@`                                                      @@@@        @@       @'    .' @    @ @       @@@@@@  ;            @@
                                       @@                                              `                  `@;         @      @#    @ :'      @@@@@@@              @@@
                                       @@                                               @ `@@@;         @@@            @     @ @   @  @+@    @@@@@@@               @@
                                      `@@                                                @: @@@@@@@@@@@@'                             #`     @@@@@@                @@
                                      @@#                                                 #   :'::.                           '     @  @     .@@@@   @             @@
                                      @@                                                                       @                    @ @ @           `              @@@
                                      @@                                                                     #@                     @@   ;@   :    @                @@
                                     @@@                                              '                     @@                       @'    @ @    @                 @@
                                     @@                                               @@ ;.               @@                         @@      .@@+                   @@
                                     @@                                                @@@         #@ :@@@@                          ;@                             @@
                                     @@                                                        @  @@@@@@@       @                     @                             @@`
                                     @@                                                         @@@@@@:          @@@                  @@                            @@'
                                     @@                                                                          @@@   @               @                            @@@
                                     @@                                                                           @@@' @@              @                            @@@
                                     @@                                                                           @@@@@@@+              @                           @@@
                                     @@                                                                           @@  @@@ @                                         @@@
                                     @@                                                                          @@@@  @@@@@@            @                          +@@
                                     @@                                                                          @@@  @@ @.@@@ @ @       @@                         '@@
                                     @@                                                                         @@ @@ .      @@;@  @     @@;                        '@@
                                     @@                                                                         '@`          @@ @ #@     :@@                        '@'
                                     @@                                                                         @@            @   @  '    @@                        @@'
                                     @@                                                                         @@                   @  . @ :                       @@@
                                     @@                                                                         @@@   ,:             @   .. '                       @@@
                                     @@                                                                         @@@@@@@@@@                  @                       @@.
                                     @@                                                                         @     `@@@@@                @                       @@
                                     @@                                                                        @         @@@@               @                       @@
                                     @@.                                                                       @            @#             #'                       @@
                                     @@@                                                                      +#            @@             @                        @@
                                     @@@                                                                      @              @            @@                        @@
                                      @@                                                                      @              @@          @@                        '@@
                                      @@                                     @                                @@            @@@@@       @@                         @@,
                                      @@                                                                     @               @@@@@@@@@@@@@                         @@
                                      @@.                       @                                                  @@@@@,     @@@@@@@@@@@                          @@
                                      #@@                       '@                                              @@@@@@@@@     @@@@@@@@@@@                          @@
                                       @@                                                  #                 @@@@@@@@@@@     :@     @@@@@                         .@@
                                       @@                                                @@`                ,,@@@@@@@@@ @@@@@@@      @ `                          @@@
                                       @@                                                 @                   @@@@@@@# @      @@                                  @@
                                       @@                                                                     @@@@@@@           @@@@@@@@                          @@
                                       @@                                                                     @@@@@.                 @@@@#                        @@
                                       @@@                                                                   `@@@@  #@      @@@,      @@@@@                       @@
                                       '@@                                                                  @, @@  @@    @@@@@@@@      @@@@                      :@@
                                        @@                                                                     @+      @@@  @@@@@@     :@@                       @@:
                                        @@                                                                    #@     +@@@@    @@@@@@`   @@                       @@
                                        @@                                                                   @@     @@@    #   @    @   @                        @@
                                        @@@                                                                  @     @@@@      @@@        @:                       @@
                                         @@                                                                  @    @@   @  @   @  @:@@   @:                       @@
                                         @@                                                                 '@  @@@@   @  @  @@ ;@@ @   @:                      @@+
                                         @@'                                                                @@@@@  @   @       @@ @ ,   @'                      @@
                                         `@@                                 @                              @@. @ @@@@@   : @      @   @@@                      @@
                                          @@                                 @@                            :@@   @@@@@@@@@@ @@        @@@@                     ,@@
                                          @@@                                                              @  @@@@@@@@@@@@@@@@ @  @  @@@@@                     @@
                                           @@                                                              @  @@@@@@@@@@@@@@@@@ @@# .@@ @.                     @@
                                           @@@                                                            @@  @: @@@@ @@ @' @@@  @   @   :                    @@@
                                            @@                                                            @ @@@  ,@@@    `   @@@@@  @                         @@
                                            #@@                                                          @    :@    @@@       @@@@@@@                        @@@
                                             @@'                                                                @   @  @@@@'   @   @                         @@
                                              @@                                                                  ``.      @@ @'    @                       @@@
                                              @@@                                                                  @       @   @    @                      @@@
                                               @@@                                                                         `        @                     .@@
                                                @@@@                                                    @    ,                 @    +                     @@`
                                                 @@@@@                                                 ;      @                                          @@@
                                                 @@@@@@                                                        @#                                       @@@
                                                @@@@@@@@                                        @     @  @@@@@  '@                  .                  @@@
                                               @@@   @@@@,                                     @        @@@@@@@@  @@               @@                 @@@
                                             +@@@     @@@@@                                    @     '   @@@; :@@@@@@@,           ,@@                @@@
                                            @@@#        @@@@'                                 @.       @           .@@@@@@       @@ @              `@@@
                                           @@@,          @@@@@                               @@     @  @              #@@@@@@@@@@@  @@            #@@@
                                          @@@,             @@@@@                            @@`     @ #                   '@@@@@    :@           @@@:
                                         @@@`               :@@@@@                         #@@      @@                               @@         @@@
                                        @@@                   @@@@@.                      ,@@      @@#                               @@      `@@@@
                                       @@@                      @@@@@                   @@@        @@                                @@@   @@@@@@
                                      @@@       @                 @@@@@                 @@         @                                  @@ @@@@@`
                                    .@@@       @@@@                `@@@@@@          . @@@          ,                                  @@@@@@
                                   @@@@        @@@@@                 #@@@@@@@       #@@@                                             @@@@@
                                  @@@@           @@@@                   @@@@@@@,     .@                                            @@@@@
                                 @@@;             @@@                      @@@@@@@@@@@                                           @@@@@
                                @@@                @@@                       .@@@@@@@@@:                                      @@@@@@
                              #@@@                 `@@@                         @@@@@@@@@@@@@@:                            @@@@@@@
                             @@@#                   #@@@                            @@@@@@@@@@@@@@@@@@@@@@@;.      .:@@@@@@@@@@
                            @@@                      @@@@                                .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                           @@@                        @@@@                                       '@@@@@@@@@@@@@@@@@@@@:
                          @@@                          @@@@                                             #@@@
                        @@@@                            @@@;                                            @@@                                                   @.
                       @@@@                              @@@.                                          @@@                                           @        @@
                      @@@                                 @@@`                                        @@@@@                                         +@@       @@
                    `@@@                                   @@@`                                      +@@@@@@                                         @@@     :@    @
                   ,@@@                                    `@@@.                                     @@@  @@@                                         @@@@   @@   @`
                   @@@                                       @@@.                                   @@@    @@@;                                        #@@@@@@@, @@
                  @@@                                         @@@.                                 @@@       @@@       @    @@                         @@@@@@@@@@@
                 @@@                                           @@@                                `@@:        @@@      @#    @@                        @@   @@@@@
                 @@                                             @@@                               @@@          @@@     @@    @@    @@                 @@
                @@#                                              @@@                             @@@            @@@    @@    @@    @@                @@
               @@@                                               `@@@                            @@#             @@@#  @@    @@    @@               @@
              .@@                                                 `@@@.                         @@@               `@@@ @@@  @@@   ;@@             #@@
              @@:                                                   @@@#                       :@@`                @@@@@@@@@@@  @@@@             @@@
             @@@                                                     @@@@                      @@@               @@@@@@@@@@@@@@@@@@            :@@,
            @@@                                                       @@@@                    @@@             @@@@@@` :@@@  @@@@@@    @       @@@
            @@                                                         @@@@                  @@@          @@@@@@@      #@@    @@@   ;@@     @@@'
           @@@                                                          @@@@                '@@@@@@@@@@@@@@@@@@         @@@    ;@@@@@@     @@@
           @@                                                            @@@@        `@@@@@@@@@@@@@@@@@@@@:              @@@     @@@@    @@@
          @@@                                                             @@@@    @@@@@@@@@@@@@+                          @@@           @@@
         @@@                                                               @@@@@@@@@@@@@@@@@@                              @@@        @@@@
        @@@                                                                 @@@@@@@@:     @@                                @@@     @@@@
       @@@                                                                   @@@@        @@'                                 ,@@  @@@@
      @@@                                                                    '           @@                                    @@@@@
     @@@                                                                                @@@                                     @@
    @@@                                                                                 @@
   @@@                                                                                  @@
  @@@                                                                                  @@
 @@@                                                                                   @@
 @@`                                                                                  @@
 @@                                                                                  @@@
 @                                                                                   @@
 @                                                                                  @@@
                                                                                    @@
                                                                                   @@@
                                                                                  '@@
                                                                                  @@`
                                                                                  @@
                                                                                  #
     */

    public enum MessageType {
        FromAddOn,
        FromUser
    }


    public static class From {

        public static class Links {
            private String self;

            public Links(String self) {
                this.self = self;
            }

            public String getSelf() {
                return self;
            }
        }

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

        public From(String mentionName, int id, Links links, String name) {
            this.mentionName = mentionName;
            this.id = id;
            this.links = links;
            this.name = name;
        }

        public String getMentionName() {
            return mentionName;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Links getLinks() {
            return links;
        }
    }

    public static class MessageLinks {

        public static class TwitterUser {
            private int followers;
            private String name;
            private String profileImageUrl;
            private String screenName;

            public TwitterUser(int followers, String name, String profileImageUrl, String screenName) {
                this.followers = followers;
                this.name = name;
                this.profileImageUrl = profileImageUrl;
                this.screenName = screenName;
            }

            public int getFollowers() {
                return followers;
            }

            public String getName() {
                return name;
            }

            public String getProfileImageUrl() {
                return profileImageUrl;
            }

            public String getScreenName() {
                return screenName;
            }
        }

        public static class Image {
            String image;
            String name;

            public Image(String image, String name) {
                this.image = image;
                this.name=name;
            }

            public String getImage() {
                return image;
            }

            public String getName() {
                return name;
            }
        }

        public static class TwitterStatus {
            private String name;
            private String created;
            private String text;
            private String profileImageUrl;
            private String source;
            private String screenName;

            public TwitterStatus(String name, String created, String profileImageUrl, String text, String source, String screenName) {
                this.name = name;
                this.created = created;
                this.profileImageUrl = profileImageUrl;
                this.text = text;
                this.source = source;
                this.screenName = screenName;
            }

            public String getName() {
                return name;
            }

            public String getCreated() {
                return created;
            }

            public String getProfileImageUrl() {
                return profileImageUrl;
            }

            public String getText() {
                return text;
            }

            public String getScreenName() {
                return screenName;
            }

            public String getSource() {
                return source;
            }
        }

        public static class Video {
            private String thumbnailUrl;
            private int views;
            private String author;
            private String title;

            public Video(String author, String thumbnailUrl, int views, String title) {
                this.author = author;
                this.thumbnailUrl = thumbnailUrl;
                this.views = views;
                this.title = title;
            }

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public int getViews() {
                return views;
            }

            public String getAuthor() {
                return author;
            }

            public String getTitle() {
                return title;
            }
        }

        public static class Link {
            private String description;
            private String title;
            private String headerText;
            private String linkText;
            private String faviconUrl;
            private String fullUrl;

            public Link(String description, String title, String headerText, String linkText, String fullUrl, String faviconUrl) {
                this.description = description;
                this.title = title;
                this.headerText = headerText;
                this.linkText = linkText;
                this.fullUrl = fullUrl;
                this.faviconUrl = faviconUrl;
            }

            public String getDescription() {
                return description;
            }

            public String getTitle() {
                return title;
            }

            public String getLinkText() {
                return linkText;
            }

            public String getHeaderText() {
                return headerText;
            }

            public String getFullUrl() {
                return fullUrl;
            }

            public String getFaviconUrl() {
                return faviconUrl;
            }
        }



        @SerializedName("twitter_user")
        private TwitterUser twitterUser;
        private String url;
        private Image image;
        @SerializedName("twitter_status")
        private TwitterStatus twitterStatus;
        private Video video;
        private Link link;
        private String type;

        public MessageLinks(TwitterUser twitterUser, String url, Image image, TwitterStatus twitterStatus, Video video, Link link, String type) {
            this.twitterUser = twitterUser;
            this.url = url;
            this.image = image;
            this.twitterStatus = twitterStatus;
            this.video = video;
            this.link = link;
            this.type = type;
        }

        public TwitterUser getTwitterUser() {
            return twitterUser;
        }

        public String getUrl() {
            return url;
        }

        public Image getImage() {
            return image;
        }

        public TwitterStatus getTwitterStatus() {
            return twitterStatus;
        }

        public Video getVideo() {
            return video;
        }

        public Link getLink() {
            return link;
        }

        public String getType() {
            return type;
        }
    }

    public static class File {
        private String url;
        @SerializedName("thumb_url")
        private String thumbnailUrl;
        private String name;
        private int size;

        public File(String url, String thumbnailUrl, String name, int size) {
            this.url = url;
            this.thumbnailUrl = thumbnailUrl;
            this.name = name;
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }
    }

    public static class Mentions {

        public static class Links {
            private String self;

            public Links(String self) {
                this.self = self;
            }

            public String getSelf() {
                return self;
            }
        }

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

        public Mentions(String mentionName, int id, Links links, String name) {
            this.mentionName = mentionName;
            this.id = id;
            this.links = links;
            this.name = name;
        }

        public String getMentionName() {
            return mentionName;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Links getLinks() {
            return links;
        }
    }


    private Object from;
    @SerializedName("message_links")
    private MessageLinks messageLinks;
    private File file;
    private Mentions mentions;
    @SerializedName("message_format")
    private String messageFormat;
    private String color;
    private String date;
    private String id;
    private String message;
    private String type;


    public Message(String from, String messageFormat, String color, String date, Mentions mentions, String message, String type, String id)
    {
        this.from=from;
        this.color=color;
        this.messageFormat=messageFormat;
        this.date=date;
        this.mentions=mentions;
        this.message=message;
        this.type=type;
        this.id=id;
    }


    public Message(From from, MessageLinks messageLinks, File file, Mentions mentions, String message, String type, String id)
    {
        this.from=from;
        this.messageLinks=messageLinks;
        this.file = file;
        this.mentions = mentions;
        this.message = message;
        this.type = type;
        this.id = id;
    }

    public MessageType getMessageType() {

        try {
            From test = (From)this.from;
        } catch (ClassCastException e)
        {
            return MessageType.FromAddOn;
        }

        return MessageType.FromUser;
    }

    public String getFromName() {
        if ( MessageType.FromAddOn.equals(getMessageType()) )
        {
            return (String)from;
        }

        return ((From)this.from).getName();
    }

    public MessageLinks.TwitterUser getTwitterUser() {

        if (MessageType.FromAddOn.equals(getMessageType()))
            return null;

        return this.messageLinks.getTwitterUser();
    }

    public MessageLinks getMessageLinks() {
        return messageLinks;
    }

    public File getFile() {
        return file;
    }

    public Mentions getMentions() {
        return mentions;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
