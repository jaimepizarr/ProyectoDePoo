
package quienquieresermillonario;
import Modelo.ArchEstudiantes;
import Modelo.ArchPreguntas;
import Modelo.Estudiante;
import Modelo.Materia;
import Modelo.Paralelo;
import Modelo.Pregunta;
import Modelo.Reporte;
import Modelo.Termino;
import Utileria.Comodin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("InitializerMayBeStatic")
public class JuegoPrincipal {
    Scanner entrada = new Scanner(System.in);
    private ArrayList<Termino> terminosacad = new ArrayList<>();
    private Termino terminoJuegonu;
    private ArrayList<Materia> materias = new ArrayList<>();
    private ArrayList<Paralelo> paralelos = new ArrayList<>();
    private ArrayList<Reporte> reportes =new ArrayList<>();
    private Paralelo paraleloJuego =null;
    private String premio = "";

    public static void main(String[] args) {
        /*int numero = (int) ((Math.random() * 5) + 1);
        System.out.println(numero);*/

        JuegoPrincipal game = new JuegoPrincipal();
        game.menu();

    }//main


    public void menu(){
        String entryMP= "a";
        while (entryMP!="4"){
           System.out.println("SELECCIONE UNA OPCION PARA INICIAR\n1. Configuraciones\n" +
           "2. Nuevo juego\n" +
           "3. Reporte\n" +
           "4. Salir");
           String opcion= entrada.nextLine();
            switch (opcion){
            //CONFIGURACIONES
            case "1":
                configuraciones();
                break;//CASE1 PRINCIPAL
            //NUEVO JUEGO
            case "2":
                if(terminoJuegonu != null){
                    System.out.println("JUEGO NUEVO");
                    //Materia
                    System.out.println("Ingrese Materia");
                    String nombreMateria= entrada.nextLine();
                    ArchPreguntas aprob = new ArchPreguntas(nombreMateria);
                    aprob.leerArchivo();


                    //Paralelo
                    System.out.println("Ingrese Paralelo");
                    String paralelo= entrada.nextLine();
                    ArrayList<Estudiante> est = new ArrayList<>();
                    for (Paralelo p:paralelos){
                       
                        if (p.getParaleloCod().equals(paralelo)){
                            paraleloJuego=p;
                            ArchEstudiantes arEst= new ArchEstudiantes(p);
                            arEst.leerArchivoEstudiantes();
                            for(Estudiante student :p.getEstudiantes()){
                                est.add(student);
                            }
                        }
                    }
                    //Preguntas por nivel
                    
                    Materia mat = getMateriaConNombre(nombreMateria);
                    
                    String entry = "a";
                    
                    
                    
                    int numNivel=0;
                    while(entry == "a"){
                        System.out.println("Ingrese el numero de preguntas por nivel");
                        numNivel= entrada.nextInt();
                        entrada.nextLine();
                        if(verificacion(aprob.preguntas,numNivel,mat)){
                            entry="b";
                        }
                        else{
                            System.out.println("No hay suficientes preguntas en algunos niveles");

                        }
                    }
                    
                    ArrayList<Integer> saltosPreg= saltosPreguntas(aprob.preguntas,numNivel, mat);
                    ArrayList<Pregunta> PregSegunCantNivel = new ArrayList<>();
                    
                    ArrayList<Pregunta> copia = new ArrayList<>();
                    copia= (ArrayList)aprob.preguntas.clone();
                    for (int j:saltosPreg){
                        for (int k =0; k<numNivel;k++){
                                PregSegunCantNivel.add(copia.get(k));
                                copia.remove(k);
                        }
                        for (int l=0;l<j;l++){
                            copia.remove(l);
                        }
                        
                    }
                    
                    //Participante
                    System.out.println("PARTICIPANTE: \n1.Ingresar matricula.\n2.Elegir Aleatoreamente");
                    String opcionParticipante= entrada.nextLine();
                    String participante= null;
                    switch (opcionParticipante){
                        case "1":
                            //matricula del participante
                            participante = nombreporMatricula(est);
                            break;
                        case "2":
                            //Eleccion del participante aleatoreamente
                           participante = nombreporAleatorio(est);
                            System.out.println("El estudiante elegido es: "+participante);
                            break;

                        default:
                            System.out.println("Ingrese una opcion correcta");
                    }//switch participante
                    



                //SALE E INICIA EL JUEGO
                    System.out.println("¿QUIEN QUIERE SER MILLONARIO? ESTUDIANTIL");
                    System.out.println("Bienvenido "+ participante+"!!");
                    System.out.println("Presiona una tecla para continuar");
                    entrada.nextLine();
                    aprob.leerArchivo();
                    
                    ArrayList<String> comodines = new ArrayList<>();
                    for (Comodin com: Comodin.values()){
                        comodines.add(com.name());
                        System.out.println(com.name());
                    }
                    int puntos=0;
                    /*
                    int salir = 0;
                    
                    while (salir!=1){
                        for(Pregunta mostrar:aprob.preguntas){

                            ArrayList<String> letras =new ArrayList<>();
                            letras.add("A");
                            letras.add("B");
                            letras.add("C");
                            letras.add("D");
                            
                            ArrayList<String> respIncorrectas = new ArrayList<>();
                            respIncorrectas.add(mostrar.getRespInc1());
                            respIncorrectas.add(mostrar.getRespInc2());
                            respIncorrectas.add(mostrar.getRespInc3());
                            
                            ArrayList<String> respuestas = new ArrayList<>();
                            for(String rIn:respIncorrectas){
                                respuestas.add(rIn);
                            }
                            respuestas.add(mostrar.getrCorrecta());
                            Collections.shuffle(respuestas);
                            
                            int index=respuestas.indexOf(mostrar.getrCorrecta());
                            String letraCorrecta= letras.get(index);
                            
                            System.out.println(mostrar.getEnunciado());
                            for(int p=0;p<letras.size();p++){
                                System.out.println(letras.get(p)+".- "+respuestas.get(p));
                            }
                            
                            
                            String repuestaElegida= entrada.nextLine();
                            if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                System.out.println("Perdiste");
                                System.out.println("FIN DEL JUEGO");
                                salir=1;
                                
                                break;
                            }    
                            
                                    
                            int n =0;
                            while (n!=4){
                            
                            
                            if(repuestaElegida.equals("*")){
                                System.out.println("Comodines: ");
                                int k=1;
                                for(String c: comodines){
                                    System.out.println(k+".- "+c);
                                    k++;
                                }
                               
                                System.out.println("Elija un comodin");
                                int comElegido= entrada.nextInt();
                                entrada.nextLine();
                                comodines.remove(comodines.get(comElegido-1));
                                switch (comElegido){
                                    case 1:
                                        
                                        Random aleatorio = new Random(System.currentTimeMillis());
                                        int numAleatorio1 = aleatorio.nextInt(respIncorrectas.size());
                                        //respIncorrectas.remove(numAleatorio1);
                                        //letras.remove("C");
                                        
                                        int numAleatorio2 = aleatorio.nextInt(respIncorrectas.size());
                                        //respIncorrectas.remove(numAleatorio2);
                                        //letras.remove("D");
                                        String incorrecta1 = respIncorrectas.get(numAleatorio1);
                                        String incorrecta2 = respIncorrectas.get(numAleatorio2);
                                        System.out.println("La opcion: " +incorrecta1 + " es incorrecta");
                                        System.out.println("La opcion: " +incorrecta2 + " es tambien incorrecta");
                                        
                                        break;
                                        
                                    case 2:
                                        System.out.println("Ha usado el comodin CONSULTA AL COMPAÑERO");
                                        break;
                                    
                                    case 3:
                                        System.out.println("Ha usado el comodin CONSULTA AL SALON");
                                } 
                                System.out.println("Cual es la respuesta correcta?");
                                repuestaElegida= entrada.nextLine();
                                if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                    System.out.println("Perdiste");
                                    System.out.println("FIN DEL JUEGO");
                                    salir=1;
                                    n=4;
                                    entryMP="4";
                                    break;
                                }
                                
                                else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                    System.out.println("FELICITACIONES! HAZ CONTESTADO CORRECTAMENTE");
                                    System.out.println("SIGUIENTE PREGUNTA:");
                                    puntos++;
                                    n=4;
                                    break;
                                }
                                break;
                            }
                            else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                System.out.println("FELICITACIONES! HAZ CONTESTADO CORRECTAMENTE");
                                System.out.println("SIGUIENTE PREGUNTA:");
                                puntos++;
                                n=4;
                            }*/
                    int n = 0;

                    while (n<aprob.preguntas.size()){
                            Pregunta mostrar = aprob.preguntas.get(n);
                            ArrayList<String> letras =new ArrayList<>();
                            letras.add("A");
                            letras.add("B");
                            letras.add("C");
                            letras.add("D");

                            ArrayList<String> respIncorrectas = new ArrayList<>();
                            respIncorrectas.add(mostrar.getRespInc1());
                            respIncorrectas.add(mostrar.getRespInc2());
                            respIncorrectas.add(mostrar.getRespInc3());

                            ArrayList<String> respuestas = new ArrayList<>();
                            for(String rIn:respIncorrectas){
                                    respuestas.add(rIn);
                            }
                            respuestas.add(mostrar.getrCorrecta());
                            Collections.shuffle(respuestas);

                            int index=respuestas.indexOf(mostrar.getrCorrecta());
                            String letraCorrecta= letras.get(index);

                            System.out.println(mostrar.getEnunciado());
                            for(int p=0;p<letras.size();p++){
                                    System.out.println(letras.get(p)+".- "+respuestas.get(p));
                            }		

                            String repuestaElegida= entrada.nextLine();
                            if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                    System.out.println("Perdiste");
                                    System.out.println("FIN DEL JUEGO");
                                    n=aprob.preguntas.size();

                                    break;
                            }    
                            else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                    System.out.println("FELICITACIONES! HAZ CONTESTADO CORRECTAMENTE");
                                    System.out.println("SIGUIENTE PREGUNTA:");
                                    puntos++;
                                    n++;
                            }


                            else if (repuestaElegida.equals("*")){
                                System.out.println("Comodines: ");
                                int k=1;
                                for(String c: comodines){
                                        System.out.println(k+".- "+c);
                                        k++;
                                }

                                System.out.println("Elija un comodin");
                                String comElegido= entrada.nextLine();

                                comodines.remove(comElegido);
                                switch (comElegido){
                                        case "COMODIN50_50":
                                                Random aleatorio = new Random(System.currentTimeMillis());
                                                int numAleatorio1 = aleatorio.nextInt(respIncorrectas.size());
                                                //respIncorrectas.remove(numAleatorio1);
                                                //letras.remove("C");

                                                int numAleatorio2 = aleatorio.nextInt(respIncorrectas.size());
                                                //respIncorrectas.remove(numAleatorio2);
                                                //letras.remove("D");
                                                String incorrecta1 = respIncorrectas.get(numAleatorio1);
                                                String incorrecta2 = respIncorrectas.get(numAleatorio2);
                                                System.out.println("La opcion: " +incorrecta1 + " es incorrecta");
                                                System.out.println("La opcion: " +incorrecta2 + " es tambien incorrecta");


                                                break;

                                        case "CONSULTA_COMPAÑERO":
                                                System.out.println("Ha usado el comodin CONSULTA AL COMPAÑERO");
                                                break;

                                        case "CONSULTA_SALON":
                                                System.out.println("Ha usado el comodin CONSULTA AL SALON");
                                } 
                                System.out.println("Cual es la respuesta correcta?");
                                repuestaElegida= entrada.nextLine();
                                if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                        System.out.println("Perdiste");
                                        System.out.println("FIN DEL JUEGO");
                                        n=aprob.preguntas.size();
                                        break;
                                }

                                else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                        System.out.println("FELICITACIONES! HAZ CONTESTADO CORRECTAMENTE");
                                        System.out.println("SIGUIENTE PREGUNTA:");
                                        puntos++;
                                        n++;

                                }
                                    
                            }

                    }
                

