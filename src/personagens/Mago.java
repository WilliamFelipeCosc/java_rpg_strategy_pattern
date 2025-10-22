package personagens;

public class Mago extends Personagem {
    private static final int REGENERACAO_MANA = 10;
    
    public Mago(String nome) {
        super(nome, 5, 7, 18, 70, 150);
    }
    
    @Override
    public String getClasse() {
        return "Mago";
    }
    
    @Override
    public double getReducaoDano() {
        return 0.0;
    }
    
    @Override
    public double getChanceEsquiva() {
        return 0.0;
    }
    
    @Override
    public void habilidadePassiva() {
        // Regeneração de Mana - +10 mana por turno
        recuperarMana(REGENERACAO_MANA);
        System.out.println("✨ " + getNome() + " regenerou " + REGENERACAO_MANA + " de mana!");
    }
}
