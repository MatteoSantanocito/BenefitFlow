import java.util.EventObject;

public class FormEvent extends EventObject {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public FormEvent(Object source, String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
    }
}
