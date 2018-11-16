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

public class Main {
    
    public static void main(String[] args) throws Exception {
    
        ConnectionFactory connectionFactory = new ConnectionFactory();
        
        //Class.forName("org.postgresql.Driver");
        DriverManager.registerDriver(new org.postgresql.Driver());
        
        try {
            //connectionFactory.definirParametros("jdbc:postgresql://localhost:5432/EPBD", "escravo", "escravoDoEstudante");
            //Conector.redefinirConnectionFactory();
            
            EspecialidadePostgresql especialidadePostgresql = new EspecialidadePostgresql();
            
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
    
}
