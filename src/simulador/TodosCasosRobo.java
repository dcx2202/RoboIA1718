package simulador;
public class TodosCasosRobo
{
	
	//Criacao/Iniciacao de variaveis/objetos
	private static final int VIDAMAX = 750;
	private static final int ENERGMAX = 500;
	private static final int CURA1 = 100;
	private static final int CURA2 = 200;
	private static final int CURA3 = 400;
	private static final int ENERGCURA1 = 100;
	private static final int ENERGCURA2 = 200;
	private static final int ENERGCURA3 = 400;
	private static final int ENERGGRUA = 300;
	private static final int ENERGSOM = 50;
	private static final int ENERGTOQUE = 150;
	private static final int DANOGRUA = 200;
	private static final int DANOSOM = 50;
	private static final int DANOTOQUE = 100;
	private int energAtual;
	private int vidaAtual;
	static int posicaoAtual;
	
	
	//Construtor
	public TodosCasosRobo()
	{
		vidaAtual = VIDAMAX;
		energAtual = ENERGMAX;
		posicaoAtual = 1;
	}
	
	//Getters
	public int getEnergDisponivel()
	{
		return energAtual;
	}

	public int getEnergia() {
		return energAtual;
	}

	public int getVida() {
		return vidaAtual;
	}

	public int getPosicaoAtual()
	{
		return posicaoAtual;
	}
	
	
	//Setters
	public void setPosicaoAtual(int valor)
	{
		posicaoAtual = valor;
	}
	
	
	//Ataque
	public void escolheAtaque(TodosCasosInimigo inimigo)
	{
		int energ_disp = getEnergDisponivel();

        if (vidaAtual > 0) {
            if (inimigo.getVida() > Simulador.VIDA_INI_GRUA && energ_disp > ENERGGRUA + Simulador.ENERG_ATAQUE_RES) {
                ataqueGrua(inimigo);
            } else if (inimigo.getVida() > Simulador.VIDA_INI_TOQUE && energ_disp > ENERGTOQUE + Simulador.ENERG_ATAQUE_RES) {
                ataqueToque(inimigo);
            } else if (inimigo.getVida() > Simulador.VIDA_INI_SOM && energ_disp > ENERGSOM + Simulador.ENERG_ATAQUE_RES) {
                ataqueSom(inimigo);
            }
        }
	}
	
	public void ataqueSom(TodosCasosInimigo inimigo)
	{
		if(energAtual - ENERGSOM >= 0)
		{
			energAtual -= ENERGSOM;
			inimigo.recebeDano(DANOSOM);
		}
	}
	
	public void ataqueToque(TodosCasosInimigo inimigo)
	{
		if(energAtual - ENERGTOQUE >= 0)
		{
			energAtual -= ENERGTOQUE;
			inimigo.recebeDano(DANOTOQUE);
		}
	}
	
	public void ataqueGrua(TodosCasosInimigo inimigo)
	{
		if(energAtual - ENERGGRUA >= 0)
		{
			energAtual -= ENERGGRUA;
			inimigo.recebeDano(DANOGRUA);
		}
	}
	
	
	//Defesa
	public void recebeDano(int valor)
	{
		if(vidaAtual-valor < 0)
		{
			vidaAtual = 0;
		}
		else
			vidaAtual -=valor;
	}
	
	
	//Cura
	public void curar()
	{
		if(vidaAtual > 0)
		{
			int energ_disp = getEnergDisponivel();
			
			if(energ_disp >= ENERGCURA3 + Simulador.ENERG_CURA_RES && getVida() <= Simulador.VIDA_CURA3)
			{
				vidaAtual += CURA3;
				energAtual -= ENERGCURA3;
			}
			else if(energ_disp >= ENERGCURA2 + Simulador.ENERG_CURA_RES && getVida() <= Simulador.VIDA_CURA2)
			{
				vidaAtual += CURA2;
				energAtual -= ENERGCURA2;
			}
			else if(energ_disp >= ENERGCURA1 + Simulador.ENERG_CURA_RES && getVida() <= Simulador.VIDA_CURA1)
			{
				vidaAtual += CURA1;
				energAtual -= ENERGCURA1;
			}
		}
	}
	
	//Outros
	public void recuperaEnergia()
	{
		if(vidaAtual > 0)
		{
			if((energAtual * 1.5) <= ENERGMAX)
				energAtual = (int) Math.round(energAtual * 1.5);
			else
				energAtual = ENERGMAX;
		}
	}
}