package RequestObject;

public class RequestAccount {

    private String userName;         // sa fie exact la fel cum este in "requestbody.put..."

    private String password;

    public String getUserName() {          //Facem getteri si setteri  (era posibil si doar getteri)
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RequestAccount(String userName, String password) {      // Si facem un constructor care sa tina datele noastre
        this.userName = userName;
        this.password = password;
    }
}
