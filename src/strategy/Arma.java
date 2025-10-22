package strategy;

import personagens.Personagem;
import java.util.List;

public interface Arma {
    int getDanoBase();
    String getNome();
    int getCustoMana();
    boolean podeUsar(Personagem personagem);
    void atacar(Personagem atacante, Personagem alvo);
    void atacar(Personagem atacante, List<Personagem> alvos);
}
