package personagens;

public class Arqueiro extends Personagem {
    private static final double CHANCE_ESQUIVA = 0.25; // 25%
    
    public Arqueiro(String nome) {
        super(nome, 8, 15, 7, 90, 80);
    }
    
    @Override
    public String getClasse() {
        return "Arqueiro";
    }
    
    @Override
    public double getReducaoDano() {
        return 0.0;
    }
    
    @Override
    public double getChanceEsquiva() {
        return CHANCE_ESQUIVA;
    }
    
    @Override
    public void habilidadePassiva() {
        // Esquiva - já aplicado automaticamente em getChanceEsquiva()
    }
}
