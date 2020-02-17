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

public class AirBnBXMLParser {

    public static void parseList(String path, ArrayList<Hote> hoteList, ArrayList<Logement> logementList) throws Exception {

        //  Preparation du document
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(new File(path));

        //  Préparation de l'arborescende des noeuds
        final Element root = document.getDocumentElement();
        final NodeList nodeRoot = root.getChildNodes();
        final int nbNodes = nodeRoot.getLength();

        //  Itération des noeuds
        for (int i = 0; i < nbNodes; i++) {

            if (nodeRoot.item(i).getNodeType() == Node.ELEMENT_NODE) {

                //  --  NODE ELEMENT

                final Element nodeLogement = (Element) nodeRoot.item(i);

                //  --  HOTE

                //  Récupération des noeuds de l'hote
                final Element nodeHote = (Element) nodeLogement.getElementsByTagName("hote").item(0);
                final Element nodeHoteNom = (Element) nodeHote.getElementsByTagName("nom").item(0);
                final Element nodeHotePrenom = (Element) nodeHote.getElementsByTagName("prenom").item(0);
                final Element nodeHoteAge = (Element) nodeHote.getElementsByTagName("age").item(0);
                final Element nodeHoteDelaiReponse = (Element) nodeHote.getElementsByTagName("delaiReponse").item(0);
                //  Création de l'hote
                final String hoteNom = nodeHoteNom.getTextContent();
                final String hotePrenom = nodeHotePrenom.getTextContent();
                final int hoteAge = Integer.parseInt(nodeHoteAge.getTextContent());
                final int hoteDelaiReponse = Integer.parseInt(nodeHoteDelaiReponse.getTextContent());
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

                //  Récupération des noeuds du logement
                final Element nodeTarifParNuit = (Element) nodeLogement.getElementsByTagName("tarifParNuit").item(0);
                final Element nodeAdresse = (Element) nodeLogement.getElementsByTagName("adresse").item(0);
                final Element nodeSuperficie = (Element) nodeLogement.getElementsByTagName("superficie").item(0);
                final Element nodeNbVoyageursMax = (Element) nodeLogement.getElementsByTagName("nbVoyageursMax").item(0);
                //  Création du logement
                Logement logement = null;
                final String adresse = nodeAdresse.getTextContent();
                final int tarifParNuit = Integer.parseInt(nodeTarifParNuit.getTextContent());
                final int superficie = Integer.parseInt(nodeSuperficie.getTextContent());
                final int nbVoyageursMax = Integer.parseInt(nodeNbVoyageursMax.getTextContent());

                //  Preparation de l'appartement
                if (nodeLogement.getNodeName().equals("Appartement")) {
                    final Element nodeNumeroEtage = (Element) nodeLogement.getElementsByTagName("numeroEtage").item(0);
                    final Element nodeSuperficieBalcon = (Element) nodeLogement.getElementsByTagName("superficieBalcon").item(0);
                    final int numeroEtage = Integer.parseInt(nodeNumeroEtage.getTextContent());
                    final int superficieBalcon = Integer.parseInt(nodeSuperficieBalcon.getTextContent());
                    logement = new Appartement(hote, adresse, tarifParNuit, superficie, nbVoyageursMax, numeroEtage, superficieBalcon);
                }
                //  Preparation de la maison
                else if (nodeLogement.getNodeName().equals("Maison")) {
                    final Element nodeSuperficieJardin = (Element) nodeLogement.getElementsByTagName("superficieJardin").item(0);
                    final Element nodePossedePiscine = (Element) nodeLogement.getElementsByTagName("possedePiscine").item(0);
                    final int superficieJardin = Integer.parseInt(nodeSuperficieJardin.getTextContent());
                    final boolean possedePiscine = Boolean.parseBoolean(nodePossedePiscine.getTextContent());
                    logement = new Maison(hote, adresse, tarifParNuit, superficie, nbVoyageursMax, superficieJardin, possedePiscine);
                }
                //  ajout du logement
                logementList.add(logement);
            }
        }
    }
}
