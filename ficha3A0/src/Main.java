import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


class DLL{
    Node head, tail;

    class Node{
        public ArrayList<Integer> numLinha;
        public String palavra;
        public Node left;
        public Node right;

        Node(String palavra, int numLinha){
            this.numLinha = new ArrayList<Integer>();
            this.numLinha.add(numLinha);
            this.palavra = palavra;
            left = null;
            right = null;
        }

        Node(String palavra, int numLinha, Node l, Node r){
            this.palavra = palavra;
            this.numLinha.add(numLinha);
            left = l;
            right = r;

        }
    }

    DLL(){
        head= tail = null;
    }

    DLL(Node no){
        head = no;
    }

    DLL(String linha, int numLinha){
        head = new Node(linha, numLinha);
    }

    public void addNode(String palavra, int linha) {
        //Create a new node
        Node newNode = new Node(palavra, linha);

        //If list is empty
        if(head == null) {
            //Both head and tail will point to newNode
            head = tail = newNode;
            //head's previous will point to null
            head.left = null;
            //tail's next will point to null, as it is the last node of the list
            tail.right = null;
        }
        else {
            //newNode will be added after tail such that tail's next will point to newNode
            tail.right = newNode;
            //newNode's previous will point to tail
            newNode.left = tail;
            //newNode will become new tail
            tail = newNode;
            //As it is last node, tail's next will point to null
            tail.right = null;
        }
    }

    public void searchAddNode(String palavra, int numLinha) {
        Node current = head;

        if(head == null) {
            Node newNode = new Node(palavra, numLinha);
            head = tail = newNode;
            head.left = null;
            tail.right = null;
            return;
        }
        while(current != null) {

            if(current.palavra == palavra) {
                current.numLinha.add(numLinha);
                return;
            }
            current = current.right;
        }
        Node newNode = new Node(palavra, numLinha);
        tail.right = newNode;
        newNode.left = tail;
        tail = newNode;
        tail.right = null;
    }

    public String linhasFunc(String palavra) {
        Node current = head;

        while(current != null) {

            if(current.palavra == palavra) {
                String aux = new String();
                for (int i=0; i<current.numLinha.size(); i++){
                    aux.concat(current.numLinha.get(i).toString() + " ");
                }
                return (aux.trim()+"\n");
            }
            current = current.right;
        }
        return ("-1\n");
    }

    public String assocFunc(String palavra, int numLinha) {
        Node current = head;

        while(current != null) {

            if(current.palavra == palavra) {
                if(current.numLinha.contains(numLinha)){
                    return ("ENCONTRADA.\n");
                }
            }
            current = current.right;
        }
        return ("NAO ENCONTRADA.\n");
    }
}




public class Main {



    public static void main(String[] args) throws IOException {

        String input, comando;
        StringTokenizer st;

        DLL dlist = new DLL();
        ArrayList<String> resultados = new ArrayList<String>();
        

        do{
            input = readLn(200);
            st = new StringTokenizer(input.trim(), "(),;. ");
            comando = st.nextToken();
            System.out.println(input);

            if (comando.equalsIgnoreCase("texto")){
                do{
                    System.out.println("entreiaqui\n");
                    input = readLn(500);
                    int numeroDaLinha = 1;
                    if(input.trim().equalsIgnoreCase("fim")){
                        System.out.println("GUARDADO");
                        resultados.add("GUARDADO.\n");
                        break;
                    }
                    st = new StringTokenizer(input.trim(), "(),;. ");
                    while(st.hasMoreTokens()){
                        //verifica se a palavra existe
                        //se existir adiciona a linha a palavra
                        //se nao existir cria a palavra
                        dlist.searchAddNode(st.nextToken(),numeroDaLinha);
                    }
                    numeroDaLinha++;
                }while(true);

            } else if (comando.equalsIgnoreCase("linhas")){
                String wordToFind = st.nextToken();
                resultados.add(dlist.linhasFunc(wordToFind));
                //procurar a palavra na DLL e dar return das linhas onde ocorre


            } else if (comando.equalsIgnoreCase("assoc")){
                String palavra = st.nextToken();
                int linha = Integer.parseInt(st.nextToken());
                //procurar a palavra na linha em questao
                resultados.add(dlist.assocFunc(palavra,linha));


            } else if (comando.equalsIgnoreCase("tchau")){
                //imprime os resultados do linhas e do assoc
                for (int n = 0; n<resultados.size(); n++){
                    System.out.println(resultados.get(n));
                }
                return;
            }

        } while(true);

    }

    static String readLn (int maxLg){ //utility function to read from stdin
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";
        try {
            while (lg < maxLg){
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e){
            return (null);
        }
        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }
}

