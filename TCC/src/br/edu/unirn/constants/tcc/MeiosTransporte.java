package br.edu.unirn.constants.tcc;

public enum MeiosTransporte {
	
	ONIBUS("ônibus"),
    CAMINHAO("caminhão"),
    CARRO("carro"),
    CARROCA("carroça");

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
