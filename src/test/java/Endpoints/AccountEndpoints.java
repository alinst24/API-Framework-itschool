package Endpoints;

public class AccountEndpoints {

    public static final String ACCOUNT_CREATE="/Account/v1/User";
    public static final String ACCOUNT_TOKEN="/Account/v1/GenerateToken";
    public static final String ACCOUNT_USERSPECIFIC="/Account/v1/User/{userID}";       // {userID} = o cheie care sa fie inlocuita
    public static final String ACCOUNT_DELETE = "/Account/v1/User/{userID}";
}
