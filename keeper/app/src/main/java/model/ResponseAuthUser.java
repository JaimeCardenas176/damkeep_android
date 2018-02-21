package model;

/**
 * Created by jaime on 21/02/18.
 */

public class ResponseAuthUser {
    private String key;
    private String nombre;
    private String email;
    private String msg;

    public ResponseAuthUser() {
    }

    public ResponseAuthUser(String key, String nombre, String email, String msg) {
        this.key = key;
        this.nombre = nombre;
        this.email = email;
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseAuthUser that = (ResponseAuthUser) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (!nombre.equals(that.nombre)) return false;
        if (!email.equals(that.email)) return false;
        return msg != null ? msg.equals(that.msg) : that.msg == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseAuthUser{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
