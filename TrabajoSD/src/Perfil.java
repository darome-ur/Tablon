import java.util.Date;
import java.io.*;

public class Perfil implements Serializable{
	
	private String nombre;
	private String idioma;
	private String nivel;
	private Medio medio;
	private String trueque;
	private String mensaje;
	private String correo;
	private Date fecha;
	
	public Perfil(String nombre,String idioma,String nivel, Medio medio,String trueque,String correo)
	{
		this.setNombre(nombre);
		this.setIdioma(idioma);
		this.setNivel(nivel);
		this.setMedio(medio);
		this.setTrueque(trueque);
		this.fecha = new Date();
		this.correo = correo;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getIdioma()
	{
		return idioma;
	}
	
	public void setIdioma(String idioma)
	{
		this.idioma = idioma;
	}
	
	public Medio getMedio() 
	{
		return medio;
	}
	
	public void setMedio(Medio medio)
	{
		this.medio = medio;
	}
	
	public String getTrueque() 
	{
		return trueque;
	}
	
	public void setTrueque(String trueque)
	{
		this.trueque = trueque;
	}
	
	public String getMensaje() 
	{
		return mensaje;
	}
	
	public void setMensaje(String mensaje) 
	{
		this.mensaje = mensaje;
	}
	
	public String getCorreo() 
	{
		return correo;
	}
	
	public void setCorreo(String correo) 
	{
		this.correo = correo;
	}
	
	public Date getFecha() 
	{
		return fecha;
	} public void mostrar()
	{
		System.out.println("Nombre: "+this.getNombre());
		System.out.println("Idioma: "+this.getIdioma());
		System.out.println("Nivel: "+this.getNivel());
		System.out.println("Medio: "+this.getMedio());
		System.out.println("Trueque: "+this.getTrueque());
		System.out.println("Correo: "+this.getCorreo());
		System.out.println("Fecha: "+this.getFecha());
		
		System.out.println("-------------------------------------------------");
		
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
