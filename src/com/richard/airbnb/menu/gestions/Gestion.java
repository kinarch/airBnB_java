package com.richard.airbnb.menu.gestions;

import com.richard.airbnb.menu.Menu;

import java.util.ArrayList;

abstract class Gestion
//        implements GestionI
{
    static final int N_OPTIONS = 4;
    static final int ADD = 1;
    static final int DELETE = 2;
    static final int DISPLAY = 3;
    static final int BACK = 4;

//    @Override
//    public void add(ArrayList<Object> list) throws Exception {
//
//    }
//
//    @Override
//    public void delete(ArrayList<Object> list) {
//        if (!list.isEmpty()) {
//            int index = 0;
//            if (list.size() > 1) {
//                System.out.println("Numéro ? (entre 0 et " + (list.size() - 1) + ") : ");
//                index = Menu.scanner.nextInt();
//            } else {
//                System.out.println("Une seul donnée enregistré.");
//            }
//            System.out.println("Supprimer le numéro " + index + " (0 : non | plus : oui) : ");
//            if (Menu.scanner.nextInt() > 1) {
//                list.remove(index);
//            }
//        } else {
//            System.out.println("Rien à supprimer.");
//        }
//    }
//
//    @Override
//    public void display(ArrayList<Object> list) {
//        if (list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                Object o = list.get(i);
//                System.out.print("* n°" + i + " : ");
//                o.toString();
//                System.out.println();
//            }
//        } else {
//            System.out.println("Rien n'a été enregistré.");
//        }
//    }
}
//
//interface GestionI {
//
//    int N_OPTIONS = 4;
//    int AJOUTER = 1;
//    int SUPPRIMER = 2;
//    int AFFICHER = 3;
//    int RETOUR = 4;
//
//    void add(ArrayList<Object> list) throws Exception;
//
//    void delete(ArrayList<Object> list) throws Exception;
//
//    void display(ArrayList<Object> list);
//
//    void back();
//}
