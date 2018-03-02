package br.edu.unirn.constants.tcc;

public enum MeiosTransporte {
	
	ONIBUS("�nibus"),
    CAMINHAO("caminh�o"),
    CARRO("carro"),
    CARROCA("carro�a");

    private String meiosTransporte;

    private MeiosTransporte (String s){
        this.meiosTransporte = s;
    }

    public String getMeiosTransporte(){
        return meiosTransporte;
    }

    @Override
    public String toString(){
        return meiosTransporte;
    }

}
