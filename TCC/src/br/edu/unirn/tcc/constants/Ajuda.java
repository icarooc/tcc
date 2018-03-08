package br.edu.unirn.tcc.constants;

public enum Ajuda {
	
	HOSPITAL("hospital"),
    UNIDADEDESAUDE("unidade de sa�de"),
    BENZEDEIRA("benzedeira"),
    FARMACIA("farm�cia");

    private String ajuda;

    private Ajuda (String s){
        this.ajuda = s;
    }

    public String getAjuda(){
        return ajuda;
    }

    @Override
    public String toString(){
        return ajuda;
    }

}
