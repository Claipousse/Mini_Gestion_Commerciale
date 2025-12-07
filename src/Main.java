import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Client> clients = new ArrayList<>(); //Liste des clients en mémoire
    private static ArrayList<Produit> produits = new ArrayList<>(); //Liste des produits en mémoire
    private static ArrayList<Facture> factures = new ArrayList<>(); //Liste des factures en mémoire
    private static final Scanner scanner = new Scanner(System.in); //Scanner pour que l'utilisateur puisse saisir
    //(ndt c'est intelliJ qui m'a fait rajouter le final, de ce que j'ai compris on pourra plus l'utiliser apres, un peu comme un usage unique et dans notre cas ça serait une bonne pratique apparemment)

    public static void main() { //Pas de args car on s'en sert po
        chargerDonnees(); //On charge les données

        boolean continuer = true; //Tant que c'est vrai, on affiche le menu
        while (continuer) {
            System.out.println("\n========== GESTION COMMERCIALE ==========");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Gestion des produits");
            System.out.println("3. Gestion des factures");
            System.out.println("4. Quitter (et sauvegarder)");
            System.out.print("Votre choix : ");
            int choix = lireEntier(); //Je le fais dans une fonction comme on répète souvent cette partie

            switch (choix) {
                case 1:
                    menuClients(); //Je le fais dans une autre fonction pour simplifier un peu la lecture du menu
                    break;
                case 2:
                    menuProduits(); //Idem
                    break;
                case 3:
                    menuFactures(); //Idem
                    break;
                case 4:
                    sauvegarderDonnees();
                    continuer = false; //On passe sur false et la boucle stop
                    System.out.println("Ciao Kombucha !");
                    break;
                default:
                    System.out.println("Mauvais choix");
            }
        }
        scanner.close();
    }

    // --- PARTIE CLIENTS ---

    private static void menuClients() { //Affiche le menu pour la gestion des clients (choix 1)
        boolean retour = false; //On passe sur True si on veut se barrer
        while (!retour) {
            System.out.println("\n--- GESTION DES CLIENTS ---");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher tous les clients");
            System.out.println("3. Rechercher un client par ID");
            System.out.println("4. Supprimer un client");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireEntier();
            switch (choix) {
                case 1:
                    ajouterClient();
                    break;
                case 2:
                    afficherClients();
                    break;
                case 3:
                    rechercherClient();
                    break;
                case 4:
                    supprimerClient();
                    break;
                case 5:
                    retour = true;
                    break;
                default:
                    System.out.println("Mauvais choix");
            }
        }
    }

    private static void ajouterClient() {
        System.out.println("\n--- AJOUTER UN CLIENT ---");

        int id = clients.size() + 1; //Pour id unique, on prend nombre de client +1, comme une autoincrémentation

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prenom : ");
        String prenom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Adresse : ");
        String adresse = scanner.nextLine();

        System.out.print("Code postal : ");
        String codePostal = scanner.nextLine();

        System.out.print("Ville : ");
        String ville = scanner.nextLine();

        Client client = new Client(id, nom, prenom, email, adresse, codePostal, ville);
        clients.add(client);

        System.out.println("Client ajouté avec succes -> ID : " + id);
    }

    private static void afficherClients() {
        System.out.println("\n--- LISTE DES CLIENTS ---");
        if (clients.isEmpty()) { //Si vide on renvoi message d'erreur
            System.out.println("Aucun client enregistre.");
        } else {
            for (Client c : clients) {
                System.out.println(c);
                System.out.println("---"); //On sépare pour mieux lire
            }
        }
    }

    private static void rechercherClient() {
        System.out.print("\nEntrez l'ID du client : ");
        int id = lireEntier();

        Client client = trouverClientParId(id);
        if (client != null) { //Si on a quelque chose alors un client correspond
            System.out.println("\nClient trouvé :");
            System.out.println(client);
        } else { //Sinon il y a quedal, message d'erreur
            System.out.println("Client introuvable.");
        }
    }

    private static void supprimerClient() { //C'est un peu comme une recherche, mais la au lieu de lire on supprime de l'arraylist
        System.out.print("\nEntrez l'ID du client à supprimer : ");
        int id = lireEntier();

        Client client = trouverClientParId(id);
        if (client != null) {
            clients.remove(client);
            System.out.println("Client supprimé avec succès !");
        } else {
            System.out.println("Client introuvable.");
        }
    }

    private static Client trouverClientParId(int id) { //On prend l'ID qu'on recherche en param
        for (Client c : clients) { //On cherche dans l'arraylist clients
            if (c.getId() == id) { //Si ya un ID qui match
                return c; //Et on le renvoi direct
            }
        }
        return null; //Si on a rien trouvé la boucle stop et on return null
    }

    // --- PARTIE PRODUITS ---

    private static void menuProduits() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES PRODUITS ---");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Afficher tous les produits");
            System.out.println("3. Rechercher un produit par ID");
            System.out.println("4. Supprimer un produit");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireEntier();

            switch (choix) {
                case 1:
                    ajouterProduit();
                    break;
                case 2:
                    afficherProduits();
                    break;
                case 3:
                    rechercherProduit();
                    break;
                case 4:
                    supprimerProduit();
                    break;
                case 5:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private static void ajouterProduit() {
        System.out.println("\n--- AJOUTER UN PRODUIT ---");

        int id = produits.size() + 1; //Auto increment

        System.out.print("Description : "); //Saisie description
        String description = scanner.nextLine();

        System.out.print("Prix : "); //Saisie prix float
        float prix = lireFloat();

        Produit produit = new Produit(id, description, prix);
        produits.add(produit);

        System.out.println("Produit ajouté -> ID : " + id);
    }

    private static void afficherProduits() {
        System.out.println("\n--- LISTE DES PRODUITS ---");
        if (produits.isEmpty()) {
            System.out.println("Aucun produit enregistré.");
        } else {
            for (Produit p : produits) {
                System.out.println(p);
                System.out.println("---");
            }
        }
    }

    private static void rechercherProduit() {
        System.out.print("\nEntrez l'ID du produit : ");
        int id = lireEntier();

        Produit produit = trouverProduitParId(id);
        if (produit != null) {
            System.out.println("\nProduit trouvé :");
            System.out.println(produit);
        } else {
            System.out.println("Produit introuvable.");
        }
    }

    private static void supprimerProduit() {
        System.out.print("\nEntrez l'ID du produit à supprimer : ");
        int id = lireEntier();

        Produit produit = trouverProduitParId(id);
        if (produit != null) {
            produits.remove(produit);
            System.out.println("Produit supprimé avec succès !");
        } else {
            System.out.println("Produit introuvable.");
        }
    }

    private static Produit trouverProduitParId(int id) { //On prend l'ID qu'on recherche
        for (Produit p : produits) { //On parcourt l'arraylist des produits
            if (p.getId() == id) {
                return p; //On return l'id qui match
            }
        }
        return null; //Ou rien si on a rien qui match
    }

    // --- PARTIE FACTURES ---

    private static void menuFactures() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- GESTION DES FACTURES ---");
            System.out.println("1. Creer une nouvelle facture");
            System.out.println("2. Afficher toutes les factures");
            System.out.println("3. Afficher une facture par ID");
            System.out.println("4. Ajouter des produits à une facture");
            System.out.println("5. Supprimer une facture");
            System.out.println("6. Generer un PDF pour une facture");
            System.out.println("7. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = lireEntier();

            switch (choix) {
                case 1:
                    creerFacture();
                    break;
                case 2:
                    afficherFactures();
                    break;
                case 3:
                    afficherFacture();
                    break;
                case 4:
                    ajouterProduitFacture();
                    break;
                case 5:
                    supprimerFacture();
                    break;
                case 6:
                    genererPDFFacture();
                    break;
                case 7:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private static void creerFacture() {
        System.out.println("\n--- CRÉER UNE FACTURE ---");

        if (clients.isEmpty()) { //On peut pas créer une facture sans client, on fait ce checkup avant
            System.out.println("Aucun client disponible. Veuillez d'abord ajouter un client.");
            return;
        }

        int id = factures.size() + 1;

        System.out.print("Date (JJ/MM/AAAA) : "); //Traitement date
        String date = scanner.nextLine();

        System.out.print("ID du client : "); //Traitement ID client
        int idClient = lireEntier();

        Client client = trouverClientParId(idClient);
        if (client == null) {
            System.out.println("Client introuvable.");
            return;
        }

        Facture facture = new Facture(id, date, client); //On crée la facture avec les infos saisis avant
        factures.add(facture);

        System.out.println("Facture créée avec succès ! ID : " + id);
    }

    private static void afficherFactures() {
        System.out.println("\n--- LISTE DES FACTURES ---");
        if (factures.isEmpty()) { //Si ya rien on peut rien afficher, message d'erreur
            System.out.println("Aucune facture enregistrée.");
        } else {
            for (Facture f : factures) {
                System.out.println(f);
                System.out.println("=================="); //Séparateur
            }
        }
    }

    private static void afficherFacture() {
        System.out.print("\nEntrez l'ID de la facture : ");
        int id = lireEntier();

        Facture facture = trouverFactureParId(id);
        if (facture != null) {
            System.out.println(facture);
        } else {
            System.out.println("Facture introuvable.");
        }
    }

    private static void ajouterProduitFacture() {
        System.out.println("\n--- AJOUTER UN PRODUIT À UNE FACTURE ---");

        System.out.print("ID de la facture : "); //On demande la facture à laquelle on veut ajouter le produit
        int idFacture = lireEntier();

        Facture facture = trouverFactureParId(idFacture);
        if (facture == null) {
            System.out.println("Facture introuvable.");
            return;
        }

        System.out.println("\nFacture trouvée : Client " + facture.getClient().getPrenom() +
                " " + facture.getClient().getNom());
        System.out.println("Total actuel : " + facture.calculerTotal() + "€");

        if (produits.isEmpty()) {
            System.out.println("Aucun produit disponible.");
            return;
        }

        boolean continuerAjout = true;

        while (continuerAjout) {
            System.out.println("\nProduits disponibles :"); //Pour aider lors de la saisie vu qu'on se refere à l'ID et que l'id c'est pas trop parlant
            for (Produit p : produits) { //On liste les produits qu'on a
                System.out.println("  ID " + p.getId() + " : " + p.getNom() + " - " + p.getPrix() + "€");
            }

            System.out.print("\nEntrez l'ID du produit à ajouter (ou 0 pour terminer) : "); //On demande le produit qu'on veut ajouter
            int idProduit = lireEntier();

            if (idProduit == 0) {
                continuerAjout = false;
            } else {
                Produit produit = trouverProduitParId(idProduit); //On demande le produit
                if (produit == null) {
                    System.out.println("Produit introuvable.");
                } else {
                    System.out.print("Quantité : "); //Et en quelle quantité
                    int quantite = lireEntier();

                    if (quantite > 0) {
                        facture.ajouterProduit(produit, quantite);
                        System.out.println("✓ Produit ajouté : " + produit.getNom() + " x" + quantite);
                    } else {
                        System.out.println("Quantité invalide.");
                    }
                }
            }
        }

        System.out.println("\n✓ Produits ajoutés :)");
        System.out.println("Nouveau total : " + facture.calculerTotal() + "€");
    }

    private static Facture trouverFactureParId(int id) { //meme fonctionnement que trouverclients & trouverfacture
        for (Facture f : factures) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    private static void genererPDFFacture() {
        System.out.println("\n--- GÉNÉRER UN PDF ---");

        if (factures.isEmpty()) {
            System.out.println("Aucune facture disponible.");
            return;
        }

        System.out.print("Entrez l'ID de la facture : ");
        int id = lireEntier();

        Facture facture = trouverFactureParId(id);
        if (facture != null) {
            String nomFichier = "facture_" + id + ".pdf";
            GestionFactures.genererPDF(facture, nomFichier);
        } else {
            System.out.println("Facture introuvable.");
        }
    }

    private static void supprimerFacture() {
        System.out.println("\n--- SUPPRIMER UNE FACTURE ---");

        if (factures.isEmpty()) { //Si ya rien on peut rien supprimer, message d'erreur
            System.out.println("Aucune facture enregistrée.");
            return;
        }

        System.out.print("Entrez l'ID de la facture à supprimer : ");
        int id = lireEntier();

        Facture facture = trouverFactureParId(id);
        if (facture != null) { //Si on trouve la facture, on la supprime
            factures.remove(facture);
            System.out.println("✓ Facture supprimée avec succès !");
        } else {
            System.out.println("Facture introuvable.");
        }
    }

    //--- PARTIE UTILITAIRE ---

    private static void chargerDonnees() {
        clients = GestionFichier.chargerClients("clients.csv");
        produits = GestionFichier.chargerProduits("produits.csv");
        factures = GestionFichier.chargerFactures("factures.csv", clients, produits);
    }

    private static void sauvegarderDonnees() {
        GestionFichier.sauvegarderClients(clients, "clients.csv");
        GestionFichier.sauvegarderProduits(produits, "produits.csv");
        GestionFichier.sauvegarderFactures(factures, "factures.csv");
    }

    private static int lireEntier() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine()); //On retourne l'input converti en entier
            } catch (NumberFormatException e) { //Si l'input est pas bon, on le fait savoir
                System.out.print("Erreur : c'est pas un entier...");
            }
        }
    }

    private static float lireFloat() {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine()); //on retourne l'input converti en float
            } catch (NumberFormatException e) {
                System.out.print("Erreur : c'est pas un float...");
            }
        }
    }
}