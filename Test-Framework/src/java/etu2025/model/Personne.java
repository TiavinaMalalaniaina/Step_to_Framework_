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
    String prenom;

    public Personne() {}
    
    public Personne(String nom, int age) {
        setNom(nom);
    }
    
    @url("/find-all")
    public ModelView findAll() {
        ModelView mv = new ModelView("index.jsp");
        mv.addItem("nom", "Tiavina");
        mv.addItem("prenom", "Malalaniaina");
        mv.addItem("age", 18);
        return mv;
    }
    
    @url("/input")
    public ModelView InputSave() {
        ModelView mv = new ModelView("input.jsp");
        return mv;
    }
    
     @url("/save")
    public ModelView save() {
        ModelView mv = new ModelView("index.jsp");
        mv.addItem("nom", getNom());
        mv.addItem("prenom", getPrenom());
        mv.addItem("age", 18);
        return mv;
    }

    @url("/nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String str() {
        return "Personne{nom="+getNom()+"prenom"+getPrenom()+"}";
    }
    
    
    
    
}
