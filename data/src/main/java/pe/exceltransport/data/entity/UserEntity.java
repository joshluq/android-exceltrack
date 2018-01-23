package pe.exceltransport.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("email")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
