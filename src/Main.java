
class MainClass{
    public static void main(String[] args) {
        Server_data sd=new Server_data();
        // sd.createDatabase("Student");
        sd.createTable("stud");
//
//        sd.deleteTable("student");
//        sd.createTable("student");
//          sd.addData("stud");
        sd.search("stud");
        sd.search("student");

    }

}
