package Sintatico;
import Error.SintaxeException;
import Lexico.Token;
import Lexico.lexScanner;

public class Parser {
    private lexScanner scanner;
    private Token token;

    public Parser(lexScanner scanner){
        this.scanner = scanner;
    }

    public void S(){
        I();
        E();
    }

    public void E(){
        token = scanner.proximoToken();
       
        if(token != null){
            P();
            I();
            E();
        }
    }

    public void I(){
        token = scanner.proximoToken();
    
        if(token.getType() !=Token.IDENTIFICADOR){
            throw new SintaxeException("Esperava-se ID, foi encontrado "  + Token.Tk_Text[token.getType()] + " (" + token.getTeste() + ") na linha " + token.getLine() + " e coluna " + token.getColumn());
        }
        
    }

    public void P(){
        
        if(token.getType() != Token.PONTUACAO){
            throw new SintaxeException("Esperava-se PONTUACAO, foi encontrado " + Token.Tk_Text[token.getType()] + " (" + token.getTeste() + ") na linha " + token.getLine() + " e coluna " + token.getColumn());
        }
    }
}

// S -> SIP | P

//gramática usando LL(1)
// S -> IE 
// E -> PIE | Vazio
// I -> id
// P -> ,|.|;|:
