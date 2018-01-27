package pe.exceltransport.data.network;

import pe.exceltransport.data.BuildConfig;

public enum Url {

    ROOT(BuildConfig.API_URL + BuildConfig.API_VERSION);

    private String value;

    Url(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
