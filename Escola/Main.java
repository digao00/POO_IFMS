package Escola;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Disciplina> listaDisciplinas = new ArrayList<>();
        readDisciplina(listaDisciplinas, "C:\\Users\\joaor\\OneDrive\\Desktop\\jao_tarefa\\Escola\\teste.txt");
        Disciplina d1 = new Disciplina("Matemática", "Elton", 2);
        d1.addEstudante(new Estudante("84392", "João Rodrigo", (short)17));
        d1.addEstudante(new Estudante("35756", "Thiago", (short)16));
        listaDisciplinas.add(d1);
        writeDisciplinas(listaDisciplinas, "C:\\Users\\joaor\\OneDrive\\Desktop\\jao_tarefa\\Escola\\teste.txt");
        for (Disciplina disciplina : listaDisciplinas) {
            disciplina.print();
        }
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

    //pega as disciplinas e alunos do arquivo e adciona
    public static void readDisciplina(ArrayList<Disciplina> lista, String arquivoNome) {
        File file = new File(arquivoNome);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split("#");
                Disciplina d = new Disciplina(info[0], info[1], Integer.valueOf(info[2]));
                lista.add(d);
                for(int i = 0; i < d.getNumEstudantes(); i++) {
                    String[] infoEstudante = sc.nextLine().split("#");
                    Estudante e = new Estudante(infoEstudante[0], infoEstudante[1], Short.valueOf(infoEstudante[2]));
                    d.addEstudante(e);
                }
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
    public static void writeDisciplinas(ArrayList<Disciplina> lista, String arquivoNome) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(arquivoNome);
            for (Disciplina disciplina : lista) {
                pw.println(disciplina.getNome() + "#" + disciplina.getProf() + "#" + disciplina.getNumEstudantes());
                for (Estudante estudante : disciplina.getListaEstudantes()) {
                    pw.println(estudante.getCpf() + "#" + estudante.getNome() + "#" + estudante.getIdade());
                }
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

}
