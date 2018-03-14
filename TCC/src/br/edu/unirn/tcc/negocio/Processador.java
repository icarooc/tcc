package br.edu.unirn.tcc.negocio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author ÍcaroCosta
 */
public class Processador {
    
    BufferedImage imagem;
    BufferedImage binary;
    int altura;
    int largura;
    double [][] alfa;
    double [][] pesos;
    double [][] alfa2;
    double [][] pesos2;
    double bias;
    double bias2;
    int[][] rotulos;
    LinkedList<Integer> areas;
    int[][] centros;
    int ag=0;
    BufferedImage mascara;
    BufferedImage saidaGeral;

    public BufferedImage getSaidaGeral() {
        return saidaGeral;
    }

    public void setSaidaGeral(BufferedImage saidaGeral) {
        this.saidaGeral = saidaGeral;
    }

    public BufferedImage getBinary() {
        return binary;
    }

    public void setBinary(BufferedImage binary) {
        this.binary = binary;
    }
    

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
        this.largura = imagem.getWidth();
        this.altura = imagem.getHeight();
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    private Processador(){
        this.imagem = null;
        this.altura = 0;
        this.largura = 0;
        bias = 3.682737244457200;
        bias2 = 0.937855685179114;
    }
    
    public Processador(BufferedImage image){
    	new Processador();
    	this.setImagem(image);
    	this.setBinary(image);
    }
            
    public void redimensionar(){
        float escala;
        if (altura>largura){
            escala = (float) (1.0-((altura-600.0)/altura));
        } else {
            escala = (float) (1.0-((largura-600.0)/largura));
        }
        ImageIcon ii = new ImageIcon(imagem);
        ImageIcon ii2 = new ImageIcon(imagem);
        
        BufferedImage img = new BufferedImage(Math.abs(Math.round(largura*escala)), Math.abs(Math.round(altura*escala)), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.drawImage(ii.getImage(), 0, 0, Math.abs(Math.round(largura*escala)), Math.abs(Math.round(altura*escala)), null);
        
        BufferedImage img2 = new BufferedImage(Math.abs(Math.round(largura*escala)), Math.abs(Math.round(altura*escala)), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img2.createGraphics();
        g2.drawImage(ii2.getImage(), 0, 0, Math.abs(Math.round(largura*escala)), Math.abs(Math.round(altura*escala)), null);
        
        ii.setImage(img);
        ii2.setImage(img2);
        imagem = img;
        binary = imagem;
        saidaGeral = img2;
    }
        
    public void segmentar2(){
        BufferedImage bin = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
        double[] scaleShift2 = {-162.635099299997,-111.007981500001,-154.542844300008};
        double[] scaleFactor2 = {0.0254047590962728,0.153385140588106,0.0650487336455759};
        double[][] tmp = new double[imagem.getWidth()*imagem.getHeight()][3];
        for (int i=0;i<imagem.getHeight();i++){
            for (int j=0;j<imagem.getWidth();j++){
                Color c = new Color(imagem.getRGB(j, i));
                tmp[imagem.getHeight()*j + i][0] = (double) c.getRed();
                tmp[imagem.getHeight()*j + i][1] = (double) c.getGreen();
                tmp[imagem.getHeight()*j + i][2] = (double) c.getBlue();
            }
        }
        for (int i=0;i<tmp.length;i++){
            for (int j=0;j<3;j++){
                tmp[i][j] = scaleFactor2[j]*(tmp[i][j]+scaleShift2[j]);
            }
        }
        //SimpleMatrix sTMP = new SimpleMatrix(tmp);
        this.carregarSVMPeso2();
        this.carregarSVMAlfa2();
        SimpleMatrix salfa = new SimpleMatrix(this.alfa2);
        SimpleMatrix spesos = new SimpleMatrix(this.pesos2);
        double[] classificados = new double[tmp.length];
        double[][] aux = new double[3][1];
        SimpleMatrix saux = new SimpleMatrix(aux);
        for (int i=0;i<tmp.length;i++){
            saux.setColumn(0, 0, tmp[i][0],tmp[i][1],tmp[i][2]);
            double[][] a1 = new double[alfa2.length][1];
            SimpleMatrix sa1 = new SimpleMatrix(a1);
            sa1 = spesos.mult(saux);
            for (int j=0;j<sa1.numRows();j++){
                sa1.set(j, 0, 1.0+sa1.get(j, 0));
                //sa1.set(j, 0, Math.pow(sa1.get(j,0),2.0));
            }
            double a2 = (salfa.transpose().mult(sa1)).get(0, 0);
            classificados[i] = a2+bias2;
        }
        for (int i=0;i<bin.getHeight();i++){
            for (int j=0;j<bin.getWidth();j++){
                if ((i==0)||(i==(bin.getHeight()-1))||(j==0)||(j==(bin.getWidth()-1))){
                    binary.setRGB(j, i, Color.BLACK.getRGB());
                } else {
                    if (classificados[bin.getHeight()*j + i] <= 0.0) {
                        bin.setRGB(j, i, Color.WHITE.getRGB());
                    } else {
                        bin.setRGB(j, i, Color.BLACK.getRGB());
                    }
                }
            }
        }
        this.binary = bin;
    }
    
    public void carregarSVMAlfa2 (){
        LinkedList<String> strcol = new LinkedList<String>();
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
        	File file = new File(classLoader.getResource("/alfa_smv2.txt").getFile());
        	Scanner scanner = new Scanner(file);
        	while (scanner.hasNextLine()){
        		String line = scanner.nextLine();
        		strcol.add(line);
        	}
        }   catch (IOException e) {
        }
        alfa2 = new double[strcol.size()][1];
        for (int i=0;i<strcol.size();i++){
            alfa2[i][0] = Double.parseDouble(strcol.get(i));
        }
    }
    
    public void carregarSVMPeso2 (){
        LinkedList<String> strcol = new LinkedList<String>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
        	File file = new File(classLoader.getResource("/pesos_svm2.txt").getFile());
        	Scanner scanner = new Scanner(file);
        	while (scanner.hasNextLine()){
        		String line = scanner.nextLine();
        		strcol.add(line);
        	}
        }   
        catch (IOException e) {
        	e.printStackTrace();
        	System.out.println(e.getMessage());
        }
        pesos2 = new double[39][3];
        for (int i=0; i<strcol.size(); i++){
            if (i<=38){
                pesos2[i][0] = Double.parseDouble(strcol.get(i));
            }
            if ((i>38)&&(i<=77)){
                pesos2[i%39][1] = Double.parseDouble(strcol.get(i));
            }
            if (i>=78){
                pesos2[i%78][2] = Double.parseDouble(strcol.get(i));
            }
        }
    }
    
