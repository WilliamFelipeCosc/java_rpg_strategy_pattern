package strategy;

import effects.Queimadura;
import java.util.List;
import personagens.Personagem;

public class CajadoArcano implements Arma {
    private static final int DANO_BASE = 8;
    private static final int CUSTO_MANA = 25;
    private static final int INTELIGENCIA_MINIMA = 12;
    
    @Override
    public int getDanoBase() {
        return DANO_BASE;
    }
    
    @Override
    public String getNome() {
        return "Cajado Arcano";
    }
    
    @Override
    public int getCustoMana() {
        return CUSTO_MANA;
    }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getInteligencia() >= INTELIGENCIA_MINIMA;
    }
    
    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (!podeUsar(atacante)) {
            System.out.println(atacante.getNome() + " não tem inteligência suficiente para usar " + getNome());
            return;
        }
        
        if (atacante.getManaAtual() < CUSTO_MANA) {
            System.out.println(atacante.getNome() + " não tem mana suficiente!");
            return;
        }
        
        atacante.gastarMana(CUSTO_MANA);
        
        int dano = DANO_BASE;
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com " + getNome() + "!");
        System.out.println("🔥 BOLA DE FOGO! " + alvo.getNome() + " está queimando!");
        
        alvo.adicionarEfeito(new Queimadura());
        alvo.receberDano(dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + " de dano!");
    }
    
    @Override
    public void atacar(Personagem atacante, List<Personagem> alvos) {
        if (!alvos.isEmpty()) {
            atacar(atacante, alvos.get(0));
        }
    }
}
