package br.edu.unirn.tcc.beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.edu.unirn.tcc.constants.Sexo;
import br.edu.unirn.tcc.constants.TipoFoto;
import br.edu.unirn.tcc.constants.UF;
import br.edu.unirn.tcc.dao.PacienteDAO;
import br.edu.unirn.tcc.dominio.Fotos;
import br.edu.unirn.tcc.dominio.Paciente;
import br.edu.unirn.tcc.negocio.Processador;

@ManagedBean
@SessionScoped
public class PacienteMBean {
	
	TabView tabView;
	
	private StreamedContent fotoSalva;
	private StreamedContent fotoProcessada;
	private StreamedContent fotoDownload;	
	private Paciente paciente = new Paciente();
	
	public void iniciarProcessamento(){
		if (paciente != null && (paciente.getId() != null && paciente.getId() != 0)){
			try{
				
				ImageIcon ii = new ImageIcon(paciente.getFotos().get(0).getFotos());
				
				BufferedImage bufferedImg = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            	Graphics2D g2 = bufferedImg.createGraphics();
            	g2.drawImage(ii.getImage(), 0, 0, Math.abs(600), Math.abs(600), null);
            	
            	ByteArrayOutputStream os = new ByteArrayOutputStream();
            	ImageIO.write(bufferedImg, "png", os);
            	fotoSalva = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
            	
            	Processador processador = new Processador(bufferedImg);
            	processador.processarTudo();
            	
            	ByteArrayOutputStream osProcessada = new ByteArrayOutputStream();
            	ImageIO.write(processador.getSaidaGeral(), "png", osProcessada);
            	fotoProcessada = new DefaultStreamedContent(new ByteArrayInputStream(osProcessada.toByteArray()), "image/png");
            	
            	this.prepararDownload(processador.getSaidaGeral());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Paciente> getListaCompleta(){
		PacienteDAO dao = new PacienteDAO();
		try{
			return dao.findAllOrdenado("nome", "asc");
		}finally{
			dao.close();
		}	
	}

	public String salvar() throws IOException{
		PacienteDAO pDao = new PacienteDAO();
		if (paciente.getId() == 0){
			pDao.create(paciente);
		} else {
			pDao.update(paciente);
		}
		System.out.println(paciente.getId());
		//FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
		paciente = new Paciente();
		return "index.xhtml?faces-redirect=true";
	}
	
	public void upload(FileUploadEvent event) throws IOException {
	    UploadedFile uploadedFile = event.getFile();
	    System.out.println(uploadedFile.getContents());
	    
	    Fotos foto =  new Fotos();
	    foto.setTipoFoto(TipoFoto.OCLUSALINFERIOR);
	    foto.setFotos(uploadedFile.getContents());
	    paciente.getFotos().add(foto);
	    
	    FacesContext.getCurrentInstance().addMessage(
	               null, new FacesMessage("Upload completo", 
	               "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
	}
	
	public String selecionar(Paciente paciente){
		this.paciente = paciente;
		this.iniciarProcessamento();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Processamento concluído com sucesso!"));
		return "/pacienteFoto.xhtml";
	}
	
	public String exclusaoRapida(Paciente paciente){
		PacienteDAO dao = new PacienteDAO();
		try{
			dao.delete(paciente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente deletado com sucesso"));
		}finally{
			dao.close();  
		}
		return null;
	}
	
	public String editar(Paciente paciente){
		this.paciente = paciente;
		return "/paciente.xhtml";
	}
	
	private void prepararDownload(BufferedImage baixar) throws IOException{
		ByteArrayOutputStream osDown = new ByteArrayOutputStream();
    	ImageIO.write(baixar, "png", osDown);
    	ByteArrayInputStream saida = new ByteArrayInputStream(osDown.toByteArray());
        this.fotoDownload = new DefaultStreamedContent(saida, "image/png", "downloaded_processada.jpg");
        System.out.println("chegou ao fim!!!");
	}
	
	public List<Sexo> getSexos(){
		List<Sexo> sexos = new ArrayList<Sexo>();
		for (Sexo s : Sexo.values()){
			sexos.add(s);
		}
		return sexos;
	}
	
	public List<UF> getUfs(){
		List<UF> ufs = new ArrayList<UF>();
		for (UF u : UF.values()){
			ufs.add(u);
		}
		return ufs;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public StreamedContent getFotoSalva() {
		return fotoSalva;
	}

	public StreamedContent getFotoProcessada() {
		return fotoProcessada;
	}

	public StreamedContent getFotoDownload() {
		return fotoDownload;
	}

}
