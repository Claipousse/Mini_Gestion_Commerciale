import java.util.ArrayList;

public class Facture {
    private int id;
    private String date; // DD/MM/AAAA
    private Client client;
    private ArrayList<Produit> produits; //Liste produits sur la facture
    private ArrayList<Integer> quantites; //Quantité associé

    //Constructeur
    public Facture(int id, String date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.produits = new ArrayList<>();
        this.quantites = new ArrayList<>();
    }

    //Constructeur vide
    public Facture() {
        this.id = 0;
        this.date = "";
        this.client = null;
        this.produits = new ArrayList<>();
        this.quantites = new ArrayList<>();
    }

    //get
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public ArrayList<Integer> getQuantites() {
        return quantites;
    }

    //set
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    public void setQuantites(ArrayList<Integer> quantites) {
        this.quantites = quantites;
    }

    //Logique Métier

    // Ajouter un produit avec sa quantité à la facture
    public void ajouterProduit(Produit produit, int quantite) {
        produits.add(produit);
        quantites.add(quantite);
    }

    // Retirer un produit par son index de la facture
    public void retirerProduit(int index) {
        if (index >= 0 && index < produits.size()) {
            produits.remove(index);
            quantites.remove(index);
        }
    }

    // Calcule le montant total de la facture
    public float calculerTotal() {
        float total = 0;
        for (int i = 0; i < produits.size(); i++) { //On check tous les produits de la liste qu'on a
            total += produits.get(i).getPrix() * quantites.get(i); //On y ajoute leur prix x leur quantité
        }
        return total;
    }

    //Affichage de la facture (j'ai fait avec IA à partir de la boucle, c'est IntelliJ qui ma fait rajouté les append()
    @Override
    public String toString() {
        //result est notre string finale, à laquelle on va ajouter au fur et à mesure les elements via append()
        StringBuilder result = new StringBuilder("Facture n°" + id + " - Date : " + date + "\n");
        result.append("Client : ").append(client.getPrenom()).append(" ").append(client.getNom()).append("\n");
        result.append("Produits :\n");

        for (int i = 0; i < produits.size(); i++) {
            Produit p = produits.get(i);
            int qte = quantites.get(i);
            result.append("  - ").append(p.getNom()).append(" x").append(qte).append(" : ").append(p.getPrix() * qte).append("€\n");
        }
        result.append("Total : ").append(calculerTotal()).append("€");
        return result.toString();
    }


    //Conversion en CSV, meme format que celui voulu dans l'enonce
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(";"); //On met l'id, puis ;
        sb.append(date).append(";"); //Après on met la date, puis re ;
        sb.append(client.getId()).append(";");//On met l'id client, puis ;

        // Construction de la partie "idsproduits|quantites"
        for (int i = 0; i < produits.size(); i++) { //On repete l'opération autant de fois qu'il y a de produits dans la facture
            sb.append(produits.get(i).getId()).append("|").append(quantites.get(i));
            if (i < produits.size() - 1) {
                sb.append(",");  // Séparateur entre les produits
            }
        }
        return sb.toString();
        //A la fin, on a des lignes de la forme : idfacture;datefacture;idclient;idsproduits|quantites
    }


    public static Facture fromFileFormat(String line, ArrayList<Client> clients, ArrayList<Produit> produits) {
        String[] parts = line.split(";");

        // 1. Récupérer id, date
        int id = Integer.parseInt(parts[0]);
        String date = parts[1];

        // 2. Retrouver le client par son ID
        int idClient = Integer.parseInt(parts[2]);
        Client client = null;
        for (Client c : clients) {
            if (c.getId() == idClient) {
                client = c;
                break;
            }
        }

        // 3. Créer la facture
        Facture facture = new Facture(id, date, client);

        // 4. Ajouter les produits avec leurs quantités
        // Format : "2|2,3|1" → produit 2 (qté 2), produit 3 (qté 1)
        if (parts.length > 3 && !parts[3].isEmpty()) {
            String[] produitsData = parts[3].split(",");

            for (String produitData : produitsData) {
                String[] pq = produitData.split("\\|");  // Échapper le |
                int idProduit = Integer.parseInt(pq[0]);
                int quantite = Integer.parseInt(pq[1]);

                // Retrouver le produit par son ID
                for (Produit p : produits) {
                    if (p.getId() == idProduit) {
                        facture.ajouterProduit(p, quantite);
                        break;
                    }
                }
            }
        }
        return facture;
    }
}
