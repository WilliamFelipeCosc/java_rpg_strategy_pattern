package strategy;

import java.util.List;
import personagens.Personagem;

public class AdagaSombria implements Arma {
    private static final int DANO_BASE = 10;
    private static final int CUSTO_MANA = 10;
    private static final int DESTREZA_MINIMA = 12;
    private static final int MULTIPLICADOR_FURTIVO = 3;
    
    @Override
    public int getDanoBase() {
        return DANO_BASE;
    }
    
    @Override
    public String getNome() {
        return "Adaga Sombria";
    }
    
    @Override
    public int getCustoMana() {
        return CUSTO_MANA;
    }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getDestreza() >= DESTREZA_MINIMA;
    }
    
    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (!podeUsar(atacante)) {
            System.out.println(atacante.getNome() + " não tem destreza suficiente para usar " + getNome());
            return;
        }
        
        if (atacante.getManaAtual() < CUSTO_MANA) {
            System.out.println(atacante.getNome() + " não tem mana suficiente!");
            return;
        }
        
        atacante.gastarMana(CUSTO_MANA);
        
        int dano = DANO_BASE;
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com " + getNome() + "!");
        
        // Ataque furtivo se o inimigo estiver desprevenido (vida cheia ou primeira rodada)
        if (alvo.getVidaAtual() == alvo.getVidaMaxima()) {
            dano *= MULTIPLICADOR_FURTIVO;
            System.out.println("🗡️ ATAQUE FURTIVO! Dano triplo!");
        }
        
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
