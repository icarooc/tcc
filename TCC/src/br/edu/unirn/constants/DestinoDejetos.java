package br.edu.unirn.constants;

public enum DestinoDejetos {
	
	SISTEMAESGOTO("sistema de esgoto"),
    FOSSA("fossa"),
    CEUABERTO("céu aberto");

    private String destinoDejetos;

    private DestinoDejetos (String s){
        this.destinoDejetos = s;
    }

    public String getDestinoDejetos(){
        return destinoDejetos;
    }

    @Override
    public String toString(){
        return destinoDejetos;
    }

}
