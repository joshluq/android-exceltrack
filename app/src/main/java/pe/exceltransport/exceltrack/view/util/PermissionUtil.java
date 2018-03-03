package pe.exceltransport.exceltrack.view.util;


import android.Manifest;

public class PermissionUtil {

    public static final int LOCATION = 1000;

    public enum Permission {
        LOCATION(PermissionUtil.LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);

        private int code;
        private String perm;

        Permission(int code, String perm) {
            this.code = code;
            this.perm = perm;
        }

        public int getCode(){
            return code;
        }

        public String getPerm() {
            return perm;
        }
    }
}