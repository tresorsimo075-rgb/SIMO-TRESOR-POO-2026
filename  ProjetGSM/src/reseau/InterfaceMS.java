package reseau;

public interface InterfaceMS {
    boolean sAttacher(BTS bts);
    boolean appeler(MS destinataire);
    void afficherAppelsRecus();
}