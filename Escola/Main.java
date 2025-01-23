package Escola;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Estudante> listaEstudantes = new ArrayList<>();
        readEstudantes(listaEstudantes, "C:\\Users\\aluno\\Desktop\\a\\Escola\\teste.txt");
        for (Estudante estudante : listaEstudantes) {
            estudante.print();
        }
        writeEstudantes(listaEstudantes, "C:\\Users\\aluno\\Desktop\\a\\Escola\\teste.txt");
    }

    //apaga o conteúdo e escreve os estudantes da lista atual no arquivo txt
    public static void writeEstudantes(ArrayList<Estudante> lista, String arquivoNome) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(arquivoNome);
            for (Estudante estudante : lista) {
                pw.println(estudante.getCpf() + "#" + estudante.getNome() + "#" + estudante.getIdade());
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("\nErro: %s", e.getMessage());
            
        }
        finally {
            if (pw != null) {
                pw.close();    
            }
            
        }
    }

    //pega os estudantes do arquivo txt e coloca na lista
    public static void readEstudantes(ArrayList<Estudante> lista, String arquivoNome) {
        File file = new File(arquivoNome);
        Scanner sc = null;
        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split("#");
                lista.add(new Estudante(info[0], info[1], Short.valueOf(info[2])));    
            }

        } 
        catch (FileNotFoundException e) {
            System.out.printf("\nErro: Arquivo não encontrado\n%s", e.getMessage());

        }
        catch (IndexOutOfBoundsException e) {
            System.out.printf("\nErro: %s", e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (sc != null) {
                sc.close();
            }
            
        }
    }
    //criar o método "readDiciplina"
}
