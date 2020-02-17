package com.richard.airbnb.tools;

import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Maison;
import com.richard.airbnb.models.utilisateurs.Hote;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public final class AirBnBXMLParser {

    private AirBnBXMLParser() {
    }

    public static void parseList(String path, ArrayList<Hote> hoteList, ArrayList<Logement> logementList) throws Exception {

        //  Preparation du document
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(new File(path));

        //  Affichage du prologue
        System.out.println("********** Document **********");
        System.out.println("version : " + document.getXmlVersion());
        System.out.println("encodage : " + document.getXmlEncoding());
        System.out.println("standalone : " + document.getXmlStandalone());

        //  Préparation de l'arborescende des noeuds sous forme de liste
        final Element root = document.getDocumentElement();
        final NodeList nodeList = root.getChildNodes();
        final int nbNodes = nodeList.getLength();

        //  Itération des noeuds
        for (int i = 0; i < nbNodes; i++) {

            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                final Element eltLogement = (Element) nodeList.item(i);

                //  --  HOTE

                //  Récupération des noeuds de l'hote
                final Element eltHote = (Element) eltLogement.getElementsByTagName("hote").item(0);
                final Element eltHoteNom = (Element) eltHote.getElementsByTagName("nom").item(0);
                final Element eltHotePrenom = (Element) eltHote.getElementsByTagName("prenom").item(0);
                final Element eltHoteAge = (Element) eltHote.getElementsByTagName("age").item(0);
                final Element eltHoteDelaiReponse = (Element) eltHote.getElementsByTagName("delaiReponse").item(0);
                //  Récupération des valeurs
                final String hoteNom = eltHoteNom.getTextContent();
                final String hotePrenom = eltHotePrenom.getTextContent();
                final int hoteAge = Integer.parseInt(eltHoteAge.getTextContent());
                final int hoteDelaiReponse = Integer.parseInt(eltHoteDelaiReponse.getTextContent());
                //  Instanciation de l'hote
                final Hote hote = new Hote(hoteNom, hotePrenom, hoteAge, hoteDelaiReponse);
                //  Ajout de l'hote
                boolean addHote = true;
                for (Hote h : hoteList) {
                    if (h.equals(hote)) {
                        addHote = false;
                        break;
                    }
                }
                if (addHote) {
                    hoteList.add(hote);
                }

                //  --  LOGEMENT

                //  Récupération des elements du noeud logement
                final Element eltAdresse = (Element) eltLogement.getElementsByTagName("adresse").item(0);
                final Element eltTarifParNuit = (Element) eltLogement.getElementsByTagName("tarifParNuit").item(0);
                final Element eltSuperficie = (Element) eltLogement.getElementsByTagName("superficie").item(0);
                final Element eltNbVoyageursMax = (Element) eltLogement.getElementsByTagName("nbVoyageursMax").item(0);
                //  Récupération des valeurs du logement
                final String adresse = eltAdresse.getTextContent();
                final int tarifParNuit = Integer.parseInt(eltTarifParNuit.getTextContent());
                final int superficie = Integer.parseInt(eltSuperficie.getTextContent());
                final int nbVoyageursMax = Integer.parseInt(eltNbVoyageursMax.getTextContent());

                //  Création du logement
                Logement logement = null;

                //  Appartement ?
                if (eltLogement.getNodeName().equals("Appartement")) {
                    //  récupération des noeuds d'un appartement
                    final Element eltNumeroEtage = (Element) eltLogement.getElementsByTagName("numeroEtage").item(0);
                    final Element eltSuperficieBalcon = (Element) eltLogement.getElementsByTagName("superficieBalcon").item(0);
                    //  récupération des valeurs d'un appartement
                    final int numeroEtage = Integer.parseInt(eltNumeroEtage.getTextContent());
                    final int superficieBalcon = Integer.parseInt(eltSuperficieBalcon.getTextContent());
                    //  instanciation
                    logement = new Appartement(hote, adresse, tarifParNuit, superficie, nbVoyageursMax, numeroEtage, superficieBalcon);
                }
                //  Maison ?
                else if (eltLogement.getNodeName().equals("Maison")) {
                    //  récupération des noeuds d'une maison
                    final Element eltSuperficieJardin = (Element) eltLogement.getElementsByTagName("superficieJardin").item(0);
                    final Element eltPossedePiscine = (Element) eltLogement.getElementsByTagName("possedePiscine").item(0);
                    //  récupération des valeurs d'une maison
                    final int superficieJardin = Integer.parseInt(eltSuperficieJardin.getTextContent());
                    final boolean possedePiscine = Integer.parseInt(eltPossedePiscine.getTextContent()) == 1;
                    //  instanciation
                    logement = new Maison(hote, adresse, tarifParNuit, superficie, nbVoyageursMax, superficieJardin, possedePiscine);
                }

                //  ajout du logement
                logementList.add(logement);
            }
        }
        //  end...
        System.out.println("Parse completed.");
    }
}
