/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2025.model;

import etu2025.framework.ModelView;
import etu2025.framework.annotation.url;
import etu2025.framework.util.Utils;
import java.util.Date;
import java.text.ParseException;

/**
 *
 * @author tiavi
 */
public class Personne {
    String nom;
    String prenom;
    Integer age;
    String[] loisir;
    Date dtn;

    public Personne() {}
    
    public Personne(String nom, int age) {
        setNom(nom);
    }
    
    @url("/find-all.action")
    public ModelView findAll() throws ParseException {
        ModelView mv = new ModelView("index.jsp");
        mv.addItem("nom", "Tiavina");
        mv.addItem("prenom", "Malalaniaina");
        mv.addItem("age", 18);
        return mv;
    }
    
    @url("/input.action")
    public ModelView InputSave() {
        ModelView mv = new ModelView("input.jsp");
        return mv;
    }
    
     @url("/save.action")
    public ModelView save() {
        ModelView mv = new ModelView("index.jsp");
        mv.addItem("nom", getNom());
        mv.addItem("prenom", getPrenom());
        mv.addItem("age", getAge());
        mv.addItem("loisir", getLoisir());
        mv.addItem("dtn", getDtn());
        return mv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String[] getLoisir() {
        return loisir;
    }

    public void setLoisir(String[] loisir) {
        this.loisir = loisir;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Date getDtn() {
        return dtn;
    }
    
    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }
    
    public String str() {
        return "Personne{nom="+getNom()+"prenom"+getPrenom()+"}";
    }

    
    
    
}
