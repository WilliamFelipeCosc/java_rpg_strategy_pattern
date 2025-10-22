package effects;

import personagens.Personagem;

public class Queimadura implements StatusEffect {
    private static final int DANO_POR_TURNO = 10;
    private static final int DURACAO_INICIAL = 2;
    
    private int duracaoRestante;
    
    public Queimadura() {
        this.duracaoRestante = DURACAO_INICIAL;
    }
    
    @Override
    public String getNome() {
        return "Queimadura";
    }
    
    @Override
    public int getDuracaoRestante() {
        return duracaoRestante;
    }
    
    @Override
    public void aplicarEfeito(Personagem alvo) {
        if (estaAtivo()) {
            System.out.println("🔥 " + alvo.getNome() + " sofre " + DANO_POR_TURNO + " de dano por queimadura!");
            alvo.receberDanoDireto(DANO_POR_TURNO);
        }
    }
    
    @Override
    public void decrementarDuracao() {
        if (duracaoRestante > 0) {
            duracaoRestante--;
        }
    }
    
    @Override
    public boolean estaAtivo() {
        return duracaoRestante > 0;
    }
}
