package Rest;

public class RestRequestType {

    // Layer-ul 1

    // Clasa aceasta reprezinta tipul requestului
    //  - si folosim static , ca sa il putem folosi peste tot unde dorim,fara sa fie nevoie de obiect si final pentru ca sunt constante(nu isi modifica valoarea))
    public static final String REQUEST_POST="post";       // si astfel, tot timpul cand vrem sa folosim cuvantul "post" avem aceasta clasa in care putem sa accesam constanta
    public static final String REQUEST_PUT="put";
    public static final String REQUEST_GET="get";
    public static final String REQUEST_DELETE="delete";



    // apoi facem pentru status codes : clasa RestRequestStatus
}
