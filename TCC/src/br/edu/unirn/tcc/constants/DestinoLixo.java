package br.edu.unirn.tcc.constants;

public enum DestinoLixo {
	
	COLETADO("coletado"),
    QUEIMADOENTERRADO("queimado/enterrado"),
    CEUABERTO("c�u aberto");

    private String destinoLixo;

    private DestinoLixo (String s){
        this.destinoLixo = s;
    }

    public String getDestinoLixo(){
        return destinoLixo;
    }

    @Override
    public String toString(){
        return destinoLixo;
    }

}