    public void rgb2ycbcr() {
        int r,g,b,y,cb,cr;
        Color c;
        for (int i=0;i<imagem.getHeight();i++){
            for (int j=0;j<imagem.getWidth();j++){
                c = new Color(imagem.getRGB(j, i));
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                y = (int)(0.299*r + 0.587*g + 0.114*b);
                cb = 128 + (int)(-0.169*r - 0.331*g + 0.500*b);
                cr = 128 + (int)(0.500*r - 0.419*g - 0.081*b);
                imagem.setRGB(j, i, new Color(y, cb, cr).getRGB());
            }
        }
    }
    
    public void ajustar (){
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        int[] vermelho = this.stretchlim(imagem, "r");
        int[] verde = this.stretchlim(imagem, "g");
        int[] azul = this.stretchlim(imagem, "b");
        int minr = vermelho[0];  //stretch min level
        int maxr = vermelho[1]; //stretch max level
        int ming = verde[0];  //stretch min level
        int maxg = verde[1]; //stretch max level
        int minb = azul[0];  //stretch min level
        int maxb = azul[1]; //stretch max level
        try {
            int[] r = new int[width * height];
            int[] g = new int[width * height];
            int[] b = new int[width * height];
            int[] e = new int[width * height];
            int[] data = new int[width * height];
            imagem.getRGB(0, 0, width, height, data, 0, width);
//            int[] old_histogram_r = this.obterHistograma(imagem, "r");
//            int[] old_histogram_g = this.obterHistograma(imagem, "g");
//            int[] old_histogram_b = this.obterHistograma(imagem, "b");
            
            int[] new_histogram_r = new int[256];
            int[] new_histogram_g = new int[256];
            int[] new_histogram_b = new int[256];
            for (int i = 0; i < (height * width); i++) {
                r[i] = (int) ((data[i] >> 16) & 0xff);  //shift 3rd byte to first byte location
                g[i] = (int) ((data[i] >> 8) & 0xff);   //shift 2nd byte to first byte location
                b[i] = (int) (data[i] & 0xff);          //it is already at first byte location
                r[i] = (int) (1.0 * (r[i] - minr) / (maxr - minr) * 255);
                g[i] = (int) (1.0 * (g[i] - ming) / (maxg - ming) * 255);
                b[i] = (int) (1.0 * (b[i] - minb) / (maxb - minb) * 255);
                if (r[i] > 255) {
                    r[i] = 255;
                }
                if (g[i] > 255) {
                    g[i] = 255;
                }
                if (b[i] > 255) {
                    b[i] = 255;
                }
                if (r[i] < 0) {
                    r[i] = 0;
                }
                if (g[i] < 0) {
                    g[i] = 0;
                }
                if (b[i] < 0) {
                    b[i] = 0;
                }
                new_histogram_r[r[i]]++;
                new_histogram_g[g[i]]++;
                new_histogram_b[b[i]]++;
                e[i] = (r[i] << 16) | (g[i] << 8) | b[i];
            }
            imagem.setRGB(0, 0, width, height, e, 0, width);
        } catch (Exception e) {
            System.err.println("Error: " + e);
            Thread.dumpStack();
        }
    }
    
