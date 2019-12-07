import java.io.*;
import java.net.*;

import objetos.Anuncio;
import objetos.Tablon;

public class TablonServ {
	
	
	
	public static void main(String[] args)
	{
		Tablon t = new Tablon();
		try
		{
			ServerSocket s = new ServerSocket(8880);
			while (true)
			{
				Socket ss = s.accept();
				ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
				ObjectOutputStream ous = new ObjectOutputStream(ss.getOutputStream());
				BufferedReader b = new BufferedReader(new InputStreamReader(ss.getInputStream()));
				Writer w = (new OutputStreamWriter(ss.getOutputStream()));
				try
				{
					
					String orden = b.readLine();
					if(orden.startsWith("PUL"))
					{
						//Aqui llega a entrar
						Anuncio p = (Anuncio) ois.readObject();//falla aqui
				
						publicar(t,p);
						w.write("Publicacion con exito."+"\r\n");
						w.flush();
					}
					if(orden.startsWith("BN"))
					{
						String[] trozos = orden.split(" ");
						ous.writeObject(searchPostByNombre(t,trozos[2]));
					}
					if(orden.startsWith("BI"))
					{
						String[] trozos = orden.split(" ");
						ous.writeObject(searchPostByIdioma(t,trozos[2]));
					}
					if(orden.startsWith("BL"))
					{
						String[] trozos = orden.split(" ");
						ous.writeObject(searchPostByNivel(t,trozos[2]));
					}
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
	}
	
	
	public static Tablon searchPostByIdioma(Tablon t,String idioma)
	{
		Tablon aux = new Tablon();
		for(int i = 0; i < t.tam(); i++)
		{
			if(t.getPerfil(i).getIdioma().equals(idioma))
			{
				aux.add(t.getPerfil(i));
			}
		}
		return aux;
	}
	public static Tablon searchPostByNombre(Tablon t,String n)
	{
		Tablon aux = new Tablon();
		for(int i = 0; i < t.tam(); i++)
		{
			if(t.getPerfil(i).getIdioma().equals(n))
			{
				aux.add(t.getPerfil(i));
			}
		}
		return aux;
	}
	public static Tablon searchPostByNivel(Tablon t,String l)
	{
		Tablon aux = new Tablon();
		for(int i = 0; i < t.tam(); i++)
		{
			if(t.getPerfil(i).getNivel().equals(l))
			{
				aux.add(t.getPerfil(i));
			}
		}
		return aux;
	}
	public static void publicar(Tablon t,Anuncio p)
	{
		t.add(p);
	}

}
