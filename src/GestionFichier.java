import java.io.*;
import java.util.ArrayList;

public class GestionFichier {

    public static void sauvegarderClients(ArrayList<Client> clients, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Client client : clients) {
                writer.write(client.toFileFormat());
                writer.newLine();
            }
            System.out.println("Clients sauvegardés :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Client> chargerClients(String filename) {
        ArrayList<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clients.add(Client.fromFileFormat(line));
            }
            System.out.println("Clients chargés :)");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return clients;
    }

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