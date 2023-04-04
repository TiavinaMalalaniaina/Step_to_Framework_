/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2025.model;

import etu2025.framework.ModelView;
import etu2025.framework.annotation.url;

/**
 *
 * @author tiavi
 */
public class Personne {
    String nom;
    int age;

    public Personne() {}
    
    public Personne(String nom, int age) {
        setNom(nom);
        setAge(age);
    }
    
    @url("/prenom")
    public ModelView getPrenom() {
        ModelView mv = new ModelView("index.jsp");
        mv.addItem("nom", "Tiavina");
        mv.addItem("prenom", "Malalaniaina");
        mv.addItem("age", 18);
        return mv;
    }

    @url("/nom")
    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    
}
