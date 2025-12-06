//D'après le modèle UML, il faut juste l'id, nom et email
//Mais d'après la structure du texte en CSV, il faut bien plus de champs, notamment le prenom, adresse, code postal, ville/commune
//Au cas ou je vais donc mettre ces champs en plus
public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String codepostal;
    private String ville;

    //Constructeur
    public Client(int id, String nom, String prenom, String email, String adresse, String codepostal, String ville) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.codepostal = codepostal;
        this.ville = ville;
    }

    //Constructeur vide
    public Client() {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.adresse = "";
        this.codepostal = "";
        this.ville = "";
    }

    //Get
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getCodepostal() {
        return codepostal;
    }
    public String getVille() {
        return ville;
    }

    //Set
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    //Changement format d'affichage
    @Override
    public String toString() {
        return "Client n°" + id + " : " + prenom + " " + nom +
                "\n  Email : " + email +
                "\n  Adresse : " + adresse + ", " + codepostal + " " + ville;
    }

    //Pour parser l'objet client dans un fichier CSV
    //ndt: Pour celui la je suis allé voir sur Claude parce que tout seul j'ai pas trouvé comment faire
    public static Client fromFileFormat(String line) {
        String[] parts = line.split(";"); //On sépare tout par un ; pour le CSV

        int id = Integer.parseInt(parts[0]);
        String nom = parts[1];
        String prenom = parts[2];
        String email = parts[3];
        String adresse = parts[4];
        String codePostal = parts[5];
        String ville = parts[6];

        return new Client(id, nom, prenom, email, adresse, codePostal, ville);
    }

    public String toFileFormat() {
        return id + ";" + nom + ";" + prenom + ";" + email + ";" + adresse + ";" + codepostal + ";" + ville;
    }
}






