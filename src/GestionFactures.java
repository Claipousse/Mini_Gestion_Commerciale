//Bon pour ce fichier ya pas mal de code que Claude m'a donné, parce que trouver ça seul c'est quand même chaud
//En contrepartie comme les autres fichiers je commenterai le code au fur et à mesure
//Pour utiliser la bibliothèque itextpdf-5.5.12.jar sur IntelliJ on peut faire :
// Clic droit sur le projet -> Open Modules Settings -> Librairies -> + -> Ajouter la bibliothèque


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream; //Pour écrire fichier PDF

public class GestionFactures {
    public static void genererPDF(Facture facture, String nomFichier) { //Prend en parametre la facture qu'on veut transformer en PDF, et le nom du fichier qui sera donné
        Document document = new Document(); //Nouveau document vierge
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomFichier)); //getInstance fait le lien entre document et fichier de sortie
            document.open(); //On ouvre pour écrire dessus

            //3 styles d'écriture
            Font titreFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font sousTitreFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            //Titre
            Paragraph titre = new Paragraph("FACTURE N°" + facture.getId(), titreFont); //Crée paragraphe avec titre
            titre.setAlignment(Element.ALIGN_CENTER); //Centre
            titre.setSpacingAfter(20); //Ajoute espace après paragraphe
            document.add(titre); //Ajoute ce paragraphe au PDF

            //Date
            Paragraph date = new Paragraph("Date : " + facture.getDate(), normalFont); //Date DD/MM/AAAA
            date.setSpacingAfter(10); //Espace après
            document.add(date); //On ajoute au PDF

            //Infos client (titre)
            Paragraph clientTitre = new Paragraph("Informations Client", sousTitreFont);
            clientTitre.setSpacingAfter(5);
            document.add(clientTitre);

            //Détails du client
            Client client = facture.getClient(); //On récupère le client de la facture
            Paragraph clientInfo = new Paragraph( //On prends toutes les infos du client qu'on met dans un paragraphe
                    client.getPrenom() + " " + client.getNom() + "\n" + client.getEmail() + "\n" + client.getAdresse() + "\n" + client.getCodepostal() + " " + client.getVille(), normalFont
            );
            clientInfo.setSpacingAfter(20);
            document.add(clientInfo);

            //Infos produits (titre)
            Paragraph produitsTitre = new Paragraph("Détail des produits", sousTitreFont);
            produitsTitre.setSpacingAfter(10);
            document.add(produitsTitre);

            //Crée un tableau de 4 colonnes (Produit, Prix unitaire, Quantité, Total), prends 100% largeur de page
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(20);

            //Cellule Produits
            PdfPCell cellProduit = new PdfPCell(new Phrase("Produit", sousTitreFont));
            cellProduit.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProduit.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cellProduit);

            //Cellule Prix à l'unité
            PdfPCell cellPrix = new PdfPCell(new Phrase("Prix unitaire", sousTitreFont));
            cellPrix.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPrix.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cellPrix);

            //Cellule quantité
            PdfPCell cellQuantite = new PdfPCell(new Phrase("Quantité", sousTitreFont));
            cellQuantite.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellQuantite.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cellQuantite);

            //Cellule total
            PdfPCell cellTotal = new PdfPCell(new Phrase("Total", sousTitreFont));
            cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cellTotal);

            for (int i = 0; i < facture.getProduits().size(); i++) { //Pour chaques produits de la facture
                Produit p = facture.getProduits().get(i); //On récupère le produit en question
                int qte = facture.getQuantites().get(i); //Puis sa quantité
                float totalLigne = p.getPrix() * qte; //On fait prix x quantite

                //On ajoute toutes les infos du produit au tableau cellules par cellules
                table.addCell(new Phrase(p.getNom(), normalFont));
                table.addCell(new Phrase(String.format("%.2f €", p.getPrix()), normalFont));
                table.addCell(new Phrase(String.valueOf(qte), normalFont));
                table.addCell(new Phrase(String.format("%.2f €", totalLigne), normalFont));
            }

            document.add(table); //Quand toutes les lignes sont remplies on peut ajouter tout ça au PDF

            //Total général de la facture
            Paragraph totalGeneral = new Paragraph("TOTAL : " + String.format("%.2f €", facture.calculerTotal()), titreFont);
            totalGeneral.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalGeneral);

            document.close(); //Quand on a finit on ferme le document
            System.out.println("PDF genere avec succes : " + nomFichier);

        } catch (Exception e) {
            System.err.println(e.getMessage()); //Si ya une erreur, on le fait savoir
        }
    }
}
