/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraswing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Calculos {

    public String calculadora(String expressao) {
        // RECEBE TIPO = 2 + 2- 1 COMO STRING
        String resultado;
        //CRIA ARRAY PARA OS NUMERO 1 14 15
        List<Double> listaNumeros = new ArrayList<Double>();
        // CRIA ARRAY PARA AS OPERAÇÕES + - / .
        List<Character> listaOperadores = new ArrayList<Character>();
        /*
        ATRIBUI AOS ARRAYS O PARAMENTRO RECEBIDO, atraves das funçoes
        de conversões abaixo. obterNumeros / obterOperadores
        */
        listaNumeros = obterNumeros(expressao);
        listaOperadores = obterOperadores(expressao);
        //passa eles para calcular
        resultado = calcularValor(listaNumeros, listaOperadores);
        // valor a ser exibido na tela do jframe
        return resultado;
 
    }
 
    private String calcularValor(List<Double> listaNumeros, List<Character> listaOperadores) {
        String resultado = "";
        double total = 0.0;
        int j=0;
        //laço para percorrer, até zerar as operações
        for (int i = 0; i < listaNumeros.size()-1; i++) {
 
            if ( total==0.0) {
                //só recebe o valor numerico
                double numero1 = listaNumeros.get(i).doubleValue();
                double numero2 = listaNumeros.get(i + 1).doubleValue();
                //só receber a string de operação
                char operador = listaOperadores.get(i).charValue();
                
                total = executarOperacao(numero1, operador, numero2);
            }
            else if (total>0.0) {
                 
                double numero2 = listaNumeros.get(i).doubleValue();
                char operador = listaOperadores.get(j).charValue();
                //passa para a função responsavel por fazer as operações matematícas
                total = executarOperacao(total, operador, numero2);
                j++;
            }
             
        }
 
        resultado = ""+total;
        return resultado;
    }
    //calcula valor
    private double executarOperacao(double numero1, char operador, double numero2) {
        double resultado = 0.0;
        //de acordo com o char recebido calcula o valor 
        if (operador == '+') {
            resultado = numero1 + numero2;
        } else if (operador == '-') {
            resultado = numero1 - numero2;
        } else if (operador == '/') {
            resultado = numero1 / numero2;
        } else if (operador == '*') {
            resultado = numero1 * numero2;
        } else if(operador == '%'){
            resultado = (numero1 / 100) * numero2;
        } else if(operador == 'R'){
            resultado = numero1 % numero2;
        }
        return resultado;
    }
    //OBTER PARTE NUMERICA DA STRING RECEBIDO
    private List<Double> obterNumeros(String expressao) {
 
        List<Double> listaNumeros = new ArrayList<Double>();
 
        String numeroEmString = "";
        for (int i = 0; i < expressao.length(); i++) {
 
            if (isOperador(expressao.charAt(i))) {
                Double numero = Double.valueOf(numeroEmString);
                listaNumeros.add(numero);
                numeroEmString = "";
            } else {
                numeroEmString = numeroEmString.concat("" + expressao.charAt(i));
            }
        }
        if(!numeroEmString.isEmpty())
        {
                Double numero = Double.valueOf(numeroEmString);
                listaNumeros.add(numero);
             
        }
        return listaNumeros;
    }
    //OBTENDO OPERADOR DA STRING RECEBIDA
    // ele separa a parte numerica da string e deixa como retorno so o char com o operador
    private List<Character> obterOperadores(String expressao) {
 
        List<Character> listaOperadores = new ArrayList<Character>();
 
        for (int i = 0; i < expressao.length(); i++) {
            //percorre uma especie de tabela de caracteres e se igual retorna ele
            if (isOperador(expressao.charAt(i))) {
                listaOperadores.add(new Character(expressao.charAt(i)));
            }
        }
 
        return listaOperadores;
    }
  // auxiliar para converções, só converte caso um destes chars seja encontrado
    private boolean isOperador(char caracter) {
        boolean isOperador = false;
        if (caracter == '-' || caracter == '+' || caracter == '/' || caracter == '*' || caracter == '%' || caracter == 'R') {
            isOperador = true;
        }
        return isOperador;
    }
}
