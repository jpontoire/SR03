class Adresse {
    private String rue;
    private String ville;

    public Adresse(String rue, String ville) {
        this.rue = rue;
        this.ville = ville;
    }
    public String getRue() {
        return rue;
    }
    public String getVille() {
        return ville;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    @Override
    public String toString() {
        return "Adresse [rue=" + rue + ", ville=";
    }
}

class Propriétaire {
    private String nom;
    private Adresse adresse;
    public Propriétaire(String nom, Adresse adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }
    public String getNom() {
        return nom;
    }
    public Adresse getAdresse() {
        return adresse;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    @Override
    public String toString() {
        return nom + " (" + adresse + ")";
    }
}

class Voiture {
    private static int nbrVoiture;
    private String marque;
    private String immatriculation;
    private String couleur;
    private Propriétaire proprietaire;

    public Voiture(String marque, String immatriculation) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        nbrVoiture++;
    }
    public Voiture(String marque, String immatriculation, String couleur) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        nbrVoiture++;
    }
    public Voiture(String marque, String immatriculation, String couleur, Propriétaire proprietaire) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.proprietaire = proprietaire;
        nbrVoiture++;
    }

    public Propriétaire getProprietaire() {
        return this.proprietaire;
    }

    public void afficherInfos() {
        System.out.println("Marque : " + marque);
        System.out.println("Plaque d'immatriculation : " + immatriculation);
        System.out.println("Couleur : " + couleur);
        if (proprietaire != null) {
            System.out.println("Propriétaire : " + proprietaire);
        }
    }

    public static void main(String[] args) {
        Voiture voiture1 = new Voiture("Toyota", "CG-459-YH");
        Voiture voiture2 = new Voiture("Mercedes", "CG-459-YH", "rouge");
        Adresse adresse1 = new Adresse("rue du Bac", "Paris");
        Propriétaire propriétaire1 = new Propriétaire("Patrick", adresse1);
        Voiture voiture3 = new Voiture("Toyota", "CG-125-RD", "verte", propriétaire1);

        System.out.println("Informations de la première voiture :");
        voiture1.afficherInfos();
        System.out.println();

        System.out.println("Informations de la deuxième voiture :");
        voiture2.afficherInfos();
        System.out.println();

        System.out.println("Informations de la troisième voiture :");
        voiture3.afficherInfos();
        System.out.println();

        System.out.println("Nombre de voitures crées : " + Voiture.nbrVoiture);
    }
}
