import java.io.*;
import java.util.*;


public class Tablon implements Serializable, Iterable<Perfil>
{
	
	private  static ArrayList<Perfil> tablon;
	
	public Tablon()
	{
		Tablon.tablon = new ArrayList<Perfil>(1000);
	}
	public  Perfil getPerfil(int i)
	{
		return Tablon.tablon.get(i);
	}
	public void add(Perfil p)
	{
		Tablon.tablon.add(p);
	}
	public int tam()
	{
		return tablon.size();
	}
	
	public Iterator<Perfil> iterator()
	{
		return tablon.iterator();
	}
}