    public int[] obterHistograma(BufferedImage bi,String cor){
        int[] histograma = new int[256];
        int[] rgb = new int[bi.getWidth() * bi.getHeight()];
        int[] data = new int[bi.getWidth() * bi.getHeight()];
        bi.getRGB(0, 0, bi.getWidth(), bi.getHeight(), data, 0, bi.getWidth());
        for (int i = 0; i < (bi.getHeight() * bi.getWidth()); i++) {
            if (cor.equals("r")){
                rgb[i] = (int) ((data[i] >> 16) & 0xff);
                histograma[rgb[i]]++;
            }
            if (cor.equals("g")){
                rgb[i] = (int) ((data[i] >> 8) & 0xff);
                histograma[rgb[i]]++;
            }
            if (cor.equals("b")){
                rgb[i] = (int) (data[i] & 0xff);
                histograma[rgb[i]]++;
            }
        }
        return histograma;
    }
    
    public int[] stretchlim(BufferedImage img, String cor){
        int [] histograma = this.obterHistograma(img, cor);
        double [] padrao = {0.01,0.99};
        int[] cumsum = new int[histograma.length];
        double [] cdf = new double[histograma.length];
        
        int soma = histograma[0];
        cumsum[0] = histograma[0];
        int menor=0,maior=1;
        
        for (int i=1;i<cumsum.length;i++){
            cumsum[i] = cumsum[i-1]+histograma[i];
            soma += histograma[i];
        }
        for (int i=0;i<cumsum.length;i++){
            cdf[i] = ((double)cumsum[i])/((double)soma);
        }
        for (int i=0;i<cumsum.length;i++){
            if (cdf[i]>padrao[0]) {
                menor = i;
                break;
            }
        }
        for (int i=0;i<cumsum.length;i++){
            if (cdf[i]>=padrao[1]) {
                maior = i;
                break;
            }
        }
        if (menor == maior){
            menor = 1;
            maior = 256;
        } 
        int[] saida = {menor,maior};
        return saida;
    }
    
    public void limpar(){
        this.preencher();
        this.erodir();
        this.erodir();
        this.erodir();
        this.erodir();
        this.erodir();
        this.erodir();
        this.erodir();
}
    
    private void erodir(){
    BufferedImage copia = new BufferedImage(binary.getWidth(), binary.getHeight(), BufferedImage.TYPE_INT_RGB);
    double[][] matrizBinaria = new double[binary.getHeight()][binary.getWidth()];
    for (int i = 0; i <binary.getHeight(); i++){
        for (int j = 0; j < binary.getWidth(); j++){
            if (binary.getRGB(j, i) == Color.WHITE.getRGB()){
                matrizBinaria[i][j] = 1;
            } else {
                matrizBinaria[i][j] = 0;
            }
        }
    }
    int controladorL = 0;
    int controladorC = 0;
    double[][] strelDisk = {{0,0,1,1,1,0,0},
                            {0,1,1,1,1,1,0},
                            {1,1,1,1,1,1,1},
                            {1,1,1,1,1,1,1},
                            {1,1,1,1,1,1,1},
                            {0,1,1,1,1,1,0},
                            {0,0,1,1,1,0,0}};
    double[][] subConjunto = new double[strelDisk.length][strelDisk[0].length];
    for (int i=3;i<(binary.getHeight()-3);i++){
        for (int j=3;j<(binary.getWidth()-3);j++){
            if (binary.getRGB(j, i)==Color.WHITE.getRGB()){
                for (int k=i-3;k<i+4;k++){
                    for (int l=j-3;l<j+4;l++){
                        subConjunto[controladorL][controladorC] = matrizBinaria[k][l];
                        controladorC++;
                    }
                    controladorC = 0;
                    controladorL++;
                }
                controladorL = 0;
                controladorC = 0;
                if (!contido(subConjunto,strelDisk)){
                    copia.setRGB(j, i, Color.BLACK.getRGB());
                } else {
                    copia.setRGB(j, i, Color.WHITE.getRGB());
                }
            }
        }
    }
    binary = copia;
}

