
package quienquieresermillonario;
import Settings.BancoPregunta;
import Settings.Estudiante;
import Settings.Materia;
import Settings.Paralelo;
import Settings.Pregunta;
import Settings.Reporte;
import Settings.TerminoAcademico;
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
import java.util.Random;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("InitializerMayBeStatic")
public class QuienQuiereSerMillonario {
    Scanner entrada = new Scanner(System.in);
    private ArrayList<TerminoAcademico> terminos = new ArrayList<>();
    private TerminoAcademico terminoJuego;
    private ArrayList<Materia> materias = new ArrayList<>();
    private ArrayList<Paralelo> paralelos = new ArrayList<>();
    private ArrayList<Reporte> reportes =new ArrayList<>();

    public static void main(String[] args) {
        /*int numero = (int) ((Math.random() * 5) + 1);
        System.out.println(numero);*/

        QuienQuiereSerMillonario game = new QuienQuiereSerMillonario();
        game.menu();

    }//main
//holaaaa


    public void menu(){
        String i= "a";
        while (i!="4"){
           System.out.println("SELECCIONE UNA OPCION PARA INICIAR\n1. Configuraciones\n" +
           "2. Nuevo juego\n" +
           "3. Reporte\n" +
           "4. Salir");
           Paralelo paraleloAjugar =null;
           String opcion= entrada.nextLine();
            switch (opcion){
            //CONFIGURACIONES
            case "1":
                configuraciones();
                break;//CASE1 PRINCIPAL
            //NUEVO JUEGO
            case "2":
                if(terminoJuego != null){
                    System.out.println("JUEGO NUEVO");
                    //Materia
                    System.out.println("Ingrese Materia");
                    String nombMateria= entrada.nextLine();
                    BancoPregunta aprob = new BancoPregunta(nombMateria);
                    aprob.leerArchivo();


                    //Paralelo
                    System.out.println("Ingrese Paralelo");
                    String paralelo= entrada.nextLine();
                    ArrayList<Estudiante> est = new ArrayList<>();
                    for (Paralelo p:paralelos){
                       
                        if (p.getParaleloCod().equals(paralelo)){
                            paraleloAjugar=p;
                            p.leerArchivoEstudiantes();
                            for(Estudiante student :p.getEstudiantes()){
                                est.add(student);
                            }
                        }
                    }
                    //Preguntas por nivel
                    
                    Materia mat = getMateriaConNombre(nombMateria);
                    String entry = "a";
                    
                    
                    
                    int numeroNivel=0;
                    while(entry == "a"){
                        System.out.println("Ingrese el numero de preguntas por nivel");
                        numeroNivel= entrada.nextInt();
                        entrada.nextLine();
                        if(verificacion(aprob.preguntas,numeroNivel,mat)){
                            entry="b";
                        }
                        else{
                            System.out.println("No hay suficientes preguntas en algunos niveles");

                        }
                    }
                    
                    ArrayList<Integer> saltosPreg= saltosPreguntas(aprob.preguntas,numeroNivel, mat);
                    ArrayList<Pregunta> PregSegunCantNivel = new ArrayList<>();
                    
                    ArrayList<Pregunta> copia = new ArrayList<>();
                    copia= (ArrayList)aprob.preguntas.clone();
                    for (int j:saltosPreg){
                        for (int k =0; k<numeroNivel;k++){
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
                            System.out.println("Ingrese su matricula:");
                            String matriculaEstudiante= entrada.nextLine();
                            String nombEst = null;
                            for (Estudiante e: est){
                                if (e.getMatricula().equals(matriculaEstudiante)){
                                    nombEst = e.getNombre();
                                }
                            }
                            participante = nombEst;
                            break;
                        case "2":
                            //Eleccion del participante aleatoreamente
                           Estudiante  participar = getRandomEstudiantes(est);
                           String nombreEstudiante=participar.getNombre();
                           participante = nombreEstudiante;
                            System.out.println("El estudiante elegido es: "+participante);
                            break;

                        default:
                            System.out.println("Ingrese una opcion correcta");
                    }//switch participante
                    



                //SALE E INICIA EL JUEGO
                    System.out.println("QUIEN QUIERE SER MILLONARIO ESTUDIANTIL");
                    System.out.println("Bienvenido "+ participante+"!!");
                    System.out.println("Presiona una tecla para continuar");
                    String nextXt=entrada.nextLine();
                    aprob.leerArchivo();
                    ArrayList<String> comodines = new ArrayList<>();
                    for (Comodin com: Comodin.values()){
                        comodines.add(com.name());
                    }
                    int puntos=0;
                    int salir = 0;
                    
                    while (salir!=1){
                        for(Pregunta mostrar:aprob.preguntas){
                            //ArrayList<String> respCorrecta = new ArrayList<>();
                            //respCorrecta.add(mostrar.getRepuesta_correcta());
                            /*
                            ArrayList<String> respuestas = new ArrayList<>();
                            respuestas.add(mostrar.getPosible_respuesta1());
                            respuestas.add(mostrar.getPosible_respuesta2());
                            respuestas.add(mostrar.getPosible_respuesta4());
                            respuestas.add(mostrar.getRepuesta_correcta());
                            
                            */
                            ArrayList<String> letras =new ArrayList<>();
                            letras.add("A");
                            letras.add("B");
                            letras.add("C");
                            letras.add("D");
                            
                            ArrayList<String> respIncorrectas = new ArrayList<>();
                            respIncorrectas.add(mostrar.getPosible_respuesta1());
                            respIncorrectas.add(mostrar.getPosible_respuesta2());
                            respIncorrectas.add(mostrar.getPosible_respuesta4());
                            
                            ArrayList<String> respuestas = new ArrayList<>();
                            for(String rIn:respIncorrectas){
                                respuestas.add(rIn);
                            }
                            respuestas.add(mostrar.getRepuesta_correcta());
                            Collections.shuffle(respuestas);
                            
                            int index=respuestas.indexOf(mostrar.getRepuesta_correcta());
                            String letraCorrecta= letras.get(index);
                            
                            System.out.println(mostrar.getEnunciado());
                            for(int p=0;p<letras.size();p++){
                                System.out.println(letras.get(p)+".- "+respuestas.get(p));
                            }
                            
                            /*
                            System.out.println("P: "+mostrar.getEnunciado());
                            System.out.println("A.-"+respuestas.get(0));
                            System.out.println("B.-"+respuestas.get(1));
                            System.out.println("C.-"+respuestas.get(2));
                            System.out.println("D.-"+respuestas.get(3));
                            System.out.println("Ingrese la letra de la respuesta elegida");
*/
                            String repuestaElegida= entrada.nextLine();
                            if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                System.out.println("Perdiste");
                                System.out.println("FIN DEL JUEGO");
                                salir=1;
                                i="4";
                                break;
                                        
                                

                                }
                            int n =0;
                            while (n!=4){
                            
                            
                            if(repuestaElegida.equals("*")){
                                System.out.println("Comodines: ");
                                int k=0;
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
                                        int numAleatorio1 = aleatorio.nextInt(respIncorrectas.size()-1);
                                        respIncorrectas.remove(numAleatorio1);
                                        respIncorrectas.remove((numAleatorio1)-1);
                                        letras.remove("C");
                                        letras.remove("D");
                                        break;
                                        
                                    case 2:
                                        System.out.println("Ha usado el comodin CONSULTA AL COMPAÑERO");
                                        break;
                                    
                                    case 3:
                                        System.out.println("Ha usado el comodin CONSULTA AL SALON");
                                }
                            }
                            else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                System.out.println("FELICITACIONES! HAZ CONTESTADO CORRECTAMENTE");
                                System.out.println("SIGUIENTE PREGUNTA:");
                                n=4;
                                puntos++;
                                
                            }
                            int nivelAlcanzado =(int) Math.floor(puntos/numeroNivel);
                        //
                            try {
                                  //MOSRTAR INGFORMACION AQUI
                                  generarCsvReporte( terminoJuego,paraleloAjugar ,mat.getCodigo());
                                } catch (IOException ex) {
                                  Logger.getLogger(QuienQuiereSerMillonario.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                          
                          //
                            }
                
                        }
                    }
                
                break;
                
                    //CASE 2 PRINCIPAL//CASE 2 PRINCIPAL
                }
                
                else{
                    System.out.println("Debe configurar el termino");
                    
                }
                    
            //REPORTE
                break;
            case "3":
                
                menuReporte();

                break; //CASE 3 PRINCIPAL

     //SALIR
            case "4":
                i="4";
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
        TerminoAcademico termino = new TerminoAcademico(anioTAcademicoReport,numTAcademicoReport);
        Paralelo paral = new Paralelo( paraleloReport, codigoMateria, termino);


       try {
           //MOSRTAR INGFORMACION AQUI
           generarCsvReporte( termino,paral ,codigoMateria);
       } catch (IOException ex) {
           Logger.getLogger(QuienQuiereSerMillonario.class.getName()).log(Level.SEVERE, null, ex);
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
                if (materiasActivas(materias,materiaIngresada)){
                    System.out.println("Ingrese el año del Termino Academico");
                    String anioTerm= entrada.nextLine();

                    System.out.println("Ingrese el numero del Termino Academico");
                    String numTerm= entrada.nextLine();


                    System.out.println("Ingrese Numero de paralelo");
                    String numeroParalelo= entrada.nextLine();
                    paralelos.add(new Paralelo(numeroParalelo,mat,new TerminoAcademico(anioTerm,numTerm)));
                }


                break;//case4MATERIAS Y PARALELO
            case "5":
                //MOSTRAR PARALELOS EXISTENTES
                ArrayList<Paralelo> parElim = new ArrayList<>();
                for (Paralelo p: paralelos){
                    int i=1;
                    if(materiasActivas(materias,p.getMateria().getNombre())){
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
                TerminoAcademico term = new TerminoAcademico(anio,numeroTermino);
                terminos.add(term);
                break;
            case "2":
                int i=1;
                System.out.println("Los terminos existentes son: ");
                for (TerminoAcademico t:terminos){
                    System.out.println(i+". "+ t);
                    ++i;
                }
                System.out.println("Ingrese el numero del termino a eliminar");
                int termElim = entrada.nextInt();
                entrada.nextLine();
                terminos.remove(termElim-1);
            //ELIMINAR TERMINO
                break;
            case "3":
                int j=1;
                 for (TerminoAcademico t:terminos){
                    System.out.println(j+". "+ t);
                    ++j;}
                int elegir = entrada.nextInt();
                entrada.nextLine();
                terminoJuego=terminos.get(elegir-1);

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
        
        TerminoAcademico termEst = new TerminoAcademico(anioTerm,numTerm);
        Paralelo paralelEst = new Paralelo(paralelo,materia,termEst);
        paralelEst.leerArchivoEstudiantes();
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
                String codigoMateria= entrada.nextLine();
                String nombre = obtNombreConCod(codigoMateria);
                if (materiasActivas(materias,nombre)){
                    BancoPregunta ej = new BancoPregunta(nombre);
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
                if(materiasActivas(materias,nombreMat)){
                    BancoPregunta ej = new BancoPregunta(nombreMat);
                    ej.leerArchivo();

                    System.out.println("Ingrese Enunciado de la pregunta");
                    String enunciado= entrada.nextLine();

                    System.out.println("Ingrese Nivel");
                    String nivel= entrada.nextLine();

                    System.out.println("Ingrese la  respuesta CORRECTA");
                    String respuestaCorrecta= entrada.nextLine();

                    System.out.println("Ingrese Posible Respuesta 1");
                    String respuesta1= entrada.nextLine();

                    System.out.println("Ingrese Posible Respuesta 2");
                    String respuesta2= entrada.nextLine();

                    System.out.println("Ingrese Posible Respuesta 3");
                    String respuesta3= entrada.nextLine();

                    Pregunta preguntaAgg= new Pregunta(enunciado, nivel , respuestaCorrecta, respuesta1, respuesta2, respuesta3);
                    ej.preguntas.add(preguntaAgg);
                    //ALMACENAR PREGUNTAS EN CSV NOMBRE CODIGO DE LA MATERIA
                    ej.actualizarArchivo();
                    }

                break;
            case"3":
                System.out.println("Ingrese el nombre de la Materia");
                String subject = entrada.nextLine();
                BancoPregunta ej = new BancoPregunta(subject);
                ej.leerArchivo();
                System.out.println("Que pregunta desea eliminar");
                int k=1;
                for(Pregunta p:ej.preguntas){
                    System.out.println(k+".- "+p);
                    k++;
                }
                int  eliminar= entrada.nextInt();
                ej.preguntas.remove((eliminar-1));
                ej.actualizarArchivo();}//switchAdmisitrar preguntar
    }

    public Materia getMateriaConNombre(String nombre){
        Materia mat = null;
        for (Materia m : materias){
            if (m.getNombre().equals(nombre)){
                mat = m;
            }
        }
        return mat;
    }

   
    
    public boolean materiasActivas(ArrayList<Materia> mat, String nombreMat){
        ArrayList<Materia> matAct= new ArrayList<>();
        for(Materia m: mat){
            if (m.isActivo()){
                matAct.add(m);
            }
        }
        boolean bool = true;
        for(Materia m1:matAct){
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
            String ruta = "src/archivos/"+materia+"-"+paralelo+ "-"+anio+"-"+numero+"-R"+".csv"; //ruta del archivo que se va a leer
            writer = new FileWriter(ruta);
            for (Reporte est :reportes ) {
                writer.write(est.getFechaDeljuego()+ ";" + est.getParticipante().getNombre()+ ";" + est.getNiveMaximoAlcanzado()+";"+ est.getPremio()+System.lineSeparator());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(BancoPregunta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(BancoPregunta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private Estudiante getRandomEstudiantes(ArrayList<Estudiante> est) {
        Random aleatorio = new Random(System.currentTimeMillis());
        int numAleatorio = aleatorio.nextInt(est.size());
        
        return est.get(numAleatorio);
    }

    public void generarCsvReporte(TerminoAcademico termino,Paralelo paralelo, String materia) throws IOException{
        String archivoCSV = "src/archivos/"+materia+"-"+paralelo.getParaleloCod()+ "-"+termino.getAnio()+"-"+termino.getNumero()+"-R"+".csv";
        Writer writer = Files.newBufferedWriter(Paths.get(archivoCSV));
    }

    public boolean verificacion (ArrayList<Pregunta> preguntas, int numPreguntas,Materia materia){
        ArrayList<String> niveles= new ArrayList<>();
        for (Pregunta p : preguntas){
            niveles.add(p.getNivel());
        }
        int cantNivelMat = materia.getCantidadNiveles();
        ArrayList<Integer> frecuencia = new ArrayList<>();
        
        boolean bool = true;
        
        for (int i=1; i<=cantNivelMat;i++){
            String j= i+"";
            int frequency = Collections.frequency(niveles,j);
            frecuencia.add(frequency);

            /*if (frequency < numPreguntas){
                bool = false;
            }*/
        }
        int min = Collections.min(frecuencia);

        if (min<numPreguntas){ 
            bool = false;
        }
        
        return bool;
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

}//clase
