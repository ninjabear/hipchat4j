package hipchat4j.entities;

import hipchat4j.Capabilities;

import java.util.List;

/**
 * hipchat4j
 * 11/10/14/20:55
 */
public class ServerCapability {

    public class Vendor {
        private String url;
        private String name;

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }

    public class Links {
        private String self;
        private String homepage;

        public String getSelf() {
            return self;
        }

        public String getHomepage() {
            return homepage;
        }
    }

    public  class CapabilitiesList {

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
            private String url;
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
            private String url;
            private String pattern;
            private String event;
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
            private String url;
            private String availableScopes;

            public String getUrl() {
                return url;
            }

            public String getAvailableScopes() {
                return availableScopes;
            }
        }

        public class Configurable {
            private String url;
            private boolean allowAccessToRoomAdmins;

            public String getUrl() {
                return url;
            }

            public boolean isAllowAccessToRoomAdmins() {
                return allowAccessToRoomAdmins;
            }
        }

        public class OAuth2Consumer {
            private List<String> redirectionUrls;

            public List<String> getRedirectionUrls() {
                return redirectionUrls;
            }
        }

        public class HipchatApiConsumer {
            private List<String> scopes;
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

        private OAuth2Provider oauth2Provider;
        private OpenIdProvider openIdProvider;
        private List<Webhook> webhook;
        private HipchatApiProvider hipchatApiProvider;
        private Configurable configurable;
        private OAuth2Consumer oauth2Consumer;
        private HipchatApiConsumer hipchatApiConsumer;
        private Installable installable;

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
    }

    private Vendor vendor;
    private String name;
    private Links links;
    private CapabilitiesList capabilities;
    private String key;
    private String description;

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

}
