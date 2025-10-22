package personagens;

public class Guerreiro extends Personagem {
    private static final double REDUCAO_DANO = 0.20; // Pele Dura - 20%
    
    public Guerreiro(String nome) {
        super(nome, 15, 8, 5, 120, 50);
    }
    
    @Override
    public String getClasse() {
        return "Guerreiro";
    }
    
    @Override
    public double getReducaoDano() {
        return REDUCAO_DANO;
    }
    
    @Override
    public double getChanceEsquiva() {
        return 0.0;
    }
    
    @Override
    public void habilidadePassiva() {
        // Pele Dura - já aplicado automaticamente em getReducaoDano()
    }
}
