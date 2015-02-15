package hipchat4j.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * hipchat4j
 * 11/10/14/20:55
 */
public class ServerCapability {

    private Vendor vendor = new Vendor();
    private String name = "";
    private Links links = new Links();
    private CapabilitiesList capabilities = new CapabilitiesList();
    private String key = "";
    private String description = "";

    public Vendor getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Links getLinks() {
        return links;
    }

    public CapabilitiesList getCapabilities() {
        return capabilities;
    }

    public String getDescription() {
        return description;
    }

    public String getKey() {
        return key;
    }

    public class Vendor {
        private String url = "";
        private String name = "";

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }

    public class Links {
        private String self = "";
        private String homepage;
        private String api; // undocumented but returned by hipchat.com

        public String getSelf() {
            return self;
        }

        public String getHomepage() {
            return homepage;
        }

        public String getApi() {
            return api;
        }
    }

    public class CapabilitiesList {

        private OAuth2Provider oauth2Provider = new OAuth2Provider();
        private OpenIdProvider openIdProvider = new OpenIdProvider();
        private List<Webhook> webhook = new ArrayList<>();
        private HipchatApiProvider hipchatApiProvider = new HipchatApiProvider();
        private Configurable configurable = new Configurable();
        private OAuth2Consumer oauth2Consumer = new OAuth2Consumer();
        private HipchatApiConsumer hipchatApiConsumer = new HipchatApiConsumer();
        private Installable installable = new Installable();

        public OAuth2Provider getOauth2Provider() {
            return oauth2Provider;
        }

        public OpenIdProvider getOpenIdProvider() {
            return openIdProvider;
        }

        public List<Webhook> getWebhook() {
            return webhook;
        }

        public HipchatApiProvider getHipchatApiProvider() {
            return hipchatApiProvider;
        }

        public Configurable getConfigurable() {
            return configurable;
        }

        public OAuth2Consumer getOauth2Consumer() {
            return oauth2Consumer;
        }

        public HipchatApiConsumer getHipchatApiConsumer() {
            return hipchatApiConsumer;
        }

        public Installable getInstallable() {
            return installable;
        }

        public class OAuth2Provider {
            private String tokenUrl;
            private String authorizationUrl;

            public String getTokenUrl() {
                return tokenUrl;
            }

            public String getAuthorizationUrl() {
                return authorizationUrl;
            }
        }

        public class OpenIdProvider {
            private String url = "";
            private String logoutUrl;
            private String name;

            public String getName() {
                return name;
            }

            public String getUrl() {
                return url;
            }

            public String getLogoutUrl() {
                return logoutUrl;
            }
        }

        public class Webhook {
            private String url = "";
            private String pattern;
            private String event = "";
            private String name;

            public String getUrl() {
                return url;
            }

            public String getPattern() {
                return pattern;
            }

            public String getEvent() {
                return event;
            }

            public String getName() {
                return name;
            }
        }

        public class HipchatApiProvider {

            /*
             * Scopes aren't well covered in the Capabilities API page
             * but I found below on the auth page
             *
             * admin_group - Perform group administrative tasks
             * admin_room - Perform room administrative tasks
             * manage_rooms - Create, update, and remove rooms
             * send_message - Send private one-on-one messages
             * send_notification - Send room notifications
             * view_group - View users, rooms, and other group information
             * view_messages - View messages from chat rooms and private chats you have access to
             *
             * these scopes can be these
             * https://www.hipchat.com/docs/apiv2/auth
             */

            private Map<String, Scope> availableScopes;
            private String url = "";

            public String getUrl() {
                return url;
            }

            public Map<String, Scope> getAvailableScopes() {
                return availableScopes;
            }

            public class Scope {

                private String description;
                private String id;
                private String name;

                public String getDescription() {
                    return description;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }
            }
        }

        public class Configurable {
            private String url = "";
            private boolean allowAccessToRoomAdmins;

            public String getUrl() {
                return url;
            }

            public boolean isAllowAccessToRoomAdmins() {
                return allowAccessToRoomAdmins;
            }
        }

        public class OAuth2Consumer {
            private List<String> redirectionUrls = new ArrayList<>();

            public List<String> getRedirectionUrls() {
                return redirectionUrls;
            }
        }

        public class HipchatApiConsumer {
            private List<String> scopes = new ArrayList<>();
            private String fromName;

            public List<String> getScopes() {
                return scopes;
            }

            public String getFromName() {
                return fromName;
            }
        }

        public class Installable {
            private String installedUrl;
            private String uninstalledUrl;
            private boolean allowRoom;
            private boolean allowGlobal;
            private String callbackUrl;

            public String getInstalledUrl() {
                return installedUrl;
            }

            public String getUninstalledUrl() {
                return uninstalledUrl;
            }

            public boolean isAllowRoom() {
                return allowRoom;
            }

            public boolean isAllowGlobal() {
                return allowGlobal;
            }

            public String getCallbackUrl() {
                return callbackUrl;
            }
        }
    }

}
