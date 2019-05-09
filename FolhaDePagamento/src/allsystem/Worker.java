package allsystem;

public class Worker {

    public String name;
    public String address;
    public String typePayment;
    public double salary = 0;
    public int id;

    public Worker(String name, String address, String typePayment, int i) {

        this.name = name;
        this.address = address;

        if(typePayment == "hourly") this.typePayment = "hourly";
        else if(typePayment == "salaried") this.typePayment = "salaried";
        else if(typePayment == "commissioned") this.typePayment = "commissioned";

        // desconsiderando casos extras/ diferentes dos propostos ****

        this.id = id;

    }











    }

