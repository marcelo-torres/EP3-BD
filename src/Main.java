/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */

import banco_de_dados.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;

import banco_de_dados.*;
import banco_de_dados.postgresql.*;
import dados_da_clinica.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws Exception {
    
        ConnectionFactory connectionFactory = new ConnectionFactory();
        
        //Class.forName("org.postgresql.Driver");
        DriverManager.registerDriver(new org.postgresql.Driver());
        
        try {
            contarLinhasDeCodigo();
            
            //connectionFactory.definirParametros("jdbc:postgresql://localhost:5432/EPBD", "escravo", "escravoDoEstudante");
            //Conector.redefinirConnectionFactory();
            
            EspecialidadeDAOPostgresql especialidadePostgresql = new EspecialidadeDAOPostgresql();
            
            especialidadePostgresql.remover(10);
            especialidadePostgresql.remover(11);
            especialidadePostgresql.remover(12);
            especialidadePostgresql.remover(13);
            
            Especialidade especialidade1 = especialidadePostgresql.criar(10, 5, "BernAdo");
            Especialidade especialidade2 = especialidadePostgresql.criar(11, 5, "Alan");
            Especialidade especialidade3 = especialidadePostgresql.criar(12, 8, "Adobe");
            Especialidade especialidade4 = especialidadePostgresql.criar(13, 5, "Adolfo");
           
            
            System.out.println(especialidadePostgresql.buscarPeloNome("JASHD"));
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void contarLinhasDeCodigo() throws FileNotFoundException {

    final String folderPath = "/home/marcelo/NetBeansProjects/ClinicaMedica/src";

    long totalLineCount = 0;
    final List<File> folderList = new LinkedList<>();
    folderList.add(new File(folderPath));
    while (!folderList.isEmpty()) {
        final File folder = folderList.remove(0);
        if (folder.isDirectory() && folder.exists()) {
            System.out.println("Scanning " + folder.getName());
            final File[] fileList = folder.listFiles();
            for (final File file : fileList) {
                if (file.isDirectory()) {
                    folderList.add(file);
                } else if (file.getName().endsWith(".java")
                        || file.getName().endsWith(".sql")) {
                    long lineCount = 0;
                    final Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        scanner.nextLine();
                        lineCount++;
                    }
                    totalLineCount += lineCount;
                    final String lineCountString;
                    if (lineCount > 99999) {
                        lineCountString = "" + lineCount;
                    } else {
                        final String temp = ("     " + lineCount);
                        lineCountString = temp.substring(temp.length() - 5);
                    }
                    System.out.println(lineCountString + " lines in " + file.getName());
                }
            }
        }
    }
    System.out.println("Scan Complete: " + totalLineCount + " lines total");
}
    
}
