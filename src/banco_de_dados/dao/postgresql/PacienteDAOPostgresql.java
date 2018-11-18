package banco_de_dados.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.paciente.CPF;
import pessoas.paciente.Paciente;
import pessoas.paciente.Sexo;
import banco_de_dados.dao.PacienteDAO;


public class PacienteDAOPostgresql extends ConectorDAOPostgresql implements PacienteDAO {

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "Paciente";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    @Override
    public Paciente criar(int codigo, CPF cpf, String nome, Telefone telefone, String endereco, Integer idade, Sexo sexo) throws Exception {
        
        Paciente paciente = new Paciente(codigo, cpf, nome, telefone, endereco, idade, sexo);
        //Connection conexao = Conector.obterConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.setString(2, cpf.getCPFSemMarcara());
            statement.setString(3, nome);
            statement.setString(4, telefone.getTelefone());
            statement.setString(5, endereco);
            statement.setInt(6, idade);
            statement.setString(7, sexo.toString());

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel inserir o paciente no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return paciente;
        }   
    }

    @Override
    public void gravar(Paciente paciente) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET cpf = ?, nome = ?, "
                + "telefone = ?, endereco = ?, idade = ?, sexo = ? WHERE "+NOME_COMPLETO+".codigo = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, paciente.getCPF().getCPFSemMarcara());
            statement.setString(2, paciente.getNome());
            statement.setString(3, paciente.getTelefone().toString());
            statement.setString(4, paciente.getEndereco());
            statement.setInt(5, paciente.getIdade());
            statement.setString(6, paciente.getSexo().toString());
            statement.setInt(7, paciente.getCodigo());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel gravar o paciente no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
        }
    }

    @Override
    public void remover(int codigo) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE codigo = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, codigo);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel remover o paciente do banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
        }
        
    }

    @Override
    public Paciente buscarPeloCodigo(int codigo) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        Paciente paciente = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE codigo = " + codigo;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int codigoEncontrado = resultSet.getInt("codigo");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String idade = resultSet.getString("idade");
                String sexo = resultSet.getString("sexo");
                
                paciente = new Paciente(codigoEncontrado, new CPF(cpf), nome, 
                        new Telefone(telefone), endereco, new Integer(idade), 
                        Sexo.obterValor(sexo));
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar este paciente no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return paciente;
        }
    }

    @Override
    public Paciente buscarPeloCpf(CPF cpf) throws Exception {
        //Connection conexao = Conector.obterConexao();
        
        Paciente paciente = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE cpf LIKE '" + cpf.getCPFSemMarcara() + "'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String cpfEncontrado = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String idade = resultSet.getString("idade");
                String sexo = resultSet.getString("sexo");
                
                paciente = new Paciente(codigo, new CPF(cpfEncontrado), nome, 
                        new Telefone(telefone), endereco, new Integer(idade), 
                        Sexo.obterValor(sexo));
            }
        } catch(SQLException sqle) {
            throw new Exception("Erro ao buscar o paciente no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return paciente;
        }
    }

    @Override
    public LinkedList<Paciente> buscarPeloNome(String nome) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        LinkedList<Paciente> pacientesEncontrados = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE nome LIKE '%" + nome + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String cpf = resultSet.getString("cpf");
                String nomeEncontrado = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String idade = resultSet.getString("idade");
                String sexo = resultSet.getString("sexo");
                
                Paciente paciente = new Paciente(codigo, new CPF(cpf), 
                        nomeEncontrado, new Telefone(telefone), endereco,
                        new Integer(idade), Sexo.obterValor(sexo));

                pacientesEncontrados.add(paciente);
            }
        } catch(SQLException sqle) {
            throw new Exception("Erro ao buscar paciente(s) no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return pacientesEncontrados;
        } 
    }
}
