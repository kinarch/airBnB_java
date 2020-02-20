package com.richard.airbnb.tools;

import com.richard.airbnb.models.logements.Appartement;
import com.richard.airbnb.models.logements.Logement;
import com.richard.airbnb.models.logements.Maison;
import com.richard.airbnb.models.utilisateurs.Hote;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public final class AirBnBXMLParser {

    private AirBnBXMLParser() {
    }

    /**
     * Parse le fichier xml et rempli la liste d'hotes et de logements.
     *
     * @param filePath     le chemin du fichier
     * @param hoteList     la liste des hotes
     * @param logementList la liste des logements
     * @throws Exception
     */
    public static void parseListDOM(String filePath, ArrayList<Hote> hoteList, ArrayList<Logement> logementList) throws Exception {

        //  Preparation du document
        final File file = new File(filePath);
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(file);

        //  Vérification du fichier
        factory.setValidating(true);

        //  création de notre objet d'erreurs
        final ErrorHandler errHandler = new AirBnBErrorHandler();
        //  Affectation de notre objet au document pour interception des erreurs éventuelles
        builder.setErrorHandler(errHandler);

        //  Affichage du prologue
//        System.out.println("********** Document XML **********");
//        System.out.println("version : " + document.getXmlVersion());
//        System.out.println("encodage : " + document.getXmlEncoding());
//        System.out.println("standalone : " + document.getXmlStandalone());

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
                //  Ajout de l'hote
                final Hote hote = new Hote(hoteNom, hotePrenom, hoteAge, hoteDelaiReponse);
                if (!hoteList.contains(hote)) {
                    hoteList.add(hote);
                }

                //  --  LOGEMENT

                //  Récupération des elements du noeud logement
                final Element eltAdresse = (Element) eltLogement.getElementsByTagName("adresse").item(0);
                final Element eltTarifParNuit = (Element) eltLogement.getElementsByTagName("tarifParNuit").item(0);
                final Element eltSuperficie = (Element) eltLogement.getElementsByTagName("superficie").item(0);
                final Element eltNbVoyageursMax = (Element) eltLogement.getElementsByTagName("nbVoyageursMax").item(0);
                //  Récupération des valeurs du logement
                //  Préparation du nom du logement
                String nom = "";
                if (eltLogement.hasAttribute("name")) {
                    nom = eltLogement.getAttribute("name");
                }
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
                    logement = new Appartement(nom, hote, adresse, tarifParNuit, superficie, nbVoyageursMax, numeroEtage, superficieBalcon);
                }
                //  Maison ?
                else if (eltLogement.getNodeName().equals("Maison")) {
                    //  récupération des noeuds d'une maison
                    final Element eltSuperficieJardin = (Element) eltLogement.getElementsByTagName("superficieJardin").item(0);
                    final Element eltPossedePiscine = (Element) eltLogement.getElementsByTagName("possedePiscine").item(0);
                    //  récupération des valeurs d'une maison
                    final int superficieJardin = Integer.parseInt(eltSuperficieJardin.getTextContent());
                    final boolean possedePiscine = eltPossedePiscine.getTextContent().equals("1");
                    //  instanciation
                    logement = new Maison(nom, hote, adresse, tarifParNuit, superficie, nbVoyageursMax, superficieJardin, possedePiscine);
                }

                //  ajout du logement
                logementList.add(logement);
                if (!logementList.contains(logement)) {
                    logementList.add(logement);
                }
            }
        }
        //  end...
    }

    //  TODO... Essayer de finir cette methode avec XPath
    public static void parseListXPath(String filePath, ArrayList<Hote> hoteList, ArrayList<Logement> logementList) throws Exception {

        //  Preparation du document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


        final DocumentBuilder builder = factory.newDocumentBuilder();
        final File fileXML = new File(filePath);
        final Document xml = builder.parse(fileXML);
        final Element root = xml.getDocumentElement();
        final XPathFactory xpf = XPathFactory.newInstance();
        final XPath path = xpf.newXPath();

        //  Préparation de l'arborescende des noeuds sous forme de liste
        final NodeList nodeList = root.getChildNodes();
        final int nbNodes = nodeList.getLength();

        for (int i = 0; i < nbNodes; i++) {

            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                String typeLogement = nodeList.item(i).getNodeName() + "[" + i + "]";

                System.out.println(typeLogement);
                //  HOTE
                //  Récupération des informations de l'hote
                String hoteNom = (String) path.evaluate("//" + typeLogement + "/hote/nom[1]", root, XPathConstants.STRING);
                String hotePrenom = (String) path.evaluate("//" + typeLogement + "/hote/prenom[1]", root, XPathConstants.STRING);
                int hoteAge = ((Double) path.evaluate("//" + typeLogement + "/hote/age[1]", root, XPathConstants.NUMBER)).intValue();
                int hoteDelaiReponse = ((Double) path.evaluate("//" + typeLogement + "/hote/delaiReponse[1]", root, XPathConstants.NUMBER)).intValue();

                //  Ajout de l'hote
                final Hote hote = new Hote(hoteNom, hotePrenom, hoteAge, hoteDelaiReponse);
                if (!hoteList.contains(hote)) {
                    hoteList.add(hote);
                }
            }
        }

        /*
            <Appartement name="Appartement 1">
                <hote>
                    <nom>Bardu</nom>
                    <prenom>Peter</prenom>
                    <age>31</age>
                    <delaiReponse>12</delaiReponse>
                </hote>
                <tarifParNuit>50</tarifParNuit>
                <adresse>81 rue Colbert, Tours</adresse>
                <superficie>58</superficie>
                <nbVoyageursMax>4</nbVoyageursMax>
                <numeroEtage>2</numeroEtage>
                <superficieBalcon>10</superficieBalcon>
            </Appartement>
         */

        //  end...
        System.out.println("Parse completed.");
    }
}
