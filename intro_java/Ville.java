class NombreHabitantException extends Exception {
    public NombreHabitantException(String msg) {
        super(msg);
    }
}

class Ville {
    private String nomVille;
    private int nmbreHabitants;

    public Ville(String nomVille, int nmbreHabitants) throws NombreHabitantException {
        if (nmbreHabitants < 0) {
            throw new NombreHabitantException("Le nombre d'habitants ne peut pas être négatif");
        }
        this.nomVille = nomVille;
        this.nmbreHabitants = nmbreHabitants;
    }

    public String getNomVille() {
        return nomVille;
    }

    public int getNmbreHabitants() {
        return nmbreHabitants;
    }

    @Override
    public String toString() {
        return "Ville: " + nomVille + ", Nombre d'habitants: " + nmbreHabitants;
    }
}

class TestVille {
    public static void main(String[] args) {
        try {
            Ville ville1 = new Ville("Paris", 2200000);
            System.out.println(ville1);

            Ville ville2 = new Ville("Ville Fantôme", -500); // Provoque une exception
            System.out.println(ville2);
        } catch (NombreHabitantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}