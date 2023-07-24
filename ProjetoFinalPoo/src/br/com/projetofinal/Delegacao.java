package br.com.projetofinal;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Delegacao {

    
    private boolean ehNumeroInteiroValido(String s) {
        return s.matches("^-?\\d+$");
    }
	
	public String[] fazLeituraValores (String [] dadosEntrada){//utilizado na criacao dos 3 atletas
        String [] dadosSaida = new String [dadosEntrada.length];

        for (int i = 0; i < dadosEntrada.length; i++)
            dadosSaida[i] = JOptionPane.showInputDialog  ("Entre com " + dadosEntrada[i]+ ": ");

        return dadosSaida;
    }

    public Corredor criaCorredor (){

        String [] valores = new String [2];
        String [] nomeVal = {"Nome", "Numero", "Velocidade"};
        valores = fazLeituraValores (nomeVal);

        Corredor corredor = new Corredor (valores[0],valores[1],valores[2]);
        return corredor;
    }

    public Nadador criaNadador (){

        String [] valores = new String [2];
        String [] nomeVal = {"Nome", "Numero", "Estilo"};
        valores = fazLeituraValores (nomeVal);

        Nadador nadador = new Nadador (valores[0],valores[1],valores[2]);
        return nadador;
    }

    public Saltador criaSaltador (){

        String [] valores = new String [2];
        String [] nomeVal = {"Nome", "Numero", "Altura"};
        valores = fazLeituraValores (nomeVal);

        Saltador saltador = new Saltador (valores[0],valores[1],valores[2]);
        return saltador;
    }

    public void mostraAtleta (String dados){
        JOptionPane.showMessageDialog(null,"ATLETA\n-------\n +" +dados);
    }

    public void salvaAtletas (ArrayList<Atleta> atletas){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream
                    (new FileOutputStream("src/br/com/projetofinal/Delegacao.txt"));
            for (int i=0; i < atletas.size(); i++)
                outputStream.writeObject(atletas.get(i));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Impossível criar arquivo de atletas!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally { 
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Atleta> recuperaAtletas (){
        ArrayList<Atleta> atletas = new ArrayList<Atleta>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream
                    (new FileInputStream("src/br/com/projetofinal/Delegacao.txt"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Atleta) {
                    atletas.add((Atleta) obj);
                }
            }
        } catch (EOFException ex) {
            System.out.println("Fim do arquivo atingido.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo com atletas NÃO existe!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally { 
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return atletas;
        }
    }

    public void menuDelegacao (){

        ArrayList<Atleta> atletas = new ArrayList<Atleta>();


        String menu = "";
        String entrada;
        int opcao1, opcao2;

        do {
            menu = "Controle Delegação\n" +
                    "Opções:\n" +
                    "1. Entrar Atletas\n" +
                    "2. Exibir Atletas\n" +
                    "3. Limpar Atletas\n" +
                    "4. Gravar Atletas\n" +
                    "5. Recuperar Atletas\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog (menu + "\n\n");

            while (!ehNumeroInteiroValido(entrada)) {
                entrada = JOptionPane.showInputDialog(null, menu +
                        "\n\nEntrada inválida! Digite um número inteiro primeiramente.");
            }
            opcao1 = new Integer(entrada);

            switch (opcao1) {
                case 1:
                    menu = "Entrada de Atletas\n" +
                            "Opções:\n" +
                            "1. Corredor\n" +
                            "2. Nadador\n" +
                            "3. Saltador\n";

                    entrada = JOptionPane.showInputDialog (menu + "\n\n");
                    while (!ehNumeroInteiroValido(entrada)) {
                        entrada = JOptionPane.showInputDialog(null, menu +
                                "\n\nEntrada inválida! Digite um número inteiro.");
                    }
                    opcao2 = new Integer(entrada);

                    switch (opcao2){
                        case 1: atletas.add((Atleta) criaCorredor());
                            break;
                        case 2: atletas.add((Atleta) criaNadador());
                            break;
                        case 3: atletas.add((Atleta) criaSaltador());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Atleta para entrada NÃO escolhido!");
                    }

                    break;
                case 2: 
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com atletas primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i=0; i < atletas.size(); i++)	{
                        dados += atletas.get(i).toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null,dados);
                    break;
                case 3:
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com atletas primeiramente");
                        break;
                    }
                    atletas.clear();
                    JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
                    break;
                case 4:
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com atletas primeiramente");
                        break;
                    }
                    salvaAtletas(atletas);
                    JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
                    break;
                case 5:
                    atletas = recuperaAtletas();
                    if (atletas.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,"Fim do aplicativo DELEGAÇÃO");
                    break;
            }
        } while (opcao1 != 9);
    }




    

}
