package pe.exceltransport.exceltrack.view.util;

public enum Extra {

    TRIP("extra_trip");

    private String value;

    Extra(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
