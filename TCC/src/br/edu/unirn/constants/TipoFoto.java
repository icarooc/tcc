package br.edu.unirn.constants;

public enum TipoFoto {
	
	FRONTALOCLUSAO("frontal oclusao"),
    FRONTALDESOCLUSAO("frontal desoclusao"),
    LATERALDIREITA("lateral direita"),
    LATERALESQUERDA("lateral esquerda"),
    OCLUSALSUPERIOR("oclusal superior"),
    OCLUSALINFERIOR("oclusal inferior"),
    PROCESSADA("processada");

    private String tipoFoto;

    private TipoFoto (String s){
        this.tipoFoto = s;
    }

    public String getTipoFoto(){
        return tipoFoto;
    }

    @Override
    public String toString(){
        return tipoFoto;
    }

}
