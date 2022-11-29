import Error.*;
import Lexico.lexScanner;
import Sintatico.Parser;

public class Main {

    public static void main(String args[]) {
           
        try { 
            lexScanner s = new lexScanner("exemplo.txt");
            Parser p = new Parser(s);
           
            p.S();
            System.out.println("Compilado com sucesso!");
        } catch (LexicalException e ) {
            System.out.println("Erro lexico "+ e.getMessage());
        } catch (SintaxeException t) {
            System.out.println("Erro sintatico " + t.getMessage());
        } catch ( Exception f){
            System.out.println("Erro! " + f);
        }
    }

}

