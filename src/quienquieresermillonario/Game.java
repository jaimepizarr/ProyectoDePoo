
package quienquieresermillonario;
import clasesArch.ArchEstudiantes;
import clasesArch.ArchPreguntas;
import Entidades.Estudiante;
import Entidades.Materia;
import Entidades.Paralelo;
import Entidades.Pregunta;
import Entidades.Reporte;
import Entidades.Termino;
import Utileria.Comodin;
import clasesArch.ArchReporte;
import java.io.File;
import Data.*;
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
public class Game {
    Scanner entrada = new Scanner(System.in);

    MateriaParaleloDat matpardat= new MateriaParaleloDat();
    TerminosDat termdat = new TerminosDat(); 
    PreguntasDat pregDat = new PreguntasDat();
    EstudiantesDat estDat = new EstudiantesDat();


    private ArrayList<Termino> terminos_existentes = new ArrayList<>();
    private Termino termino;
    private ArrayList<Materia> materiasJuego = new ArrayList<>();
    private ArrayList<Paralelo> paralelosJuego = new ArrayList<>();
    private ArrayList<Reporte> reportes =new ArrayList<>();
    private Paralelo paraleloJuego =null;
    private String premio = "NADA";

    public static void main(String[] args) {
        /*int numero = (int) ((Math.random() * 5) + 1);
        System.out.println(numero);*/

        Game game = new Game();
        game.menu();

    }//main
    

