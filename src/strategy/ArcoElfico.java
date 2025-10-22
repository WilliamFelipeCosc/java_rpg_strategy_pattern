package strategy;

import java.util.List;
import personagens.Personagem;

public class ArcoElfico implements Arma {
    private static final int DANO_BASE = 12;
    private static final int CUSTO_MANA = 15;
    private static final int DESTREZA_MINIMA = 8;
    
    @Override
    public int getDanoBase() {
        return DANO_BASE;
    }
    
    @Override
    public String getNome() {
        return "Arco Élfico";
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
        alvo.receberDano(dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + " de dano!");
    }
    
    @Override
    public void atacar(Personagem atacante, List<Personagem> alvos) {
        if (!podeUsar(atacante)) {
            System.out.println(atacante.getNome() + " não tem destreza suficiente para usar " + getNome());
            return;
        }
        
        if (atacante.getManaAtual() < CUSTO_MANA) {
            System.out.println(atacante.getNome() + " não tem mana suficiente!");
            return;
        }
        
        atacante.gastarMana(CUSTO_MANA);
        
        System.out.println(atacante.getNome() + " usa " + getNome() + "!");
        System.out.println("🏹 CHUVA DE FLECHAS! Ataque em área!");
        
        for (Personagem alvo : alvos) {
            if (alvo.estaVivo()) {
                int dano = DANO_BASE;
                alvo.receberDano(dano);
                System.out.println(alvo.getNome() + " recebeu " + dano + " de dano!");
            }
        }
    }
}
