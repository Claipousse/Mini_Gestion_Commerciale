import java.io.*; //in out pour ecriture lecture
import java.util.ArrayList;

public class GestionFichier {

    public static void sauvegarderClients(ArrayList<Client> clients, String filename) { //On prend en param l'arraylist qui va stocker client, et nom fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { //On fait un buffer pour écrire dans le fichier
            for (Client client : clients) { //Pour chaque clients
                writer.write(client.toFileFormat()); //On écrit dans le fichier avec la fonction décrite dans Client.java
                writer.newLine(); //On saute à la ligne
            }
            System.out.println("Clients sauvegardés :)"); //Message de validation
        } catch (IOException e) { //Si ya une erreur on attrape l'erreur et on 'laffiche
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Client> chargerClients(String filename) { //On prend en param nom du fichier, on cherche à charger dans une arraylist
        ArrayList<Client> clients = new ArrayList<>(); //On crée arraylist vierge
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { //Buffer pour lire les entrées
            String line; //On initialise une ligne
            while ((line = reader.readLine()) != null) { //Tant que il n'y a pas de blanc, c'est qu'il y a quelque chose à lire (des que ya un blanc, il faut passer à la ligne suivante)
                clients.add(Client.fromFileFormat(line)); //On ajoute la ligne à l'arraylist clients avec la méthode fromFileFormat définie dans la classe Client
            }
            System.out.println("Clients chargés :)"); //Message validation
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return clients; //On retourne l'arraylist
    }

    //Meme fonctionnement que sauvegarderClients, les explications sont les memes mais la c'est avec des produits et non clients
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

    //Meme fonctionnement que sauvegarderClients, mais avec produits
    public static ArrayList<Produit> chargerProduits(String filename) {
        ArrayList<Produit> produits = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                produits.add(Produit.fromFileFormat(line));
            }
            System.out.println("Produits chargés :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return produits;
    }

    //Meme fonctionnement que les deux autres fonctions sauvegarder
    public static void sauvegarderFactures(ArrayList<Facture> factures, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Facture facture : factures) {
                writer.write(facture.toFileFormat());
                writer.newLine();
            }
            System.out.println("Factures sauvegardées :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //Meme fonctionnement que blablabla vous avez compris le pattern
    public static ArrayList<Facture> chargerFactures(String filename, ArrayList<Client> clients, ArrayList<Produit> produits) {
        ArrayList<Facture> factures = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                factures.add(Facture.fromFileFormat(line, clients, produits));
            }
            System.out.println("Factures chargées :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return factures;
    }
}