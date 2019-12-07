import java.io.*;
import java.net.*;
import java.util.*;

import objetos.Medio;
import objetos.Anuncio;
import objetos.Tablon;
public class TablonClient {
	
	public static void main(String[] args)
	{
		String n;
		System.out.println("1. Ver.");
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
					TablonClient.ver();
				}
				case "2":
				{
					Anuncio p = TablonClient.crearPerfil();
					System.out.println("Perfil creado");
					TablonClient.publicar(p);
				}
			}
			System.out.println("1. Ver.");
			System.out.println("2. Publicar.");
			System.out.println("Salir pulsar cualquier otra tecla.");
			System.out.println("Introduce opción: ");
			n = e.next();
		}
	}
	
	public static void ver()
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
			System.out.println("1. Siguiente.");
			System.out.println("2. Anterior.");
			System.out.println("3. Salir.");
			System.out.println("Introduce opción: ");
			Scanner e = new Scanner(System.in);
			n = e.next();
			
			Anuncio resultado = (Anuncio) dis.readObject(); 
			mostrar(resultado);
			
			while((n.equals("1"))||(n.equals("2")))
			{
				
				switch(n)
				{
					case "1": 
					{
						String marca = "SIG";//empieza por SIG  para indicarle al servidor que muestre el siguiente.
						w.write(marca+"\r\n"); //cargamos el nombre
						w.flush();//enviamos el nombre
						resultado = (Anuncio) dis.readObject(); 
						resultado.mostrar();
					}
					case "2":
					{
						String marca = "ANT";//empieza por ANT para indicarle al servidor que quere el anterior
						w.write(marca+"\r\n"); //cargamos el nombre
						w.flush();//enviamos el nombre
						resultado = (Anuncio) dis.readObject(); 
						resultado.mostrar();
					}
				}
				
				System.out.println("1. Siguiente.");
				System.out.println("2. Anterior.");
				System.out.println("3. Salir.");
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
	
	/*
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
	*/
	
	public static void publicar(Anuncio p)
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
		  
		    w.write("PUB"+"\r\n");
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
	
	public static Anuncio crearPerfil()
	{
		Scanner escaner = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		String nombre;
		String correo;
		String mensaje;
		String titulo;
		
		System.out.println("Introduce nombre: ");
		nombre = escaner.nextLine();

		System.out.println("Introduce titulo: ");
		titulo = escaner.nextLine();
		
		System.out.println("Introduce mensaje: ");
		mensaje = escaner.nextLine();
		
		System.out.println("Introduce correo: ");
		correo = sc.nextLine();
		
		Anuncio p = new Anuncio(nombre,titulo,mensaje,correo);
		return p;
	}
	
	public static void mostrar(Anuncio resultado)
	{
		if(resultado == null)
		{
			System.out.println("No hay.");
		}
		else 
		{
			resultado.mostrar();
		}
	}

}
