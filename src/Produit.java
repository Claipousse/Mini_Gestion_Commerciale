//Composé d'un ID, nom et prix
public class Produit {
    private int id;
    private String nom;
    private float prix;

    //Constructeur
    public Produit(int id, String nom, float prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    //Constructeur vide
    public Produit() {
        this.id = 0;
        this.nom = "";
        this.prix = 0;
    }

    //Get
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public float getPrix() {
        return prix;
    }

    //Set
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }

    //Changement format d'affichage
    @Override
    public String toString() {
        return "Produit n°" + id +
                "\n  Description : " + nom +
                "\n  Montant : " + prix + "€";
    }

    //Pour parser l'objet client dans un fichier CSV
    public static Produit fromFileFormat(String line) {
        String[] parts = line.split(";"); //On sépare tout par un ; pour le CSV

        int id = Integer.parseInt(parts[0]);
        String nom = parts[1];
        float prix = Float.parseFloat(parts[2]);

        return new Produit(id, nom, prix);
    }
}

