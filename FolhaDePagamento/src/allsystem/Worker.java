package allsystem;

public class Worker {

    private String name;
    private String address;
    private String typePayment;
    private double salary = 0;
    private int id;

    public Worker(String name, String address, String typePayment, int i) {

        this.name = name;
        this.address = address;

        if(typePayment == "hourly") this.typePayment = "hourly";
        else if(typePayment == "salaried") this.typePayment = "salaried";
        else if(typePayment == "commissioned") this.typePayment = "commissioned";

        // desconsiderando casos extras/ diferentes dos propostos ****

        this.id = id;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public int getId() {
        return id;
    }
}