    public void menu(){
        String entryMP= "a";
        while (entryMP!="4"){
           System.out.println("SELECCIONE UNA OPCION PARA INICIAR\n"+
            "1. Configuraciones\n" +
           "2. Nuevo juego\n" +
           "3. Reporte\n" +
           "4. Salir");
           String opcionMP= entrada.nextLine();
            switch (opcionMP){
            case "1":
                configuraciones();
                break;
            case "2":
                if(termino != null){
                    System.out.println("JUEGO NUEVO");
                    //Materia
                    System.out.println("Ingrese Materia");
                    String nombreMateria= entrada.nextLine();
                    
                    
                    ArchPreguntas archPreg = new ArchPreguntas(nombreMateria);
                    archPreg.leerArchivo();


                    //Paralelo
                    System.out.println("Ingrese Paralelo");
                    String paralelo= entrada.nextLine();
                    
                    ArrayList<Estudiante> estudiantes = new ArrayList<>();
                    for (Paralelo par:matpardat.getParalelosJuego()){
                       
                        if (par.getParaleloCod().equals(paralelo)){
                            paraleloJuego=par;
                            ArchEstudiantes arEst= new ArchEstudiantes(par);
                            arEst.leerArchivoEstudiantes();
                            for(Estudiante student :par.getEstudiantes()){
                                estudiantes.add(student);
                            }
                        }
                    }
                    //Preguntas por nivel
                    Materia matElegida = matpardat.getMateriaConNombre(nombreMateria);                    
                    boolean entry = true;
                    
                    int cantPxNivel=0;
                    while(entry){
                        System.out.println("Ingrese el numero de preguntas por nivel que desea: ");
                        cantPxNivel= entrada.nextInt();
                        entrada.nextLine();
                        if(verifyPregPorNivel(archPreg.preguntas,cantPxNivel,matElegida)){
                            entry=false;
                        }
                        else{
                            System.out.println("Hay niveles que no contienen la cantidad de preguntas que pidió");

                        }
                    }
                    
                    ArrayList<Integer> cantPregExtras= listCantPregExtras(archPreg.preguntas,cantPxNivel, matElegida);
                    
                    ArrayList<Pregunta> pregSegunCantNivel=pregToUse(archPreg.preguntas,cantPregExtras,cantPxNivel);
                    
                    //ELECCION DEL PARTICIPANTE
                    
                    String participante = estDat.chooseParticipante(estudiantes);
                    

                //INICIO DEL JUEGO
                
                    System.out.println("¿QUIEN QUIERE SER MILLONARIO? ESTUDIANTIL");
                    System.out.println("Bienvenido "+ participante+"!!");
                    System.out.println("Presiona una tecla para continuar");
                    entrada.nextLine();
                    
                    ArrayList<String> comodines = new ArrayList<>();
                    for (Comodin com: Comodin.values()){
                        comodines.add(com.name());
                        System.out.println(com.name());
                    }
                    int puntos=0;
                    
                    int n = 0;

                    while (n<pregSegunCantNivel.size()){
                        Pregunta mostrar = pregSegunCantNivel.get(n);
                        ArrayList<String> letrasResp =new ArrayList<>();
                        letrasResp.add("A");
                        letrasResp.add("B");
                        letrasResp.add("C");
                        letrasResp.add("D");

                        ArrayList<String> respIncorrectas = new ArrayList<>();
                        respIncorrectas.add(mostrar.getRespInc1());
                        respIncorrectas.add(mostrar.getRespInc2());
                        respIncorrectas.add(mostrar.getRespInc3());

                        ArrayList<String> respuestas = new ArrayList<>();
                        for(String rIn:respIncorrectas){
                                respuestas.add(rIn);
                        }
                        respuestas.add(mostrar.getrCorrecta());
                        
                        Collections.shuffle(respuestas); //Se baraja la lista respuesta

                        int rightIndex=respuestas.indexOf(mostrar.getrCorrecta());
                        String letraCorrecta= letrasResp.get(rightIndex);

                        System.out.println(mostrar.getEnunciado());
                        for(int p=0;p<letrasResp.size();p++){
                                System.out.println(letrasResp.get(p)+".- "+respuestas.get(p));
                        }		

                        String repuestaElegida= entrada.nextLine();
                        if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                System.out.println("TE HAS EQUIVOCADO");
                                System.out.println("FIN DEL JUEGO");
                                n=pregSegunCantNivel.size();

                                break;
                        }    
                        else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                System.out.println("FELICITACIONES! HAS ELEGIDO LA RESPUESTA CORRECTA");
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


                                    int numAleatorio2 = aleatorio.nextInt(respIncorrectas.size());

                                    String incorrecta1 = respIncorrectas.get(numAleatorio1);
                                    String incorrecta2 = respIncorrectas.get(numAleatorio2);
                                    System.out.println("La opcion: " +incorrecta1 + " es incorrecta");
                                    System.out.println("La opcion: " +incorrecta2 + " es tambien incorrecta");


                                    break;

                            case "CONSULTA_COMPAÑERO":
                                    System.out.println("Puede elegir a un compañero a quien preguntar.");
                                    break;

                            case "CONSULTA_SALON":
                                    System.out.println("Puede consultar la pregunta a todo el salón.");
                            } 
                            System.out.println("Cual es la respuesta correcta?");
                            repuestaElegida= entrada.nextLine();
                            if((!repuestaElegida.toUpperCase().equals(letraCorrecta)) &&(!repuestaElegida.equals("*")) ){
                                    System.out.println("TE HAS EQUIVOCADO");
                                    System.out.println("FIN DEL JUEGO");
                                    n=pregSegunCantNivel.size();
                                    break;
                            }

                            else if(repuestaElegida.toUpperCase().equals(letraCorrecta)){
                                    System.out.println("FELICITACIONES! HAS ELEGIDO LA RESPUESTA CORRECTA");
                                    
                                    puntos++;
                                    n++;
                            }                                    
                        }
                    }
                    int nivelAlcanzado =(int)(puntos/cantPxNivel);
                    if (nivelAlcanzado ==0){
                        System.out.println("DEBES MEJORAR, ESTUDIA MAS");
                    }
                    else if (nivelAlcanzado == 3){
                        System.out.println("EXCELENTE! HAS RESPONDIDO TODO BIEN");
                    }
                    else{
                        System.out.println("BUEN TRABAJO! LLEGASTE AL NIVEL "+ nivelAlcanzado);
                    }
                    String nivel = Integer.toString(nivelAlcanzado);
                    Date hoy = new Date();
                    if (nivelAlcanzado >= 1){
                        System.out.println("Ingrese el premio del participante");
                        premio = entrada.nextLine();

                    }

                    Reporte repJuego = new Reporte (hoy.toString(),nivel,premio,participante);
                    reportes.add(repJuego);
                    entryMP="4";                    

                    String ruta = "src/archivos/reportes/"+matElegida.getCodigo()+"-"+paralelo+ "-"+termino.getAnio()+"-"+termino.getNumero()+"-R"+".csv";
                    File reporteCsv = new File(ruta);
                    ArchReporte rep = new ArchReporte();

                    if (!reporteCsv.exists()){
                        try {
                              rep.generarCsvReporte(ruta);
                            } catch (IOException ex) {
                              Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        rep.reportes.add(repJuego);
                        rep.actualizarArchivoReporte(ruta);
                    }
                    else{
                        rep.leerArchivo(ruta);
                        rep.reportes.add(repJuego);
                        rep.actualizarArchivoReporte(ruta);
                    }
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

        String anioTerm= entrada.nextLine();
        System.out.println("Numero Termino Academico");
        String numTerm = entrada.nextLine();
       //Codigo materia
        System.out.println("Ingrese Codigo de Materia");
        String codigoMateria= entrada.nextLine();


        //Paralelo
        System.out.println("Ingrese Paralelo");
        String numParalelo= entrada.nextLine();
        String ruta = "src/archivos/reportes/"+codigoMateria+"-"+numParalelo+ "-"+anioTerm+"-"+numTerm+"-R"+".csv";

        ArchReporte reports = new ArchReporte();
        reports.leerArchivo(ruta);
        for (Reporte r: reports.reportes){
            System.out.println(r);
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
        String opcionMat= entrada.nextLine();
        switch (opcionMat){
            case "1":
                matpardat.ingresarMateria();
                /*System.out.println("Ingrese Nombre Materia");
                String nombreMat= entrada.nextLine();

                System.out.println("Ingrese Codigo Materia");
                String codigoMateria= entrada.nextLine();

                System.out.println("Cantidad de niveles");
                int cantidadNiveles= entrada.nextInt();
                entrada.nextLine();
                
                Materia materia = new Materia(nombreMat,codigoMateria,cantidadNiveles);
                materiasJuego.add(materia);                /*System.out.println("Ingrese Nombre Materia");
                String nombreMat= entrada.nextLine();

                System.out.println("Ingrese Codigo Materia");
                String codigoMateria= entrada.nextLine();

                System.out.println("Cantidad de niveles");
                int cantidadNiveles= entrada.nextInt();
                entrada.nextLine();
                
                Materia materia = new Materia(nombreMat,codigoMateria,cantidadNiveles);
                materiasJuego.add(materia);*/
                break;

            case "2":
                matpardat.editarMateria();
                /*System.out.println("Ingrese Codigo Materia");
                String codMateria= entrada.nextLine();
                int pos = 0;
                for (int i =0; i<materiasJuego.size();++i){
                    if (materiasJuego.get(i).getCodigo().equals(codMateria)){
                        pos=i;
                    }
                }
                String cambio = "S";
                System.out.println("¿Desea cambiar el nombre de la materia? \nS/N");
                while (cambio == "S"){
                    System.out.println("Ingrese el nuevo nombre");
                    String nombre_nuevo = entrada.nextLine();
                    materiasJuego.get(pos).setNombre(nombre_nuevo);
                }
                String cambio2="Y";
                System.out.println("¿Desea cambiar la cantidad de niveles de la materia? \n S/N");
                while (cambio2 == "S"){
                    System.out.println("Ingrese la nueva cantidad de niveles");
                    int niveles = entrada.nextInt();
                    entrada.nextLine();
                    materiasJuego.get(pos).setCantidadNiveles(niveles);
                }*/
                break;
            case "3":
                matpardat.desactivarMateria();
                /*
                System.out.println("Ingrese Codigo Materia");
                String codMatDesac= entrada.nextLine();
                System.out.println("¿Está seguro de desactivar esta materia?\n  S/N");
                String confirmacion= entrada.nextLine();
                switch (confirmacion){
                    case "S":
                        for (Materia mat : materiasJuego){
                            if (mat.getCodigo().equals(codMatDesac)){
                                mat.setActivo(false);
                            }
                        }
                        break;
                    case "N":
                        break; 
                }*/
                break;

            case "4":
                matpardat.agregarParalelo();
                /*
                System.out.println("Ingrese Materia");
                String nombMat= entrada.nextLine();
                Materia matForPar = getMateriaConNombre(nombMat);
                if (materiasAct(materiasJuego,nombMat)){
                    System.out.println("Ingrese el año del Termino Academico");
                    String anioTerm= entrada.nextLine();

                    System.out.println("Ingrese el numero del Termino Academico");
                    String numTerm= entrada.nextLine();


                    System.out.println("Ingrese Numero de paralelo");
                    String paralelo= entrada.nextLine();
                    
                    Paralelo parJuego = new Paralelo(paralelo,matForPar,new Termino(anioTerm,numTerm));
                    paralelosJuego.add(parJuego);
                }*/


                break;//case4MATERIAS Y PARALELO
            case "5":
                //MOSTRAR PARALELOS EXISTENTES
                matpardat.eliminarParalelo();
                /*ArrayList<Paralelo> parElim = new ArrayList<>();
                for (Paralelo p: paralelosJuego){
                    int i=1;
                    if(materiasAct(materiasJuego,p.getMateria().getNombre())){
                        System.out.println(i+".- "+p);
                        i++;
                        parElim.add(p);
                    }
                }
                System.out.println("Seleccionar paralelo a eliminar");
                int seleccion=entrada.nextInt();
                entrada.nextLine();
                paralelosJuego.remove(parElim.get(seleccion - 1));*/
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
                termdat.ingresarTermino();
                //SE INGRESA TERMINO
                //anio
                /*System.out.println("Ingresar anio: ");
                String anio= entrada.nextLine();
                //numero de termino
                System.out.println("Ingresar numero de termino");
                String numeroTermino= entrada.nextLine();
                Termino term = new Termino(anio,numeroTermino);
                terminos_existentes.add(term);*/
                break;
            case "2":
                termdat.eliminarTermino();
                /*int i=1;
                System.out.println("Los terminos existentes son: ");
                for (Termino t:terminos_existentes){
                    System.out.println(i+". "+ t);
                    ++i;
                }
                System.out.println("Ingrese el numero del termino a eliminar");
                int termElim = entrada.nextInt();
                entrada.nextLine();
                terminos_existentes.remove(termElim-1);*/
            //ELIMINAR TERMINO
                break;
            case "3":
                this.termino= termdat.configurarTerminoJuego();
                /*int j=1;
                 for (Termino t:terminos_existentes){
                    System.out.println(j+". "+ t);
                    ++j;}
                int elegir = entrada.nextInt();
                entrada.nextLine();
                this.termino=terminos_existentes.get(elegir-1);
*/
                 break;
            default:
                 System.out.println("Ingrese una opcion correcta");}//switch terminos academicos
    }
    public void adminEstudiantes(){
        EstudiantesDat.adminEstudiantes();
        /*System.out.println("Administrar estudiantes");
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
        }*/
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
                String nombre = matpardat.obtNombreConCod(codMateria);
                if (matpardat.materiasAct(matpardat.getMateriasJuego(),nombre)){
                    pregDat.visualizarPreguntas(matpardat.getMateriasJuego(),nombre);}
                /*System.out.println("Ingrese Codigo Materia");
                String codMateria= entrada.nextLine();
                String nombre = obtNombreConCod(codMateria);
                if (materiasAct(materiasJuego,nombre)){
                    ArchPreguntas ej = new ArchPreguntas(nombre);
                    ej.leerArchivo();
                    Collections.sort(ej.preguntas);
                    int indexP=1;
                    for(Pregunta mostrar:ej.preguntas){
                              System.out.println(indexP + " " +mostrar);
                              indexP+=1;
                    }
                }*/

                break;

            case"2":
                pregDat.agregPregunta();
                /*System.out.println("Ingrese Codigo Materia");
                String materiaAgregar= entrada.nextLine();
                String nombreMat = obtNombreConCod(materiaAgregar);
                if(materiasAct(materiasJuego,nombreMat)){
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
                    }*/

                break;
            case"3":
                /*System.out.println("Ingrese el nombre de la Materia");
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
                */
                pregDat.eliminarPreg();
                break;
        }
    }

    public Materia getMateriaConNombre(String nombre){
        Materia obMat = null;
        for (Materia mtr : materiasJuego){
            if (mtr.getNombre().equals(nombre)){
                obMat = mtr;
            }
        }
        return obMat;
    }

   
    
    public boolean materiasAct(ArrayList<Materia> mat, String nombreMat){
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
        for(Materia m:materiasJuego){
            if (m.getCodigo().equals(codigo)){
                nombre= m.getNombre();
            }
        }
        return nombre;
    }

    private Estudiante getRandomEstudiantes(ArrayList<Estudiante> est) {
        Random posAle = new Random(System.currentTimeMillis());
        int numAleatorio = posAle.nextInt(est.size());
        
        return est.get(numAleatorio);
    }

    public boolean verifyPregPorNivel (ArrayList<Pregunta> preguntas, int numPreguntas,Materia materia){
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

    public ArrayList<Integer> listCantPregExtras(ArrayList<Pregunta> preguntas, int numPreguntas, Materia materia){
        ArrayList<String> repeticioNiveles= new ArrayList<>();
        for (Pregunta p : preguntas){
            repeticioNiveles.add(p.getNivel());
        }
        int cantNivelMat = materia.getCantidadNiveles();
        ArrayList<Integer> extras=new ArrayList<>();
        for (int i=0; i<cantNivelMat;i++){
            String j= ""+i;
            int frecNiveles = Collections.frequency(repeticioNiveles,j);
            extras.add(frecNiveles-numPreguntas);
        }
        return extras;
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
    
    public String chooseParticipante (ArrayList<Estudiante> estudiantes){
        System.out.println("PARTICIPANTE: \n1.Ingresar matricula.\n2.Elegir Aleatoreamente");
        String opcionParticipante= entrada.nextLine();
        String participante= null;
        switch (opcionParticipante){
            case "1":
                //matricula del participante
                participante = nombreporMatricula(estudiantes);
                break;
            case "2":
                //Eleccion del participante aleatoreamente
               participante = nombreporAleatorio(estudiantes);
                System.out.println("El estudiante elegido es: "+participante);
                break;

            default:
                System.out.println("Ingrese una opcion correcta");
        }//switch participante
        return participante;
    }
    
    public ArrayList<Pregunta> pregToUse(ArrayList<Pregunta> pregTotales, ArrayList<Integer> cantPregExtras,int cantPxNivel){
        ArrayList<Pregunta> PregSegunCantNivel = new ArrayList<>();

        ArrayList<Pregunta> copia = new ArrayList<>();
        copia= (ArrayList)pregTotales.clone();
        for (int j:cantPregExtras){
            for (int k =0; k<cantPxNivel;k++){
                    PregSegunCantNivel.add(copia.get(k));
                    copia.remove(k);
            }
            for (int l=0;l<j;l++){
                copia.remove(l);
            }

        }
        return PregSegunCantNivel;
    }
    }
//clase
    
        
   