    private void preencher() {
        int[][] labels = new int[binary.getHeight()][binary.getWidth()];
        for (int i=0;i<binary.getHeight();i++){
            labels[i][0] = 1;
            labels[i][binary.getWidth()-1] = 1;
        }
        for (int i=0;i<binary.getWidth();i++){
            labels[0][i] = 1;
            labels[binary.getHeight()-1][i] = 1;
        }
        for (int i=1;i<(binary.getHeight()-1);i++){
            for (int j=1;j<(binary.getWidth()-1);j++){
                if (binary.getRGB(j, i) == Color.BLACK.getRGB()){
                    if ((labels[i-1][j] == 1)||(labels[i+1][j] == 1)||(labels[i][j-1] == 1)||(labels[i][j+1] == 1)){
                        labels[i][j] = 1;
                    }
                }
            }
        }
        for (int i=(binary.getHeight()-2);i>0;i--){
            for (int j=(binary.getWidth()-2);j>0;j--){
                if (binary.getRGB(j, i) == Color.BLACK.getRGB()){
                    if ((labels[i-1][j]==1)||(labels[i+1][j]==1)||(labels[i][j-1]==1)||(labels[i][j+1]==1)){
                        labels[i][j] = 1;
                    }
                }
            }
        }
        for (int i=1;i<(binary.getHeight()-1);i++){
            for (int j=(binary.getWidth()-2);j>0;j--){
                if (binary.getRGB(j, i) == Color.BLACK.getRGB()){
                    if ((labels[i-1][j]==1)||(labels[i+1][j]==1)||(labels[i][j-1]==1)||(labels[i][j+1]==1)){
                        labels[i][j] = 1;
                    }
                }
            }
        }
        for (int i=(binary.getHeight()-2);i>0;i--){
            for (int j=1;j<(binary.getWidth()-2);j++){
                if (binary.getRGB(j, i) == Color.BLACK.getRGB()){
                    if ((labels[i-1][j]==1)||(labels[i+1][j]==1)||(labels[i][j-1]==1)||(labels[i][j+1]==1)){
                        labels[i][j] = 1;
                    }
                }
            }
        }
        for(int i=0;i<labels.length;i++){
            for (int j=0;j<labels[i].length;j++){
                if (labels[i][j]!=1){
                    binary.setRGB(j, i, Color.WHITE.getRGB());
                }
            }
        }
    }
    
