package com.example.switcher;


import java.io.Serializable;

public class Pharmacie  {
        private String id;
        private String quartier;
        private String nom;
        private float prix;
        private String adress;
        private String tel;
        private boolean para;

    public Pharmacie(String id, String quartier, String nom, float prix, String adress, String tel, boolean para) {
        this.id = id;
        this.quartier = quartier;
        this.nom = nom;
        this.prix = prix;
        this.adress = adress;
        this.tel = tel;
        this.para = para;
    }
// Getter Methods

            public String getId() {
                    return id;
                    }
            public String getQuartier() {
                    return quartier;
                    }

            public String getNom() {
                    return nom;
                    }

            public float getPrix() {
                    return prix;
                    }

            public String getAdress() {
                    return adress;
                    }

            public String getTel() {
                    return tel;
                    }

            public boolean getPara() {
                    return para;
                    }

// Setter Methods

            public void setId(String id) {
                    this.id = id;
                    }

            public void setQuartier(String quartier) {
                    this.quartier = quartier;
                    }

            public void setNom(String nom) {
                    this.nom = nom;
                    }

            public void setPrix(float prix) {
                    this.prix = prix;
                    }

            public void setAdress(String adress) {
                    this.adress = adress;
                    }

            public void setTel(String tel) {
                    this.tel = tel;
                    }



    public void setPara(boolean para) {
                    this.para = para;
            }
}