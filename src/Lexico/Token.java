package Lexico;
public class Token {
    public static final int IDENTIFICADOR = 0;
    public static final int NUMERO = 1;
    public static final int OPERADOR = 2;
    public static final int PONTUACAO = 3;
    public static final int ATRIBUICAO = 4;
    



    private int type;
    private String teste;
    private int line;
    private int column;

    public static final String Tk_Text[] = {
        "IDENTIFICADOR", "NUMERO", "OPERADOR", "PONTUACAO", "ATRIBUICAO"
    };

    public Token(int type, String teste){
        super();
        this.type = type;
        this.teste = teste;
    }

    public Token() {
        super();
    }

    public int getType(){
        return type;
    }

    public String getTeste(){
        return teste;
    }

    public int getLine(){
        return line;
    }

    public int getColumn(){
        return column;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setTeste(String teste){
        this.teste = teste;
    }

    public void setLine(int line){
        this.line = line;
    }

    public void setColumn(int column){
        this.column = column;
    }
    
    @Override
    public String toString(){
        return "Token [type =" + type + ", text =" + teste + "]" ;

    }
}
