package Objects.RequestObject;

import java.util.HashMap;

public class RequestAccount {
    //facem reprezentarea asa cum arata in requestul de pe swagger
    //2 variabile numite asa cum sunt in request

    private String userName;         // sa fie exact la fel cum este in "requestbody.put..."

    private String password;
   //getteri si setteri

    //si un constructor pt variabile

    public RequestAccount(HashMap<String, String> testData) { // Si facem un constructor care sa tina datele noastre ; IAR ULTERIOR AM MODIFICAT CONSTRUCTORUL PENTRU PROPERTYUTILITY, sa primeasca acest hashmap de mai jos
        populateObject(testData);
    }

    private void populateObject(HashMap<String, String> testData) {
        for (String Key : testData.keySet()) {
            switch (Key) {
                case "userName":
                    setUserName(testData.get(Key)+System.currentTimeMillis());
                    break;
                case "password":
                    setPassword(testData.get(Key));
                    break;
            }
        }
    }

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





}
