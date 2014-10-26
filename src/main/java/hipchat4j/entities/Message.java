package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 26/10/14/17:47
 */
public class Message {

    public static class From {

        public static class Links {
            private String self;
        }

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

    }

    public static class MessageLinks {

        public static class TwitterUser {
            private int followers;
            private String name;
            private String profileImageUrl;
            private String screenName;
        }

        public static class Image {
            String image;
            String name;
        }

        public static class TwitterStatus {
            private String name;
            private String created;
            private String text;
            private String profileImageUrl;
            private String source;
            private String screenName;
        }

        public static class Video {
            private String thumbnailUrl;
            private int views;
            private String author;
            private String title;
        }

        public static class Link {
            private String description;
            private String title;
            private String headerText;
            private String linkText;
            private String faviconUrl;
            private String fullUrl;
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


    }

    public static class File {
        private String url;
        @SerializedName("thumb_url")
        private String thumbnailUrl;
        private String name;
        private int size;
    }

    public static class Mentions {

        public static class Links {
            private String self;
        }

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

    }


    private From from;
    @SerializedName("message_links")
    private MessageLinks messageLinks;
    private File file;
    private String date;
    private Mentions mentions;



}
