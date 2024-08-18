import java.util.Scanner;

class Server_data extends StudentDAO{
    public void addData (){
        Scanner scanner=new Scanner(System.in);
        System.out.println("How much data you want to entry");
        int num= Integer.parseInt(scanner.nextLine());
        while( num >0) {
            super.addData();
            num--;
        }
    }
    public void createTable(String name){

        super.createTable(name);
    }
}
