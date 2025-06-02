public class Notification {
    int id;
    Employe employe;
    Message message;

    public  Notification(int id, Employe employe, Message message){
        this.id= id;
        this.employe = employe;
        this.message = message;
    }

}
