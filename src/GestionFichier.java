import java.io.*; // Lecture/Ecriture dans fichiers
import java.util.ArrayList; // Listes Arraylist

public class GestionFichier {

    public static void sauvegarderClients(ArrayList<Client> clients, String filename) { //Sauvegarde la liste des clients dans un .csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { //Ouvre fichier en écriture
            for (Client client : clients) { //Pour chaque clients
                writer.write(client.toFileFormat()); //On écrit le client avec la méthode ToFileFormat définie dans Client.java
                writer.newLine(); //Saut dligne
            }
            System.out.println("Clients sauvegardés :)"); //Ptit message de validation
        } catch (IOException e) { //Si jamais ça marche pas, on attrape l'erreur (si on le met pas on a une erreur de compilation avec le try)
            System.err.println(e.getMessage()); //On affiche l'erreur
        }
    }

    public static ArrayList<Client> chargerClients(String filename) { //On prend en parametre nom du fichier
        ArrayList<Client> clients = new ArrayList<>(); //On crée une nouvelle liste, qui va stocker la liste des clients qu'on va charger
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { //Ouvre le fichier en lecture, Buffer pour lire ligne par ligne
            String line; //On lit la ligne
            while ((line = reader.readLine()) != null) { //Tant que la ligne qu'on étudie n'est pas vide
                clients.add(Client.fromFileFormat(line)); //on ajoute les infos à l'Objet
            }
            System.out.println("Clients chargés dans l'ArrayList");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return clients; //On retourne la liste des clients
    }

    //Sauvegarde des produits, j'ai repris celle pour les clients et j'ai remplacé 2 ou 3 trucs pour que ça fasse aussi les produits
    public static void sauvegarderProduits(ArrayList<Produit> produits, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Produit produit : produits) {
                writer.write(produit.toFileFormat());
                writer.newLine();
            }
            System.out.println("Produits sauvegardés :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //Charger les produits dans un ArrayList (pareil que pour clients)
    public static ArrayList<Produit> chargerProduits(String filename) {
        ArrayList<Produit> produits = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { //Ouvre le fichier en lecture, Buffer pour lire ligne par ligne
            String line; //On lit la ligne
            while ((line = reader.readLine()) != null) { //Tant que la ligne qu'on étudie n'est pas vide
                produits.add(Produit.fromFileFormat(line)); //on ajoute les infos à l'Objet
            }
            System.out.println("Clients chargés dans l'ArrayList");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return produits; //On retourne la liste des clients
    }

    public static void sauvegarderFactures(ArrayList<Facture> factures, String filename) { //Sauvegarde la liste des factures dans un .csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Facture facture : factures) {
                writer.write(facture.toFileFormat());
                writer.newLine();
            }
            System.out.println("Clients sauvegardés :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Facture> chargerFactures(String filename, ArrayList<Client> clients, ArrayList<Produit> produits) {
        ArrayList<Facture> factures = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                factures.add(Facture.fromFileFormat(line, clients, produits));
            }
            System.out.println("Factures chargées avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des factures : " + e.getMessage());
        }
        return factures;
    }
}
