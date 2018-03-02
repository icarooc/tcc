package br.edu.unirn.constants.tcc;

public enum MeiosComunicacao {
	
	RADIO("rádio"),
    TELEVISAO("televisão"),
    TELEFONE("telefone");

    private String meiosComunicacao;

    private MeiosComunicacao (String s){
        this.meiosComunicacao = s;
    }

    public String getMeiosComunicacao(){
        return meiosComunicacao;
    }

    @Override
    public String toString(){
        return meiosComunicacao;
    }

}
