package effects;

import personagens.Personagem;

public interface StatusEffect {
    String getNome();
    int getDuracaoRestante();
    void aplicarEfeito(Personagem alvo);
    void decrementarDuracao();
    boolean estaAtivo();
}
