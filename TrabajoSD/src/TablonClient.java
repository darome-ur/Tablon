import java.io.*;
import java.net.*;
import java.util.*;
public class TablonClient {
	
	public static void main(String[] args)
	{
		String n;
		System.out.println("1. Buscar.");
		System.out.println("2. Publicar.");
		System.out.println("Salir pulsar cualquier otra tecla.");
		System.out.println("Introduce opción: ");
		Scanner e = new Scanner(System.in);
		n = e.next();
		
		while((n.equals("1"))||(n.equals("2")))
		{
			switch(n)
			{
				case "1": 
				{
					TablonClient.buscar();
				}
				case "2":
				{
					Perfil p = TablonClient.crearPerfil();
					System.out.println("Perfil creado");
					TablonClient.publicar(p);
				}
			}
			System.out.println("1. Buscar.");
			System.out.println("2. Publicar.");
			System.out.println("Salir pulsar cualquier otra tecla.");
			System.out.println("Introduce opción: ");
			n = e.next();
		}
	}
	
	public static void buscar()
	{
		Socket s = null;           //Deben estar fuera del try para poder cerrarlos en el finally
		Writer w = null;
		ObjectInputStream dis = null;
		try
		{
			s = new Socket("localhost",8880); //establecemos la conexion
			w = new OutputStreamWriter(s.getOutputStream()); //Para escribor
		    dis = new ObjectInputStream(s.getInputStream());//Para leer
			String n;
			System.out.println("1. Buscar por nombre.");
			System.out.println("2. Buscar por idioma.");
			System.out.println("3. Buscar por nivel.");
			System.out.println("Salir presionar cualquier tecla");
			System.out.println("Introduce opción: ");
			Scanner e = new Scanner(System.in);
			n = e.next();
		
			while((n.equals("1"))||(n.equals("2")))
			{
				switch(n)
				{
					case "1": 
					{
						String nombre = "BN ";//empieza por BN para indicarle al servidor que es una busqueda.
						System.out.println("Introduce nombre.");
						nombre = nombre + e.next();
						w.write(nombre+"\r\n"); //cargamos el nombre
						w.flush();//enviamos el nombre
						Tablon resultado = (Tablon) dis.readObject(); 
						mostrar(resultado);
						//Correo.menu(resultado);
					}
					case "2":
					{
						String idioma = "BI ";//empieza por BN para indicarle al servidor que es una busqueda.
						System.out.println("Introduce idioma.");
						idioma = idioma + e.next();
						w.write(idioma+"\r\n"); //cargamos el nombre
						w.flush();//enviamos el nombre
						Tablon resultado = (Tablon) dis.readObject(); 
						mostrar(resultado);
						//Correo.menu(resultado);
					}
					case "3":
					{
						String nivel = "BL ";
						System.out.println("1. Primaria.");
						System.out.println("2. Secundaria.");
						System.out.println("3. B1.");
						System.out.println("4. B2.");
						System.out.println("5. C1.");
						System.out.println("6. C2");
						System.out.println("Salir presionar cualquier tecla");
						System.out.println("Introduce opcion:");
						String op = e.next();
						
						switch(op)
						{
							case "1":
							{
								nivel = nivel +"Primaria";
							}
							case "2":
							{
								nivel =nivel + "Secundaria";
							}
							case "3":
							{
								nivel =nivel + "B1";
							}
							case "4":
							{
								nivel =nivel + "B2";
							}
							case "5":
							{
								nivel =nivel + "C1";
							}
							case "6":
							{
								nivel =nivel + "C2";
							}
						}
						w.write(nivel+"\r\n"); //cargamos el nombre
						w.flush();//enviamos el nombre
						Tablon resultado = (Tablon) dis.readObject(); 
						mostrar(resultado);
						//Correo.menu(resultado);
					}
				}
				System.out.println("1. Buscar.");
				System.out.println("2. Buscar por idioma.");
				System.out.println("3. Buscar por nivel.");
				System.out.println("Salir pulsar cualquier otra tecla.");
				System.out.println("Introduce opción: ");
				n = e.next();
			}
		}
		catch (IOException ex)
		{
			 ex.printStackTrace();
		} 
		catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally//se trata aparte y como puede fallar cada cerrar cada uno debe ir con su propio try/catch
		{
			if(s != null)
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(dis != null)
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(w != null)
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	
	
	public static void publicar(Perfil p)
	{
		Socket s = null;           //Deben estar fuera del try para poder cerrarlos en el finally
		ObjectOutputStream o = null;
		DataInputStream r = null;
		Writer w = null;
		try
		{
			s = new Socket("localhost",8880); //establecemos la conexion
		    r = new DataInputStream(s.getInputStream());//Para leer
		     w = (new OutputStreamWriter(s.getOutputStream()));
		  
		    w.write("PUL"+"\r\n");
		    w.flush();
			o = new ObjectOutputStream(s.getOutputStream()); //Para escribor

		    o.writeObject(p); //cargamos el perfil
		    o.flush(); //mandamos el perfil
		    
		    System.out.println("Respuesta del servidor: " + r.readLine());
		    
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(s != null)
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(r != null)
				try {
					r.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(o != null)
				try 
				{
					o.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(w != null)
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static Perfil crearPerfil()
	{
		Scanner escaner = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		String nombre;
		String nivel;
		String trueque;
		String idioma;
		String correo;
		Medio m;
		
		System.out.println("Introduce nombre: ");
		nombre = escaner.nextLine();
		System.out.println("Introduce idioma: ");
		idioma = escaner.nextLine();
		System.out.println("Selecciona nivel:");
		System.out.println("1. B1.");
		System.out.println("2. B2.");
		System.out.println("3. C1.");
		System.out.println("4. C2.");
		System.out.println("5. PRIMARIA");
		System.out.println("6. SECUNDARIA");
		System.out.println("NINGUNO, cualquier otra tecla.");
		System.out.println("Introduce opcion:");
		String op = escaner.next();
		
		switch(op)
		{
			case "1":
			{
				nivel = "B1";
			}
			case "2":
			{
				nivel = "B2";
			}
			case "3":
			{
				nivel = "C1";
			}
			case "4":
			{
				nivel = "C2";
			}
			case "5":
			{
				nivel = "PRIMARIA";
			}
			case "6":
			{
				nivel = "SECUNDARIA";
			}
			default:			
			{
				nivel = "NINGUNO";
			}
		}
		
		System.out.println("Introduce trueque: ");
		trueque = sc.nextLine();
		
		System.out.println("Introduce correo: ");
		correo = sc.nextLine();
		
		
		System.out.println("Selecciona medio:");
		System.out.println("1. Facebook.");
		System.out.println("2. Hangouts.");
		System.out.println("3. Skype.");
		System.out.println("4. Viper.");
		System.out.println("5. Telefono");
		System.out.println("Cualquier medio, cualquier otra tecla.");
		System.out.println("Introduce opcion:");
		op = escaner.next();
		
		switch(op)
		{
			case "1":
			{
				m = Medio.Facebook;
			}
			case "2":
			{
				m = Medio.Hangouts;
			}
			case "3":
			{
				m = Medio.Skype;
			}
			case "4":
			{
				m = Medio.Viper;
			}
			case "5":
			{
				m = Medio.Tfno;
			}
			default:			
			{
				m = Medio.Cualquiera;
			}
		}
		
		Perfil p = new Perfil(nombre,idioma,nivel,m,trueque,correo);
		return p;
	}
	
	public static void mostrar(Tablon t)
	{
		if(t.tam() == 0)
		{
			System.out.println("No hay.");
		}
		else 
		{
			for(Perfil p: t)
			{
				//System.out.println("No hay.");
				p.mostrar();
			}
		}
	}

}
