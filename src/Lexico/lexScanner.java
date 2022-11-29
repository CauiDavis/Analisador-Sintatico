package Lexico;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import Error.*;

public class lexScanner {
    private char[] conteudo;
    private int estado;
    private int posicao;
    private int line;
    private int column;

    public lexScanner(String fileName){
        try {
            line = 1;
            column = 0;
            String txtConteudo;
            posicao = 0;
            txtConteudo = new String(Files.readAllBytes(Paths.get(fileName)),StandardCharsets.UTF_8);
            System.out.println("......Analisando......");
            System.out.println(txtConteudo);
            System.out.println("...............................");
            conteudo = (txtConteudo + "\0").toCharArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Token proximoToken(){
        
        
        if(isEOF()){
            return null;
        }
        char atualChar;
        Token token;
        String term = "";
        
        estado = 0;

        while(true){
            atualChar = nextChar();
            column++;
            
            switch(estado){
                
                case 0:
                    if(isCharABC(atualChar)){
                        term += atualChar;
                        if(atualChar == 'a'){
                            estado = 1;
                        }
                    }
                    else if(isDigit(atualChar)){
                        estado = 0;
                    }
                    else if(isPontuacao(atualChar) || isEOF(atualChar) || isSpace(atualChar)){
                        
                        term += atualChar;
                        token = new Token();
                        token.setType(Token.PONTUACAO);
                        token.setTeste(term);
                        token.setLine(line);
                        token.setColumn(column - term.length());
                        return token;
                    }
                    else if(isOperador(atualChar)){
                        estado = 0;
                    }
                    else{
                        throw new  LexicalException("Caracter nao reconhecido");
                    }
                    break;

                case 1:
                    if(isCharABC(atualChar)){
                        term += atualChar;
                        if(atualChar == 'c'){
                           estado = 2;
                           
                        }
                        else if(atualChar == 'b'){
                            estado = 3;
                        }
                        else{
                            estado = 0;
                        }
                    }
                    else{
                        estado = 0;
                    }
                    break;

                case 2: 
                    if(isSpace(atualChar) || isPontuacao(atualChar) || isEOF(atualChar)){
                        if(!isEOF(atualChar)){back();}
                        
                        token = new Token();
                        token.setType(Token.IDENTIFICADOR);
                        token.setTeste(term);
                        token.setLine(line);
                        token.setColumn(column - term.length());
                        return token;
                    }
                    else {
                        throw new LexicalException("Identificador mal formado");
                    }
                case 3:
                    if(isCharABC(atualChar)){
                        term += atualChar;
                        if(atualChar == 'c'){
                            estado = 4;
                        }
                        else{
                            estado = 0;
                        }
                    }
                    else{
                        estado = 0;
                    }
                    break;

                case 4:
                    if(isCharABC(atualChar)){
                        term += atualChar;
                        if(atualChar == 'b'){
                            estado = 3;
                        }
                        else if(atualChar == 'c'){
                           estado = 2;
                           
                        }
                        else{
                            estado = 0;
                        }
                    }
                    else{
                        estado = 0;
                    }
                    break;
                
            }
        }
    }

    private char nextChar(){
        if (isEOF()) {
			return '\0';
		}
        return conteudo[posicao++];
    }

    
    private boolean isCharABC(char c){
        return (c == 'a' || c == 'b' || c == 'c'); 
    }

    private boolean isChar(char c){
        return (c == 'i' || c == 'o' || c == 'n' || c == 'l' || c == 't' || c == 'u' || c == 'b' || c == 'e' || c == 'f' || c == 'a' || c == 'd');
    }

    private boolean isDigit(char c){
        return (c >= '0' && c <= '9'); 
    }

    private boolean isreserved_word(String c){
        return (c == "int" || c == "double" || c == "float");
    }

    private boolean isSpace(char c){
        if(c == '\n' || c == '\r'){line++; column = 0;}

        return (c == ' ' || c == '\n' || c == '\t' || c == '\r'); 
    }

    private boolean isOperador(char c){
        return (c == '>' || c == '<' || c == '+' || c == '-' || c == '*' || c == '/' || c == '=' ); 
    }

    private boolean isPontuacao(char c){
        return (c == '.' || c == ',' || c == ';' || c == ':');
    }

    private boolean isEOF(char c){
        return (c == '\0') ;
    }

    private boolean isEOF(){
        return posicao >= (conteudo.length);
    }

    private void back(){
        posicao -= 1;
        column -= 1;
    }
}