                        //
                    try {
                          //MOSRTAR INGFORMACION AQUI
                          generarCsvReporte(terminoJuegonu,paraleloJuego ,mat.getCodigo());
                        } catch (IOException ex) {
                          Logger.getLogger(JuegoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    int nivelAlcanzado =(int) Math.floor(puntos/numNivel);
                    String nivel = Integer.toString(nivelAlcanzado);
                    Date hoy = new Date();

                    if (nivelAlcanzado >= 1){
                        System.out.println("Ingrese el premio del participante");
                        premio = entrada.nextLine();

                    }

                    Reporte repJuego = new Reporte (hoy,nivel,premio,participante);
                    entryMP="4";
                break;
                
                
                }

                else{
                    System.out.println("Debe configurar el termino");
                    
                }
                
            //REPORTE
                break; //CASE 2 PRINCIPAL//CASE 2 PRINCIPAL
            case "3":
                
                menuReporte();

                break; //CASE 3 PRINCIPAL

     //SALIR
            case "4":
                entryMP="4";
                break;

           }//swich principal

    }//while principal
    }

    
    public void menuReporte(){
        System.out.println("JUEGOS");
        //Termino academino
        System.out.println("Anio Termino Academino");

        String anioTAcademicoReport= entrada.nextLine();
        System.out.println("Numero Termino Academico");
        String numTAcademicoReport = entrada.nextLine();
       //Codigo materia
        System.out.println("Ingrese Codigo de Materia");
        String codigoMateria= entrada.nextLine();


        //Paralelo
        System.out.println("Ingrese Paralelo");
        String paraleloReport= entrada.nextLine();
        Termino termino = new Termino(anioTAcademicoReport,numTAcademicoReport);
        Paralelo paral = new Paralelo( paraleloReport, codigoMateria, termino);


       try {
           //MOSRTAR INGFORMACION AQUI
           generarCsvReporte( termino,paral ,codigoMateria);
       } catch (IOException ex) {
           Logger.getLogger(JuegoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void configuraciones(){
        String ii= "a";
        while (!"5".equals(ii)){
            System.out.println("CONFIGURACIONES\n1. Administrar términos académicos\n" +
               "2. Administrar materias y paralelos\n" +
               "3. Administrar estudiantes\n" +
               "4. Administrar preguntas\n" +
               "5. Salir");
            String opcionConfiguracion= entrada.nextLine();
            switch (opcionConfiguracion){
                case "1":
                   adminTerminos();
                   break;

                case "2":
                    adminMatPar();
                    break;//case 2 CONFIGURACIONES

                case "3":
                    adminEstudiantes();
                    break;//case 3 CONFIGURACIONES

                case "4":
                    adminPreguntas();
                    break;//case 4 CONFIGURACIONES

                case "5":
                    ii= "5";
            }//switch configuraciones
        }//while configuraciones
    }

    public void adminMatPar(){
        System.out.println("materias y paralelos");
        //IMPRIMIR LISTADO DE MATERIAS Y PARALELOS QUE EL PROFESOR A DICTADO

        System.out.println("1.- Ingresar materia\n" +
                               "2.- Editar materia\n" +
                               "3.- Desactivar materia\n" +
                               "4.- Agregar paralelo\n" +
                               "5.- Eliminar paralelo");
        String opcionMP= entrada.nextLine();
        switch (opcionMP){
            case "1":

                System.out.println("Ingrese Nombre Materia");
                String nombre= entrada.nextLine();

                System.out.println("Ingrese Codigo Materia");
                String codigoMateria= entrada.nextLine();

                System.out.println("Cantidad de niveles");
                int cantidadNiveles= entrada.nextInt();
                entrada.nextLine();
                Materia materia = new Materia(nombre,codigoMateria,cantidadNiveles);
                materias.add(materia);
                break;

            case "2":
                System.out.println("Ingrese Codigo Materia");
                String materiaAeditar= entrada.nextLine();
                int pos = 0;
                for (int i =0; i<materias.size();++i){
                    if (materias.get(i).getNombre().equals(materiaAeditar)){
                        pos=i;
                    }
                }
                String resp = "Y";
                System.out.println("Desea cambiar el nombre de la materia? Y/N");
                while (resp == "Y"){
                    System.out.println("Ingrese el nombre que desea poner");
                    String nombre_nuevo = entrada.nextLine();
                    materias.get(pos).setNombre(nombre_nuevo);
                }
                String resp2="Y";
                System.out.println("Desea cambiar la cantidad de niveles de la materia? Y/N");
                while (resp2 == "Y"){
                    System.out.println("Ingrese la cantidad de niveles");
                    int niveles = entrada.nextInt();
                    entrada.nextLine();
                    materias.get(pos).setCantidadNiveles(niveles);
                }
                break;
            case "3":
                System.out.println("Ingrese Codigo Materia");
                String materiaAdesactivar= entrada.nextLine();
                System.out.println("Esta seguro de desactivar esta materia?\nReponder con Y/N");
                String decision= entrada.nextLine();
                switch (decision){
                    case "Y":
                        for (Materia m : materias){
                            if (m.getNombre().equals(materiaAdesactivar)){
                                m.setActivo(false);
                            }
                        }
                        break;
                    case "N":
                        break; }//Swith desicion
                break;

            case "4":
                System.out.println("Ingrese Materia");
                String materiaIngresada= entrada.nextLine();
                Materia mat = getMateriaConNombre(materiaIngresada);
                if (materiasA(materias,materiaIngresada)){
                    System.out.println("Ingrese el año del Termino Academico");
                    String anioTerm= entrada.nextLine();

                    System.out.println("Ingrese el numero del Termino Academico");
                    String numTerm= entrada.nextLine();


                    System.out.println("Ingrese Numero de paralelo");
                    String numeroParalelo= entrada.nextLine();
                    paralelos.add(new Paralelo(numeroParalelo,mat,new Termino(anioTerm,numTerm)));
                }


                break;//case4MATERIAS Y PARALELO
            case "5":
                //MOSTRAR PARALELOS EXISTENTES
                ArrayList<Paralelo> parElim = new ArrayList<>();
                for (Paralelo p: paralelos){
                    int i=1;
                    if(materiasA(materias,p.getMateria().getNombre())){
                        System.out.println(i+".- "+p);
                        i++;
                        parElim.add(p);
                    }
                }
                System.out.println("Seleccionar paralelo a eliminar");
                int seleccion=entrada.nextInt();
                entrada.nextLine();
                paralelos.remove(parElim.get(seleccion - 1));
                break;//case 5MATERIAS Y PARALELO
            default:
                System.out.println("Ingresar una opcion correcta");
                break;
        }//switch materia y paralelo
    }

    public void adminTerminos(){
        System.out.println("1.- Ingresar término\n" +
                            "2.- Eliminar término\n" +
                            "3.- Configurar término para el juego");
                                //MOSTRAR TERMINOS ACADEMICOS
        String termino=entrada.nextLine();
        switch (termino){
            case "1":
                //SE INGRESA TERMINO
                //anio
                System.out.println("Ingresar anio: ");
                String anio= entrada.nextLine();
                //numero de termino
                System.out.println("Ingresar numero de termino");
                String numeroTermino= entrada.nextLine();
                Termino term = new Termino(anio,numeroTermino);
                terminosacad.add(term);
                break;
            case "2":
                int i=1;
                System.out.println("Los terminos existentes son: ");
                for (Termino t:terminosacad){
                    System.out.println(i+". "+ t);
                    ++i;
                }
                System.out.println("Ingrese el numero del termino a eliminar");
                int termElim = entrada.nextInt();
                entrada.nextLine();
                terminosacad.remove(termElim-1);
            //ELIMINAR TERMINO
                break;
            case "3":
                int j=1;
                 for (Termino t:terminosacad){
                    System.out.println(j+". "+ t);
                    ++j;}
                int elegir = entrada.nextInt();
                entrada.nextLine();
                terminoJuegonu=terminosacad.get(elegir-1);

                 break;
            default:
                 System.out.println("Ingrese una opcion correcta");}//switch terminos academicos
    }
    public void adminEstudiantes(){
        System.out.println("Administrar estudiantes");
        //IMPRIMIR LISTADO DE ESTUDIANTES POR TERMINO ACADEMICO Y PARALELO DE MATERIA
        System.out.println("Ingrese Paralelo");
        String paralelo= entrada.nextLine();

        System.out.println("Ingrese el año del Termino Academico");
        String anioTerm= entrada.nextLine();

        System.out.println("Ingrese el numero del Termino Academico");
        String numTerm= entrada.nextLine();

        System.out.println("Ingrese Codigo de Materia");
        String codigoMateria= entrada.nextLine();
        Materia materia = new Materia(codigoMateria);
        
        Termino estermac = new Termino(anioTerm,numTerm);
        Paralelo paralelEst = new Paralelo(paralelo,materia,estermac);
        ArchEstudiantes estPar = new ArchEstudiantes(paralelEst);
        estPar.leerArchivoEstudiantes();
        ArrayList<Estudiante> listEst= paralelEst.getEstudiantes();
        for(Estudiante e: listEst){
            System.out.println(e);
        }
        //GUARDAR INFORMACION CSV

    }

    public void adminPreguntas(){

        System.out.println("Administrar Preguntas\n1.- Visualizar preguntas\n" +
        "2.- Agregar pregunta\n" +
        "3.- Eliminar pregunta");
        String seleccionA= entrada.nextLine();
        switch (seleccionA){

            case "1":
                System.out.println("Ingrese Codigo Materia");
                String codMateria= entrada.nextLine();
                String nombre = obtNombreConCod(codMateria);
                if (materiasA(materias,nombre)){
                    ArchPreguntas ej = new ArchPreguntas(nombre);
                    ej.leerArchivo();
                    Collections.sort(ej.preguntas);
                    int indexP=1;
                    for(Pregunta mostrar:ej.preguntas){
                              System.out.println(indexP + " " +mostrar);
                              indexP+=1;
                    }
                }

                break;

            case"2":
                System.out.println("Ingrese Codigo Materia");
                String materiaAgregar= entrada.nextLine();
                String nombreMat = obtNombreConCod(materiaAgregar);
                if(materiasA(materias,nombreMat)){
                    ArchPreguntas archp = new ArchPreguntas(nombreMat);
                    archp.leerArchivo();

                    System.out.println("Ingrese Enunciado de la pregunta");
                    String enunciado= entrada.nextLine();

                    System.out.println("Ingrese Nivel");
                    String nivel= entrada.nextLine();

                    System.out.println("Ingrese la  respuesta CORRECTA");
                    String respuestaCorrecta= entrada.nextLine();

                    System.out.println("Ingrese Posible Respuesta 1");
                    String respuestaInc1= entrada.nextLine();

                    System.out.println("Ingrese Posible Respuesta 2");
                    String respuestaInc2= entrada.nextLine();

                    System.out.println("Ingrese Posible Respuesta 3");
                    String respuestaInc3= entrada.nextLine();

                    Pregunta preguntaAgg= new Pregunta(enunciado, nivel , respuestaCorrecta, respuestaInc1, respuestaInc2, respuestaInc3);
                    archp.preguntas.add(preguntaAgg);
                    //ALMACENAR PREGUNTAS EN CSV NOMBRE CODIGO DE LA MATERIA
                    archp.actualizarArchivo();
                    }

                break;
            case"3":
                System.out.println("Ingrese el nombre de la Materia");
                String subject = entrada.nextLine();
                ArchPreguntas ap = new ArchPreguntas(subject);
                ap.leerArchivo();
                System.out.println("Que pregunta desea eliminar");
                int k=1;
                for(Pregunta p:ap.preguntas){
                    System.out.println(k+".- "+p);
                    k++;
                }
                int  eliminar= entrada.nextInt();
                ap.preguntas.remove((eliminar-1));
                ap.actualizarArchivo();}//switchAdmisitrar preguntar
    }

    public Materia getMateriaConNombre(String nombre){
        Materia obMat = null;
        for (Materia mtr : materias){
            if (mtr.getNombre().equals(nombre)){
                obMat = mtr;
            }
        }
        return obMat;
    }

   
    
    public boolean materiasA(ArrayList<Materia> mat, String nombreMat){
        ArrayList<Materia> materiatAct= new ArrayList<>();
        for(Materia mtr: mat){
            if (mtr.isActivo()){
                materiatAct.add(mtr);
            }
        }
        boolean bool = true;
        for(Materia m1:materiatAct){
            if (!(m1.getNombre().equals(nombreMat))){
                bool = false;
            }
        }
        return bool;


    }
    
    public String obtNombreConCod ( String codigo){
        String nombre = null;
        for(Materia m:materias){
            if (m.getCodigo().equals(codigo)){
                nombre= m.getNombre();
            }
        }
        return nombre;
    }
    public void actualizarArchivoReporte(String materia,String paralelo,String anio ,String numero) {//// OJO HACER ESTO
        FileWriter writer = null;
        try {
            String ruta = "src/archivos/reportes"+materia+"-"+paralelo+ "-"+anio+"-"+numero+"-R"+".csv"; //ruta del archivo que se va a leer
            writer = new FileWriter(ruta);
            for (Reporte est :reportes ) {
                writer.write(est.getFechaDeljuego()+ ";" + est.getParticipante().getNombre()+ ";" + est.getNiveMaximoAlcanzado()+";"+ est.getPremio()+System.lineSeparator());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private Estudiante getRandomEstudiantes(ArrayList<Estudiante> est) {
        Random posAle = new Random(System.currentTimeMillis());
        int numAleatorio = posAle.nextInt(est.size());
        
        return est.get(numAleatorio);
    }

    public void generarCsvReporte(Termino termino,Paralelo paralelo, String materia) throws IOException{
        String archivoCSV = "src/archivos/reportes/"+materia+"-"+paralelo.getParaleloCod()+ "-"+termino.getAnio()+"-"+termino.getNumero()+"-R"+".csv";
        Writer writer = Files.newBufferedWriter(Paths.get(archivoCSV));
    }

    public boolean verificacion (ArrayList<Pregunta> preguntas, int numPreguntas,Materia materia){
        ArrayList<String> niveles= new ArrayList<>();
        for (Pregunta pregun : preguntas){
            niveles.add(pregun.getNivel());
        }
        int cantniMateria = materia.getCantidadNiveles();
        ArrayList<Integer> frecuencia = new ArrayList<>();
        
        boolean condi = true;
        
        for (int cantidad=1; cantidad<=cantniMateria;cantidad++){
            String valor= cantidad+"";
            int frequency = Collections.frequency(niveles,valor);
            frecuencia.add(frequency);

            /*if (frequency < numPreguntas){
                bool = false;
            }*/
        }
        int min = Collections.min(frecuencia);

        if (min<numPreguntas){ 
            condi = false;
        }
        
        return condi;
    }

    public ArrayList<Integer> saltosPreguntas(ArrayList<Pregunta> preguntas, int numPreguntas, Materia materia){
        ArrayList<String> niveles= new ArrayList<>();
        for (Pregunta p : preguntas){
            niveles.add(p.getNivel());
        }
        int cantNivelMat = materia.getCantidadNiveles();
        ArrayList<Integer> saltos=new ArrayList<>();
        for (int i=0; i<cantNivelMat;i++){
            String j= i+"";
            int frequency = Collections.frequency(niveles,j);
            saltos.add(frequency-numPreguntas);
        }
        return saltos;
    }
    public String nombreporMatricula(ArrayList<Estudiante> est){
        System.out.println("Ingrese su matricula:");
        String matriculaest= entrada.nextLine();
        String nombEst = null;
        for (Estudiante e: est){
            if (e.getMatricula().equals(matriculaest)){
                nombEst = e.getNombre();
            }
        }
        return nombEst;
    }    
    public String nombreporAleatorio(ArrayList<Estudiante> est){
        Estudiante alestudiante = getRandomEstudiantes(est);
        String nomEstudiante= alestudiante.getNombre();
        return nomEstudiante;
        }
        
    }
//clase
    
        
   
