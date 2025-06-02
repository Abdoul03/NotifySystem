public class Message {
    int id;
    String description;
    String dateEnvoi;
    Employe employe;

    public Message (int id, String description, String dateEnvoi, Employe employe){
        this.id = id;
        this.description = description;
        this.dateEnvoi = dateEnvoi;
        this.employe = employe;

    }
}