    private boolean contido(double[][] a, double[][] b){
        boolean estaContido = true;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j] == 1){
                    if (a[i][j] == 0) {
                        estaContido = false;
                    } 
                } else {
                    continue;
                }
            }
        }
        return estaContido;
    }
    
    private void conncomp2(int tipo){
        Stack<int[]> pilha = new Stack<int[]>();
        int [] aux;
        int [] aux2 = new int[2];
        rotulos = new int[binary.getHeight()][binary.getWidth()];
        for (int i=0;i < (binary.getHeight()); i++){
            for (int j=0;j < (binary.getWidth()); j++){
                rotulos[i][j] = 0;
            }
        }
        int label = 1;
        for (int i=1;i < (binary.getHeight()-1); i++){
            for (int j=1;j < (binary.getWidth()-1); j++){
                if (binary.getRGB(j, i)==Color.WHITE.getRGB() && (rotulos[i][j]==0)){
                    aux = new int[2];
                    aux[0] = j;
                    aux[1] = i;
                    rotulos[i][j] = label;
                    pilha.add(aux);
                    while (!pilha.isEmpty()){
                        aux2 = pilha.pop();
                        if (binary.getRGB(aux2[0]-1, aux2[1])==Color.WHITE.getRGB() && (rotulos[aux2[1]][aux2[0]-1]==0)){
                            aux = new int[2];
                            aux[0] = aux2[0]-1;
                            aux[1] = aux2[1];
                            rotulos[aux[1]][aux[0]] = label;
                            pilha.add(aux);
                        }
                        if (binary.getRGB(aux2[0]+1, aux2[1])==Color.WHITE.getRGB() && (rotulos[aux2[1]][aux2[0]+1]==0)){
                            aux = new int[2];
                            aux[0] = aux2[0]+1;
                            aux[1] = aux2[1];
                            rotulos[aux[1]][aux[0]] = label;
                            pilha.add(aux);
                        }
                        if (binary.getRGB(aux2[0], aux2[1]-1)==Color.WHITE.getRGB() && (rotulos[aux2[1]-1][aux2[0]]==0)){
                            aux = new int[2];
                            aux[0] = aux2[0];
                            aux[1] = aux2[1]-1;
                            rotulos[aux[1]][aux[0]] = label;
                            pilha.add(aux);
                        }
                        if (binary.getRGB(aux2[0], aux2[1]+1)==Color.WHITE.getRGB() && (rotulos[aux2[1]+1][aux2[0]]==0)){
                            aux = new int[2];
                            aux[0] = aux2[0];
                            aux[1] = aux2[1]+1;
                            rotulos[aux[1]][aux[0]] = label;
                            pilha.add(aux);
                        } if (tipo == 8) {
                            if (binary.getRGB(aux2[0]-1, aux2[1]-1)==Color.WHITE.getRGB() && (rotulos[aux2[1]-1][aux2[0]-1]==0)){
                                aux = new int[2];
                                aux[0] = aux2[0]-1;
                                aux[1] = aux2[1]-1;
                                rotulos[aux[1]][aux[0]] = label;
                                pilha.add(aux);
                            }
                            if (binary.getRGB(aux2[0]-1, aux2[1]+1)==Color.WHITE.getRGB() && (rotulos[aux2[1]+1][aux2[0]-1]==0)){
                                aux = new int[2];
                                aux[0] = aux2[0]-1;
                                aux[1] = aux2[1]+1;
                                rotulos[aux[1]][aux[0]] = label;
                                pilha.add(aux);
                            }
                            if (binary.getRGB(aux2[0]+1, aux2[1]+1)==Color.WHITE.getRGB() && (rotulos[aux2[1]+1][aux2[0]+1]==0)){
                                aux = new int[2];
                                aux[0] = aux2[0]+1;
                                aux[1] = aux2[1]+1;
                                rotulos[aux[1]][aux[0]] = label;
                                pilha.add(aux);
                            }
                            if (binary.getRGB(aux2[0]+1, aux2[1]-1)==Color.WHITE.getRGB() && (rotulos[aux2[1]-1][aux2[0]+1]==0)){
                                aux = new int[2];
                                aux[0] = aux2[0]+1;
                                aux[1] = aux2[1]-1;
                                rotulos[aux[1]][aux[0]] = label;
                                pilha.add(aux);
                            }
                        }
                    }
                    label++;
                }
            }
        }
    }
    
    private void bwareaopen(int tam, int tipo){
        LinkedList<Integer> eliminados = new LinkedList<Integer>();
        conncomp2(tipo);
        regionpropscalculararea();
        for (int i=0;i<areas.size();i++){
            if (areas.get(i) <tam){
                eliminados.add(i+1);
            }
        }
        while (!eliminados.isEmpty()){
            int aux = eliminados.pop();
            for (int i = 1; i<binary.getHeight()-1;i++){
                for (int j = 1; j<binary.getWidth()-1;j++){
                    if (rotulos[i][j] == aux){
                        binary.setRGB(j, i, Color.BLACK.getRGB());
                    }
                }
            }
        }
    }
    
    private void regionpropscalculararea(){
        areas = new LinkedList<Integer>();
        for (int i=0;i<(maior(rotulos));i++){
            areas.add(0);
        }
        for (int i = 0; i<rotulos.length;i++){
            for (int j = 0; j<rotulos[i].length;j++){
                if (rotulos[i][j]!=0){
                    areas.set(rotulos[i][j]-1, areas.get(rotulos[i][j]-1)+1);
                }
            }
        }
    }
    
    private void regionpropscentros(){
        centros = new int[2][areas.size()];
        int somax = 0;
        int somay = 0;
        for (int i = 0; i < areas.size(); i++){
            for (int j = 0; j<rotulos.length;j++){
                for (int k = 0; k<rotulos[j].length;k++){
                    if (rotulos[j][k] == (i+1)){
                        somax += k;
                        somay += j;
                    }
                }
            }
            centros[0][i] = (int) Math.ceil(somax/areas.get(i));
            centros[1][i] = (int) Math.ceil(somay/areas.get(i));
            somax = 0;
            somay = 0;
        }
    }
    
    public void corroer2() {
        int tamQuad = 16;
        int somaAreas1 = 0;
        int somaAreas2 = 1;
        while (somaAreas1 != somaAreas2){
            this.bwareaopen((int)(tamQuad/2),4);
            this.conncomp2(4);
            this.regionpropscalculararea();
            this.regionpropscentros();
            somaAreas2 = somaAreas1;
            somaAreas1 = this.somaArea(areas);
            int totalGrupos = areas.size();
            for (int i=0; i<totalGrupos; i++){
                double porOcupacao = 0.0;
                int inicioColuna = Math.max(0, centros[0][i]-(tamQuad/2));
                int inicioLinha = Math.max(0, centros[1][i]-(tamQuad/2));
                for (int j=inicioLinha; j< Math.min(inicioLinha+tamQuad, rotulos.length); j++){
                    for (int  k=inicioColuna; k< Math.min(inicioColuna+tamQuad, rotulos[0].length); k++){
                        if (rotulos[j][k]!=0){
                            porOcupacao += 1.0;
                        }
                    }
                }
                porOcupacao = porOcupacao/(Math.pow((double)tamQuad, 2.0));
                if (porOcupacao > 0.3){
                    for (int j=0;j<tamQuad;j++){
                        for (int k=0;k<tamQuad;k++){
                            if ((inicioColuna+k<binary.getWidth())&&(inicioLinha+j<binary.getHeight())){
                                binary.setRGB(inicioColuna+k, inicioLinha+j, Color.WHITE.getRGB());
                            }
                        }
                    }
                }
            }
            this.erodir();
        }
        this.conncomp2(4);
    }
        
    private int maior (int[][] rtl){
        int maiorTemp = rtl[0][0];
        for (int[] is : rtl) {
            for (int i : is) {
                if (maiorTemp < i){
                    maiorTemp = i;
                }
            }
        }
        return maiorTemp;
    }
    
    private int somaArea(LinkedList<Integer> vetor){
        int resultado = 0;
        for (int i = 0; i < vetor.size(); i++) {
            resultado += vetor.get(i);
        }
        return resultado;
    }
    
    private double media(LinkedList<Integer> inteiros){
        double saida = 0;
        for (int i : inteiros) {
            saida += i;
        }
        return (saida/inteiros.size());
    }
    
    private double desvio(LinkedList<Integer> inteiros) {
        double media = this.media(inteiros);
        double soma = 0;
        for (int i : inteiros) {
            soma += Math.pow((media-i),2.0);
        }
        soma = soma/(inteiros.size()-1);
        double desvio = Math.sqrt(soma);
        return desvio;
    }
    
    private int tratarImagem() {
        this.conncomp2(4);
        int qtdObj = maior(rotulos);
        int tg = qtdObj;
        if (qtdObj > 0){
            this.regionpropscalculararea();
            this.regionpropscentros();
            double media = this.media(areas);
            double desvio = this.desvio(areas);
            double pequenos = media - desvio;
            if (pequenos < 0){
                pequenos = media*0.1;
            }
            this.bwareaopen((int)(pequenos),8);
            this.regionpropscentros();
            this.conncomp2(4);
            qtdObj = this.maior(rotulos);
            tg = qtdObj;
        }
        return tg;
    }
    
    private void pintarSaida(){
        for (int i = 0; i < imagem.getHeight(); i++){
            for (int j = 0; j < imagem.getWidth(); j++) {
                if (binary.getRGB(j, i)==Color.WHITE.getRGB()){
                    saidaGeral.setRGB(j, i, Color.BLUE.getRGB());
                }
            }
        }
    }
    
    public void processarTudo(){
    	this.redimensionar();
    	this.rgb2ycbcr();
    	this.ajustar();
    	this.segmentar2();
    	this.limpar();
    	this.corroer2();
    	this.tratarImagem();
    	this.pintarSaida();
    	
    }
}
