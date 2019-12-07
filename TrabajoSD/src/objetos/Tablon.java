package objetos;
import java.io.*;
import java.util.*;


public class Tablon implements Serializable, Iterable<Anuncio>
{
	
	private  static ArrayList<Anuncio> tablon;
	
	public Tablon()
	{
		Tablon.tablon = new ArrayList<Anuncio>();
	}
	public  Anuncio getPerfil(int i)
	{
		return Tablon.tablon.get(i);
	}
	public void add(Anuncio p)
	{
		Tablon.tablon.add(p);
	}
	public int tam()
	{
		return tablon.size();
	}
	public Iterator<Anuncio> iterator()
	{
		return tablon.iterator();
	}
}
